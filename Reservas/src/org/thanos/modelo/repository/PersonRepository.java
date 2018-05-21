package org.thanos.modelo.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.thanos.connection.Conexion;
import org.thanos.modelo.entities.Admin;
import org.thanos.modelo.entities.Student;

public class PersonRepository {
	
	public static Object GetUserFactory(int id, int type) throws Exception{
        Connection conn = Conexion.getConexion();
        String query =  "SELECT * FROM person u WHERE u.`id` = '"+id+"'";
        Statement cn = conn.createStatement();
        ResultSet response = cn.executeQuery(query);
        Object obj = null;
        if(response.next()){
        	if (type == 2) {
        		Student stu = new Student();
        		stu.Name = response.getString("name");
        		stu.LastName = response.getString("last_name");
        		stu.Code = response.getString("code");
        		stu.Email = response.getString("email");
        		obj = stu;
        	}else if (type == 1) {
        		Admin ad = new Admin();
        		ad.Name = response.getString("name");
        		ad.LastName = response.getString("last_name");
        		ad.Email = response.getString("email");
        		obj = ad;
        	}
            
        }
        conn.close();
        return obj;
	}
}
