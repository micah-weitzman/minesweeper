import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;



@SuppressWarnings("serial")
public class NewSquare extends JToggleButton {
	
	public final boolean bomb;
	
	public Color c;
	
	public final int numOfBombs;
	
	public final int x; 
	public final int y; 
	
	private BufferedImage img; 

	public NewSquare (Color c, boolean bomb, int numOfBombs, int x, int y) {
		super();  
		
		this.c = c;
		this.bomb = bomb;

		
		this.numOfBombs = numOfBombs; 
		
		this.x = x;
		this.y = y; 

	}

	
	@Override
	public void paint (Graphics g) {
		
		super.paint(g);
	}
	
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (isSelected() && bomb) {
        	try {
                if (img == null) {
                    img = ImageIO.read(new File("files/bomb.jpg"));
                    setIcon(new ImageIcon(img)); 
                }
            } catch (IOException e) {
                System.out.println("Internal Error:" + e.getMessage());
            }
        	
        	
        } else  if (isSelected()) {
	        g.setColor(new Color(180, 180, 180));
	        g.fillRect(0, 0, getSize().width - 1, getSize().height - 1);
	        if (numOfBombs > 0) {
		        switch(numOfBombs) {
		        	case 1: c = Color.BLUE; break;
		        	case 2: c = new Color(50,100,20); break;
		        	case 3: c = new Color(230, 40, 25); break;
		        	case 4: c = new Color(2, 0, 100); break;
		        	case 5: c = new Color(102, 0, 1); break;
		        	case 6: c = new Color(20, 110, 110); break;
		        	case 7: c = new Color(112, 0, 112); break;
		        	case 8: c = new Color(110, 110, 110); break;
		        	default: c = new Color(180, 180, 180); break;
		        }
		        
		        g.setFont(new Font("MONOSPACED", Font.PLAIN, 25)); 
		        g.setColor(c);
		        g.drawString(""+ numOfBombs, 8,22); 
	        }
        } else {
			g.setColor(c); 
			g.fillRect(0, 0, getSize().width - 1, getSize().height - 1);
			g.setColor(new Color(65, 90, 205));
			g.fillRect(2, 2, getSize().width - 5, getSize().height - 5);
			
		}   
    }
	
}
