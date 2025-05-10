package com.kdt.mapper.api;

import com.kdt.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Mapper
public interface IUserMapper {

    @Select("select * from tb_user")
    public List<User> queryUserList();

}
