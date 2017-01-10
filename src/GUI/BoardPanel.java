package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Model.GameState;


public class BoardPanel extends JPanel {

	GameState gameState;
	BufferedImage[] imageBuffer = new BufferedImage[24];//board
	BufferedImage[] imageBuffer2 = new BufferedImage[25];//pieces
	BufferedImage[] imageBuffer3 = new BufferedImage[4];//harmonies
	int[][] tileMap = {
			{0, 0, 0, 0, 0, 0, 1, 12, 3, 3, 3, 18, 1, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 1, 1, 1, 1, 12, 3, 18, 1, 1, 1, 1, 0, 0, 0, 0},
			{0, 0, 0, 1, 1, 1, 1, 1, 1, 15, 1, 1, 1, 1, 1, 1, 0, 0, 0},
			{0, 0, 1, 1, 1, 1, 1, 1, 9, 5, 11, 1, 1, 1, 1, 1, 1, 0, 0},
			{0, 1, 1, 1, 1, 1, 1, 9, 2, 5, 3, 11, 1, 1, 1, 1, 1, 1, 0},
			{0, 1, 1, 1, 1, 1, 9, 2, 2, 5, 3, 3, 11, 1, 1, 1, 1, 1, 0},
			{1, 1, 1, 1, 1, 9, 2, 2, 2, 5, 3, 3, 3, 11, 1, 1, 1, 1, 1},
			{11, 1, 1, 1, 9, 2, 2, 2, 2, 5, 3, 3, 3, 3, 11, 1, 1, 1, 17},
			{3, 11, 1, 9, 2, 2, 2, 2, 2, 5, 3, 3, 3, 3, 3, 11, 1, 17, 3},
			{3, 3, 14, 8, 8, 8, 8, 8, 8, 4, 7, 7, 7, 7, 7, 7, 16, 3, 3},
			{3, 18, 1, 12, 3, 3, 3, 3, 3, 6, 2, 2, 2, 2, 2, 10, 1, 12, 3},
			{18, 1, 1, 1, 12, 3, 3, 3, 3, 6, 2, 2, 2, 2, 10, 1, 1, 1, 12},
			{1, 1, 1, 1, 1, 12, 3, 3, 3, 6, 2, 2, 2, 10, 1, 1, 1, 1, 1},
			{0, 1, 1, 1, 1, 1, 12, 3, 3, 6, 2, 2, 10, 1, 1, 1, 1, 1, 0},
			{0, 1, 1, 1, 1, 1, 1, 12, 3, 6, 2, 10, 1, 1, 1, 1, 1, 1, 0},
			{0, 0, 1, 1, 1, 1, 1, 1, 12, 6, 10, 1, 1, 1, 1, 1, 1, 0, 0},
			{0, 0, 0, 1, 1, 1, 1, 1, 1, 13, 1, 1, 1, 1, 1, 1, 0, 0, 0},
			{0, 0, 0, 0, 1, 1, 1, 1, 17, 3, 11, 1, 1, 1, 1, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 17, 3, 3, 3, 11, 1, 0, 0, 0, 0, 0, 0}};


	public BoardPanel(GameState gs) {
		gameState = gs;
		loadImages();
		setSize(608, 608);
		setBackground(new Color(174, 222, 247));//pale blue
		setLayout(null);
		setDoubleBuffered(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBoard(g);
		drawPieces(g);
		drawHarmonies(g);
	}

	private void drawBoard(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		BufferedImage tile;
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				tile = imageBuffer[tileMap[j][i]];
				g2.drawImage(tile, i * 32, j * 32, 32, 32, null);

			}
		}
		try {
			BufferedImage frame =
					ImageIO.read(getClass().getResource("../images/frame.png"));
			g2.drawImage(frame, 0, 0, 608, 608, null);
		} catch (Exception e) {
		}
	}


	private void drawPieces(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		BufferedImage tile;
		int[][] matrix = gameState.getTileMatrix();
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (matrix[j][i] != -1) {
					try {
						tile = imageBuffer2[matrix[j][i] + 1];
						g2.drawImage(tile, i * 32, j * 32, 32, 32, null);
					} catch (Exception e) {
						System.out.println(matrix[j][i]);
					}
				}


			}
		}
	}

	private void drawHarmonies(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ArrayList<Integer[]> harmonies = gameState.getHarmonies();
		for (int i = 0; i < harmonies.size(); i++) {
			Integer[] harmony = harmonies.get(i);
			BufferedImage token = imageBuffer3[0];
			BufferedImage dot = imageBuffer3[1];
			if (harmony[4] == 2) {
				token = imageBuffer3[2];
				dot = imageBuffer3[3];
			}
			int x = harmony[0];
			int y = harmony[1];

			//draw start tile
			g2.drawImage(token, x * 32, y * 32, 32, 32, null);
			//draw dots between
			if (harmony[5] != 1) {//horizontal
				for (x++; x < harmony[2]; x++) {
					g2.drawImage(dot, x * 32, y * 32, 32, 32, null);
				}
			} else {
				for (y++; y < harmony[3]; y++) {
					g2.drawImage(dot, x * 32, y * 32, 32, 32, null);
				}
			}
			//draw end tile
			g2.drawImage(token, x * 32, y * 32, 32, 32, null);
		}
	}

	private void loadImages() {
		try {
			for (int i = 0; i < 19; i++) {
				imageBuffer[i] =
						ImageIO.read(getClass().getResource("../images/" + i + ".png"));
			}
		} catch (Exception e) {
			System.out.println("error1");
		}


		String[] sprites = {"001", "002", "003", "004", "005", "006", "007",
				"008", "009", "010", "011", "012", "201", "202", "203", "204",
				"205", "206", "207", "208", "209", "210", "211", "212"};

		try {
			imageBuffer2[0] = ImageIO.read(getClass().getResource(
					"../images/pieces/blank.png"));
			for (int i = 1; i < 25; i++) {
				imageBuffer2[i] = ImageIO.read(getClass().getResource(
						"../images/pieces/" + sprites[i - 1] + ".png"));
			}
		} catch (Exception e) {
		}

		String[] sprites2 = {"blue1", "blue2", "magenta1", "magenta2"};

		try {
			for (int i = 0; i < 4; i++) {
				imageBuffer3[i] = ImageIO.read(getClass().getResource(
						"../images/" + sprites2[i] + ".png"));
			}
		} catch (Exception e) {
		}
	}

}
