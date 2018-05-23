package org.thanos.aspects;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.thanos.connection.Conexion;
import org.thanos.modelo.entities.AuditLog;
import org.thanos.modelo.repository.ModelRepository;
import org.thanos.server.config.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public aspect GetToDataBase {
    @After("execution(* org.thanos.modelo.repository.*.*(..))")
    public void GetToDataBAse(JoinPoint joinPoint)
    {
        try {
        	 System.out.println("Consulta a la base de datos desde " + joinPoint.getSignature().getName()+ "   " + joinPoint.getArgs()[0]);
        }catch (Exception e) {
        	System.out.println("Consulta a la base de datos desde " + joinPoint.getSignature().getName()+ "   " );
		}
    }
    
    @After("execution(* org.thanos.rooms.RoomRepository.*(..))")
    public void GetToDataBAseComponetnRoom(JoinPoint joinPoint)
    {
        System.out.println("Consulta a a la base de datos de Salones de clase, desde" + joinPoint.getSignature().getName()+ "   ");
        AuditLog a = new AuditLog();
        a.User = "SYSTEM";
        a.Descrition = "LECTURA AL SISTEMA DE SALONES desde " +joinPoint.getSignature().getName();
		/*
		try {
			ModelRepository.InsertAudit(a);
		} catch (SQLException e) {}*/
    }
    
    @Before("call(* org.thanos.modelo.repository.UserRepository.NewLogin(..))")
    public void NeedCreateDataBase(JoinPoint joinPoint)  {
    	Connection conn = null;
    	try {
    		conn = Conexion.getConexion();
    	}catch (Exception e) {
    		 System.out.println("BASE DE DATOS NO EXISTE ... Creando ...");
    		 MysqlDataSource ds = new MysqlDataSource();
	         ds.setServerName(Settings.DB_Local.SERVIDOR);
	         ds.setPortNumber(Settings.DB_Local.PUERTO);
    	     ds.setUser(Settings.DB_Local.NOMBRE_USUARIO);//cambiar por root
    	     ds.setPassword(Settings.DB_Local.CONTRASENA_USUARIO);//borrar
    	     String q = "CREATE DATABASE IF NOT EXISTS `thanos_reservations`;";
    	     Connection ccn;
			try {
				ccn = ds.getConnection();
				Statement cn = ccn.createStatement();
				cn.executeUpdate(q);
				ccn.close();
				ModelRepository.CreateTables();
				System.out.println("SE CREARON LAS TABLAS");
				 AuditLog a = new AuditLog();
		         a.User = "SYSTEM";
		         a.Descrition = "NUEVA INSTANCIA DE LA APLICACION";
				
				ModelRepository.InsertAudit(a);
			
			} catch (SQLException e1) {
			}
		}
    }
}
