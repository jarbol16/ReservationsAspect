package org.thanos.aspects;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.thanos.connection.Conexion;
import org.thanos.modelo.repository.ModelRepository;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

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
    
    @Before("call(* org.thanos.modelo.repository.UserRepository.NewLogin(..))")
    public void NeedCreateDataBase(JoinPoint joinPoint)  {
    	Connection conn = null;
    	try {
    		conn = Conexion.getConexion();
    	}catch (Exception e) {
    		 MysqlDataSource ds = new MysqlDataSource();
	         ds.setServerName("127.0.0.1");
	         ds.setPortNumber(3306);
    	     ds.setUser("root");
    	     String q = "CREATE DATABASE IF NOT EXISTS `thanos_reservations`;";
    	     Connection ccn;
			try {
				ccn = ds.getConnection();
				Statement cn = ccn.createStatement();
				cn.executeUpdate(q);
				ModelRepository.CreateTables();
				System.out.println("SE CREARON LAS TABLAS");
			} catch (SQLException e1) {
			}
		}
    }
}
