package com.kdt.service.impl;

import com.kdt.entity.User;
import com.kdt.repository.api.IUserRepository;
import com.kdt.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    @Qualifier(value = "IUserRepository")
    private IUserRepository userRepository;

    @Override
    public User query(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
            throw new IllegalArgumentException("用户不存在!");
        });
    }

    @Override
    public User add(User user) {
        User retUser = userRepository.save(user);
        return retUser;
    }

    @Override
    public boolean delete(Integer userId) {
        userRepository.deleteById(userId);
        return true;
    }
}
