import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The TestCollection class stores all tests in an array, then it allow users to create practice tests and practice them.
 * @author IuliaMurariu
 */

public class TestCollection {

	private final int DEFAULT_CAPACITY = 100;
	
	/**
	 * Array that stores Test objects.
	 */
	private Test[] myTests;
	
	/**
	 * Counts number of Test objects in the array.
	 */
	private int count;
	
	/**
	 * Default constructor that creates an array of a default size.
	 */
	public TestCollection(){
		myTests = new Test[DEFAULT_CAPACITY];
		count = 0;
	}
	
	/**
	 * Second constructor creates an array of custom size.
	 * @param size size of the array
	 */
	public TestCollection(int size){
		myTests = new Test[size];
		count = 0;
	}
	
	/**
	 * isEmpty returns true if there are no tests stored in the array.
	 * @return boolean 
	 */
	public boolean isEmpty(){
		return count ==0;
	}
	
	/**
	 * add adds a new Test object to the Test array.
	 * @param test Test object added to the array
	 */
	public void add(Test test){
		if (count == myTests.length)
			expandCapacity();
		myTests[count] = test;
		count ++;
	}
	
	/**
	 * returnTest returns the Test object at the input position within the array.
	 * @param i Integer representing the location of the Test object in the Test array
	 * @return Test Test stored at the given array indexed position
	 */
	public Test returnTest(int i){
		return myTests[i];
	}
	
	/**
	 * toString returns a numbered list of Test object names contained in the array.
	 * @return result a String containing all the Test object names
	 */
	public String toString(){
		String result = "";
		for(int i = 0; i < count && count !=0; i++){
			result = result + (i+1) + ". " + myTests[i].testName + "\n";
		}
		return result;
	}
	
	/**
	 * expandCapacity a helper method that doubles the array's initial capacity by adding all the Tests to a larger array and replacing the smaller array with the larger one.
	 */
	public void expandCapacity(){
		Test[] larger = new Test[myTests.length*2]; 
		for (int i = 0; i< myTests.length; i++){
			larger[i] = myTests[i];
		}
		myTests = larger;
	}
	
	/**
	 * The main method that allows the user to create new Tests, store them in a TestCollection object and practice each test.
	 * @param args
	 */
	public static void main (String[]args){
		
		try{
			//creating a new TestCollection object to store Test objects
			TestCollection myTests = new TestCollection();
			
			//creates a boolean "quit" that will be the key to exiting the program
			boolean quit = false;
			
			//introducing the user to the program
			System.out.println("Hello there, and welcome to your study tool! " + "\n" + "Use it to write quizzes for yourself, and prep for tests.");
			
			//variable to store user input 
			String input;
			
			//this part of the program runs while the boolean "quit" is false
			while (!quit){
				
				//informing the user how to use the program: type PRACTICE to practice tests, QUIZ to create a new quiz, QUIT to end the program
				System.out.println("To quiz yourself using existing quizzes, type PRACTICE.");
				System.out.println("To create a new quiz, type QUIZ.");
				System.out.println("To quit the program, type QUIT.");
				
				//creating a BufferedReader object to read input
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				//initializing the "input" variable with the user input which is either PRACTICE, QUIZ, or QUIT
				input = br.readLine();
				
				//if QUIT was input, the program will verify that the user wants to quit by having them type YES
				if (input.equals("QUIT")){
					System.out.println("Are you sure you want to exit the program? The following quizzes won't be saved.");
					System.out.println(myTests.toString());
					System.out.println("Type YES to exit, and NO to continue with the program.");
					String answer = br.readLine();
					if (answer.equals("YES"))
						quit = true;
					else if (answer.equals("NO")){
						quit = false;
					}
					else{
						System.out.println("Something was wrong with your input.");
					}
				}
				
				//if QUIZ was input, the user can create a Test
				else if (input.equals("QUIZ")){
					//creating a quiz name
					String name;
					System.out.println("Enter a quiz name:" );
					name = br.readLine();
					//creating a new test object
					Test test = new Test(name);
					//the user is informed that their input has to be SAVE to save the quiz, this will turn the boolean "save" to true
					System.out.println("Once completed type SAVE to save the quiz.");
					boolean save = false;
					
					//while the quiz hasn't been saved, the user can keep creating questions and answers to add to the Test
					while(!save){
						//user is asked to input a question
						System.out.println("Question: ");
						String question = br.readLine();
						
						//after being asked to create a question, if the user enters SAVE, the Test object is completed and added to the TestCollection
						if(question.equals("SAVE")){
							save = true;
							myTests.add(test);
							System.out.println();
							System.out.println(test.testName + " has been saved. View it below.") ;
							System.out.println(test.toString());
							break;
						}
						
						//the user is asked to provide an answer to the question they created 
						System.out.println("Answer: ");
						String answer = br.readLine();
						
						//after being asked to create an answer, if the user enters SAVE, the Test object is completed and added to the TestCollection
						if(answer.equals("SAVE")){
							save = true;
							myTests.add(test);
							System.out.println();
							System.out.println(test.testName + " has been saved. View it below.") ;
							System.out.println(test.toString());
							break;
						}
						
						//if SAVE hasn't been entered as the input for either the question or answer, a new question object is created and added to the Test object
						Question q = new Question(question, answer);
						test.add(q);
					}
				}
				
				//if the user enters PRACTICE, they will be able to practice any of the saved tests 
				else if (input.equals("PRACTICE")){
					//if the TestCollection is empty, the user is informed that there are no saved tests 
					if (myTests.isEmpty()){
						System.out.println("You have no saved tests.");
					}
					//if there are tests in the TestCollection "myTests" the user can go through the Test objects created 
					else{
						//A list of the created tests appears and the user is asked to enter the number beside the test they would like to take
						System.out.println("The following quizzes have been saved:");
						System.out.println(myTests.toString());
						System.out.println("Enter the number of the quiz you'd like to practice.");
						String testNum = br.readLine();
						
						//mechanism to check if the input is an integer
						boolean isInt = true;
						try{
							Integer.parseInt(testNum);
						}
						catch(NumberFormatException e){
							isInt = false;
						}
						
						//if we have verified that a valid integer was entered, it will be stored in a variable
						if (isInt ){
							int intTestNum = Integer.parseInt(testNum);
							
							//if the input number before is a valid test number we continue
							if ( 0 <= intTestNum && intTestNum <=myTests.count){
					
								//retrieving the test from the TestCollection "myTests"
								Test currentTest = myTests.returnTest(intTestNum-1);
					
								//a variable representing the number of times the user gets a question wrong is initialized
								int countWrong = 0;
								
								//going through the Questions stored in the Test object
								for(int i = 0; i < currentTest.size(); i ++){
									//returns a question from the Test
									System.out.println(currentTest.returnQuestion(i));
						
									//user can input an answer
									System.out.println("Answer: ");
									String response = br.readLine();
						
									//boolean that confirms that a correct answer was provided by the user
									boolean done = false;
						
									//storing answer to question in a variable
									String answer = currentTest.returnAnswer(i);
						
									//keeps asking the user to input an answer if an incorrect answer is provided 
									while(!done){
										//if the response is the answer, we are done
										if (response.equals(answer))
											done = true;
										//if the user types SKIP as the answer, it signifies they want to move to the next question, we verify that they are sure they want to move on to the next
										else if (response.equals("SKIP")){
											System.out.println("Are you sure you want to skip this question? \nIt will be marked as incorrect, and we won't be going over it again!");
											System.out.println("Type YES to skip, and NO to try the question again.");
											response = br.readLine();
											if(response.equals("YES")){
												//the skipped questions are the only questions marked as wrong, so we update the "countWrong" variable
												countWrong = countWrong+1;
												done = true;
												break;
											}
										}
										//if an incorrect answer is provided, the question reappears and the user is asked to answer it or type SKIP to move to the next question
										else{
											System.out.println("Oops! That's incorrect. Try again or type SKIP to move to the next question.");
											System.out.println(currentTest.returnQuestion(i));
											System.out.println("Answer: ");
											response = br.readLine();
										}
									}
								}
								//Informs the user that the test is complete, and the number of questions that were answered correctly 
								System.out.println("Congratulations! You have completed " + currentTest.testName + "! ");
								System.out.println("You have answered " + (currentTest.size() - countWrong) + "/" + currentTest.size() + " questions correctly!");
							}
							//if an invalid test number was entered, informs the user
							else{
								System.out.println("That's not a valid quiz number.");
							}
						}
					//if a number was not entered that signifies a test number, this informs the user 
					else{
						System.out.println("You did not input an integer.");
						}
					}
				}
				//informs the user if something is wrong with their input which should be one of QUIT, QUIZ, PRACTICE
				else{
					System.out.println("Something was wrong with your input.");
					System.out.println();
				}
			}
		}
		//catches an IO exception if invalid input is entered 
		catch(IOException e){
			System.out.println(e.getMessage() + "You didn't enter any input!");
		}
		
	}
}
