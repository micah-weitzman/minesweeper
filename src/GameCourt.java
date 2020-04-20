import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class GameCourt extends JPanel {

	private JLabel status; // Current status text, i.e. "Running..."

    // Game constants
    public static final int COURT_WIDTH = 400;
    public static final int COURT_HEIGHT = 400;
    
    public static final int ARRAY_WIDTH = 20;
    public static final int ARRAY_HEIGHT = 20; 
    
    public NewSquare[][] squareArray = new  NewSquare[ARRAY_WIDTH][ARRAY_HEIGHT];
    
    private int[][] maskArray = new int[ARRAY_HEIGHT][ARRAY_WIDTH]; 
    
    private ScoreBoard scoreBoard; 
    
    public boolean playing; 


    public GameCourt(JLabel status, ScoreBoard scoreBoard) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.status = status; 
        this.scoreBoard = scoreBoard; 
        
        
       reset(); 
    }
    
    private ItemListener itemListener = new ItemListener() {

    	
        public void itemStateChanged(ItemEvent itemEvent) 
        { 
            int state = itemEvent.getStateChange(); 
            scoreBoard.playing = true; 
    		
            int remaining = 0; 
        	for (int i = 0; i < ARRAY_HEIGHT; i++) {
        		for (int j = 0; j < ARRAY_WIDTH; j++) {
        			if(!squareArray[i][j].isSelected() && !squareArray[i][j].bomb) {
        				remaining++; 
        			}
        		}
        	}
        	if (remaining == 0) {
        		status.setText("YOU WON!!!!!"); 
        		scoreBoard.playing = false; 
        		playing = false; 
        		scoreBoard.won = true; 
        		for (int i = 0; i < ARRAY_HEIGHT; i++) {
            		for (int j = 0; j < ARRAY_WIDTH; j++) {
            			if(squareArray[i][j].bomb) {
            				squareArray[i][j].setEnabled(false); 
            			}
            		}
            	}
        	} 
        	if (state == ItemEvent.SELECTED) { 
                NewSquare ns = (NewSquare) itemEvent.getItem();
                if (ns.numOfBombs == 0 && !ns.bomb) {
                	revealTheRest(ns.x, ns.y); 	
                }else if (ns.bomb) {
                	for (int i = 0; i < ARRAY_HEIGHT; i++) {
                		for (int j = 0; j < ARRAY_WIDTH; j++) {
                			if(squareArray[i][j].bomb) {
                				squareArray[i][j].setSelected(true); 
                			} else {
                				squareArray[i][j].setEnabled(false);
                			}
                		}
                	}
                	status.setText("You lost :-("); 
                	scoreBoard.playing = false; 
                	playing = false; 
                }
            } 
            else { 
                NewSquare ns = (NewSquare) itemEvent.getItem();
                ns.setSelected(true);
            } 
            repaint(); 
        } 
    }; 
    
    private MouseListener mouseListener = new MouseAdapter() {
        public void mouseReleased(MouseEvent mouseEvent) {
          if (SwingUtilities.isRightMouseButton(mouseEvent) && playing) {
        	  NewSquare ns = (NewSquare) mouseEvent.getComponent(); 
        	  ns.c = Color.YELLOW; 
        	  repaint(); 
          }
          System.out.println();
        }
    };
    

    private void revealTheRest(int x, int y) {
    	if (squareArray[x][y].numOfBombs > 0) {
    		squareArray[x][y].setSelected(true);
    	} else {
    		for (int uD = -1; uD < 2; uD ++) {
    			for (int lR = -1; lR < 2; lR++) {
    				int udAdj = x + uD;
    				int lrAdj = y + lR; 
    				
    				if ((udAdj >= 0) && (udAdj < ARRAY_HEIGHT) && (lrAdj >= 0) && 
    						(lrAdj < ARRAY_WIDTH)
    						&& !(uD == 0 && lR == 0)){
    					squareArray[udAdj][lrAdj].setSelected(true); 
    				}
    			}
    		}
    	}
    }
   
    
    private void generateNewField() {
        for (int i = 0; i < ARRAY_WIDTH; i++) {
        	for (int j = 0; j < ARRAY_HEIGHT; j ++) {
        		double randInt = Math.random(); 
        		if (randInt < 0.08) {
        			maskArray[i][j] = 1;
        		} else {
        			maskArray[i][j] = 0; 
        		}
        	}
        }
    }
    
    public void reset() {
    	
    	this.removeAll(); 
    	this.updateUI();
    	status.setText("Running...");
    	this.playing = true; 
    	
    	generateNewField(); 
         
        for (int i = 0; i < ARRAY_WIDTH; i++) {
         	for (int j = 0; j < ARRAY_HEIGHT; j ++) {
         		NewSquare newSquare; 
         		if (maskArray[i][j] == 1) {
         			 newSquare = new NewSquare(Color.BLUE, true, 0, i, j); 
         		} else {
         			
         			int bombsAround = 0; 
         			
         			for (int upDown = -1; upDown < 2; upDown++) {
         				for (int leftRight = -1; leftRight < 2; leftRight++) {
         					int ud = i + upDown;
         					int lr = j + leftRight; 
         					
         					if (ud >= 0 && ud < ARRAY_HEIGHT && lr >= 0 && lr < ARRAY_WIDTH && 
         							maskArray[ud][lr] == 1) {
         						bombsAround++; 
         					}
         				}
         			}
         			
         			 newSquare = new NewSquare(Color.BLUE, false, bombsAround, i, j); 
         		}
     			this.add(newSquare);
         		
                 newSquare.addItemListener(itemListener);
                 newSquare.addMouseListener(mouseListener); 
                 squareArray[i][j] = newSquare;
         	}
         }
    }
   

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}