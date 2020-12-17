package com.cdm.web.logger;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;

@Aspect
public class LogAdvice {

	// �α��� ���� ����
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LogAdvice.class);

	@Around("execution(* com..controller..*Controller.*(..)) "
	    		+ "or execution(* com..service..*Impl.*(..))"
	    		+ "or execution(* com..dao..*Impl.*(..))")
	    public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
	            
	            //�� �޼��带 ȣ���� �ð� 
	            long start = System.currentTimeMillis();
	            //�� joinPoint.proceed()�ڵ带 �������� 
	            //�̻��� �ڵ尡 ���� ��
	            Object result = joinPoint.proceed(); //���ٽɾ��� ����
	            //������ �ڵ尡 �ٽɾ��� ���� ��
	          
	            String type = joinPoint.getSignature().getDeclaringTypeName();   //ȣ���� Ŭ���� �̸� 
	            String name="";
	            if(type.indexOf("Controller") > -1) {
	                name =" Controller \t:";
	            }else if (type.indexOf("Service") > -1) {
	                name = "ServiceImpl \t:";
	            }else if(type.indexOf("DAO") > -1) {
	                name = "DaoImpl \t:";
	            }
	            // ȣ�� �Ǵ� �޼��� �̸�
	            logger.info(name+type+"."+joinPoint.getSignature().getName() + "()");
	            
	            //method�� ���޵Ǵ� �Ű�������
	            logger.info(Arrays.toString(joinPoint.getArgs()));
	            
	            //�� �޼��带 ������ ������ �ð�
	            long end = System.currentTimeMillis();
	            //�� �޼��尡 ȣ��ǰ� �����µ��� �ɸ��� �ð�
	            long time = end - start; 
	            System.out.println("����ð� : " + time);
	            
	            return result;
	        }
}
