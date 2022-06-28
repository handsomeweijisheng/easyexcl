package com.wjs.service;

import org.springframework.stereotype.Service;

/**
 * @author wjs
 * @create 2022-06-28 16:59
 */
@Service
public class UserService {
    public String getUser() {
        System.out.println("获取用户的信息");
        return "zhangsan";
    }
}

