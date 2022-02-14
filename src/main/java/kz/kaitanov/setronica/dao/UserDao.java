package kz.kaitanov.setronica.dao;

import kz.kaitanov.setronica.model.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> getByUsername(String username);

    void addUser(User user);

}