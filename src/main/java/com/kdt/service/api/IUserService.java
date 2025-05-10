package com.kdt.service.api;

import com.kdt.entity.User;

public interface IUserService {

    User query(Integer userId);

    User add(User user);

    boolean delete(Integer userId);

}
