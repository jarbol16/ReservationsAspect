package org.thanos.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;

public aspect Audit {

    @After("execution(* org.thanos.modelo.repository.UserRepository.NewLogin(..))")
    public void logAfterLoggin(JoinPoint joinPoint)
    {
        System.out.println("Intento de ingreso al sistema: " + joinPoint.getSignature().getName()+ joinPoint.getArgs()[0]);
    }
}

