package GUI;

import javax.swing.JFrame;

import Model.GameState;

public class GameFrame extends JFrame {

	BoardPanel bp;
	GameState gameState;

	public GameFrame(GameState gs) {

		gameState = gs;
		setSize(1036, 653);
		setVisible(true);
		setTitle("Pai Sho");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		bp = new BoardPanel(gs);
		PlayerPanel pp = new PlayerPanel(gs);

		add(bp);
		add(pp);


		bp.grabFocus();
		bp.requestFocusInWindow();

		revalidate();
		repaint();
	}
}
