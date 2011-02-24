package sokoban1;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import acm.program.ConsoleProgram;

public class ConsoleSokoban extends ConsoleProgram implements KeyListener {
	SokobanGameEngine gameEngine;
	
	public void init() {
		gameEngine = new SokobanGameEngine();
		this.getConsole().addKeyListener(this);
		//Font font = new Font(this.getFont().toString(), Font.PLAIN, 16);
		this.setSize(200, 140);
		//this.setFont(font);
	}
	public void run() {
//		this.getConsole().setBackground(new Color(0, 20, 20));
//		this.getConsole().setForeground(new Color(230, 230, 230));
//		this.getConsole().setFont(new Font(this.getConsole().getFont().toString(), Font.PLAIN, 20));
		String lvl = "#######|#  #.  #|# $#   #|#  # @##|#  # $##|#    .##|########";
		gameEngine.loadLevel(lvl, 8, 8);
//		gameEngine.loadLevelFromFile("/Sokoban/lvl03.txt");
		while(!gameEngine.hasWon()){
			char[][] theLvl; 
			theLvl = gameEngine.getLvl();
			printLevel(theLvl);
			println(gameEngine.findTheGuy());
			String move = readLine("What's your move: ");
			if(move.length() > 0){  
				switch(move.charAt(0)){
				case 'u':
					gameEngine.move(0,-1); 
					break;
				case 'd':
					gameEngine.move(0,1); 
					break;
				case 'r':
					gameEngine.move(1,0); 
					break;
				case 'l':
					gameEngine.move(-1,0); 
					break;
				default: 
					break;
				}
			} else { move = readLine("Try to actually give a command: "); }
		}
		println("Congratulation you made it in " + gameEngine.getMoves() + " steps!");


	}
	public void printLevel(char[][] lvl){
		for(int y = 0; y < lvl.length; y++){
			for(int x = 0; x < lvl[y].length; x++){
				print(lvl[x][y]);
			}
			print("\n");
		}
	}
	public void printStats() {
		println("Targets left: "+gameEngine.targetsLeft());
		println("Targets taken: "+gameEngine.targetsTaken());
//TODO	println("Moves used: "+gameEngine.nrOfMoves();
	}
	
}