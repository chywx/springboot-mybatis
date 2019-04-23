package com.ocean.redis.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisLockImpl implements RedisDistributionLock {
	// 加锁超时时间，单位毫秒， 即：加锁时间内执行完操作，如果未完成会有并发现象
	private static final long LOCK_TIMEOUT = 5 * 1000;

	private static final Logger LOG = LoggerFactory.getLogger(RedisLockImpl.class);
	@Autowired
	private StringRedisTemplate redisTemplate;

	// public RedisLockImpl(StringRedisTemplate redisTemplate) {
	// this.redisTemplate = redisTemplate;
	// }

	/**
	 * 加锁 取到锁加锁，取不到锁一直等待知道获得锁
	 * 
	 * @param lockKey
	 * @param threadName
	 * @return
	 */
	@Override
	public synchronized long lock(String lockKey, String threadName) {
		LOG.info(threadName+"开始执行加锁");
		while (true) { // 循环获取锁
			// 锁时间
			Long lock_timeout = currtTimeForRedis() + LOCK_TIMEOUT + 1;
			Boolean execute = redisTemplate.execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
					// 定义序列化方式
					RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
					byte[] value = serializer.serialize(lock_timeout.toString());
					boolean flag = redisConnection.setNX(lockKey.getBytes(), value);
					return flag;
				}
			});
			if (execute) {
				// 如果加锁成功
				LOG.info(threadName +"加锁成功 ++++ 111111");
				// 设置超时时间，释放内存
				redisTemplate.expire(lockKey, LOCK_TIMEOUT, TimeUnit.MILLISECONDS);
				return lock_timeout;
			} else {
				// 获取redis里面的时间
				String result = redisTemplate.opsForValue().get(lockKey);
				Long currt_lock_timeout_str = result == null ? null : Long.parseLong(result);
				System.out.println("currt_lock_timeout_str"+currt_lock_timeout_str);
				// 锁已经失效
				if (currt_lock_timeout_str != null && currt_lock_timeout_str < System.currentTimeMillis()) {
					// 判断是否为空，不为空时，说明已经失效，如果被其他线程设置了值，则第二个条件判断无法执行
					// 获取上一个锁到期时间，并设置现在的锁到期时间
					Long old_lock_timeout_Str = Long
							.valueOf(redisTemplate.opsForValue().getAndSet(lockKey, lock_timeout.toString()));
					if (old_lock_timeout_Str != null && old_lock_timeout_Str.equals(currt_lock_timeout_str)) {
						// 多线程运行时，多个线程签好都到了这里，但只有一个线程的设置值和当前值相同，它才有权利获取锁
						LOG.info(threadName + "加锁成功 ++++ 22222");
						// 设置超时间，释放内存
						redisTemplate.expire(lockKey, LOCK_TIMEOUT, TimeUnit.MILLISECONDS);
						// 返回加锁时间
						return lock_timeout;
					}
				}
			}

			try {
				LOG.info(threadName +"等待加锁， 睡眠100毫秒");
				// TimeUnit.MILLISECONDS.sleep(100);
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		TimeUnit.MILLISECONDS.sleep(2000);
	}

	/**
	 * 解锁
	 * 
	 * @param lockKey
	 * @param lockValue
	 * @param threadName
	 */
	@Override
	public synchronized void unlock(String lockKey, long lockValue, String threadName) {
		LOG.info(threadName + "执行解锁==========");//正常直接删除 如果异常关闭判断加锁会判断过期时间
		// 获取redis中设置的时间
		String result = redisTemplate.opsForValue().get(lockKey);
		Long currt_lock_timeout_str = result == null ? null : Long.valueOf(result);

		// 如果是加锁者，则删除锁， 如果不是，则等待自动过期，重新竞争加锁
		if (currt_lock_timeout_str != null && currt_lock_timeout_str == lockValue) {
			redisTemplate.delete(lockKey);
			LOG.info(threadName + "解锁成功------------------");
		}
	}

	/**
	 * 多服务器集群，使用下面的方法，代替System.currentTimeMillis()，获取redis时间，避免多服务的时间不一致问题！！！
	 * 
	 * @return
	 */
	@Override
	public long currtTimeForRedis() {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
				return redisConnection.time();
			}
		});
	}
}
