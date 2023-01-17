package com.zhu.test;

import com.zhu.utils.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

public class JedisTest {
    /*声明Jedis对象*/
    private Jedis jedis;

    @BeforeEach
    void setUp() {
        /*实例化 Jedis 设置redis的地址和端口号 传参为地址和端口号*/
        jedis= JedisConnectionFactory.getJedis();
        /*使用密码进行链接*/
        jedis.auth("123456");
        /*选择redis库，如果不选默认是第一个库*/
        jedis.select(0);
    }


    @Test
    void StringTest() {
        /*和命令一样 通过set可以传键值对 返回的是字符串OK*/
        System.out.println(jedis.set("jedisName", "zhu"));
        /*通过get键拿到值*/
        System.out.println(jedis.get("jedisName"));
    }

    @Test
    void HashTest() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name","zhuyijian");
        hashMap.put("age","22");
        /*可以通过field value传或者通过hashMap多个存入*/
        jedis.hset("redisTest:stu:4",hashMap);
    }

    @AfterEach
    void tearDown() {
        /*释放jedis资源*/
        if (jedis != null) {
            jedis.close();
        }
    }
}
