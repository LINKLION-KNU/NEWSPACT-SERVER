package org.likelion.newsfactbackend.domain.auth.dao;


import org.likelion.newsfactbackend.user.domain.User;

public interface AuthDAO {
    void createUser(User user);
}
