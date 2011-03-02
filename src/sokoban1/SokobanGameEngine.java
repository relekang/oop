package sokoban1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import apple.laf.CoreUIConstants.ShowArrows;



public class SokobanGameEngine {
	private char[][] lvlArray;
	private boolean[][] targets;
	private boolean lvlLoaded;
	private int xPosition, yPosition, moves;
	public char[][] getLvl(){
		if(lvlLoaded) return lvlArray;
		else return null;
	}
	private class Movement {
		void move(int dx, int dy){
			switch(getSymbol(xPosition + dx, yPosition + dy)){
			case '$': //Box
				char two = getSymbol(xPosition + (dx*2), yPosition +(dy*2));
				if(!(two == '#' || two == '*')){
					if(two == '.') { setSymbol('*',xPosition + (dx*2), yPosition +(dy*2)); } else { setSymbol('$',xPosition + (dx*2), yPosition +(dy*2)); }
					setSymbol('@',xPosition + dx, yPosition + dy);
					leave(xPosition, yPosition);
				}
				break;
			case ' ':
				setSymbol('@',xPosition + dx, yPosition + dy);
				leave(xPosition, yPosition);
				break;
			case '.':
				setSymbol('+',xPosition + dx, yPosition + dy);
				leave(xPosition, yPosition);
				break;
			default:
				break;
			}
		}
		private void leave(int x, int y){
			switch(getSymbol(x, y)){
			case '@': setSymbol(' ', x, y); break;
			case '+': setSymbol('.', x, y); break;
			}
		}
	}
	
	public char[][] move(int dx, int dy){
		moves++;
		Movement mvmnt = new Movement();
		mvmnt.move(dx, dy);
		return lvlArray;
	}
	
	public String findTheGuy() {
		for(int i = 0; i < lvlArray.length; i++){
			for (int ii = 0; ii < lvlArray[i].length; ii++) {
				if(lvlArray[i][ii] == '@' || lvlArray[i][ii] == '+'){
					xPosition = i;
					yPosition = ii;
				}
			}
		}
		return "TheGuyIs at ("+xPosition+","+yPosition+"): "+lvlArray[xPosition][yPosition];
	}
	public int targetsLeft() {
		int targetsLeft = 0;
		for(int i = 0; i < lvlArray.length; i++){
			for (int ii = 0; ii < lvlArray[i].length; ii++) {
				if(lvlArray[i][ii] == '.' || lvlArray[i][ii] == '+'){
					targetsLeft++;
				}
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
	public void loadLevel(String lvl, int x, int y){
		System.out.println("Loading...");
		int xPos = 0; int yPos = 0;
		lvlArray = new char[x][y];
		targets = new boolean[x][y];
		char[] symbols = lvl.toCharArray();
		for(char symbol : symbols){//lvl.toCharArray()){
			if(xPos < lvlArray[yPos].length && yPos < lvlArray.length ){
				System.out.println("Symbol: "+symbol+" ("+yPos+","+xPos+")");
				if(symbol != '|'){
					lvlArray[xPos][yPos] = symbol;
					if(symbol == '*' || symbol == '.' || symbol == '+'){ targets[xPos][yPos] = true; } else { targets[xPos][yPos] = false;}
				}
			}
			if(xPos < x){ xPos++; } else { xPos = 0; }
			if(symbol == '|'){ yPos++; xPos = 0; }
		}
		lvlLoaded = true;
	}
	public void loadLevelFromFile(String filepath){
		File lvlFile = new File(filepath);
		BufferedReader fileStream = null;
		String level = "";
		try {
			fileStream = new BufferedReader(new FileReader(lvlFile));
			String line = fileStream.readLine();
		      while (line != null) {
		        level = level + line;
		        line = fileStream.readLine();
		      }
			fileStream.close();
			System.out.println(level);
			int x = getColumns(level);
			int y = getRows(level);
			loadLevel(level,x,y);
			//loadLevel(level, getColumns(level), getRows(level));
			
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	private int getRows(String level) {
		int nrOfLines = 1;
		for(char symbol : level.toCharArray()){
			if(symbol == '|'){
				nrOfLines++;
			}
		}
		nrOfLines++;
		return nrOfLines;
	}
	private int getColumns(String level) {
		int count = 0;
		for(char symbol : level.toCharArray()){
			if(symbol == '|'){ return count; }
			count++;
		}
		return count;
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
	public int getMoves() {
		return this.moves;
	}
	public boolean isLevelLoaded(){
		return lvlLoaded;
	}
}
