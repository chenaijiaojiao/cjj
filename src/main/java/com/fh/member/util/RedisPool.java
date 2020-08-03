package com.fh.member.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {

    private RedisPool(){

    }

    private static JedisPool jedisPool;

    private static void initPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //同一时间最多连接数量(用户)
        jedisPoolConfig.setMaxTotal(1000);
        //同一时间最大连接数量(用户)
        jedisPoolConfig.setMinIdle(500);
        //同一时间最小连接数量(用户)
        jedisPoolConfig.setMaxIdle(500);
        jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
    }

    //只会在jvm虚拟机加载类，执行一次 【保证只能创建一个redis连接池】
    //static静态块只能调用静态的static方法
    static {
        initPool();
    }

    public static Jedis getResource(){
        return jedisPool.getResource();
    }

}
