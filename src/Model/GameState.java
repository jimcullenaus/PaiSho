package Model;

import java.util.ArrayList;

public class GameState {
	int[][] tileMatrix;
	//list of array[tile1x,tile1y,tile2x, tile2y, player, verticle]
	//tile1 should be higher or lefter than tile2
	ArrayList<Integer[]> harmonies;
	//the banks contains the number of each type of pieces not yet played
	//example bank[1]=3 means non of the 3 1r3 peices have been played yet
	int[] bank;

	public GameState() {
		tileMatrix = new int[19][19];
		harmonies = new ArrayList<Integer[]>();
		Integer[] test = {9, 9, 9, 12, 1, 1};
		harmonies.add(test);
		int[] b = {3, 3, 3, 3, 3, 3, 1, 1, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 1, 1, 0, 0, 0, 0};
		bank = b;

		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				tileMatrix[j][i] = -1;
			}
		}
	}

	/**
	 * @param str, the latest command to process
	 * @return return 0 if no problem, 1 if illegal move,
	 * and 2 if bonus round
	 */
	public int update(String str) {
		String[] split = str.split(" ");
		int player = Integer.parseInt(split[0]);

		//SELECT
		if (split[1].equals("SELECT")) {
			String[] selection = split[2].split(",");
			for (int i = 0; i < 4; i++) {
				int x = Integer.parseInt(selection[i]);
				//8,9,10,11,  21,22,23,24
				x = (player * 12) - 4 + x;
				bank[x]++;
			}
		}
		//PLACE
		if (split[1].equals("PLACE")) {
			String[] s = split[2].split(",");
			int tile = Integer.parseInt(s[0]);
			int i = tile + (player - 1) * 12;
			//removes tile from bank
			if (bank[i] > 0) {
				bank[i]--;
			} else {
				return 1;
			}
			int x = Integer.parseInt(s[1]);
			int y = Integer.parseInt(s[2]);
			tileMatrix[y][x] = tile + (player - 1) * 12;
		}
		//MOVE
		if (split[1].equals("MOVE")) {

			String[] s = split[2].split(",");
			int tile = Integer.parseInt(s[0]);
			int i = tile + (player - 1) * 12;

			int x = Integer.parseInt(s[1]);
			int y = Integer.parseInt(s[2]);
			int destx = Integer.parseInt(s[3]);
			int desty = Integer.parseInt(s[4]);
			tileMatrix[y][x] = -1;
			tileMatrix[desty][destx] = tile + (player - 1) * 12;
		}
		if (updateHarmonies()) {
			return 2;
		}
		return 0;
	}

	/**
	 * same as update but used for bonus rounds so there can't
	 * be additional bonus rounds in one turn
	 */
	public int update(String s, Boolean b) {
		int r = update(s);
		if (r == 2) {
			return 0;
		}
		return r;
	}

	public int[][] getTileMatrix() {
		return tileMatrix;
	}

	public int[] getBank() {
		return bank;
	}

	public ArrayList<Integer[]> getHarmonies() {
		return harmonies;
	}

	/**
	 * Add any new harmonies and removes new invalid harmonies
	 * returns true if there is a change
	 */
	private boolean updateHarmonies() {
		return false;
	}
}
