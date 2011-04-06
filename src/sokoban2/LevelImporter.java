package sokoban2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LevelImporter implements Level{

	@Override
	public String[] getLines() {
		return null;
	}
	public String[] getLines(String filepath) {
		File lvlFile = new File(filepath);
		ArrayList<String> tmp = new ArrayList<String>();
		BufferedReader fileStream = null;
		try {
			fileStream = new BufferedReader(new FileReader(lvlFile));
			String line = fileStream.readLine();
			while (line != null) {
				tmp.add(line);
				line = fileStream.readLine();
			}
			fileStream.close();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO: handle exception
		}
		String[] lines = new String[tmp.size()];
		for (int i = 0; i < lines.length; i++) {
			lines[i] = tmp.get(i);
			lines[i].replace('|', ' ');
		} 
		return lines;
	}

	

}
