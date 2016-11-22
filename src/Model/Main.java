package Model;
import GUI.GameFrame;

public class Main {

    public static void main(String[] args) {
	 
	GameState gameState = new GameState();
	
	GameFrame gui = new GameFrame(gameState);
	
	boolean gameRunning = true;
	
	
	Player p1 = new Player();
	Player p2 = new Player();
	
	//both players select their special tiles
	String selection = p1.select();
	p2.update(selection);
	selection = p2.select();
	p1.update(selection);
	gameState.update(selection);
	
	
	String move;
	//main game loop
	while (gameRunning) {
	    move = p1.move();
	    p2.update(move);
	    gameRunning = gameState.update(move);
	    move = p2.move();
	    p1.update(move);
	    gameRunning = gameState.update(move);
	}

    }
    
    private boolean processSelect(String str) {
	String[] splitStr = str.split(",");
	if (splitStr.length!=4) {
	    return false;
	}
	return true;
	
    }

}
