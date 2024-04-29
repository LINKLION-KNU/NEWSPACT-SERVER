package org.likelion.newsfactbackend.news.repository;

import org.likelion.newsfactbackend.news.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
