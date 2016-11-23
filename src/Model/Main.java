package Model;
import GUI.GameFrame;

public class Main {

    public static void main(String[] args) {
	 
	GameState gameState = new GameState();
	
	GameFrame gui = new GameFrame(gameState);
	
	
	
	Player p1 = new HumanPlayer(1);
	Player p2 = new HumanPlayer(2);
	
	//players 1 select their special tiles
	String selection = "1 "+p1.select();
	p2.update(selection);
	gameState.update(selection);
	//player 2 selects their special tiles
	selection = "2 "+p2.select();
	p1.update(selection);
	gameState.update(selection);
	//NOTE: whether the player has been given the oppenents selection before
	//theirs is requested can be used to determine player number
	
	String move;
	
	//main game loop
	while (true) {
	    //PLAYER 1s turn
	    move = "1 "+p1.move();
	    p2.update(move);
	    gui.repaint();
	    if (!gameState.update(move)){
		break;}
	    
	    
	    //PLAYER 2s turn
	    move = "2 "+p2.move();
	    p1.update(move);
	    gui.repaint();
	    if (!gameState.update(move)){
		break;}
	}

    }
    

}
