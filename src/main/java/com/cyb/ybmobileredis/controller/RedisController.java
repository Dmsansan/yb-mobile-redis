package com.cyb.ybmobileredis.controller;

import com.cyb.ybmobileredis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName：RedisController
 * @Description：TODO
 * @Author：chenyb
 * @Date：2020/8/22 10:34 下午
 * @Versiion：1.0
 */
@RestController
public class RedisController {
    @Autowired
    private RedisService redisService;
    @GetMapping("redisTest")
    public Object redisTest(String key,String value){
        redisService.set(key,value);
        return redisService.get(key);
    }
}
