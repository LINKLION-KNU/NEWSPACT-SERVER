package org.likelion.newsfactbackend.domain.auth.dao.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.newsfactbackend.domain.auth.dao.AuthDAO;
import org.likelion.newsfactbackend.user.domain.User;
import org.likelion.newsfactbackend.user.repository.UserRepository;
import org.springframework.stereotype.Component;
@Slf4j
@RequiredArgsConstructor
@Component
public class AuthDAOImpl implements AuthDAO {
    private final UserRepository userRepository;
    @Override
    public void createUser(User user) {

        userRepository.save(user);
    }
}
