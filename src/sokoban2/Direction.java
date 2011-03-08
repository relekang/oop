package sokoban2;

public class Direction {

	public final static Direction
		UP = new Direction(0, -1, 'u'),
		DOWN = new Direction(0, 1, 'd'),
		LEFT = new Direction(-1, 0, 'l'),
		RIGHT = new Direction(1, 0, 'r');
	
	public final static Direction[] directions = {
		UP, DOWN, LEFT, RIGHT
	};
	
	public final int dx, dy;
	public final char c;
	
	private Direction(int dx, int dy, char c) {
		this.dx = dx;
		this.dy = dy;
		this.c = c;
	}
	
	public static Direction directionFor(char c) {
		for (int i = 0; i < directions.length; i++) {
			if (directions[i].c == c) {
				return directions[i];
			}
		}
		return null;
	}
}
