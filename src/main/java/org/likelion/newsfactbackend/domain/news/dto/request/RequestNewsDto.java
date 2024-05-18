package org.likelion.newsfactbackend.domain.news.dto.request;

import lombok.*;
import org.likelion.newsfactbackend.domain.news.dto.Company;
import org.likelion.newsfactbackend.domain.news.dto.Keyword;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RequestNewsDto {
    private Keyword keyword;
    private Company company;
}
