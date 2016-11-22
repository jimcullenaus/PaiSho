package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class BoardPanel extends JPanel{
    
    BufferedImage[] imageBuffer = new BufferedImage[24];
    int[][] tileMap = {
	    {0,0,0,0,0,0,1,12,3,3,3,18,1,0,0,0,0,0,0},
	    {0,0,0,0,1,1,1,1,12,3,18,1,1,1,1,0,0,0,0},
	    {0,0,0,1,1,1,1,1,1,15,1,1,1,1,1,1,0,0,0},
	    {0,0,1,1,1,1,1,1,9,5,11,1,1,1,1,1,1,0,0},
	    {0,1,1,1,1,1,1,9,2,5,3,11,1,1,1,1,1,1,0},
	    {0,1,1,1,1,1,9,2,2,5,3,3,11,1,1,1,1,1,0},
	    {1,1,1,1,1,9,2,2,2,5,3,3,3,11,1,1,1,1,1},
	    {11,1,1,1,9,2,2,2,2,5,3,3,3,3,11,1,1,1,17},
	    {3,11,1,9,2,2,2,2,2,5,3,3,3,3,3,11,1,17,3},
	    {3,3,14,8,8,8,8,8,8,4,7,7,7,7,7,7,16,3,3},
	    {3,18,1,12,3,3,3,3,3,6,2,2,2,2,2,10,1,12,3},
	    {18,1,1,1,12,3,3,3,3,6,2,2,2,2,10,1,1,1,12},
	    {1,1,1,1,1,12,3,3,3,6,2,2,2,10,1,1,1,1,1},
	    {0,1,1,1,1,1,12,3,3,6,2,2,10,1,1,1,1,1,0},
	    {0,1,1,1,1,1,1,12,3,6,2,10,1,1,1,1,1,1,0},
	    {0,0,1,1,1,1,1,1,12,6,10,1,1,1,1,1,1,0,0},
	    {0,0,0,1,1,1,1,1,1,13,1,1,1,1,1,1,0,0,0},
	    {0,0,0,0,1,1,1,1,17,3,11,1,1,1,1,0,0,0,0},
	    {0,0,0,0,0,0,1,17,3,3,3,11,1,0,0,0,0,0,0}};
    
    
    public BoardPanel() {
	loadImages();
	setSize(608, 608);
	setBackground(new Color (174, 222, 247));//pale blue
	setLayout(null);
	setDoubleBuffered(true);
    }
    
    @Override
    protected void paintComponent(Graphics g){
	super.paintComponent(g);
	drawBoard(g);
	drawPieces();
    }
    
    private void drawBoard(Graphics g){
	Graphics2D g2 = (Graphics2D)g;
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	BufferedImage tile;
	for (int i=0; i<19; i++) {
	    for (int j=0; j<19; j++) {
		tile = imageBuffer[tileMap[j][i]];
		g2.drawImage(tile, i*32, j*32, 32, 32, null);
		 
	    }
	}
	try{
	BufferedImage frame =
		ImageIO.read(getClass().getResource("../images/frame.png"));
		g2.drawImage(frame, 0, 0, 608, 608, null);
	}catch (Exception e){}
    }
    private void drawPieces(){
	
    }
    private void loadImages() {
	try{
	for (int i=0; i<19; i++) {
	    imageBuffer[i] = 
		    ImageIO.read(getClass().getResource("../images/"+i+".png"));
	}
	} catch (Exception e) {
	    System.out.println("error1");
	}
    }

}
