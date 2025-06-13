package com.ice.campus.common.cache.manager;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 分布式锁服务类
 * 提供分布式环境下的锁操作，包括可重入锁、公平锁、读写锁等
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/11 22:21
 */
@Slf4j
@Service
public class LockManager {

    @Resource
    private RedissonClient redissonClient;

    /**
     * 获取可重入锁对象
     *
     * @param lockKey 锁的key
     * @return RLock 锁对象
     */
    public RLock getReentrantLock(String lockKey) {
        return redissonClient.getLock(lockKey);
    }

    /**
     * 获取公平锁对象
     *
     * @param lockKey 锁的key
     * @return RLock 锁对象
     */
    public RLock getFairLock(String lockKey) {
        return redissonClient.getFairLock(lockKey);
    }

    /**
     * 尝试获取锁
     *
     * @param lockKey   锁的key
     * @param waitTime  等待时间
     * @param leaseTime 持有时间
     * @param unit      时间单位
     * @return 是否获取成功
     */
    public boolean tryLock(String lockKey, long waitTime, long leaseTime, TimeUnit unit) {
        RLock lock = getReentrantLock(lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("获取分布式锁[{}]被中断", lockKey, e);
            return false;
        }
    }

    /**
     * 释放锁
     *
     * @param lockKey 锁的key
     */
    public void unlock(String lockKey) {
        RLock lock = getReentrantLock(lockKey);
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    /**
     * 执行带有锁的操作
     *
     * @param lockKey   锁的key
     * @param waitTime  等待时间
     * @param leaseTime 持有时间
     * @param unit      时间单位
     * @param supplier  需要执行的操作
     * @param <T>       返回类型
     * @return 操作结果
     */
    public <T> T executeWithLock(String lockKey, long waitTime, long leaseTime, TimeUnit unit, Supplier<T> supplier) {
        RLock lock = getReentrantLock(lockKey);
        try {
            if (lock.tryLock(waitTime, leaseTime, unit)) {
                try {
                    return supplier.get();
                } finally {
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            } else {
                throw new RuntimeException("获取锁[" + lockKey + "]失败，请稍后重试");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("获取锁[" + lockKey + "]被中断", e);
        }
    }

    /**
     * 执行带锁操作，获取锁失败时执行失败处理
     *
     * @param lockKey   锁key
     * @param waitTime  等待时间
     * @param leaseTime 持有时间
     * @param unit      时间单位
     * @param success   成功获取锁后的操作
     * @param fail      获取锁失败后的操作
     * @param <T>       返回类型
     * @return 操作结果
     */
    public <T> T executeWithLock(String lockKey, long waitTime, long leaseTime, TimeUnit unit,
                                 Supplier<T> success, Supplier<T> fail) {
        RLock lock = getReentrantLock(lockKey);
        try {
            if (lock.tryLock(waitTime, leaseTime, unit)) {
                try {
                    return success.get();
                } finally {
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            }
            return fail.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("获取分布式锁[{}]被中断", lockKey, e);
            return fail.get();
        }
    }

    /**
     * 执行带有锁的操作(无返回值)
     *
     * @param lockKey   锁的key
     * @param waitTime  等待时间
     * @param leaseTime 持有时间
     * @param unit      时间单位
     * @param runnable  需要执行的操作
     */
    public void executeWithLock(String lockKey, long waitTime, long leaseTime, TimeUnit unit, Runnable runnable) {
        RLock lock = getReentrantLock(lockKey);
        try {
            if (lock.tryLock(waitTime, leaseTime, unit)) {
                try {
                    runnable.run();
                } finally {
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            } else {
                throw new RuntimeException("获取锁[" + lockKey + "]失败，请稍后重试");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("获取锁[" + lockKey + "]被中断", e);
        }
    }

    /**
     * 检查锁是否被当前线程持有
     *
     * @param lockKey 锁的key
     * @return 是否持有
     */
    public boolean isHeldByCurrentThread(String lockKey) {
        RLock lock = getReentrantLock(lockKey);
        return lock.isHeldByCurrentThread();
    }

    /**
     * 强制释放锁(不检查持有者)
     *
     * @param lockKey 锁的key
     */
    public void forceUnlock(String lockKey) {
        RLock lock = getReentrantLock(lockKey);
        lock.forceUnlock();
    }
}