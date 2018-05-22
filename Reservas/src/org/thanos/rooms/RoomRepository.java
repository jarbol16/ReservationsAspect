package org.thanos.rooms;

import java.util.*;

import org.thanos.connection.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoomRepository {
	public ArrayList<Room> GetRoomsInBlock(int blockId) throws SQLException{
		ArrayList<Room> list = new ArrayList<>();
		Room room;
        Connection conn = Conexion.getConexion();
        String query =  "SELECT * FROM person u WHERE u.`id` = '"+blockId+"'";
        Statement cn = conn.createStatement();
        ResultSet response = cn.executeQuery(query);
        while(response.next()) {
        	room = new Room();
        	room.Name = response.getString("name");
        	list.add(room);
        }
		return list;
	}
}
