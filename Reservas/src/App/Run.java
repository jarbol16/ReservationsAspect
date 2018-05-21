package App;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

	public class Run {
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Login _lo = new Login();
						Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
				        int x = (int) ((dimension.getWidth() - _lo.getWidth()) / 2);
				        int y = (int) ((dimension.getHeight() - _lo.getHeight()) / 2);
				        //_lo.setUndecorated(true);
				        _lo.setLocation(x, y);
						
						_lo.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}
}
