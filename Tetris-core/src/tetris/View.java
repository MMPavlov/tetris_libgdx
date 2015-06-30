package tetris;

import java.util.Random;

public class View {
	
	final static int BOX_SIZE = 30;
	final static int ORIGIN_X = 50;
	final static int ORIGIN_Y = 50;
	static Random r = new Random();
	
	private Graphics _graphics;
	static int rndColor() {
		return (int)(Math.abs(r.nextInt())%7+1);
	}
	
	public void setGraphics(final Graphics graphics) {
		_graphics = graphics;
	}

	public void draw(final State state) {
		drawField(state.getField().getData());
		drawFigure(state.getFigure().getData(), state.row, state.col);
	}

	private void drawFigure(final int[][] data, final int row, final int col) {
		drawBox(data[0][0], row,  col);
		drawBox(data[1][0], row+1, col);
		drawBox(data[2][0], row+2, col);
	}

	private void drawField(final int[][] data) {
		drawBoxes(data, 0, 0, true);
	}

	private void drawBoxes(final int[][] data, final int row, final int col, final boolean drawBackgound) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (!drawBackgound && data[i][j] == 0) {
					continue;
				}
				drawBox(data[i][j], row + i, col + j);
			}
		}
	}


	protected void drawBox(final int colorIndex, final int row, final int col) {
		_graphics.fillRect(ORIGIN_X + col * BOX_SIZE, ORIGIN_Y + row * BOX_SIZE, BOX_SIZE, BOX_SIZE, colorIndex);
	}

}
