package org.likelion.newsfactbackend.domain.news.service.impl;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.likelion.newsfactbackend.domain.news.dto.RecommendNewsDto;
import org.likelion.newsfactbackend.domain.news.dto.request.PageRequestNewsDto;
import org.likelion.newsfactbackend.domain.news.dto.request.RequestNewsDto;
import org.likelion.newsfactbackend.domain.news.dto.response.ResponseNewsDto;
import org.likelion.newsfactbackend.domain.news.service.NewsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    @Value("${news.oids}")
    private List<String> OIDS;

    @Value("${news.logo.urls}")
    private List<String> URLS;
    private static final List<String> PRESS_NAMES = List.of(
            "조선일보", "매일경제", "연합뉴스", "뉴시스", "동아일보", "한국경제", "경향신문", "머니투데이", "중앙일보", "서울신문" //이데일리
    );

    private static final String BASE_URL = "https://search.naver.com/search.naver?where=news&query={search}&sm=tab_opt&sort=1&photo=0&field=0&pd=0&ds=&de=&docid=&related=0&mynews=1&office_type=1&office_section_code=3&news_office_checked={oid}&nso=so%3Add%2Cp%3Aall&is_sug_officeid=0&office_category=0&service_area=0";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3";
    private static final int TIMEOUT = 10000; // 10초
    private static final int RETRY_COUNT = 3; // 재시도 횟수

    private Map<String, String> oidUrlMapping;
    private Map<String, String> pressNameMapping;
    @PostConstruct
    public void init() {
        oidUrlMapping = new HashMap<>();
        for (int i = 0; i < OIDS.size(); i++) {
            oidUrlMapping.put(OIDS.get(i), URLS.get(i));
        }

        pressNameMapping = new HashMap<>();
        for (int i = 0; i < OIDS.size(); i++) {
            pressNameMapping.put(OIDS.get(i), PRESS_NAMES.get(i));
        }
    }

    public ResponseNewsDto getNews(RequestNewsDto requestNewsDto) {
        return new ResponseNewsDto();
    }

    @Override
    public List<ResponseNewsDto> fetchAllNewsArticles(String search) throws IOException {
        return fetchNewsArticles(search);
    }
    @Override
    public List<ResponseNewsDto> fetchNewsArticles(String search) throws IOException {
        List<ResponseNewsDto> articles = new ArrayList<>();

        for (String oid : OIDS) {
            String url = BASE_URL.replace("{search}", search).replace("{oid}", oid);
            Document doc = null;
            for (int attempt = 1; attempt <= RETRY_COUNT; attempt++) {
                try {
                    doc = Jsoup.connect(url)
                            .header("User-Agent", USER_AGENT)
                            .header("Accept-Language", "en-US,en;q=0.5")
                            .header("Referer", "https://www.naver.com/")
                            .timeout(TIMEOUT)
                            .get();
                    break; // 성공 시 루프 종료
                } catch (IOException e) {
                    System.err.println("Attempt " + attempt + " failed for URL: " + url + " - " + e.getMessage());
                    if (attempt == RETRY_COUNT) {
                        throw e; // 최대 재시도 횟수에 도달하면 예외를 던짐
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1); // 재시도 전 대기
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new IOException("Interrupted during retry delay", ie);
                    }
                }
            }

            Elements links = doc.select("div.info_group a.info");
            boolean validArticleFound = false;

            for (Element link : links) {
                String articleUrl = link.attr("href");
                if (articleUrl.contains("sports") || articleUrl.contains("entertain")) {
                    continue; // "sports" 또는 "entertain"이 포함된 URL은 건너뜀
                }

                if (!articleUrl.contains("n.news")) {
                    continue; // "n.news" 형태의 URL이 아닌 경우 건너뜀
                }

                ResponseNewsDto article = fetchNewsArticleDetails(articleUrl, oid);
                if (article != null) {
                    articles.add(article);
                    validArticleFound = true;
                    break; // 유효한 기사를 찾으면 루프 종료
                }
            }

            if (!validArticleFound) {
                System.err.println("No valid article found for OID: " + oid);
            }

            try {
                TimeUnit.SECONDS.sleep(1); // 다음 요청 전에 대기
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IOException("Interrupted during sleep", e);
            }
        }

        return articles;
    }
    public List<RecommendNewsDto> getRecommendedArticles(List<ResponseNewsDto> allArticles) {
        List<RecommendNewsDto> recommendedArticles = new ArrayList<>();
        Random random = new Random();
        int recommendedCount = 4;

        if (allArticles != null) {
            int count = Math.min(recommendedCount, allArticles.size());

            for (int i = 0; i < count; i++) {
                int index = random.nextInt(allArticles.size());
                ResponseNewsDto article = allArticles.remove(index);

                recommendedArticles.add(RecommendNewsDto.builder()
                        .thumbnailUrl(article.getThumbnailUrl())
                        .title(article.getTitle())
                        .companyLogo(article.getCompanyLogo())
                        .newsUrl(article.getNewsUrl())
                        .build());
            }
        }


        return recommendedArticles;
    }
    @Override
    public Page<ResponseNewsDto> searchNews(PageRequestNewsDto pageRequestNewsDto) throws IOException {
        List<ResponseNewsDto> allArticles = fetchNewsArticles(pageRequestNewsDto.getKeyword());
        Pageable pageable = PageRequest.of(0, pageRequestNewsDto.getSize().intValue());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allArticles.size());

        List<ResponseNewsDto> pagedArticles = allArticles.subList(start, end);

        return new PageImpl<>(pagedArticles, pageable, allArticles.size());
    }

    public ResponseNewsDto fetchNewsArticleDetails(String url, String oid) throws IOException {
        Document doc = null;
        for (int attempt = 1; attempt <= RETRY_COUNT; attempt++) {
            try {
                doc = Jsoup.connect(url)
                        .header("User-Agent", USER_AGENT)
                        .header("Accept-Language", "en-US,en;q=0.5")
                        .header("Referer", "https://www.naver.com/")
                        .timeout(TIMEOUT)
                        .get();
                break; // 성공 시 루프 종료
            } catch (IOException e) {
                System.err.println("Attempt " + attempt + " failed for URL: " + url + " - " + e.getMessage());
                if (attempt == RETRY_COUNT) {
                    throw e; // 최대 재시도 횟수에 도달하면 예외를 던짐
                }
                try {
                    TimeUnit.SECONDS.sleep(1); // 재시도 전 대기
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new IOException("Interrupted during retry delay", ie);
                }
            }
        }

        String companyLogo = oidUrlMapping.getOrDefault(oid, "");
        String company = pressNameMapping.getOrDefault(oid, "");

        String title = "";  // 뉴스 제목
        String contents = "";  // 내용
        String thumbnailUrl = ""; // 썸네일 url
        String publishDate = "";  // 발행일자
        String category = ""; // 카테고리
        String subContents = ""; // 썸네일 아래 내용
        String subTitle = ""; // 기사 요약 내용 (100자)

        category = doc.select("#contents > div.media_end_categorize > a > em").text();
        title = doc.select(".media_end_head_headline").text();
        //subContents = doc.select("#dic_area > span:nth-child(1) > em").text();


        // 썸네일 아래
        Element subArticleContent = doc.selectFirst("#dic_area > span:nth-child(1) > em");
        if (subArticleContent != null) {
            subContents = subArticleContent.text();
        }
        if (subArticleContent == null) {
            subContents = doc.select("#dic_area > span > em").text();
//            } if(subArticleContent == null){
//                subContents = doc.select("#dic_area > div.ab_photo.photo_left > div > span.end_photo_org > em").text();
//            }
        }

        // 썸네일 이미지
        Element imgElement = doc.selectFirst("#img1");
        if (imgElement != null) {
            thumbnailUrl = imgElement.absUrl("data-src");
        }

        // 발행일자
        Element dateElement = doc.selectFirst(".media_end_head_info_datestamp_time");
        if (dateElement != null) {
            publishDate = dateElement.text();
        }


        Element subArticleContents = doc.selectFirst(".go_trans._article_content");
        if (subArticleContents != null) {
            subTitle = subArticleContents.text();
            /* 글자 100자 출력 */
            int maxLength = 100;
            if (subTitle.length() > maxLength) {
                subTitle = subTitle.substring(0, maxLength);
            }
        }

        // <br> 남기고 제거
        Element articleContent = doc.selectFirst(".go_trans._article_content");
        if (articleContent != null) {
            contents = articleContent.html(); //text();, wholeText();
            articleContent.select("span").forEach(Element::remove);
            articleContent.select("strong").remove();
            articleContent.select("div").forEach(Element::remove);
            contents = articleContent.html();
        }


        // 줄바꿈
        List<String> cleanedContentsList = Arrays.asList(contents.split("\\n<br>\\n<br>\\n"));

        List<String> contentsList = new ArrayList<>();
        for (String paragraph : cleanedContentsList) {
            String cleanContents = paragraph.replaceAll("<br>|<br>\\n|<!-- r_start //--><!-- r_end //-->|<!-- r_start //-->|<!-- r_end //-->|\\n|", "");
            contentsList.add(cleanContents);
        }

        // ResponseNewsDto 객체 생성 및 반환
        return ResponseNewsDto.builder()
                .companyLogo(companyLogo) // 언론사 로고
                .contentsList(contentsList) // 줄바꿈 포함한 뉴스
                .company(company) // 언론사
                .category(category) // 뉴스 카테고리
                .title(title) // 제목
                .subContents(subContents) // 썸네일 아래
                .thumbnailUrl(thumbnailUrl) // 썸네일
                .subTitle(subTitle)  // 페이징 할 때 먼저 100자
                .publishDate(publishDate) // 발행일자
                .newsUrl(url) // 뉴스 원문 url
                .build();

    }
}
