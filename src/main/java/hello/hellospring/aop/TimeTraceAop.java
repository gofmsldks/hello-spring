package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class TimeTraceAop {
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
/*
@Aspect
public class TimeTraceAop {


    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)")

    //@Around("execution(* hello.hellospring..*(..))")

    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {...}

} AOP 객체를 따로 빈으로 설정해줄때 순환참조 문제가 발생하므로 자기자신의 객체가 만들어진 곳은 측정하지 않는다.

*/