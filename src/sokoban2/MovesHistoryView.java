package sokoban2;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.explodingpixels.macwidgets.HudWidgetFactory;
import com.explodingpixels.macwidgets.HudWindow;

public class MovesHistoryView extends JPanel{
	private String movesString;
	JFrame movesHud;
	JLabel movesLbl;
	JButton undoBtn;
	
	public MovesHistoryView() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		movesHud = new JFrame("Moves");
		movesHud.setSize(300, 80);
		movesHud.setLocationRelativeTo(null);
		movesHud.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		gbc.gridx = 0; gbc.gridy = 0;
		movesLbl = new JLabel("");
		this.add(movesLbl,gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		undoBtn  = new JButton("Undo");
		this.add(undoBtn, gbc);
		movesHud.setContentPane(this);
		movesHud.setVisible(true);
	}
	@Override
	public void setVisible(boolean visible) {
		movesHud.setVisible(visible);
	}
	public void setMovesString(String movesString) {
		this.movesString = movesString;
	}
	public String getMovesString() {
		return movesString;
	}
	public void updateMovesLbl(String moveString) {
		this.movesString = moveString;
		movesLbl.setText(movesString);
	}
	public void pack() {
		this.movesHud.setSize(300, movesLbl.getHeight() + 70);
	}
}
