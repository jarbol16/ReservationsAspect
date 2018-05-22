package org.thanos.aspects;

import java.sql.SQLException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.thanos.modelo.entities.AuditLog;
import org.thanos.modelo.repository.ModelRepository;

public aspect Audit {

    @After("execution(* org.thanos.modelo.repository.UserRepository.NewLogin(..))")
    public void logAfterLoggin(JoinPoint joinPoint)
    {
        System.out.println("Ingreso al sistema por el usuario " + joinPoint.getSignature().getName()+ joinPoint.getArgs()[0]);
        AuditLog a = new AuditLog();
        a.User =(String)joinPoint.getArgs()[0];
        a.Descrition = "Ingreso al sistema";
        try {
			ModelRepository.InsertAudit(a);
		} catch (SQLException e) {
		}
        
    }
    
    @Before("execution(* org.thanos.reservations.ReservationRespository.InsertUserReservation(..))")
    public void ValidCredentiasl(JoinPoint joinPoint) {
    	try {
    		System.out.println("Se detecta una reserva desde " + joinPoint.getSignature().getName()+ joinPoint.getArgs()[0]);
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    @After("call(* org.thanos.reservations.ReservationRespository.InsertUserReservation(..))")
    public void ReservationOk(JoinPoint joinPoint) {
    	try {
    		AuditLog a = new AuditLog();
	        a.User =(String)joinPoint.getArgs()[0];
	        a.Descrition = "Crea una reserva";
	        try {
				ModelRepository.InsertAudit(a);
			} catch (SQLException e) {
			}
    		System.out.println("Se detecta una reserva desde " + joinPoint.getSignature().getName()+ joinPoint.getArgs()[0]);
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }
}

