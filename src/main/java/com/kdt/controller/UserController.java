package com.kdt.controller;

import com.kdt.dto.UserDTO;
import com.kdt.entity.User;
import com.kdt.repository.api.IUserRepository;
import com.kdt.service.api.IUserService;
import com.kdt.utils.jwt.JWTUtil;
import com.kdt.utils.response.ResponseHttpStatus;
import com.kdt.utils.response.ResponseMessage;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
//@CrossOrigin
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRepository userRepository;

//    @Autowired
//    private IUserMapper userMapper;

    @PostMapping("/login")
    public ResponseMessage login(@RequestBody UserDTO userDTO) {
        if (!StringUtils.hasLength(userDTO.getUserName()) || !StringUtils.hasLength(userDTO.getPassword())) {
            return ResponseMessage.fail(null);
        }
        List<User> userList = userRepository.findUserByUserName(userDTO.getUserName());
        if (CollectionUtils.isEmpty(userList)) {
            return ResponseMessage.failMessage("当前用户不存在，需要注册!", null);
        }
        User user = userList.get(0);
        String jwt = JWTUtil.generateJWT(user.getUserName());
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("token", jwt);
        return ResponseMessage.success(retMap);
    }


    @PostMapping("/logout")
    public ResponseMessage loginOut(@RequestHeader("x-token") String xToken) {
        if (!StringUtils.hasLength(xToken)) {
            return ResponseMessage.fail(ResponseHttpStatus.PARAM_INCORRECT);
        }
        Boolean invalidateJWT = JWTUtil.invalidateJWT(xToken);
        return ResponseMessage.success(invalidateJWT);
    }

    @GetMapping("/info")
    public ResponseMessage info(@RequestParam("token") String token) {
        if (!StringUtils.hasLength(token)) {
            return ResponseMessage.failMessage("参数token不存在！", null);
        }
        String userName;
        try {
            userName = JWTUtil.getClaimsByJWT(token).getSubject();
        } catch (ExpiredJwtException expiredJwtException) {
            logger.info(expiredJwtException.getMessage());
            return ResponseMessage.failMessage(ResponseHttpStatus.TOKEN_EXPIRED, null);
        }
        List<User> userList = userRepository.findUserByUserName(userName);
        if (CollectionUtils.isEmpty(userList)) {
            return ResponseMessage.failMessage("当前用户不存在，需要注册!", null);
        }
        User user = userList.get(0);
        return ResponseMessage.success(user);
    }
}
