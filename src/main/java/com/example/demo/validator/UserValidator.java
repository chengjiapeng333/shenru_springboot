package com.example.demo.validator;


import com.example.demo.pojo.User;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by chengjiapeng on 2018/10/30.
 */
public class UserValidator implements Validator{

    //该验证器只支持User类的验证
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(target==null) {
            errors.rejectValue("", null, "用户不能为空");
            return;
        }
        User user = (User) target;
        if(StringUtils.isEmpty(user.getUserName())) {
            errors.rejectValue("userName", null, "用户名不能为空");
        }
    }
}
