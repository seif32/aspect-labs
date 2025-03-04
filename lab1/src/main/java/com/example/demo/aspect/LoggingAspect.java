package com.example.demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
  @Before("execution(* com.example.demo.controller.MyController.*(..))")
  public void beforeControllerMethods() {
    System.out.println("Seif Aspect: Before calling the controller method.");
  }
}
