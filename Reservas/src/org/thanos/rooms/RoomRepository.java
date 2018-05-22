package org.thanos.rooms;

import java.util.*;

import org.thanos.connection.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoomRepository {
	public static ArrayList<Room> GetRoomsInBlock(long blockId) throws SQLException{
		ArrayList<Room> list = new ArrayList<>();
		Room room;
        Connection conn = Conexion.getConexion();
        String query =  "SELECT * FROM classroom c WHERE c.`block_id` = '"+blockId+"'";
        Statement cn = conn.createStatement();
        ResultSet response = cn.executeQuery(query);
        while(response.next()) {
        	room = new Room();
        	room.Name = response.getString("description");
        	room.NumChairs = response.getInt("capacity");
        	room.Id = response.getInt("id");
        	list.add(room);
        }
        conn.close();
		return list;
	}
	
	
	public static ArrayList<Block> GetBlocks() throws Exception{
		ArrayList<Block> list = new ArrayList<>();
		Block block;
		Connection conn = Conexion.getConexion();
        String query =  "SELECT * FROM block";
        Statement cn = conn.createStatement();
        ResultSet response = cn.executeQuery(query);
        while(response.next()) {
        	block = new Block();
        	block.Description = response.getString("description");
        	block.Id = response.getLong("id");
        	block.NumRooms = response.getInt("num_room");
        	list.add(block);
        }
        conn.close();
		return list;
	}
}
