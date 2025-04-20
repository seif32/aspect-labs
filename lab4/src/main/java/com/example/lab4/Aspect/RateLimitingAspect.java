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

  @Around("@annotation(com.example.lab4.Annotation.RateLimited)")
  public Object rateLimit(ProceedingJoinPoint joinPoint) throws Throwable {
    String key = "rate_limit:" + joinPoint.getSignature().toShortString();
    System.out.println("=== RATE LIMIT CHECK ===");
    System.out.println("Using key: " + key);

    Long currentCount = redisTemplate.opsForValue().increment(key, 1);
    System.out.println("Current count: " + currentCount);

    if (currentCount == 1) {
      System.out.println("Setting expiration...");
      Boolean expirySet = redisTemplate.expire(key, 1, TimeUnit.MINUTES);
      System.out.println("Expiry set: " + expirySet);
    }

    if (currentCount > 3) {
      System.out.println("=== RATE LIMIT EXCEEDED ===");
      throw new RuntimeException("Rate limit exceeded - try again later");
    }

    System.out.println("=== REQUEST ALLOWED ===");
    return joinPoint.proceed();
  }
}
