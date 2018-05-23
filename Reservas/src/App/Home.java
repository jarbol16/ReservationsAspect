package App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.thanos.modelo.entities.Person;
import org.thanos.modelo.entities.Student;
import org.thanos.modelo.repository.ModelRepository;
import org.thanos.modelo.repository.PersonRepository;
import org.thanos.reservations.ReservationRespository;
import org.thanos.reservations.UserReservation;

import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_reservations;
	/**
	 * Create the frame.
	 */
	public Home() {
		
		
		DefaultTableModel modelo = new DefaultTableModel();
		String[] cl = {"Usuario","Codigo","Email","Fecha","Observacion","Salon","Bloque"};
		modelo.addColumn("Usuario");
		modelo.addColumn("Codigo");
		modelo.addColumn("Email");
		modelo.addColumn("Fecha");
		modelo.addColumn("Observacion");
		modelo.addColumn("Salon");
		modelo.addColumn("Bloque");
		modelo.addRow(cl);
		try {
			ArrayList<UserReservation> list = ReservationRespository.RetrieveReservations(Session.user.Username, Session.user.Type);
			String[] row = new String[7];
			for (int i=0;i<list.size();i++) {
				row[0] = ""+ list.get(i).person.Name;
				row[1] = list.get(i).person.LastName;
				row[2] = list.get(i).person.Email;
				row[3] = list.get(i).reservation.DateInit;
				row[4] = list.get(i).reservation.Observation;
				row[5] = list.get(i).room.Name;
				row[6] = list.get(i).room.BlockRoom.Description;
				modelo.addRow(row);
			}
			
			
		}catch (SQLException ex) {
			System.out.println(ex.getMessage());
			// TODO: handle exception
		}
		
		table_reservations = new JTable(modelo);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 656);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBackground(new Color(255, 255, 255));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 882, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
		);
		
		JPanel panel_home = new JPanel();
		panel_home.setBorder(null);
		panel_home.setBackground(Color.WHITE);
		tabbedPane.addTab("Home", null, panel_home, null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 153));
		
		JPanel panel_1 = new JPanel();
		String ms = "Mis reservas";
		if (Session.user.Type == 1)
			ms = "Reservas Pendientes";
		
		JLabel lblReservasPendientes = new JLabel(ms);
		lblReservasPendientes.setForeground(new Color(0, 51, 102));
		lblReservasPendientes.setFont(new Font("Tahoma", Font.BOLD, 18));
		GroupLayout gl_panel_home = new GroupLayout(panel_home);
		gl_panel_home.setHorizontalGroup(
			gl_panel_home.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
				.addGroup(gl_panel_home.createSequentialGroup()
					.addGap(10)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel_home.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblReservasPendientes)
					.addContainerGap(653, Short.MAX_VALUE))
		);
		gl_panel_home.setVerticalGroup(
			gl_panel_home.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_home.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(lblReservasPendientes)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		
		panel_1.add(table_reservations, BorderLayout.CENTER);
		
		JLabel lblBienvenido = new JLabel("Bienvenido");
		lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBienvenido.setForeground(Color.WHITE);
		
		JLabel lbl_name = new JLabel("New label");
		String global_name = "";
		try {
			if (Session.user.Type == 1) {
				Person p = (Person)PersonRepository.GetUserFactory(Session.user.PersonId, Session.user.Type);
				lbl_name.setText(p.Name + " " + p.LastName);
				Session.FullName = p.Name + " " + p.LastName;
			}else if (Session.user.Type == 2) {
				Student s = (Student)PersonRepository.GetUserFactory(Session.user.PersonId, Session.user.Type);
				lbl_name.setText(s.Name + " " + s.LastName + " COD:" + s.Code );
				Session.FullName = s.Name + " " + s.LastName;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lbl_name.setForeground(Color.WHITE);
		lbl_name.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(new Color(0, 51, 102));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				Login _lo = new Login();
				Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		        int x = (int) ((dimension.getWidth() - _lo.getWidth()) / 2);
		        int y = (int) ((dimension.getHeight() - _lo.getHeight()) / 2);
		        //_lo.setUndecorated(true);
		        _lo.setLocation(x, y);
				_lo.setVisible(true);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBienvenido)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lbl_name)
							.addPreferredGap(ComponentPlacement.RELATED, 489, Short.MAX_VALUE)
							.addComponent(btnSalir)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBienvenido)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_name)
						.addComponent(btnSalir))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		panel_home.setLayout(gl_panel_home);
		
		
		
		if(Session.user.IsAdmin()) {
			
			
			/*JPanel panel_rooms = new JPanel();
			panel_rooms.setBackground(Color.WHITE);
			tabbedPane.addTab("New tab", null, panel_rooms, null);
			
			JPanel panel_new_reservation = new JPanel();
			panel_new_reservation.setBackground(Color.WHITE);
			tabbedPane.addTab("New tab", null, panel_new_reservation, null);*/
			tabbedPane.addTab("Audit", null, new Panel_Audit(), null);
		}else if(Session.user.IsStudent()) {
			
			tabbedPane.addTab("Reservar", null, new Panel_NewReservation(), null);
		}
		contentPane.setLayout(gl_contentPane);
	}
}
