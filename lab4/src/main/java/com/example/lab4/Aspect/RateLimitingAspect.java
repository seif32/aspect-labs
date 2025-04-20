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
public class RateLimitingAspect {

  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  @Around("@annotation(com.example.redislab.annotation.RateLimited)")
  public Object rateLimit(ProceedingJoinPoint joinPoint) throws Throwable {
    String key = "rate_limit:" + joinPoint.getSignature().toShortString();
    Long currentCount = redisTemplate.opsForValue().increment(key, 1);

    if (currentCount == 1) {
      redisTemplate.expire(key, 1, TimeUnit.MINUTES);
    }

    if (currentCount > 10) {
      throw new RuntimeException("Rate limit exceeded - try again later");
    }

    return joinPoint.proceed();
  }
}
