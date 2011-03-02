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
	}
	public char[][] getLvl(){
		if(lvlLoaded) return lvlArray;
		else return null;
	}
	public void loadLevel(String lvl, int x, int y){
		System.out.println("Loading...");
		int xPos = 0; int yPos = 0;
		lvlArray = new char[x][y];
		char[] symbols = lvl.toCharArray();
		for(char symbol : symbols){//lvl.toCharArray()){
			if(xPos < lvlArray[yPos].length && yPos < lvlArray.length ){
//				System.out.println("Symbol: "+symbol+" ("+yPos+","+xPos+")");
				if(symbol != '|') lvlArray[xPos][yPos] = symbol; 
			}
			if(xPos < x){ xPos++; } else { xPos = 0; }
			if(symbol == '|'){ yPos++; xPos = 0; }
		}
		lvlLoaded = true;
	}
	
	public class LevelImporter{
		public void fromFile(String filepath){
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
				if(symbol == '|') nrOfLines++;
			}
			return nrOfLines;
		}
		private int getColumns(String level) {
			int count = 0;
			for(char symbol : level.toCharArray()){
				if(symbol == '|')return count;
				count++;
			}
			return count;
		}
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
	}
	
	public char[][] move(int dx, int dy){
		mover.move(dx, dy);
		return lvlArray;
	}
	
	public void makeLastMoveCaps() {
		setMovesString(Character.toUpperCase(getMovesString().charAt(getMovesString().length()-1)),getMovesString().length()-1);
	}
	public void setMovesString(char upperCase, int i) {
		char[] chars = this.movesString.toCharArray();
		chars[i] = upperCase;
		String newString = "";
		for (char c : chars) { newString += c; }
		this.movesString = newString;
		
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
	public int getMoves() {
		return this.moves;
	}
	public boolean isLevelLoaded(){
		return lvlLoaded;
	}

	public void addToMovesString(String movesString) {
		this.movesString += movesString;
	}

	public String getMovesString() {
		return movesString;
	}
	public void loadLevelFromFile(String lvl) {
		importer.fromFile(lvl);
		
	}
}
