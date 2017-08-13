/**
 * @author IuliaMurariu
 * The Question class stores questions and their respective answers.
 */

public class Question {
	
	/**
	 * question stores the question 
	 */
	protected String question;
	/**
	 * answer stores the answer to the question
	 */
	protected String answer;
	
	/**
	 * Question takes in two parameters, a question and an answer, and stores them as attributes of the Question object
	 * @param q question 
	 * @param a answer
	 */
	public Question(String q, String a){
		question = q;
		answer = a;
	}
	/**
	 * equalsA checks if the answer is the same String as the input String
	 * @param other possible answer to the question that we are trying to verify
	 * @return boolean true if input is the same as the input String, false otherwise
	 */
	public boolean equalsA (String other){
		return answer.equals(other);
	}
	
	/**
	 * QtoString retrieves the question stored in the Question object
	 * @return question
	 */
	public String QtoString(){
		return question;
	}
	
	/**
	 * AtoString retrieves the answer stored in the Question object
	 * @return answer
	 */
	public String AtoString(){
		return answer;
	}
}
