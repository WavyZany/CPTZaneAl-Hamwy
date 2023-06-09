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
			con.println("scoreboard");
			// bubble sort
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
		
		// we need to bubble sort it in the array itself
		con.println("Printing PERCENT to scoreboard");
		TextOutputFile txtScoreboard = new TextOutputFile("scoreboard.txt",true);
				
		txtScoreboard.println(strUser);
		txtScoreboard.println(dblPercent);
		
		txtScoreboard.close();
		
		
		con.sleep(1000);
		con.clear();
		
		TextInputFile txtScoreboard2 = new TextInputFile("scoreboard.txt");
		
		
		
		while(!txtScoreboard2.eof()){
			String strReadLine = txtScoreboard2.readLine();
			intRow++;
			System.out.println("INSIDE LOOP");
		}
		
		txtScoreboard2.close();
		
		TextInputFile txtScoreboard3 = new TextInputFile("scoreboard.txt");
		
	
		String strScoreboard[][] = new String[intRow][2];
		
		System.out.println("Test");
		
		while(!txtScoreboard3.eof()){
			System.out.println("Test1");
			strScoreboard[intRow -1][0] = txtScoreboard3.readLine();
			strScoreboard[intRow -1][1] = txtScoreboard3.readLine();
		}
		
		
		System.out.println("Test2");
		
		
		int intBelow;
		int intCurrent;
		int intCounter;
		int intCounter2;
		String strTemp;
		
		for(intCounter2 = 0; intCounter2 < intCount - 1; intCounter2++){
			
			for(intCounter = 0; intCounter < intCount- intCounter2 -1; intCounter++){
				
				System.out.println("JIN");
				
				intBelow = Integer.parseInt(strScoreboard[intCounter+1][1]);
				intCurrent = Integer.parseInt(strScoreboard[intCounter][1]);
				
				System.out.println("EDWARD");
				
				if(intBelow > intCurrent){
					strTemp = strScoreboard[intCounter+1][1];
					
					strScoreboard[intCounter+1][1] = strScoreboard[intCounter][1];
					strScoreboard[intCounter][1] = strTemp;
					
					strTemp = strScoreboard[intCounter+1][1];
					strScoreboard[intCounter+1][1] = strScoreboard[intCounter][1];
					strScoreboard[intCounter][1] = strTemp;
					
					strTemp = strScoreboard[intCounter+1][0];
					strScoreboard[intCounter+1][0] = strScoreboard[intCounter][0];
					strScoreboard[intCounter][0] = strTemp;
				}
			}
		}
		
		txtScoreboard2.close();
		
		System.out.println("Edward");
		
		int intCounting = 0;
		while(intCounting < intRow){
			con.println(strScoreboard[intRow][0]);
			con.println(strScoreboard[intRow][1]);
			intCounting++;
			System.out.println("Edward");
		}
		
		
		
	}
		

		
		
			
		
		//mainMenuAdd(con);

}

	
		
		
			
			
	

