package org.likelion.newsfactbackend.keyword.repository;

import org.likelion.newsfactbackend.keyword.domain.TrendKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrendKeywordRepository extends JpaRepository<TrendKeyword, Long> {

}
