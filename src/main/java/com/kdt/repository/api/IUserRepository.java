package com.kdt.repository.api;

import com.kdt.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends CrudRepository<User, Integer> {
    List<User> findUserByUserName(String userName);
}
