package com.ocean.redis.lock2;

public class Test {
    public static void main(String[] args) {
        RedisLockService service = new RedisLockService();
        for (int i = 0; i < 50; i++) {
            ThreadA threadA = new ThreadA(service);
            threadA.start();
        }
    }
}



class ThreadA extends Thread {
    private RedisLockService service;

    public ThreadA(RedisLockService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }
}