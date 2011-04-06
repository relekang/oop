package sokoban2;

import java.util.ArrayList;

public class GameEngine {
	private static final char TARGET = '.';
	private static final char BOX = '$';
	private static final char BOXATTARGET = '*';
	private static final char MOVER = '@';
	private static final char MOVERATTARGET = '+';
	private static final char WALL = '#';
	private static final char BLANK = ' ';
	
	int xPos; int yPos;
	private char[][] level = 
			{{'#', '#', '#', '#', '#', '#', '#'},
             {'#', ' ', ' ', ' ', '$', '.', '#'},
             {'#', '.', '$', ' ', '.', ' ', '#'},
             {'#', ' ', '@', '$', ' ', ' ', '#'},
             {'#', ' ', ' ', ' ', ' ', ' ', '#'},
             {'#', '#', '#', '#', '#', '#', '#'}}; 
		/*
			{
			 {' ',' ',' ','#','#','#',' ',' ',' ',' ',' '},
			 {' ',' ','#','#',' ','#',' ','#','#','#','#'},
			 {' ','#','#',' ',' ','#','#','#',' ',' ','#'},
			 {'#','#',' ','$',' ',' ',' ',' ',' ',' ','#'},
			 {'#',' ',' ',' ','@','$',' ','#',' ',' ','#'},
			 {'#','#','#',' ','$','#','#','#',' ',' ','#'},
			 {' ',' ','#',' ',' ','#','.','.',' ',' ','#'},
			 {' ','#','#',' ','#','#','.','#',' ','#','#'},
			 {' ','#',' ',' ',' ',' ',' ',' ','#','#',' '},
			 {' ','#',' ',' ',' ',' ',' ','#','#',' ',' '},
			 {' ','#','#','#','#','#','#','#',' ',' ',' '},
			}; */
	private ArrayList<Character> movesHistory;
	private int moves;
	Movement movement;
	LevelImporter importer;
	public GameEngine() {
		importer = new LevelImporter();
		String[] lines = importer.getLines("/Sokoban/lvl02.txt");
		level = newLevel(lines);
		movement = new Movement();
		movesHistory = new ArrayList<Character>();
		moves = 0;
		findTheGuy();
	}
	public void newLevel(String filepath){
		String[] lines = importer.getLines(filepath);
		level = newLevel(lines);
		findTheGuy();
		emptyMovesString();
	}
	
	private char[][] newLevel(String[] lines) {
		char[][] newLevel = new char[lines.length][lines[0].length()];
		for (int y = 0; y < lines.length; y++) {
			for (int x = 0; x < lines[y].length(); x++) {
				newLevel[y][x] = lines[y].charAt(x);
			}
		}
		
		return newLevel;
		
	}
	public char[][] getLevel() {
		return level;
	}
	public void move(Direction direction){
		movement.move(direction, false);
	}
	public char getSymbol(int x, int y){
		if(x > 0 && x < level[y].length && y < level.length && y > 0) return level[y][x];
		else return 0;
	}
	public void setSymbol(char c, int x, int y){
		if(x < level[y].length && y < level.length) level[y][x] = c;
	}
	
	public void setMovesString(char c, int location) {
		if(location < movesHistory.size()) movesHistory.add(location, c);
	}
	public void addMovesString(char newChar) {
		movesHistory.add(newChar);
	}
	private void emptyMovesString() {
		movesHistory.removeAll(movesHistory);
	}
	public String getMovesString(boolean html) {
		String tmp ="";
		if(html) tmp += "<html>";
		int counter = 0;
		for (char  c : movesHistory) {
			tmp += c;
			counter++;
			if(counter > 30 && html){counter = 0;tmp += "<br/>";}
		}
		if(html) tmp+= "</html>";
		return tmp;
	}
	public void makeLastMoveCaps(){
		if(movesHistory.size() > 0) movesHistory.set(movesHistory.size()-1, Character.toUpperCase(movesHistory.get(movesHistory.size()-1)));
	}
	public boolean hasWon() {
		return (targetsLeft() == 0);
	}
	public int getMoves() {
		return movesHistory.size();
	}
	public void findTheGuy() {
		for(int y = 0; y < level.length; y++){
			for (int x = 0; x < level[y].length; x++) {
				if(level[y][x] == MOVER || level[y][x] == MOVERATTARGET){
					xPos = x;
					yPos = y;
					System.out.println("Found him: x="+x+", y="+y);
				}
			}
		}
	}
	public int targetsLeft() {
		int targetsLeft = 0;
		for(int y = 0; y < level.length; y++){
			for (int x = 0; x < level[y].length; x++) {
				if(level[y][x] == TARGET || level[y][x] == MOVERATTARGET) targetsLeft++;
			}
		}
		return targetsLeft;
	}
	public int targetsTaken(){
		int targetsTaken = 0;
		for(int y = 0; y < level.length; y++){
			for (int x = 0; x < level[y].length; x++) {
				if(level[y][x] == BOXATTARGET){
					targetsTaken++;
				}
			}
		}
		return targetsTaken;
	}
	private class Movement{
		void move(Direction direction, boolean undoing){
			int dx = direction.dx; int dy = direction.dy;
			switch(getSymbol(xPos + dx, yPos + dy)){
			case BOX:
				if((xPos+(dx*2)) > 0 || (xPos+(dx*2)) < level[yPos].length || (yPos+(dy*2)) > 0 || (yPos+(dy*2)) < level.length){
					char two = getSymbol(xPos + (dx*2), yPos +(dy*2));
					if(!(two == WALL || two == BOXATTARGET || two == BOX)){
						if(two == TARGET) { setSymbol(BOXATTARGET,xPos + (dx*2), yPos +(dy*2)); } else { setSymbol(BOX,xPos + (dx*2), yPos +(dy*2)); }
						setSymbol(MOVER,xPos + dx, yPos + dy);
						leave(xPos, yPos);
						if(!undoing) addMovesString(Character.toUpperCase(direction.c));
					}
				}
				break;
			case BOXATTARGET:
				if((xPos+(dx*2)) > 0 || (xPos+(dx*2)) < level[yPos].length || (yPos+(dy*2)) > 0 || (yPos+(dy*2)) < level.length){
					char two = getSymbol(xPos + (dx*2), yPos +(dy*2));
					if(!(two == WALL || two == BOXATTARGET || two == BOX)){
						if(two == TARGET) { setSymbol(BOXATTARGET,xPos + (dx*2), yPos +(dy*2)); } else { setSymbol(BOX,xPos + (dx*2), yPos +(dy*2)); }
						setSymbol(MOVERATTARGET,xPos + dx, yPos + dy);
						leave(xPos, yPos);
						if(!undoing) addMovesString(Character.toUpperCase(direction.c));
					}
				}
				break;
			case BLANK:
				setSymbol(MOVER,xPos + dx, yPos + dy);
				leave(xPos, yPos);
				if(!undoing) addMovesString(direction.c);
				break;
			case TARGET:
				setSymbol(MOVERATTARGET,xPos + dx, yPos + dy);
				leave(xPos, yPos);
				if(!undoing) addMovesString(direction.c);
				break;
			case WALL:
				break;
			default:
				break;
			}
			moves++;
			findTheGuy();
		}
		private void leave(int x, int y){
			switch(getSymbol(x, y)){
			case MOVER: setSymbol(BLANK, x, y); break;
			case MOVERATTARGET: setSymbol(TARGET, x, y); break;
			}
		}
		
	}
	public void undo() {
		char lastMove = getLastMove();
		removeLastMove();
		switch(lastMove){
		case 'u':
			movement.move(Direction.DOWN, true);
			break;
		case 'd':
			movement.move(Direction.UP, true);
			break;
		case 'l':
			movement.move(Direction.RIGHT, true);
			break;
		case 'r':
			movement.move(Direction.LEFT, true);
			break;
		case 'U':
			movement.move(Direction.DOWN, true);
			moveBox(Direction.DOWN);
			break;
		case 'D':
			movement.move(Direction.UP, true);
			moveBox(Direction.UP);
			break;
		case 'L':
			movement.move(Direction.RIGHT, true);
			moveBox(Direction.RIGHT);
			break;
		case 'R':
			movement.move(Direction.LEFT, true);
			moveBox(Direction.LEFT);
			break;
		}
		//if(getMoves() > 0) moves--;
		findTheGuy();
	}
	
	private void moveBox(Direction direction) {
		int dx = -direction.dx; int dy = -direction.dy;
		if(level[yPos+(2*dy)][xPos+(2*dx)] == BOXATTARGET){
			level[yPos+(2*dy)][xPos+(2*dx)] = TARGET;
			if(level[yPos+dy][xPos+dx] == TARGET) level[yPos+dy][xPos+dx] = BOXATTARGET;
			else level[yPos+dy][xPos+dx] = BOX;
		} else if(level[yPos+(2*dy)][xPos+(2*dx)] == BOX){
			level[yPos+(2*dy)][xPos+(2*dx)] = BLANK;
			if(level[yPos+dy][xPos+dx] == TARGET) level[yPos+dy][xPos+dx] = BOXATTARGET;
			else level[yPos+dy][xPos+dx] = BOX;
		} else {
			System.out.println("Something went wrong. (y:"+(yPos+(2*dy))+",x:"+(xPos+(2*dx))+"("+dx+"))"+level[yPos+(2*dy)][xPos+(2*dx)]);
		}
	}
	private void removeLastMove() {
		if(movesHistory.size() > 0) movesHistory.remove(movesHistory.size() -1);
	}
	private char getLastMove() {
		if(movesHistory.size() > 0) return movesHistory.get(movesHistory.size() -1);
		else return 'x';
	}
	
	
}
