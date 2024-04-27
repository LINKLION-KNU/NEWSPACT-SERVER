package org.likelion.newsfactbackend.user.repository;


import org.likelion.newsfactbackend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNickName(String nickName);
}
