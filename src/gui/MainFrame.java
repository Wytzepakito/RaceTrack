package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;





public class MainFrame {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
			
				MainFrame frame = new MainFrame();


			}
		});
	}
	
	public MainFrame() {
		createMainFrame();
	}

	public void createMainFrame() {
		JFrame f = new JFrame("Race track");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(0, 0, 1600, 900);
		Field fieldPane = new Field(950, 950);
		f.add(fieldPane);
		f.pack();
		f.setVisible(true);
		fieldPane.repaint();
		
		
	}
	
}
