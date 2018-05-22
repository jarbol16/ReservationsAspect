package org.thanos.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;

public aspect GetToDataBase {
    @After("execution(* org.thanos.modelo.repository.*.*(..))")
    public void GetToDataBAse(JoinPoint joinPoint)
    {
        System.out.println("Consulta a la base de datos desde " + joinPoint.getSignature().getName()+ "   "+ joinPoint.getArgs()[0]);
    }
    
    @After("execution(* org.thanos.rooms.RoomRepository.*(..))")
    public void GetToDataBAseComponetnRoom(JoinPoint joinPoint)
    {
        System.out.println("Consulta a a la base de datos de Salones de clase, desde" + joinPoint.getSignature().getName()+ "   ");
    }
}
