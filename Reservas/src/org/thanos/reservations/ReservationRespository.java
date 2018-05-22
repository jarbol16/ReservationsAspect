package org.thanos.reservations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;

import org.thanos.connection.Conexion;

public class ReservationRespository {

	public static long InsertReservation(Reservation res) throws SQLException {
		Connection conn = Conexion.getConexion();
        String query =  "INSERT into reservation  SET id=1,date_init=UTC_TIMESTAMP(),date_end=UTC_TIMESTAMP(),observations='"+res.Observation+"'";
        Statement cn = conn.createStatement();
        long id = cn.executeUpdate(query);
		return id;
	}
	
	public static long InsertUserReservation(UserReservation res) throws SQLException {
		long resId = InsertReservation(res.reservation);
		Connection conn = Conexion.getConexion();
        String query =  "INSERT INTO user_reservation u ON u.user_id = "+res.user.Id+",u.classroom_id="+res.room.Id+",u.reservation_id="+resId+";";
        Statement cn = conn.createStatement();
        long id = cn.executeUpdate(query);
		return id;
	}
		
}
