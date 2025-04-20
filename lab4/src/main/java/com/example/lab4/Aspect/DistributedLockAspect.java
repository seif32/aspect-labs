package com.example.lab4.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class DistributedLockAspect {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  @Around("@annotation(com.example.redislab.annotation.DistributedLock)")
  public Object applyLock(ProceedingJoinPoint joinPoint) throws Throwable {
    String lockKey = "lock:" + joinPoint.getSignature().toShortString();
    boolean lockAcquired = false;

    try {
      // Try to acquire lock with 10 second timeout
      lockAcquired = redisTemplate.opsForValue().setIfAbsent(lockKey, "locked", 10, TimeUnit.SECONDS);

      if (!lockAcquired) {
        throw new RuntimeException("Could not acquire lock - resource is busy");
      }

      // Simulate long operation (for testing)
      if (joinPoint.getSignature().getName().contains("longOperation")) {
        Thread.sleep(10000); // 10 seconds
      }

      return joinPoint.proceed();
    } finally {
      if (lockAcquired) {
        redisTemplate.delete(lockKey);
      }
    }
  }
}
