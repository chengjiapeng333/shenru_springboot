package com.example.demo.convert;

import com.example.demo.pojo.SexEnum;
import javax.persistence.AttributeConverter;

/**
 * Created by chengjiapeng on 2018/10/26.
 */
public class SexConverter implements AttributeConverter<SexEnum, Integer>{
    @Override
    public Integer convertToDatabaseColumn(SexEnum sex) {
        return sex.getId();
    }

    @Override
    public SexEnum convertToEntityAttribute(Integer id) {
        return SexEnum.getEnumById(id);
    }
}
