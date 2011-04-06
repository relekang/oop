package oving9;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class HighscoreView extends JFrame {

	private static HighscoreView frame;
	private static JPanel panel;
	private JTable highscoreTable;
	public static void main(String[] args) {
		frame = new HighscoreView();
		frame.setVisible(true);
		frame.add(panel);
		frame.pack();
	}

	
	
	public HighscoreView() {
		
		panel = new JPanel();
		highscoreTable = new JTable(10,2);
		panel.add(highscoreTable);
		
	}
	
}
