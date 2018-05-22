package App;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.thanos.modelo.entities.User;
import org.thanos.reservations.Reservation;
import org.thanos.reservations.ReservationRespository;
import org.thanos.reservations.UserReservation;
import org.thanos.rooms.Block;
import org.thanos.rooms.Room;
import org.thanos.rooms.RoomRepository;

import com.mysql.jdbc.BlobFromLocator;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.SpringLayout;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.ComponentOrientation;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class Panel_NewReservation extends JPanel {

	/**
	 * Create the panel.
	 */
	ArrayList<Room> rooms = new ArrayList<>();
	public long blockId = 0;
	public int roomId = 0;
	private JTextField txt_observation;
	public Panel_NewReservation() {
		this.setBackground(Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 153));
		JLabel lbl_num_rooms = new JLabel("0");
		JLabel lbl_num = new JLabel("0");
		JLabel lblBloque = new JLabel("Bloque");
		/*
		 * consulta de bloques
		 * */
		JComboBox<String> cbx_blocks = new JComboBox<String>() ;
		JComboBox<String> cbx_rooms = new JComboBox<String>();
		cbx_blocks.setBackground(new Color(51, 153, 204));
		ArrayList<Block> blocks;
		
		try {
			blocks = RoomRepository.GetBlocks();
			
			for(int i=0;i<blocks.size();i++) {
				
				cbx_blocks.addItem(blocks.get(i).Description);
			}
			
			cbx_blocks.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	                String block_str = (String)cbx_blocks.getSelectedItem();
	                for (int i=0;i<blocks.size();i++) {
	                	if(blocks.get(i).Description.equals(block_str)) {
	                		lbl_num_rooms.setText(""+blocks.get(i).NumRooms);
	                		blockId = blocks.get(i).Id;
	                		//rooms = new ArrayList<Room>();
	                		cbx_rooms.removeAllItems();
	                		try {
								rooms = RoomRepository.GetRoomsInBlock(blocks.get(i).Id);
								
								
								for (int j=0;j<rooms.size();j++) {
									cbx_rooms.addItem(rooms.get(j).Name);
								}
								return;
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	                	}
	                }
	              
	            }
	        });
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		cbx_rooms.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	 String room_str = (String)cbx_rooms.getSelectedItem();
	                for (int i=0;i<rooms.size();i++) {
	                	if(rooms.get(i).Name.equals(room_str)) {
	                		lbl_num.setText(""+rooms.get(i).NumChairs);
	                		roomId = rooms.get(i).Id;
	                	}
	                }
            	
            }
        });
		
		cbx_rooms.setBackground(new Color(51, 153, 204));
		
		JPanel panel_room = new JPanel();
		panel_room.setBackground(new Color(0, 102, 102));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 102));
		
		JLabel label = new JLabel("CAPACIDAD");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblSalones = new JLabel("Salones");
		lblSalones.setForeground(Color.WHITE);
		
		
		
		lbl_num_rooms.setForeground(Color.ORANGE);
		lbl_num_rooms.setFont(new Font("Tahoma", Font.BOLD, 18));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(47)
							.addComponent(label))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(97)
							.addComponent(lbl_num_rooms)))
					.addContainerGap(49, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(88, Short.MAX_VALUE)
					.addComponent(lblSalones)
					.addGap(80))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lbl_num_rooms)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSalones)
					.addGap(20))
		);
		panel.setLayout(gl_panel);
		
		JLabel lblSalon = new JLabel("Salon");
		
		JButton btnReservar = new JButton("Reservar");
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserReservation re = new UserReservation();
				re.reservation = new Reservation();
				re.reservation.Observation = txt_observation.getText();
				re.room = new Room();
				re.room.Id = roomId;
				re.user = new User();
				re.user.Id = Session.user.Id;
				try {
					ReservationRespository.InsertUserReservation(Session.user.Username,re);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
		});
		btnReservar.setBackground(new Color(0, 128, 128));
		
		txt_observation = new JTextField();
		txt_observation.setColumns(10);
		GroupLayout gl_panel_reservations = new GroupLayout(this);
		gl_panel_reservations.setHorizontalGroup(
			gl_panel_reservations.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
				.addGroup(gl_panel_reservations.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_reservations.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblBloque)
						.addComponent(cbx_blocks, 0, 183, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 10, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panel_reservations.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblSalon, Alignment.LEADING)
						.addComponent(panel_room, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
						.addComponent(cbx_rooms, 0, 202, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(gl_panel_reservations.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnReservar, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel_reservations.createSequentialGroup()
					.addContainerGap()
					.addComponent(txt_observation, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_reservations.setVerticalGroup(
			gl_panel_reservations.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_reservations.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_reservations.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBloque)
						.addComponent(lblSalon))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_reservations.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbx_blocks, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbx_rooms, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_panel_reservations.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_room, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(txt_observation, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnReservar))
		);
		
		JLabel lblCapacidad = new JLabel("CAPACIDAD");
		lblCapacidad.setForeground(Color.WHITE);
		lblCapacidad.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		
		lbl_num.setForeground(Color.ORANGE);
		lbl_num.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblSillas = new JLabel("Sillas");
		lblSillas.setForeground(Color.WHITE);
		GroupLayout gl_panel_room = new GroupLayout(panel_room);
		gl_panel_room.setHorizontalGroup(
			gl_panel_room.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_room.createSequentialGroup()
					.addGroup(gl_panel_room.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_room.createSequentialGroup()
							.addGap(44)
							.addComponent(lblCapacidad))
						.addGroup(gl_panel_room.createSequentialGroup()
							.addGap(90)
							.addGroup(gl_panel_room.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSillas)
								.addComponent(lbl_num))))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		gl_panel_room.setVerticalGroup(
			gl_panel_room.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_room.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCapacidad)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbl_num)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSillas)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		panel_room.setLayout(gl_panel_room);
		
		
		JLabel lbl_name_reservation = new JLabel("lbl_name");
		lbl_name_reservation.setForeground(Color.WHITE);
		lbl_name_reservation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lbl_name_reservation.setText(Session.FullName);
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbl_name_reservation)
					.addContainerGap(796, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lbl_name_reservation)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		this.setLayout(gl_panel_reservations);
	}
}
