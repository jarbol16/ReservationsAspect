package org.thanos.reservations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;

import org.thanos.connection.Conexion;

public class ReservationRespository {

	public static long InsertReservation(String user,Reservation res) throws SQLException {
		Connection conn = Conexion.getConexion();
        String query =  "INSERT into reservation  SET id=1,date_init=UTC_TIMESTAMP(),date_end=UTC_TIMESTAMP(),observations='"+res.Observation+"'";
        Statement cn = conn.createStatement();
        long id = cn.executeUpdate(query);
		return id;
	}
	
	public static long InsertUserReservation(String user,UserReservation res) throws SQLException {
		long resId = InsertReservation(user,res.reservation);
		Connection conn = Conexion.getConexion();
        String query =  "INSERT INTO user_reservation SET user_id = "+res.user.Id+",classroom_id="+res.room.Id+",reservation_id="+resId+";";
        Statement cn = conn.createStatement();
        long id = cn.executeUpdate(query);
		return id;
	}
		
}
