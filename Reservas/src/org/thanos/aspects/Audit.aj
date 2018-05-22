package org.thanos.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;

public aspect Audit {

    @After("execution(* org.thanos.modelo.repository.UserRepository.NewLogin(..))")
    public void logAfterLoggin(JoinPoint joinPoint)
    {
        System.out.println("Ingreso al sistema por el usuario " + joinPoint.getSignature().getName()+ joinPoint.getArgs()[0]);
    }
    
    @Before("call(* org.thanos.reservations.ReservationRespository.*(..))")
    public void ValidCredentiasl(JoinPoint joinPoint) {
    	try {
    		System.out.println("Se detecta una reserva desde " + joinPoint.getSignature().getName()+ joinPoint.getArgs()[0]);
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }
}

