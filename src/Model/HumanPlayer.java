package Model;

import java.util.Scanner;

public class HumanPlayer extends Player {
	public HumanPlayer(int i) {
		super(i);
	}

	@Override
	public String move() {
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		return str;

	}
}
