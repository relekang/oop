package sokoban2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LevelImporter implements Level{
	@Override
	public String[] getLines() { return null; }
	public String[] getLines(String filepath) {
		File lvlFile = new File(filepath);
		BufferedReader fileStream = null;
		String[] level = new String[1];
		try {
			fileStream = new BufferedReader(new FileReader(lvlFile));
			String line = fileStream.readLine();
			while (line != null) {
				//level[i] = line;
				line = fileStream.readLine();
			}
			fileStream.close();
			return null;
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO: handle exception
		}
		return null;
	}

	public char[][] newLevel() {
		return null;
	}
	public char[][] newLevel(String lvl) {
		int x = getColumns(lvl);
		int y = getRows(lvl);
		return txtLvlToChar(lvl,x,y);
	}
	public char[][] newLevel(String lvl,boolean isFile) {
		if(!isFile) return newLevel(lvl);
		return fromFile(lvl);
	}
	private char[][] fromFile(String filepath){
		String[] lines = getLines(filepath);
		return txtLvlToChar(lines);
	}
	private char[][] txtLvlToChar(String lvl, int x, int y){//		System.out.println("Loading...");
		int xPos = 0; int yPos = 0;
		char[][] lvlArray = new char[x][y];
		for(char symbol : lvl.toCharArray()){
			if(xPos < lvlArray[yPos].length && yPos < lvlArray.length ){
				//				System.out.println("Symbol: "+symbol+" ("+yPos+","+xPos+")");
				if(symbol != '|') lvlArray[xPos][yPos] = symbol; 
			}
			if(xPos < x){ xPos++; } else { xPos = 0; }
			if(symbol == '|'){ yPos++; xPos = 0; }
		}
		return lvlArray;
	}
	private char[][] txtLvlToChar(String[] lvlLines){
		char[][] lvlArray = new char[lvlLines[1].length()][lvlLines.length];
		for (int y = 0; y < lvlLines.length; y++) {
			for (int x = 0; x < lvlLines[y].length(); x++) {
				lvlArray[x][y] = lvlLines[y].charAt(x);
			}
		}
		return lvlArray;
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
