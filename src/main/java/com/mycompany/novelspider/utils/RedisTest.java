package com.mycompany.novelspider.utils;

import redis.clients.jedis.Jedis;

public class RedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        System.out.println("服务正在运行: " + jedis.ping());
        jedis.set("runoobkey","www.runoob.com");
        System.out.println("redis存储的字符串为: " + jedis.get("runoobkey"));
    }
}
