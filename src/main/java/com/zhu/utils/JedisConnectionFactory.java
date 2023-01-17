package com.zhu.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

public class JedisConnectionFactory {
    /*常量Jedis连接池，方便静态代码块访问*/
    private static final JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        /*最大连接数*/
        jedisPoolConfig.setMaxTotal(8);
        /*最大空闲连接数*/
        jedisPoolConfig.setMaxIdle(8);
        /*最小空闲连接数*/
        jedisPoolConfig.setMinIdle(0);
        /*设置最大等待时间为1000毫秒*/
        jedisPoolConfig.setMaxWait(Duration.ofMillis(1000));
        /*设置最大等待时间（redis4已启用，不建议使用这种方式）*/
        /*jedisPoolConfig.setMaxWaitMillis(1000);*/

        /*实例出jedis连接池对象，传入 连接池配置对象 地址 端口号 等待时间 密码*/
        jedisPool=new JedisPool(jedisPoolConfig,"192.168.109.128",6379,1000,"123456");
    }

    public static Jedis getJedis(){
        /*通过jedis连接池对象调用getResource返回一个jedis对象*/
        return jedisPool.getResource();
    }

}
