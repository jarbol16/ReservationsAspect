package App;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import org.thanos.modelo.entities.AuditLog;
import org.thanos.modelo.repository.ModelRepository;

public class Panel_Audit extends JPanel {

	/**
	 * Create the panel.
	 */
	JTable table;
	public Panel_Audit() {
		this.setBackground(Color.WHITE);
		
		
		DefaultTableModel modelo = new DefaultTableModel();
		String[] cl = {"Codigo","Usuario","Descripcion","Fecha"};
		modelo.addColumn("Codigo");
		modelo.addColumn("Usuario");
		modelo.addColumn("Descripcion");
		modelo.addColumn("Fecha");
		modelo.addRow(cl);
		try {
			ArrayList<AuditLog> list = ModelRepository.RetrieveLogAudit();
			String[] row = new String[4];
			for (int i=0;i<list.size();i++) {
				row[0] = ""+ list.get(i).Id;
				row[1] = list.get(i).User;
				row[2] = list.get(i).Descrition;
				row[3] = list.get(i).DataTime;
				modelo.addRow(row);
			}
			
			
		}catch (SQLException ex) {
			System.out.println(ex.getMessage());
			// TODO: handle exception
		}
		
		table = new JTable(modelo);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 153, 153));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		GroupLayout gl_panel_audit = new GroupLayout(this);
		gl_panel_audit.setHorizontalGroup(
			gl_panel_audit.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addGroup(gl_panel_audit.createSequentialGroup()
					.addGap(10)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
					.addGap(10))
		);
		gl_panel_audit.setVerticalGroup(
			gl_panel_audit.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_audit.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
		);
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_2.add(table, BorderLayout.CENTER);
		
		JLabel lbl_user_name = new JLabel(Session.FullName);
		lbl_user_name.setForeground(Color.WHITE);
		lbl_user_name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel = new JLabel("Auditoria de Sistema");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbl_user_name)
					.addPreferredGap(ComponentPlacement.RELATED, 472, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(26, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbl_user_name)
						.addComponent(lblNewLabel))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		this.setLayout(gl_panel_audit);
	}

}
