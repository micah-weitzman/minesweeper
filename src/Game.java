import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;


public class Game implements Runnable {
	
	public void run() {

		
		// Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("Minesweeper");
        frame.setLocation(400, 160);

        
        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);
        
        
        // Score Board layout
        JButton help = new JButton("Help"); 
        JButton save = new JButton("Save Score"); 
        JButton highScores = new JButton("High Scores");
        
        final ScoreBoard score_board = new ScoreBoard(); 
        frame.add(score_board, BorderLayout.NORTH);
        
        score_board.add(highScores); 
        score_board.add(save); 
        

        score_board.add(new Box.Filler(new Dimension(50,10), new Dimension(50,10), 
        		new Dimension(50,10)));
        score_board.add(score_board.t);
        Dimension fill = new Dimension(50,10);
        score_board.t.setPreferredSize(new Dimension(100, 40));
        score_board.add(new Box.Filler(fill, fill, fill));
        score_board.add(help);



        // Main playing area
        final GameCourt court = new GameCourt(status, score_board);
        frame.add(court, BorderLayout.CENTER);
        court.setLayout(new GridLayout(GameCourt.ARRAY_WIDTH, GameCourt.ARRAY_HEIGHT));
        for (int i = 0; i < GameCourt.ARRAY_WIDTH; i++) {
        	for (int j = 0; j < GameCourt.ARRAY_HEIGHT; j ++) {
        		court.add(court.squareArray[i][j]);
        	}
        }


        // Reset button
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
                score_board.reset(); 
            }
        });
        status_panel.add(reset, BorderLayout.AFTER_LAST_LINE);
        
        ParaseScore parsedScores; 
        try {
			parsedScores = new ParaseScore("files/highscore.txt");

        
        
        // Save button listener 
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (score_board.won) {
                	 String fstName = JOptionPane.showInputDialog("Enter First Name:");
                	 if (fstName != null) {
                		 String lstName = JOptionPane.showInputDialog("Enter Last Name:"); 
                		 if (lstName != null) {
                    		 String age = "" + Integer.parseInt(JOptionPane.showInputDialog("Enter "
                         	 		+ "age \n (From 0 to 99)")); 
                    		 
                         		 System.out.println(""+score_board.time+fstName+lstName+age);
                         		 if (parsedScores.addNewScorer(
                         				 new HighScorer(score_board.time, fstName, lstName, age))) {
                         			 JOptionPane.showMessageDialog(frame, "Your score was "
                         			 		+ "successfully saved"); 
                         		 } else {
                         			 JOptionPane.showMessageDialog(frame, "There was an error "
                         			 		+ "processing your high score. \n Please try again"); 
                         		 }
                		 } else {
                			 JOptionPane.showMessageDialog(frame, "There was an error processing"
                			 		+ " your high score. \n Please try again"); 
                		 }
                	 } else {
                		 JOptionPane.showMessageDialog(frame, "There was an error processing your "
                	        		+ "high score. \n Please try again"); 
                	 }
                }
            }
        });
        
        
     // High Scores button listener 
     		highScores.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                 	JOptionPane.showMessageDialog(frame, 
                 		"High Scores: \n"
                 		+ "1) " + parsedScores.highScorers.get(0).stringScoreBoard()
                 		+ "2) " + parsedScores.highScorers.get(1).stringScoreBoard()
                 		+ "3) " + parsedScores.highScorers.get(2).stringScoreBoard()
                 		+ "4) " + parsedScores.highScorers.get(3).stringScoreBoard()
                 		+ "5) " + parsedScores.highScorers.get(4).stringScoreBoard()
                 		+ "6) " + parsedScores.highScorers.get(5).stringScoreBoard()
                 		+ "7) " + parsedScores.highScorers.get(6).stringScoreBoard()
                 		+ "8) " + parsedScores.highScorers.get(7).stringScoreBoard()
                 		+ "9) " + parsedScores.highScorers.get(8).stringScoreBoard()
                 		+ "10) " + parsedScores.highScorers.get(9).stringScoreBoard()
                 		);
                 }
             });
        
        
		
        } catch (IOException e1) {
        	// TODO Auto-generated catch block
        	e1.printStackTrace();
        } 
    
        
        // Help button listener
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(frame, 
            		"RULES: \n"
            		+ "1) Left click on the blue boxes to reveal what's beneath \n"
            		+ "2) Be sure not to click on a bomb! \n"
            		+ "3) The numbers represent how many of the surrounding 8 boxs are bombs \n"
            		+ "4) You win when you reveal all the non-bomb boxes \n"
            		+ "5) To impove you score, try and complete the puzzle in the quickest time \n"
            		+"6) Pro tip: right click on box that you think might contain a bomb to keep"
            			+ " track of all of them\n"
            		+ "7) Make sure to click \"Save Score\" to save your score to be entered into"
            		+ " the list of high scores\n"
            		+ "8) Click on the \"High Scores\" button to view all the top scorers.");
            }
        });
        
        
        
		
        

        // Put the frame on the screen
        frame.pack();
        frame.setSize(620,640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
       
        frame.setResizable(false); 


	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}

}
