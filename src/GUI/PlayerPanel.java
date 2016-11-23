package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Model.GameState;

public class PlayerPanel extends JPanel{
    
    GameState gameState;
    BufferedImage[] imageBuffer;
    int[] bank;
    
    public PlayerPanel(GameState gs){
	imageBuffer = new BufferedImage[25];
	gameState = gs;
	bank = gs.getBank();
	loadImages();
	setSize(100, 304);
	setBackground(new Color (220, 220, 220));
	setLayout(null);
	setDoubleBuffered(true);
	
    }
    
    @Override
    protected void paintComponent(Graphics g){
	super.paintComponent(g);
	drawPieces(g);
    }

    private void drawPieces(Graphics g) {
	
	Graphics2D g2 = (Graphics2D)g;
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	
	BufferedImage tile = imageBuffer[0];
	//draw empty slots top
	for (int i=0;i<6;i++){
	    for (int j=0; j<5;j++){
		g2.drawImage(tile, i*32+700, j*32+10, 32, 32, null);
	    }
	}
	
	for (int i=0;i<6;i++){
	    for (int j=0; j<bank[i];j++){
		g2.drawImage(imageBuffer[i+1], i*32+700, j*32+10, 32, 32, null);
	    }
	}
	for (int i=0;i<6;i++){
	    for (int j=0; j<bank[i+6];j++){
		g2.drawImage(imageBuffer[i+7], i*32+700, j*32+106, 32, 32, null);
	    }
	}
	
	
	//draw empty slots bottom
	for (int i=0;i<6;i++){
	    for (int j=0; j<5;j++){
		g2.drawImage(tile, i*32+700, j*32+310, 32, 32, null);
	    }
	}
	
	for (int i=0;i<6;i++){
	    for (int j=0; j<bank[i+12];j++){
		g2.drawImage(imageBuffer[i+13], i*32+700, j*32+310, 32, 32, null);
	    }
	}
	
	for (int i=0;i<6;i++){
	    for (int j=0; j<bank[i+18];j++){
		g2.drawImage(imageBuffer[i+19], i*32+700, j*32+406, 32, 32, null);
	    }
	}
	
    }
    
    
    private void loadImages(){
	String[] sprites = {"001","002","003","004","005","006","007",
		"008","009","010","011","012","201","202","203","204",
		"205","206","207","208","209","210","211","212"};

	try{
	imageBuffer[0] = ImageIO.read(getClass().getResource(
		"../images/pieces/blank.png"));
	for (int i=1;i<25;i++) {
	    imageBuffer[i] = ImageIO.read(getClass().getResource(
			"../images/pieces/"+sprites[i-1]+".png"));
	}
	}catch (Exception e) {
	}
    }
}
