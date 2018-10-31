package com.example.demo.controller;

import com.example.demo.dao.JpaUserRepository;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chengjiapeng on 2018/10/26.
 */
@Controller
@RequestMapping("/jpa")
public class JpaController {
    @Autowired
    private JpaUserRepository jpaUserRepository = null;

    @GetMapping("/getUser")
    @ResponseBody
    public User getUser(Long id) {
        User user = jpaUserRepository.findById(id).get();
        return user;
    }
}
