public class HighScorer {
	
	public int score; 
	public String firstName; 
	public String lastName;
	public String age; 
	
	public HighScorer(int score, String firstName, String lastName, String age) {
		
		this.score = Math.max(Math.min(999, score), 0);
		this.firstName = firstName; 
		this.lastName = lastName; 
		this.age = age; 
		
	}
	
	public HighScorer() {
		score = 999; 
		firstName = "empty"; 
		lastName = "";
		age = "0";
	}
	
	
	public String stringScoreBoard() {
	
		return "" + score + " "  
			+ firstName + " " 
			+ lastName + " (age:  " 
			+ age + ") \n"; 
	
	}
	
	public String toString() {
		
		String stringOfScore; 
		
		if (score < 10) {
			stringOfScore = "00" + score; 
		} else if (score < 100) {
			stringOfScore = "0" + score; 
		} else {
			stringOfScore = "" + score; 
		}
		
		return stringOfScore + firstName + '`' + lastName + '~'+ age; 
		
	}
	
}
