package com.spider.spiderPTT.config;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.spider.spiderPTT.util.DateUtil;



@Aspect
@Component
public class TaskInterceptor {
	
	
	private static final Logger log = LoggerFactory.getLogger(TaskInterceptor.class);
	

	@Pointcut("execution(* com..*.task..*(..)))")
	private void taskAspect() {
		
	}
	
	@Around(value = "taskAspect()")
	private Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getSignature().getDeclaringTypeName();
		String methodName = pjp.getSignature().getName();
		Date begin = new Date();
		log.info("定时任务" + className+"."+methodName + "-------开始执行时间:" + DateUtil.getDateStr(begin,DateUtil.secondFormat));
		Object obj  = pjp.proceed();
		Date end = new Date();
		log.info("定时任务"+ className+"."+ methodName + "------执行结束执行时间:" + DateUtil.getDateStr(end,DateUtil.secondFormat));
		log.info("定时任务"+ className+"."+ methodName + "总耗时："+(end.getTime()-begin.getTime()));
		
		return obj;
		
	}

}
