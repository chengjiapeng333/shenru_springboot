package com.example.demo.pojo;

/**
 * Created by chengjiapeng on 2018/10/26.
 */
public enum SexEnum {
    MALE(1, "男"),
    FEMALE(2, "女");

    private int id;

    private String name;

    SexEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static SexEnum getEnumById(int id) {
        for(SexEnum sexEnum: SexEnum.values()) {
            if(sexEnum.getId()==id) {
                return sexEnum;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
