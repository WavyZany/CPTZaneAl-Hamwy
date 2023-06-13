import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class mathtraininggame{
	public static void main(String[] args){
		Console con = new Console("Math Training Game", 1280, 720);
		mathMenu(con);
		
		// make sure to follow variable names of User interface design
		
	}	// CUT OFF
	
	// MAIN MENU METHOD
	public static void mathMenu(Console con){
		BufferedImage imgBackground = con.loadImage("background.jpg");
		con.drawImage(imgBackground, 0, 0);
		BufferedImage imgLogo = con.loadImage("logo.png");
		con.drawImage(imgLogo, 1045, 0);
		con.repaint();
		
		con.println("PLAY (P)");
		con.println("SCOREBOARD (S)");
		con.println("HELP (H)");
		char chrMenu = con.getChar();
		
		//  [**] put a loop if they do not choose any option, than it loops back until they choose one
		if(chrMenu == 'p' || chrMenu == 'P'){
			playMenu(con);
		}else if(chrMenu == 's' || chrMenu == 'S'){
			con.clear();
			viewScoreboard(con);
			// values not printing????
		}else if(chrMenu == 'h' || chrMenu == 'H'){
			
			con.clear();
			con.println("HELP:");
			con.println("Try playing the game by pressing 'PLAY (p)' in the menu");
			con.println("First enter your username you want displayed");
			con.println("Then type in the name of a quiz you would like to play");
			con.println("Make sure the quiz name is spelled correctly (including cases)");
			con.println("You will be given a question. Try to solve it!");
			con.println("Then enter your answer into the quiz");
			con.println("There will always be three possible answers");
			con.println("Good luck!");
			con.println("Press any key to go back to the main menu");

			char chrBack = con.getChar();
			if(chrBack == 'a'){
				con.clear();
				mathMenu(con);
			}else{
				con.clear();
				mathMenu(con);
			}
			
		}else{
			con.println("Invalid option");
			con.println("Choose again");	
			con.sleep(1000);
			con.clear();
			mathMenu(con);
		}
	
	}
	public static void playMenu(Console con){
		con.clear();
		con.println("What is you username?");
		String strUser = con.readLine();
		
		
		con.clear();
		TextInputFile txtQuizzes = new TextInputFile("quizzes.txt");
		con.println("OPTIONS:");
		
		String strQuizList;
		while(!txtQuizzes.eof()){
			strQuizList = txtQuizzes.readLine();
			con.println(strQuizList);
		}
		txtQuizzes.close();
		
		con.println("\nPlease type in a quiz name");
		String strQuizName = con.readLine();
		con.clear();
		
		con.setDrawColor(Color.BLACK);
		con.fillRect(0,0,1280,720);
		con.repaint();
		
		//[**] Add something where if they choose a quiz not on the list, it tells them to choose again
	
		strQuizName = strQuizName + ".txt";
		TextInputFile txtQuizInitialization = new TextInputFile(strQuizName);
		
		String strQuiz[][];
		int intCount = countEntries(strQuizName);
		strQuiz = loadQuestions(intCount, strQuizName);
		printQuestions(strQuiz, intCount, strUser, strQuizName, con);

	}
	public static int countEntries(String strQuizName){
		TextInputFile txtEntry = new TextInputFile(strQuizName);
		int intLines = 0;
		String strData;
		while(!txtEntry.eof()){
			strData = txtEntry.readLine();
			intLines = intLines + 1;
		}
		txtEntry.close();
		return intLines / 4;
		
	}
	public static String[][] loadQuestions(int intCount, String strQuizName){
		String strOrder[][] = new String[intCount][4];
		TextInputFile txtEntry = new TextInputFile(strQuizName);
		int intRow;
		for(intRow = 0; intRow < intCount; intRow++){
			strOrder[intRow][0] = txtEntry.readLine();
			strOrder[intRow][1] = txtEntry.readLine();
			strOrder[intRow][2] = txtEntry.readLine();
			strOrder[intRow][3] = txtEntry.readLine();
		}
		txtEntry.close();
		return strOrder;
		
	}
	public static void printQuestions(String strQuiz[][], int intCount, String strUser, String strQuizName, Console con){
		int intRow;
		String strAnswer;
		
		double dblScore = 0;
		double dblQuestions = 0;
		double dblPercent = 0;
		
		for(intRow = 0; intRow < intCount; intRow++){	
			con.println(strUser+"						"+strQuizName+"		"+"SCORE:"+dblScore+"/"+dblQuestions+"	Percent:"+dblPercent+"%");
			con.println("\n\n\n\n");
			con.println(strQuiz[intRow][0]);
			con.println("[TYPE ANSWER BELOW]");
			strAnswer = con.readLine();
			if(strAnswer.equals(strQuiz[intRow][1]) || strAnswer.equals(strQuiz[intRow][2]) || strAnswer.equals(strQuiz[intRow][3])){
				con.println("CORRECT");
				//con.sleep(1000);
				dblScore++;
				dblQuestions++;
				con.clear();
				dblPercent = dblScore / dblQuestions * 100.0;
			}else{
				con.println("INCORRECT");
				//con.sleep(1000);
				dblQuestions++;
				con.clear();
				dblPercent = dblScore / dblQuestions * 100.0;
			}
		}
		
		// Print player score to scoreboard file
		con.println("Printing PERCENT to scoreboard");
		TextOutputFile txtScoreboard = new TextOutputFile("scoreboard.txt",true);
		txtScoreboard.println(strUser);
		txtScoreboard.println(dblPercent);
		txtScoreboard.close();
		con.sleep(1000);
		con.clear();
		
		viewScoreboard(con);
		/* PUTTING ALL THIS IN A METHOD
		// Get number of rows in the scoreboard file
		int intScoreboardRows = 0;
		TextInputFile txtScoreboard2 = new TextInputFile("scoreboard.txt");
		while(!txtScoreboard2.eof()){
			String strReadLine = txtScoreboard2.readLine();
			intScoreboardRows++;
		}
		txtScoreboard2.close();

		// Read the scoreboard into array
		int intPlayerScores = intScoreboardRows / 2;
		int intPlayerScores2 = intPlayerScores; 		
		

		TextInputFile txtScoreboard3 = new TextInputFile("scoreboard.txt");
		String strScoreboard[][] = new String[intPlayerScores][2];		
		String strScoreboardName;
		String strPercentScore;
		while(!txtScoreboard3.eof()){
			System.out.println("Test1");
			strScoreboardName = txtScoreboard3.readLine();
			strPercentScore = txtScoreboard3.readLine();
			System.out.println("intPlayerScores=" + intPlayerScores + "|name=" + strScoreboardName +  "|score=" + strPercentScore);
			strScoreboard[intPlayerScores -1][0] = strScoreboardName;
			strScoreboard[intPlayerScores -1][1] = strPercentScore;
			intPlayerScores--;
		}
		txtScoreboard3.close();
				
		// Print the scoreboard array
		System.out.println("READ SCOREBOARD");
		for (int intReadScoreboard = 0; intReadScoreboard < intPlayerScores2; intReadScoreboard++) {
			System.out.print("strScoreboard[" + intReadScoreboard + "]=[0]=" + strScoreboard[intReadScoreboard][0] + "  ");
			System.out.println("strScoreboard[" + intReadScoreboard + "]=[1]=" + strScoreboard[intReadScoreboard][1] + "  ");
		}
		
		// Bubble sort it in the array itself
		double dblBelow;
		double dblCurrent;
		int intCounter;
		int intCounter2;
		String strTemp;
		
		for(intCounter2 = 0; intCounter2 < intPlayerScores2 - 1; intCounter2++){
			
			for(intCounter = 0; intCounter < intPlayerScores2 - intCounter2 -1; intCounter++){
				
				System.out.println("intCounter2["+intCounter2+"] intCounter["+intCounter+"]");
				
				dblBelow = Double.parseDouble(strScoreboard[intCounter+1][1]);
				dblCurrent = Double.parseDouble(strScoreboard[intCounter][1]);
				
				System.out.println("doubleBelow("+dblBelow+") doubleCurrent("+dblCurrent+")");
				
				if(dblBelow > dblCurrent){
					strTemp = strScoreboard[intCounter+1][1];
					strScoreboard[intCounter+1][1] = strScoreboard[intCounter][1];
					strScoreboard[intCounter][1] = strTemp;
					

					strTemp = strScoreboard[intCounter+1][0];
					strScoreboard[intCounter+1][0] = strScoreboard[intCounter][0];
					strScoreboard[intCounter][0] = strTemp;
				}
			}
		}
		
		// Print the scoreboard array
		System.out.println("SORTED SCOREBOARD");
		for (int intReadScoreboard = 0; intReadScoreboard < intPlayerScores2; intReadScoreboard++) {
			System.out.print("strScoreboard[" + intReadScoreboard + "]=[0]=" + strScoreboard[intReadScoreboard][0] + "  ");
			System.out.println("strScoreboard[" + intReadScoreboard + "]=[1]=" + strScoreboard[intReadScoreboard][1] + "  ");
		}
		
		int intCounting = 0;
		while(intCounting < intPlayerScores2){
			con.println(strScoreboard[intCounting][0]);
			con.println(strScoreboard[intCounting][1]);
			intCounting++;
		}
		
		
	*/	
	}
	public static void viewScoreboard(Console con){
		
		// Get number of rows in the scoreboard file
		int intScoreboardRows = 0;
		TextInputFile txtScoreboard2 = new TextInputFile("scoreboard.txt");
		while(!txtScoreboard2.eof()){
			// Make sure there is no extra line at the top of the file 
			String strReadLine = txtScoreboard2.readLine();
			intScoreboardRows++;
			System.out.println(intScoreboardRows);
		}
		txtScoreboard2.close();

		// Read the scoreboard into array
		int intPlayerScores = intScoreboardRows / 2;
		int intPlayerScores2 = intPlayerScores; 		
		

		TextInputFile txtScoreboard3 = new TextInputFile("scoreboard.txt");
		String strScoreboard[][] = new String[intPlayerScores][2];		
		String strScoreboardName;
		String strPercentScore;
		while(!txtScoreboard3.eof()){
			strScoreboardName = txtScoreboard3.readLine();
			strPercentScore = txtScoreboard3.readLine();
			System.out.println("intPlayerScores=" + intPlayerScores + "|name=" + strScoreboardName +  "|score=" + strPercentScore);
			strScoreboard[intPlayerScores -1][0] = strScoreboardName;
			strScoreboard[intPlayerScores -1][1] = strPercentScore;
			intPlayerScores--;
		}
		txtScoreboard3.close();
				
		// Print the scoreboard array
		System.out.println("READ SCOREBOARD");
		for (int intReadScoreboard = 0; intReadScoreboard < intPlayerScores2; intReadScoreboard++) {
			System.out.print("strScoreboard[" + intReadScoreboard + "]=[0]=" + strScoreboard[intReadScoreboard][0] + "  ");
			System.out.println("strScoreboard[" + intReadScoreboard + "]=[1]=" + strScoreboard[intReadScoreboard][1] + "  ");
		}
		
		// Bubble sort it in the array itself
		double dblBelow;
		double dblCurrent;
		int intCounter;
		int intCounter2;
		String strTemp;
		
		for(intCounter2 = 0; intCounter2 < intPlayerScores2 - 1; intCounter2++){
			
			for(intCounter = 0; intCounter < intPlayerScores2 - intCounter2 -1; intCounter++){
				
				System.out.println("intCounter2["+intCounter2+"] intCounter["+intCounter+"]");
				
				dblBelow = Double.parseDouble(strScoreboard[intCounter+1][1]);
				dblCurrent = Double.parseDouble(strScoreboard[intCounter][1]);
				
				System.out.println("doubleBelow("+dblBelow+") doubleCurrent("+dblCurrent+")");
				
				if(dblBelow > dblCurrent){
					strTemp = strScoreboard[intCounter+1][1];
					strScoreboard[intCounter+1][1] = strScoreboard[intCounter][1];
					strScoreboard[intCounter][1] = strTemp;
					

					strTemp = strScoreboard[intCounter+1][0];
					strScoreboard[intCounter+1][0] = strScoreboard[intCounter][0];
					strScoreboard[intCounter][0] = strTemp;
				}
			}
		}
		
		// Print the scoreboard array
		System.out.println("SORTED SCOREBOARD");
		for (int intReadScoreboard = 0; intReadScoreboard < intPlayerScores2; intReadScoreboard++) {
			System.out.print("strScoreboard[" + intReadScoreboard + "]=[0]=" + strScoreboard[intReadScoreboard][0] + "  ");
			System.out.println("strScoreboard[" + intReadScoreboard + "]=[1]=" + strScoreboard[intReadScoreboard][1] + "  ");
		}
		
		
		int intCounting = 0;
		while(intCounting < intPlayerScores2){
			con.println(strScoreboard[intCounting][0]);
			con.println(strScoreboard[intCounting][1]);
			intCounting++;
		}
		
		con.println("Press any key to go back");
		char chrScoreboardBack = con.getChar();
		
		// BE AWARE - We need to make a counter so it knows if they completed atleast one quiz, it goes to mathMenu2 and not just mathMenu
		if(chrScoreboardBack == 'a'){
			con.clear();
			mathMenu2(con);
		}else{
			con.clear();
			mathMenu2(con);
		}
		
	}
	public static void mathMenu2(Console con){
		BufferedImage imgBackground = con.loadImage("background.jpg");
		con.drawImage(imgBackground, 0, 0);
		BufferedImage imgLogo = con.loadImage("logo.png");
		con.drawImage(imgLogo, 1045, 0);
		con.repaint();
		
		con.println("PLAY (P)");
		con.println("SCOREBOARD (S)");
		con.println("HELP (H)");
		con.println("ADD QUIZ (Q)");
		char chrMenu = con.getChar();
		
		if(chrMenu == 'p' || chrMenu == 'P'){
			playMenu(con);
		}else if(chrMenu == 's' || chrMenu == 'S'){
			viewScoreboard(con);
		}else if(chrMenu == 'h' || chrMenu == 'H'){
			
			con.clear();
			con.println("HELP:");
			con.println("Try playing the game by pressing 'PLAY (p)' in the menu");
			con.println("First enter your username you want displayed");
			con.println("Then type in the name of a quiz you would like to play");
			con.println("Make sure the quiz name is spelled correctly (including cases)");
			con.println("You will be given a question. Try to solve it!");
			con.println("Then enter your answer into the quiz");
			con.println("There will always be three possible answers");
			con.println("Good luck!");
			con.println("Press any key to go back to the main menu");

			char chrBack = con.getChar();
			if(chrBack == 'a'){
				con.clear();
				mathMenu2(con);
			}else{
				con.clear();
				mathMenu2(con);
			}
			
		}else if(chrMenu == 'q' || chrMenu == 'q'){
			con.clear();
			con.println("What is the name of your quiz?");
			String strAddQuizName = con.readLine();
			
			TextOutputFile txtAddQuiz = new TextOutputFile("quizzes.txt",true);
			txtAddQuiz.println(strAddQuizName);
			txtAddQuiz.close();
			
			
			con.clear();
			
			con.println("What is the question?");
			String strFirstQuestion = con.readLine();
			con.println("Type in three answers below:");
			String strAnswer1 = con.readLine();
			String strAnswer2 = con.readLine();
			String strAnswer3 = con.readLine();
			
			TextOutputFile txtNewQuiz = new TextOutputFile(strAddQuizName+".txt");
			txtNewQuiz.println(strFirstQuestion);
			txtNewQuiz.println(strAnswer1);
			txtNewQuiz.println(strAnswer2);
			txtNewQuiz.println(strAnswer3);
			
			
			String strOtherQuestion = "";
			
			while(!strOtherQuestion.equalsIgnoreCase("NO")){
				con.println("Enter another question? Type 'NO' to create the quiz");
				// [***] check what happens if you enter NO first time around
				strOtherQuestion = con.readLine();
				
				if(strOtherQuestion.equalsIgnoreCase("NO")){
					con.println("Created quiz successfully!");
					con.sleep(1000);
					con.clear();
				}else{
					
				
					con.println("Type in three answers below:");
					strAnswer1 = con.readLine();
					strAnswer2 = con.readLine();
					strAnswer3 = con.readLine();
					
					txtNewQuiz.println(strOtherQuestion);
					txtNewQuiz.println(strAnswer1);
					txtNewQuiz.println(strAnswer2);
					txtNewQuiz.println(strAnswer3);
					
					con.clear();
				}
				
			}
			
			txtNewQuiz.close();
			
			mathMenu2(con);
			
			
		}else{
			con.println("Invalid option");
			con.println("Choose again");	
			con.sleep(1000);
			con.clear();
			mathMenu2(con);
		}
		
	
	}
	
		
			
		
		//mainMenuAdd(con);

}

	
		
		
			
			
	

