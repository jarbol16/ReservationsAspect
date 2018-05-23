package org.thanos.reservations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.thanos.connection.Conexion;
import org.thanos.modelo.entities.Person;
import org.thanos.modelo.entities.User;
import org.thanos.rooms.Block;
import org.thanos.rooms.Room;

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
	
	public static ArrayList<UserReservation> RetrieveReservations(String user,int userType) throws SQLException{
		ArrayList<UserReservation> list = new ArrayList<>();
		UserReservation re;
		Connection conn = Conexion.getConexion();
        String query =  "SELECT CONCAT(p.name,' ',p.last_name) as name,p.code,p.email,r.date_init,r.observations,c.description,b.description as 'block'\r\n" + 
        		"FROM user_reservation ur\r\n" + 
        		"INNER JOIN user u ON ur.user_id = u.id\r\n" + 
        		"INNER JOIN person p ON u.person_id = p.id\r\n" + 
        		"INNER JOIN reservation r ON ur.reservation_id = r.id\r\n" + 
        		"INNER join classroom c ON ur.classroom_id = c.id\r\n" + 
        		"INNER JOIN block b on c.block_id = b.id\r\n";
        if (userType == 2) {
        	query +=" WHERE u.user = '"+user+"' ";
        }
        Statement cn = conn.createStatement();
        ResultSet response = cn.executeQuery(query);
        while(response.next()) {
        	re = new UserReservation();
        	re.reservation = new Reservation();
        	re.reservation.Observation = response.getString("observations");
        	re.reservation.DateInit = response.getString("date_init");
        	re.person = new Person();
        	re.person.Email = response.getString("email");
        	re.person.Name = response.getString("name");
        	re.room = new Room();
        	re.room.Name = response.getString("description");
        	re.person.LastName =  response.getString("code");
        	re.room.BlockRoom = new Block();
        	re.room.BlockRoom.Description = response.getString("block");
        	
        	list.add(re);
        }
        conn.close();
		return list;
	}
	
}
