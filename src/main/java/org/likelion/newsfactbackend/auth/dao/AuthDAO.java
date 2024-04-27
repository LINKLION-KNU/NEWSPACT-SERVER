package org.likelion.newsfactbackend.auth.dao;


import org.likelion.newsfactbackend.user.domain.User;

public interface AuthDAO {
    void createUser(User user);
}
