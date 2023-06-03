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
		}else if(chrMenu == 'h' || chrMenu == 'H'){
			// [*****] Finish help option as game goes on.
			con.clear();
			con.println("HELP:");
			con.println("Try playing the game by pressing 'PLAY (p)'!");
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
		
		//[**] Add something where if they choose a quiz not on the list, it tells them to choose again
	
		strQuizName = strQuizName + ".txt";
		//txtQuizzes = new TextInputFile(strQuiz);
		
		String strQuiz[][];
		int intCount = countEntries();
		//while(!txtQuizzes.eof()){
			//strQuizList = txtQuizzes.readLine();
			//con.println(strQuizList);
			//// Making sure that it only prints out question and not answers
			//strQuizList = txtQuizzes.readLine();
			//strQuizList = txtQuizzes.readLine();
			//trQuizList = txtQuizzes.readLine();
		//}	
	}
	public static int countEntries(){
		//TextInputFile
		return 0;
	}
			
			
	
}
