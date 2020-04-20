import java.io.*;
import java.util.LinkedList;

public class ParaseScore {
	
	public LinkedList<HighScorer> highScorers = new LinkedList<HighScorer>(); 
	
	private final int USER_TO_DISPLAY = 10; 
	
	private String file; 

	public ParaseScore(String file) throws IOException {
		this.file = file; 
		
		try {

	        BufferedReader buffRead = new BufferedReader(new FileReader(file));

	        String line = buffRead.readLine(); 
	        
			int playerNumber = 0; 
	        
			while(line != null || playerNumber < USER_TO_DISPLAY) {
				
		        
		       if (line.equals("empty")) {
		        	highScorers.add(new HighScorer());
		        	playerNumber++; 

		       }else if (line.indexOf('`') == 0 || line.indexOf('`') == (line.length()-1) || 
		        		!line.contains("`") || !line.contains("~")) {
					buffRead.close();
					throw new IOException(line); 
				}  else {
		        	String firstName = line.substring(3, line.indexOf('`'));
		        	String lastName = line.substring(line.indexOf('`')+1, line.indexOf('~'));
		        	String age = line.substring(line.indexOf('~')+1);
		        	int score = Integer.parseInt(line.substring(0,3));
		        	
		        	HighScorer player = new HighScorer(score, firstName, lastName, age); 
		        	highScorers.add(player); 
		        	playerNumber++; 
		        }
	            line = buffRead.readLine();
	            
	        }
			buffRead.close(); 

	    } catch (FileNotFoundException ex) {
	        ex.printStackTrace();
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
		
	}
	
	public boolean addNewScorer(HighScorer p) {
	
		int i = 0; 
		while(i < USER_TO_DISPLAY) {	
			if (p.score < highScorers.get(i).score) {
				highScorers.add(i, p);
				highScorers.pollLast();
				
				BufferedWriter writer;
				try {
					writer = new BufferedWriter(new FileWriter(file));
					
					for (int j = 0; j < USER_TO_DISPLAY; j++) {
				    	writer.write( highScorers.get(j).toString() + "\n");
					}
				    writer.close();
				    return true; 
					
				} catch (IOException e) {
					return false; 
				}
			} else {
				i++;
			}
		}
		return false; 
	}
	
}
