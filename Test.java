/**
 * @author IuliaMurariu
 * The Test class stores a number of Questions in an array. 
 */
public class Test {

	private final int DEFAULT_CAPACITY = 100;
	/**
	 * qList is an array that stores a list of Question objects
	 */
	protected Question [] qList;
	/**
	 * count keeps track of the number of Question objects in the array
	 */
	private int count;
	/**
	 * stores the name of the Test
	 */
	protected String testName;
	
	/**
	 * Default constructor that creates a Question array of a default capacity of 100.
	 * @param name name of the test
	 */
	public Test (String name){
		testName = name;
		qList = new Question [DEFAULT_CAPACITY];
		count = 0;
	}
	
	/**
	 * Constructor that crates a Question array of a custom capacity.
	 * @param newCapacity a custom capacity 
	 * @param name a test name
	 */
	public Test (int newCapacity, String name){
		testName = name;
		qList = new Question [newCapacity];
		count = 0;
	}
	
	/**
	 * add adds a new Question to the end of the Question array
	 * @param q the entered question
	 */
	public void add(Question q){
		if (count == qList.length)
			expandCapacity();
		qList[count] = q;
		count ++;
	}
	
	/**
	 * returnQuestion retrieves a question at a given index
	 * @param i index of the desired question
	 * @return question the Question at the input index
	 */
	public String returnQuestion(int i){
		return qList[i].QtoString();
	}
	
	/**
	 * returnAnswer retrieves an answer at a given index
	 * @param i index of the desired answer
	 * @return answer at the input index
	 */
	public String returnAnswer(int i){
		return qList[i].AtoString();
	}
	
	/**
	 * size gives the count of the number of questions in the array
	 * @return count the number of things in the array
	 */
	public int size(){
		return count;
	}
	
	/**
	 * isEmpty returns true if the array is empty, and false if it's not
	 * @return boolean true or false
	 */
	public boolean isEmpty(){
		return count == 0;
	}
	
	/**
	 * toString returns a list of questions and answers from the test array
	 * @return result the resulting String which lists the questions and answers
	 */
	public String toString(){
		String result = "";
		for (int i = 0; i<count && !isEmpty(); i++){
			result = result + (i+1) + ". " + qList[i].QtoString() + "\n" + "Answer: " + qList[i].AtoString()+"\n";
		}
		return result;
	}
	
	/**
	 * expandCapacity is a helper method that creates a larger array if the existing one is full of Questions
	 */
	public void expandCapacity(){
		Question[] larger = new Question[qList.length*2]; 
		for (int i = 0; i< qList.length; i++){
			larger[i] = qList[i];
		}
		qList = larger;
	}
	
}
