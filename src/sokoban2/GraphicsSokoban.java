package sokoban2;

import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sun.codemodel.internal.JSwitch;

import acm.graphics.GImage;

import acm.program.GraphicsProgram;

/*
 * @startuml
 * class GraphicsSokoban {
 * }
 * class GraphicsProgram {
 * }
 * GraphicsProgram <|-- GraphicsSokoban
 * @enduml
 */

public class GraphicsSokoban extends GraphicsProgram {
	SokobanGameEngine gameEngine;
	GridBagConstraints gbc;
	JPanel statusPanel, gamePanel,lvlPanel;
	JLabel movesLbl, targetsLeftLbl;
	JComboBox lvlComboBox;
	char[][] theLvl;
	public void init() {
		gameEngine = new SokobanGameEngine();
		gbc = new GridBagConstraints();
		makeStatusLbl();
//		makeLvlPanel();
		this.addKeyListener(listener);
	}
	public void run() {
		String lvl = "#######|#  #.  #|# $#   #|#  # @##|#  # $##|#    .##|########";
		gameEngine.loadLevel(lvl, 8, 8);
//		gameEngine.loadLevelFromFile("/Sokoban/lvl00.txt");
		gamePanel = new JPanel();
		add(gamePanel, SOUTH);
		
		gamePanel.setFocusable(true);
		theLvl = gameEngine.getLvl();
		updateBoard(theLvl);
		gameEngine.findTheGuy();
		updateStatus();
	}
	public KeyListener listener = new KeyListener() {
		
		@Override
		public void keyReleased(KeyEvent e) {
			if(!gameEngine.hasWon()){
				if(e.getKeyCode() == 37){gameEngine.move(-1,0);gameEngine.addToMovesString("l");} 
				else if(e.getKeyCode() == 38){gameEngine.move(0,-1);gameEngine.addToMovesString("u");}
				else if(e.getKeyCode() == 39){gameEngine.move(1,0);gameEngine.addToMovesString("r");}
				else if(e.getKeyCode() == 40){gameEngine.move(0,1);gameEngine.addToMovesString("d");}
				updateBoard(theLvl);
				updateStatus();
				gameEngine.findTheGuy();
				hasWon();
				System.out.println(e.getKeyCode());
			}
		}
		@Override
		public void keyTyped(KeyEvent e) { 	}
		@Override
		public void keyPressed(KeyEvent e) { }
		
		
	};
	private void updateBoard(char[][] level) {
		for (int i = 0; i < level.length; i++) {
			for (int ii = 0; ii < level[i].length; ii++) {
				switch(level[ii][i]){
				case ' ':
					GImage theImage = new GImage("sokoban/icons/blank16x16.png");
					add(theImage, ii*16, i*16);
					break;
				case '#':
					GImage theImage1 = new GImage("sokoban/icons/wall16x16.png");
					add(theImage1, ii*16, i*16);
					break;
				case '.':
					GImage theImage2 = new GImage("sokoban/icons/target16x16.png");
					add(theImage2, ii*16, i*16);
					break;
				case '@':
					GImage theImage3 = new GImage("sokoban/icons/mover16x16.png");
					add(theImage3, ii*16, i*16);
					break;
				case '+':
					GImage theImage4 = new GImage("sokoban/icons/mover_on_target16x16.png");
					add(theImage4, ii*16, i*16);
					break;
				case '$':
					GImage theImage5 = new GImage("sokoban/icons/movable16x16.png");
					add(theImage5, ii*16, i*16);
					break;
				case '*':
					GImage theImage6 = new GImage("sokoban/icons/movable_on_target16x16.png");
					add(theImage6, ii*16, i*16);
					break;
				}
			}
		}
	}
	private void updateStatus(){
		int moves = 0; int targetsLeft = 0;
		if(gameEngine.isLevelLoaded()){
			moves = gameEngine.getMoves();
			targetsLeft = gameEngine.targetsLeft();
		}
		movesLbl.setText("Moves: " + moves);
		targetsLeftLbl.setText("Targets left: " + targetsLeft);
	}
	private void makeStatusLbl() {
		statusPanel = new JPanel();
		movesLbl = new JLabel("Moves: 0");
		targetsLeftLbl = new JLabel("Targets left: 0");
		gbc.gridx = 0;
		gbc.gridy = 0;
		statusPanel.add(movesLbl, gbc);
		gbc.gridx = 1;
		statusPanel.add(targetsLeftLbl, gbc);
		this.add(statusPanel, NORTH);
	}
	private void makeLvlPanel() {
		lvlPanel = new JPanel();
		lvlComboBox = new JComboBox();
		lvlPanel.add(lvlComboBox);
		add(lvlPanel, SOUTH);
		
	}
	private void hasWon() {
		if(gameEngine.hasWon()){ JOptionPane.showMessageDialog(this, "You made it with " + gameEngine.getMoves() +" moves\n Moves: "+gameEngine.getMovesString(), "Congratulation", JOptionPane.INFORMATION_MESSAGE);	}	
	}
}

