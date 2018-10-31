package com.example.demo.controller;

import com.example.demo.pojo.ValidatorPojo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chengjiapeng on 2018/10/26.
 */
@RequestMapping("/my")
@Controller
public class MyController {

    /**
     * eg:http://localhost:8080/my/no/annotation?initVal=10&longVal=200
     * 无注解情况下获取参数， 参数可为空， 但要求参数名称与http 请求中的参数一致
     * @param initVal
     * @param longVal
     * @param str
     * @return
     */
    @GetMapping("/no/annotation")
    @ResponseBody
    public Map<String, Object> noAnnotation(Integer initVal, Long longVal, String str) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("initVal", initVal);
        paramsMap.put("longVal", longVal);
        paramsMap.put("str", str);
        return paramsMap;
    }


    /**
     * eg:http://localhost:8080/my/annotation?init_val=10&long_val=200&str_val=str
     * @RequestParamas 参数用于将前端参数与后端参数对应起来， 默认情况下参数不能为空， 若为空， 将required 设置为false
     * @param initVal
     * @param longVal
     * @param strVal
     * @return
     */
    @GetMapping("/annotation")
    @ResponseBody
    public Map<String, Object> requestParams(@RequestParam(value = "init_val", required = false) Integer initVal
            , @RequestParam(value = "long_val", required = false) Long longVal
            , @RequestParam(value = "str_val", required = false) String strVal) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("initVal", initVal);
        paramsMap.put("longVal", longVal);
        paramsMap.put("strVal", strVal);
        return paramsMap;
    }

    /**
     * eg:http://localhost:8080/my/requestArray?intArr=1,2,3&longArr=4,5,6&strArr=str1,str2,str3
     * 传递数组，数组中的元素用逗号分隔
     * @param intArr
     * @param longArr
     * @param strArr
     * @return
     */
    @GetMapping("/requestArray")
    @ResponseBody
    public Map<String , Object> requestArray(int [] intArr, Long [] longArr, String [] strArr) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("initArr", intArr);
        paramsMap.put("longArr", longArr);
        paramsMap.put("strArr", strArr);
        return paramsMap;
    }


    @GetMapping("/format/form")
    public String showFormat() {
        return "/format/formatter";
    }

    @PostMapping("/format/commit")
    @ResponseBody
    public Map<String, Object> format(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                      @NumberFormat(pattern = "#,###.##")Double number) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("date", date);
        dataMap.put("number", number);
        return dataMap;
    }

    @GetMapping("/valid/page")
    public String validPage() {
        return "/validator/pojo";
    }

    @PostMapping("/valid/validate")
    @ResponseBody
    public Map<String, Object> validate(@Valid @RequestBody ValidatorPojo vp, Errors errors) {
        Map<String, Object> errMap = new HashMap<>();
        List<ObjectError> oes = errors.getAllErrors();
        for(ObjectError oe:oes) {
            String key = null;
            String msg = null;
            //字段错误
            if(oe instanceof FieldError) {
                FieldError fe = (FieldError) oe;
                //获取错误验证字段名
                key = fe.getField();
            }else {
                //非字段错误
                key = oe.getObjectName();
            }
            msg = oe.getDefaultMessage();
            errMap.put(key, msg);
        }
        return errMap;
    }
}
