import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class ScoreBoard extends JPanel{
	
	public int time = 0;  
	JLabel t; 
	public boolean playing; 
	public boolean won; 
	
	public ScoreBoard() {
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.t = new JLabel("Time: " + time);
		t.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
		
		
		Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); 
		
		reset(); 
	}
	
	void tick() {
		if (playing && time < 1000) {
			time++; 
			t.setText("Time: " + time);
			t.repaint(); 
		}

	}
	
	public void reset() {
		time = 0; 
		won = false; 

	}
	
	@Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
    }
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 50);
    }
	
}
