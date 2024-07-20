package org.likelion.newsfactbackend.domain.news.service.impl;

import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.likelion.newsfactbackend.domain.news.dto.request.RequestNewsDto;
import org.likelion.newsfactbackend.domain.news.dto.response.ResponseNewsDto;
import org.likelion.newsfactbackend.domain.news.service.NewsService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private static final List<String> OIDS = List.of("1023", "1009", "1001", "1003", "1020", "1015", "1032", "1008", "1025", "1081"); // 언론사 id 이데일리 - 1018

    // 언론사 로고
    private static final List<String> URLS = List.of(
            "https://mimgnews.pstatic.net/image/upload/office_logo/023/2020/09/03/logo_023_6_20200903164340.png",
            "https://mimgnews.pstatic.net/image/upload/office_logo/009/2018/10/05/logo_009_6_20181005175405.png",
            "https://mimgnews.pstatic.net/image/upload/office_logo/001/2020/09/15/logo_001_6_20200915184213.png",
            "https://mimgnews.pstatic.net/image/upload/office_logo/003/2019/01/23/logo_003_6_20190123191323.jpg",
            "https://mimgnews.pstatic.net/image/upload/office_logo/020/2019/01/22/logo_020_6_20190122142722.png",
            "https://mimgnews.pstatic.net/image/upload/office_logo/015/2020/09/15/logo_015_6_20200915190950.png",
            "https://mimgnews.pstatic.net/image/upload/office_logo/032/2020/09/15/logo_032_6_20200915155035.png",
            "https://mimgnews.pstatic.net/image/upload/office_logo/008/2020/09/24/logo_008_6_20200924115228.png",
            "https://mimgnews.pstatic.net/image/upload/office_logo/025/2021/08/24/logo_025_6_20210824123340.png",
            "https://mimgnews.pstatic.net/image/upload/office_logo/081/2022/01/07/logo_081_6_20220107180811.png" // 서울신문
            //"https://mimgnews.pstatic.net/image/upload/office_logo/018/2020/09/15/logo_018_6_20200915185838.png" // 이데일리
    );
    // 언론사 목록
    private static final List<String> PRESS_NAMES = List.of(
            "조선일보", "매일경제", "연합뉴스", "뉴시스", "동아일보", "한국경제", "경향신문", "머니투데이", "중앙일보", "서울신문" //이데일리
    );
    private static final String BASE_URL = "https://search.naver.com/search.naver?where=news&query={search}&sm=tab_opt&sort=1&photo=0&field=0&pd=0&ds=&de=&docid=&related=0&mynews=1&office_type=1&office_section_code=3&news_office_checked={oid}&nso=so%3Add%2Cp%3Aall&is_sug_officeid=0&office_category=0&service_area=0";
    private static final Gson gson = new Gson();
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
                        TimeUnit.SECONDS.sleep(2); // 재시도 전 대기
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
                TimeUnit.SECONDS.sleep(2); // 다음 요청 전에 대기
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IOException("Interrupted during sleep", e);
            }
        }

        return articles;
    }
    @Override
    public Page<ResponseNewsDto> searchNews(RequestNewsDto newsRequestDto) throws IOException {
        List<ResponseNewsDto> allArticles = fetchNewsArticles(newsRequestDto.getKeyword());
        Pageable pageable = PageRequest.of(0, newsRequestDto.getSize().intValue());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allArticles.size());

        List<ResponseNewsDto> pagedArticles = allArticles.subList(start, end);

        return new PageImpl<>(pagedArticles, pageable, allArticles.size());
    }

    private ResponseNewsDto fetchNewsArticleDetails(String url, String oid) throws IOException {
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
                    TimeUnit.SECONDS.sleep(2); // 재시도 전 대기
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new IOException("Interrupted during retry delay", ie);
                }
            }
        }

        String companyLogo = oidUrlMapping.getOrDefault(oid, "");
        String company = pressNameMapping.getOrDefault(oid, "");

        // 뉴스 유형에 따른 제목 및 내용 추출
        String title = "";
        String subTitle = "";
        String thumbnail = "";
        String publishDate = "";

        if (url.contains("m.entertain")) { // 연예 뉴스 추출
            title = doc.select("#content > div.NewsEnd_container__HcfWh > div > div.NewsEnd_main_group__d5k8S > div > div.NewsEndMain_comp_article_head__Uqd6M > div.NewsEndMain_article_head_title__ztaL4 > h2").text();

            Element contentElement = doc.select("#comp_news_article > div._article_content").first();
            if (contentElement != null) {
                subTitle = contentElement.text();
            }
            Element imgElement = doc.selectFirst("#comp_news_article > div._article_content > span > span > span > img");
            if (imgElement != null) {
                thumbnail = imgElement.absUrl("src");
            }
            Element entDateElement = doc.selectFirst("#content > div.NewsEnd_container__HcfWh > div > div.NewsEnd_main_group__d5k8S > div > div.NewsEndMain_comp_article_head__Uqd6M > div.article_head_info > div.NewsEndMain_article_head_date_info__jGlzH > div > em");
            if (entDateElement != null) {
                publishDate = entDateElement.text();
            }

        } else if (url.contains("m.sports")) { // 스포츠 뉴스 추출
            title = doc.select("a.news_tit").text();
            Element contentElement = doc.select("#comp_news_article > div._article_content > span:nth-child(6)").first();
            if (contentElement != null) {
                contentElement.select("div, p").remove();
                subTitle = contentElement.text();
            }
            Element imgElement = doc.selectFirst("#comp_news_article > div._article_content > span > span > span > img");
            if (imgElement != null) {
                thumbnail = imgElement.absUrl("src");
            }
            Element dateElement = doc.selectFirst("#content > div.NewsEnd_container__HcfWh > div > div.NewsEnd_main_group__d5k8S > div > div.NewsEndMain_comp_article_head__Uqd6M > div.article_head_info > div.NewsEndMain_article_head_date_info__jGlzH > div:nth-child(1) > em");
            if (dateElement != null) {
                publishDate = dateElement.text();
            }
        }

        // 일반 뉴스 추출
        else {
            title = doc.select(".media_end_head_headline").text();
            Element articleContent = doc.selectFirst(".go_trans._article_content");
            if (articleContent != null) {
                subTitle = articleContent.text();
                /* 글자 100자 출력 */
                int maxLength = 100;
                if (subTitle.length() > maxLength) {
                    subTitle = subTitle.substring(0, maxLength);
                }
            }
            Element imgElement = doc.selectFirst("#img1");
            if (imgElement != null) {
                thumbnail = imgElement.absUrl("data-src");
            }
            Element dateElement = doc.selectFirst(".media_end_head_info_datestamp_time");
            if (dateElement != null) {
                publishDate = dateElement.text();
            }

        }

        // ResponseNewsDto 객체 생성 및 반환
        return ResponseNewsDto.builder()
                .companyLogo(companyLogo)
                .company(company)
                .title(title)
                .subTitle(subTitle)
                .thumbnail(thumbnail)
                .publishDate(publishDate)
                .newsUrl(url)
                .build();
    }
}
