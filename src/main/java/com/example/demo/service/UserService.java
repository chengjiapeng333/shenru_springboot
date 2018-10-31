package com.example.demo.service;

import com.example.demo.dao.JpaUserRepository;
import com.example.demo.pojo.SexEnum;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chengjiapeng on 2018/10/29.
 */
@Service
public class UserService {
    @Autowired
    private JpaUserRepository jpaUserRepository;

    public User insertUser(User user) {
        user.setSex(SexEnum.MALE);
        return jpaUserRepository.save(user);
    }

    public User getUser(Long id) {
        return jpaUserRepository.findById(id).get();
    }
}
