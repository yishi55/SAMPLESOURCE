package com.github.k3286.one_system.dao;

import java.util.List;

import com.github.k3286.one_system.model.User;

public interface UserDao {
    User getById(String id);

    List<User> getUserListByName(String name);

    List<User> getUserList();

    void add(User user);

    void update(User user);

    void delete(String id);

}
