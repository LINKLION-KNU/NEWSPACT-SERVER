package org.likelion.newsfactbackend.user.repository;


import org.likelion.newsfactbackend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    boolean existsByEmailAndLoginType(String email, String loginType);
    boolean existsByNickName(String nickname);
    boolean existsByNickNameAndLoginType(String nickname, String loginType);
    User findByNickNameAndLoginType(String nickname, String loginType);
    User findByEmailAndLoginType(String email, String loginType);
}
