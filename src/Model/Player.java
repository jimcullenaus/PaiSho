package Model;

import java.awt.image.BufferedImage;

public class Player {

	int playerNo;

	public Player(int i) {
		playerNo = i;
	}

	/**
	 * @return "SELECT #,#,#,#"
	 */
	public String select() {
		String response = "SELECT 1,2,2,0";
		return response;
	}

	public void update(String str) {

	}

	public String move() {
		return "NULL";
	}

	public String bonusMove() {
		return "NULL";
	}

}
