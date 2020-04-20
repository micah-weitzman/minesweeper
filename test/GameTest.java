import static org.junit.Assert.*;

import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.Test;

public class GameTest {
	
	final JPanel status_panel = new JPanel();
    final JLabel status = new JLabel("Running...");
    final ScoreBoard score_board = new ScoreBoard(); 
	
	GameCourt court = new GameCourt(status, score_board); 
	
	@Test
	public void testParseScore() throws IOException {
	        try {
	        	ParaseScore parsedScores = new ParaseScore("files/test_score.txt");
	        	ParaseScore parsedScores1 = new ParaseScore("files/test_score1.txt");
				assertTrue("Empty age is 0", parsedScores.highScorers.get(0).age.equals("0")); 
				assertTrue("Empty first name", parsedScores.highScorers.get(0).firstName
						.equals("empty"));
				assertTrue("Empty last name", parsedScores.highScorers.get(0).lastName.equals(""));
				assertTrue("Empty score is 999", parsedScores.highScorers.get(0).score == 999);

				assertTrue("Format works properly in reverse", parsedScores.highScorers.get(1).
						toString().equals("045Micah`Weitzman~20")); 
				
				HighScorer p = new HighScorer(80, "test", "Test", "40"); 
				
				assertTrue("New score added", parsedScores1.addNewScorer(p)); 
				
	        
	        } catch (IOException e1) {
	    		// TODO Auto-generated catch block
	    		e1.printStackTrace();
	    	} 

	}
	
	@Test
	public void courtTest() {
		
		assertTrue("Game is playing", court.playing); 
		
		for (int i = 0; i < GameCourt.ARRAY_HEIGHT; i++) {
    		for (int j = 0; j < GameCourt.ARRAY_WIDTH; j++) {
    			if(court.squareArray[i][j].bomb) {
    				court.squareArray[i][j].setSelected(true); 
    			}
    		}
    	}
    		
    	assertFalse("Game stops playing is bomb selected", court.playing); 
    			
		
		
	}
}
