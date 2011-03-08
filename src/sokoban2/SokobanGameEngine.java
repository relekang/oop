package sokoban2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SokobanGameEngine {
	private char[][] lvlArray;
	private boolean lvlLoaded;
	private int xPos, yPos, moves;
	private String movesString;
	private LevelImporter importer;private Movement mover;
	public SokobanGameEngine() {
		mover = new Movement();
		importer = new LevelImporter();
		String lvl = "#######|#  #.  #|# $#   #|#  # @##|#  # $##|#    .##|########";
		lvlArray = importer.newLevel(lvl);//(lvl, 8, 8);
	}
	public char[][] getLvl(){
		if(lvlLoaded) return lvlArray;
		else return null;
	}
	
	
	
	private class Movement {
		void move(int dx, int dy){
			switch(getSymbol(xPos + dx, yPos + dy)){
			case '$': //Box
				char two = getSymbol(xPos + (dx*2), yPos +(dy*2));
				if(!(two == '#' || two == '*')){
					if(two == '.') { setSymbol('*',xPos + (dx*2), yPos +(dy*2)); } else { setSymbol('$',xPos + (dx*2), yPos +(dy*2)); }
					setSymbol('@',xPos + dx, yPos + dy);
					leave(xPos, yPos);
					makeLastMoveCaps();
				}
				break;
			case ' ':
				setSymbol('@',xPos + dx, yPos + dy);
				leave(xPos, yPos);
				break;
			case '.':
				setSymbol('+',xPos + dx, yPos + dy);
				leave(xPos, yPos);
				
				break;
			default:
				break;
			}
			moves++;
		}
		private void leave(int x, int y){
			switch(getSymbol(x, y)){
			case '@': setSymbol(' ', x, y); break;
			case '+': setSymbol('.', x, y); break;
			}
		}
		public void makeLastMoveCaps() {
			setMovesString(Character.toUpperCase(getMovesString().charAt(getMovesString().length()-1)),getMovesString().length()-1);
		}
		
		
	}
	public char[][] move(int dx, int dy){
		mover.move(dx, dy);
		return lvlArray;
	}
	
	
	
	public void findTheGuy() {
		for(int i = 0; i < lvlArray.length; i++){
			for (int ii = 0; ii < lvlArray[i].length; ii++) {
				if(lvlArray[i][ii] == '@' || lvlArray[i][ii] == '+'){
					xPos = i;
					yPos = ii;
				}
			}
		}
	}
	public int targetsLeft() {
		int targetsLeft = 0;
		for(int y = 0; y < lvlArray.length; y++){
			for (int x = 0; x < lvlArray[y].length; x++) {
				if(lvlArray[y][x] == '.' || lvlArray[y][x] == '+') targetsLeft++;
			}
		}
		return targetsLeft;
	}
	public int targetsTaken(){
		int targetsTaken = 0;
		for(int i = 0; i < lvlArray.length; i++){
			for (int ii = 0; ii < lvlArray[i].length; ii++) {
				if(lvlArray[i][ii] == '*'){
					targetsTaken++;
				}
			}
		}
		return targetsTaken;
	}
	public char getSymbol(int x, int y) {
		return lvlArray[x][y];
	}
	public void setSymbol(char symbol, int x, int y) {
		lvlArray[x][y] = symbol;
	}
	public boolean hasWon(){
		return (targetsLeft() == 0);
	}
	
	public boolean isLevelLoaded(){
		return lvlLoaded;
	}
	public int getMoves() {
		return this.moves;
	}
	public String getMovesString() {
		return movesString;
	}
	public void setMovesString(char upperCase, int i) {
		char[] chars = this.movesString.toCharArray();
		chars[i] = upperCase;
		String newString = "";
		for (char c : chars) { newString += c; }
		this.movesString = newString;
		
	}
	public void addToMovesString(String movesString) {
		movesString += movesString;
	}
	public void loadLevelFromFile(String lvl) {
		lvlArray = importer.newLevel(lvl, true);	
	}
}
