package views;

import javax.swing.JOptionPane;

public class MiniDatabaseEngineGUI {
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new View();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
