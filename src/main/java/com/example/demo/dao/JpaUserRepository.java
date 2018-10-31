package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by chengjiapeng on 2018/10/26.
 */

public interface JpaUserRepository extends JpaRepository<User, Long>{
}
