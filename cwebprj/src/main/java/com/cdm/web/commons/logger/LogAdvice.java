package com.cdm.web.commons.logger;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LogAdvice {

	// 로깅을 위한 변수
	private static final Log logger = LogFactory.getLog(LogAdvice.class);

	@Around("execution(* com..controller..*Controller.*(..)) " + "or execution(* com..service..*Impl.*(..))"
			+ "or execution(* com..dao..*Impl.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {

		long start = System.currentTimeMillis(); // 이 메서드를 호출한 시간

		// 비지니스 로직 실행 전
		Object result = joinPoint.proceed(); // ★핵심업무 실행

		String type = joinPoint.getSignature().getDeclaringTypeName(); // 호출한 클레스 이름
		String name = "";
		if (type.indexOf("Controller") > -1) {
			name = " Controller \t:";
		} else if (type.indexOf("Service") > -1) {
			name = "ServiceImpl \t:";
		} else if (type.indexOf("DAO") > -1) {
			name = "DaoImpl \t:";
		}

		logger.info(name + type + "." + joinPoint.getSignature().getName() + "()"); // 호출 되는 메서드 이름
		logger.info(Arrays.toString(joinPoint.getArgs())); // method에 전달되는 매개변수들

		long end = System.currentTimeMillis();
		long time = end - start; // 이 메서드가 호출되고 끝나는데에 걸리는 시간

		logger.info("실행시간 : " + time);
		return result;
	}
}
