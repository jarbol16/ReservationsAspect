package App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.thanos.modelo.entities.Person;
import org.thanos.modelo.entities.Student;
import org.thanos.modelo.repository.PersonRepository;

import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTable table;
	/**
	 * Create the frame.
	 */
	public Home() {
		
		
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
		GroupLayout gl_panel_home = new GroupLayout(panel_home);
		gl_panel_home.setHorizontalGroup(
			gl_panel_home.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
		);
		gl_panel_home.setVerticalGroup(
			gl_panel_home.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_home.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(499, Short.MAX_VALUE))
		);
		
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
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBienvenido)
						.addComponent(lbl_name))
					.addContainerGap(713, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBienvenido)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbl_name)
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
