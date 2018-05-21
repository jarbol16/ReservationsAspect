package App;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class Panel_NewReservation extends JPanel {

	/**
	 * Create the panel.
	 */
	public Panel_NewReservation() {
		this.setBackground(Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 153, 153));
		
		JLabel lblBloque = new JLabel("Bloque");
		
		JComboBox cbx_blocks = new JComboBox();
		
		JScrollPane panel_rooms = new JScrollPane();
		GroupLayout gl_panel_reservations = new GroupLayout(this);
		gl_panel_reservations.setHorizontalGroup(
			gl_panel_reservations.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addGroup(gl_panel_reservations.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBloque)
					.addContainerGap(394, Short.MAX_VALUE))
				.addGroup(gl_panel_reservations.createSequentialGroup()
					.addContainerGap()
					.addComponent(cbx_blocks, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_rooms, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_reservations.setVerticalGroup(
			gl_panel_reservations.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_reservations.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblBloque)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_reservations.createParallelGroup(Alignment.LEADING)
						.addComponent(cbx_blocks, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_rooms, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
					.addContainerGap())
		);
		
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
