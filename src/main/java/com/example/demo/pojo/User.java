package com.example.demo.pojo;

import com.example.demo.convert.SexConverter;

import javax.persistence.*;

/**
 * Created by chengjiapeng on 2018/10/26.
 */
@Entity(name = "user")
@Table(name = "t_user")
public class User {

    @Id
    //配置何种逐渐生成策略， 一种依赖于数据库的递增策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(name = "user_name")
    private String userName =null;

    @Convert(converter = SexConverter.class)
    private SexEnum sex = null;

    private String note = null;

    public User() {
    }

    public User(Long id, String userName, SexEnum sex, String note) {
        this.id = id;
        this.userName = userName;
        this.sex = sex;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
