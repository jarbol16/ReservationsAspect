package org.thanos.modelo.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.thanos.connection.Conexion;
import org.thanos.modelo.entities.User;

public class UserRepository {

	 public static User NewLogin(String user,String pass)throws Exception {
	        User _user = new User();
	        Connection conn = Conexion.getConexion();
	        String query =  "SELECT * FROM user u WHERE u.`user` = '"+user+"' and u.password = '"+pass+"'";
	        Statement cn = conn.createStatement();
	        ResultSet response = cn.executeQuery(query);
	        if(response.next()){
	            _user.Permissions = response.getString("permissions");
	            _user.Type = response.getInt("user_type_id");
	            _user.PersonId = response.getInt("person_id");
	        }
	        conn.close();
	        return _user;
	   }
}
