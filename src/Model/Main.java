package Model;
import GUI.GameFrame;

public class Main {

    public static void main(String[] args) {
	 
	GameState gameState = new GameState();
	
	GameFrame gui = new GameFrame(gameState);
	
	
	int pnum;
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
	String bonusMove;
	pnum=1;
	
	//main game loop
	while (true) {
	    
	    
	    if (pnum ==1){
		    move = "1 "+p1.move();
		}else{
		    move = "2 "+p2.move();
		}
	    p1.update(move);
	    p2.update(move);
	    int status = gameState.update(move);
	    //if illegal move break game
	    if (status == 1){
		System.out.println("Broken");
		break;
	    }
	    gui.repaint();
	    //if the player has earn't a bonus move
	    if (status==2){
		if (pnum ==1){
		    move = "1 "+p1.bonusMove();
		}else{
		    move = "2 "+p2.bonusMove();
		}
		p1.update(move);
		p2.update(move);
		status = gameState.update(move,true);
		    //if illegal move break game
		    if (status== 1){
			System.out.println("Broken");
			break;
		    }
		gui.repaint();
	    }
	    
	    if (pnum==1){
		pnum = 2;
	    } else {
		pnum =1;
	    }
	    
	    
	}

    }
    

}
