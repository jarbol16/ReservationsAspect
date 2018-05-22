package App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.thanos.modelo.entities.User;
import org.thanos.modelo.repository.UserRepository;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txt_user;
	private JTextField txt_pass;

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 381, 252);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txt_user = new JTextField();
		txt_user.setColumns(10);
		
		txt_pass = new JTextField();
		txt_pass.setColumns(10);
		
		JButton btn_loggin = new JButton("Ingresar");
		btn_loggin.addActionListener(new ActionListener() {
			private User _user;
			public void actionPerformed(ActionEvent e) {
				 try {
					_user = UserRepository.NewLogin(txt_user.getText(), txt_pass.getText());
					Session.user = _user;
					if (_user.IsAdmin() || _user.IsStudent()) {
						Home h = new Home();
						Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
				        int x = (int) ((dimension.getWidth() - h.getWidth()) / 2);
				        int y = (int) ((dimension.getHeight() - h.getHeight()) / 2);
				        //_lo.setUndecorated(true);
				        h.setLocation(x, y);
						setVisible(false);
						h.setVisible(true);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
			}
		});
		btn_loggin.setBorder(null);
		btn_loggin.setBackground(new Color(0, 153, 153));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 153, 153));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(90)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btn_loggin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txt_pass, Alignment.LEADING)
								.addComponent(txt_user, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addComponent(txt_user, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(txt_pass, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btn_loggin, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(25))
		);
		
		JLabel lblSistemaDeReservas = new JLabel("SISTEMA DE RESERVAS");
		lblSistemaDeReservas.setForeground(Color.WHITE);
		lblSistemaDeReservas.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblSistemaDeReservas);
		contentPane.setLayout(gl_contentPane);
	}
}
