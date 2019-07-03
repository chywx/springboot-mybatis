package com.ocean.redis.jedis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisTest {

    private Jedis jedis = null;

    @Before
    public void before() {
        jedis = new Jedis("www.chendahai.cn");
        jedis.auth("123456");
    }

    @After
    public void after() {
        jedis.close();
    }

    @Test
    public void ping() {
        System.out.println(jedis.ping());
    }

    @Test
    public void testString() {
        jedis.set("phone", "13121939122");
        System.out.println(jedis.get("phone"));
        jedis.append("phone", "-86");
        System.out.println(jedis.get("phone"));
        jedis.del("phone");
        System.out.println(jedis.get("phone"));
        // 设置多个键值对==》mset
        jedis.mset("name", "dahai", "age", "25");
        System.out.println(jedis.mget("age"));
        jedis.incr("age");
        System.out.println(jedis.mget("age"));
    }

    @Test
    public void testHash() {
        Map<String, String> map = new HashMap<>();
        map.put("userName", "lisi");
        map.put("age", "22");
        map.put("gender", "男");
        // 放入map中
        jedis.hmset("user", map);
        // 获取指定map的key值
        List<String> user = jedis.hmget("user", "age", "userName");
        System.out.println(user);
        // 删除key
        jedis.hdel("user", "gender");
        System.out.println(jedis.hmget("user", "gender"));
        // 是否存在key 返回true/false
        System.out.println(jedis.exists("user"));
        // map里面key的个数
        System.out.println(jedis.hlen("user"));
        // 获取所有key值
        System.out.println(jedis.hkeys("user"));
        // 获取所有value
        System.out.println(jedis.hvals("user"));
    }

    @Test
    public void testList() {
        jedis.del("context");
        System.out.println(jedis.lrange("context", 0, -1));
        // lpust存放数据
        jedis.lpush("context", "你好啊");
        jedis.lpush("context", "我叫张三");
        jedis.lpush("context", "他叫李四");
        //lrange 获取数据 接收3个参数  1、key  2、从什么开始 3、到那里结束 -1 表示最后一个
        System.out.println(jedis.lrange("context", 0, 1));
        System.out.println(jedis.lrange("context", 0, 2));
        System.out.println(jedis.lrange("context", 0, -1));
    }

    @Test
    public void testSet() {
        //存放数据，默认去重
        jedis.sadd("users", "libai");
        jedis.sadd("users", "huahua");
        jedis.sadd("users", "libai");
        // 获取加入的value值
        System.out.println(jedis.smembers("users"));
        // 判断user 是否某个值
        System.out.println(jedis.sismember("users", "libai"));
        // 返回个数
        System.out.println(jedis.scard("users"));
        // 移除
        System.out.println(jedis.srem("users", "libai"));
        System.out.println(jedis.smembers("users"));
    }

    @Test
    public void testZset() {
        //存放数据 按score 进行排序
        jedis.zadd("userss", 12, "libai");
        jedis.zadd("userss", 56, "zhangsan");
        jedis.zadd("userss", 89, "wangwu");
        jedis.zadd("userss", 58, "uu");
        jedis.zadd("userss", 1, "start");
        // 获取加入userss中的value
        System.out.println(jedis.zrange("userss", 0, -1));
        // 移除
        System.out.println(jedis.zrem("userss", "wangwu"));
        // 获取加入userss中的value
        System.out.println(jedis.zrange("userss", 0, -1));
        // 元素的索引值
        System.out.println(jedis.zrank("userss", "uu"));
        // 进行反转操作
        System.out.println(jedis.zrevrange("userss", 0, -1));
    }

    /*
    事务处理流程
    1、jedis.watch(key1, key2...);    // 监视keys
    2、Transaction transaction = jedis.muti();    // 开启事务
    3、事务体；在事务体中，当代码出错或监视的key被本事务对象之外的对象修改时，事务不会提交，返回空列表
    4、List<Object> result = transaction.exec();    // 提交事务
    5、result.isEmpty();    // 判断事务是否成功
    6、jedis.unwatch();    // 解除监视
     */
    public void testTransaction() {
        Transaction multi = jedis.multi();
        multi.set("k3", "v3");
        multi.set("k2", "v2");
        // 提交事务
        multi.exec();
        // 回滚事务
        multi.discard();
        // 判断是否成功
        multi.exec().isEmpty();
        // 监控k1
        jedis.watch("k1");
        // 取消监控，执行提交事务也会执行
        jedis.unwatch();
    }

    @Test
    public void testTransaction2() {
        jedis.select(4);
        System.out.println(jedis.get("k1"));
        jedis.watch("k1");
        Transaction multi = jedis.multi();
        System.out.println(multi.get("k1"));
        System.out.println(multi.get("k2"));
        List<Object> exec = multi.exec();
        System.out.println(exec);
        System.out.println(exec.isEmpty());
    }

}
