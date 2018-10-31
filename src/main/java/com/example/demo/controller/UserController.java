package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chengjiapeng on 2018/10/29.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 调用控制器之前先执行这个方法
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }

    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public String add() {
        return "/user/add";
    }

    @PostMapping("/insert")
    @ResponseBody
    public User insert(@RequestBody User user) {
        User resultUser = userService.insertUser(user);
        return resultUser;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User get(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    /**
     * http://localhost:8080/user/converter?user=1-user_name_1-note_1
     * @param user
     * @return
     */
    @GetMapping("/converter")
    @ResponseBody
    public User getUserByConverter(User user) {
        return user;
    }

    /**
     * http://localhost:8080/user/validator?user=1--note_1
     * @param user
     * @param errors
     * @param date
     * @return
     */
    @GetMapping("/validator")
    @ResponseBody
    public Map<String, Object> validator(@Valid User user, Errors errors, Date date) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("date", date);
        if(errors.hasErrors()) {
            List<ObjectError> oes = errors.getAllErrors();
            for(ObjectError oe:oes) {
                //判定是否字段错误
                if(oe instanceof FieldError) {
                    FieldError fe = (FieldError) oe;
                    map.put(fe.getField(), fe.getDefaultMessage());
                }else {
                    map.put(oe.getObjectName(), oe.getDefaultMessage());
                }
            }
        }
        return map;
    }
}
