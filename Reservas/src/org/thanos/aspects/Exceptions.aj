package org.thanos.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;

public aspect Exceptions {

	 	@AfterThrowing(
	            pointcut = "execution(* org.thanos.modelo.*.*(..))",
	            throwing = "ex"
	    )
	    public void afterThrowingLog(JoinPoint joinPoint, Exception ex){
	        
	        System.out.println("AfterThrowing: Excepcion en el metodo: "
	                            +joinPoint.getSignature().getName()
	                            +",  Clase: "+joinPoint.getTarget().getClass().getName());
	        System.out.println("Excepcion: "+ex.getMessage());
	        
	    }
	 	@AfterThrowing(
	 			pointcut = "execution(* org.thanos.connection.*.*(..))"
	 	)
	 	public void afterThrowingDBLog(JoinPoint joinPoint) {
	        System.out.println("AfterThrowing: Excepcion en conexion al servidor: "
                    +joinPoint.getSignature().getName()
                    +",  Clase: "+joinPoint.getTarget().getClass().getName());
	 	}
}
