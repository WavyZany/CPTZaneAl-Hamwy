import arc.*;
import java.awt.Color;

public class mathtraininggame{
	public static void main(String[] args){
		Console con = new Console("Math Training Game", 1280, 720);
		//mathMenu(con);
		
		char charKey = con.getChar();
		con.println(charKey); // make sure to follow variable names of User interface design
		
	}	// CUT OFF
	
	// MAIN MENU METHOD
	public static void mathMenu(Console con){
		con.setBackgroundColor(Color.WHITE);
		con.setDrawColor(Color.GREEN);
		con.fillRoundRect(500, 260, 300, 200, 70, 70);
		con.setDrawColor(Color.BLACK);
		con.drawString("PLAY", 500, 260);
		
		
		
	}
		
	
}
