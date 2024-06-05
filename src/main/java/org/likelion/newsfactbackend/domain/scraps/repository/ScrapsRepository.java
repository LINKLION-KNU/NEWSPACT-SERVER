package org.likelion.newsfactbackend.domain.scraps.repository;

import org.likelion.newsfactbackend.domain.scraps.domain.Scraps;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapsRepository extends JpaRepository<Scraps, Long> {
}
