package sokoban2;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GraphicsSokoban extends JPanel implements KeyListener, ActionListener{
	GameEngine gameEngine;
	GridBagConstraints gbc;
	static JFrame frame;
	JPanel statusPanel, gamePanel;
	JLabel movesLbl, targetsLeftLbl;
	JLabel[][] graphicBoard;
	MovesHistoryView movesHistoryView;
	JMenuBar menuBar;JMenu lvlMenu;JMenuItem[] lvlMenuItems;
	Icon blankImg, wallImg, targetImg, moverImg, moverOnTargetImg, movableImg, movableOnTargetImg;
	public static void main(String[] args) {
		frame = new JFrame("Sokoban");
		frame.add(new GraphicsSokoban());
//		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		
	}
	
	public GraphicsSokoban() {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		gbc = new GridBagConstraints();
		setLayout(new GridBagLayout());
		createStatusPanel();		
		gamePanel = new JPanel();
		gamePanel.setLayout(new GridBagLayout());
		gbc.gridx = 0; gbc.gridy = 1;
		add(gamePanel,gbc);
		gameEngine = new GameEngine();
		movesHistoryView = new MovesHistoryView();
		movesHistoryView.setVisible(true);
		frame.addKeyListener(this);
		createMenu();
		blankImg = new ImageIcon("resources/sokoban/icons/blank16x16.png");
		wallImg = new ImageIcon("resources/sokoban/icons/wall16x16.png");
		moverImg = new ImageIcon("resources/sokoban/icons/mover16x16.png");
		targetImg = new ImageIcon("resources/sokoban/icons/target16x16.png");
		moverOnTargetImg = new ImageIcon("resources/sokoban/icons/mover_on_target16x16.png");
		movableImg = new ImageIcon("resources/sokoban/icons/movable16x16.png");
		movableOnTargetImg = new ImageIcon("resources/sokoban/icons/movable_on_target16x16.png");
		updateBoard();
		frame.pack();
	}
	private void createMenu() {
		menuBar = new JMenuBar();
		lvlMenu = new JMenu("Level");
		menuBar.add(lvlMenu);
		lvlMenuItems = new JMenuItem[5];
		for (int i = 0; i < lvlMenuItems.length; i++) {
			lvlMenuItems[i] = new JMenuItem("Level "+i);
			lvlMenu.add(lvlMenuItems[i]);
			lvlMenuItems[i].addActionListener(this);
		}
		frame.setJMenuBar(menuBar);
		
	}

	private void createStatusPanel() {
		statusPanel = new JPanel();
		statusPanel.setLayout(new GridBagLayout());
		movesLbl = new JLabel("Moves: 000");
		targetsLeftLbl = new JLabel("Targets left: 000");
		gbc.gridx = 0; gbc.gridy = 0;
		statusPanel.add(movesLbl,gbc);
		gbc.gridx = 1; gbc.gridy = 0;
		statusPanel.add(targetsLeftLbl,gbc);
		gbc.gridx = 0; gbc.gridy = 0;
		add(statusPanel, gbc);
	}
/*	private void createBoard(){
		char[][] level = gameEngine.getLevel();
		graphicBoard = new JLabel[level.length][level[0].length];
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[y].length; x++) {
				graphicBoard[y][x] = new JLabel(getIcon(level[y][x]));
				gbc.gridx = x; gbc.gridy = y;
				gamePanel.add(graphicBoard[y][x], gbc);
			}
		}
	}
	*/
	private Icon getIcon(char symbol) {
		switch(symbol){
		case ' ':
			return blankImg;
		case '#':
			return wallImg;
		case '.':
			return targetImg;
		case '@':
			return moverImg;
		case '+':
			return moverOnTargetImg;
		case '$':
			return movableImg;
		case '*':
			return movableOnTargetImg;
		}
		return null;
	}

	public void updateBoard() {
		gamePanel.removeAll();
		char[][] level = gameEngine.getLevel();
		graphicBoard = new JLabel[level.length][level[0].length];
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[y].length; x++) {
				//graphicBoard[y][x].setIcon(getIcon(level[y][x]));
				gbc.gridx = x; gbc.gridy = y;
				gamePanel.add(new JLabel(getIcon(level[y][x])),gbc);
			}
		}
	}

	private void updateStatus() {
		movesLbl.setText("Moves: "+gameEngine.getMoves());
		targetsLeftLbl.setText("Targets left: "+gameEngine.targetsLeft());
		movesHistoryView.updateMovesLbl(gameEngine.getMovesString(true));
		movesHistoryView.pack();
	}	
	@SuppressWarnings("unused")
	private void sysoutLevel(char[][] level) {
		for (int y = 0; y < level.length; y++) {
			for (int x = 0; x < level[y].length; x++) {
				System.out.print(level[y][x]);
			}
			System.out.println();
		}
	}

	private void hasWon() {
		if(gameEngine.hasWon())  JOptionPane.showMessageDialog(this, "You made it with " + gameEngine.getMoves() +" moves\n Moves: "+gameEngine.getMovesString(false));	
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(!gameEngine.hasWon()){
			     if(e.getKeyCode() == 37) gameEngine.move(Direction.LEFT); 
			else if(e.getKeyCode() == 38) gameEngine.move(Direction.UP);
			else if(e.getKeyCode() == 39) gameEngine.move(Direction.RIGHT);
			else if(e.getKeyCode() == 40) gameEngine.move(Direction.DOWN);
			else if(e.getKeyCode() == 8)  gameEngine.undo();
			updateBoard();
			updateStatus();
			hasWon();
			System.out.println(gameEngine.getMovesString(false));
		}
		
	}
	

	@Override
	public void keyPressed(KeyEvent e) { }

	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < lvlMenuItems.length; i++) {
			if(e.getSource() == lvlMenuItems[i]) {gameEngine.newLevel("/Sokoban/lvl0"+i+".txt");updateBoard();updateStatus();}
			frame.pack();
		}
		
	}
	

}
