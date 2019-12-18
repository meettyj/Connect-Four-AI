import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class ConnectFour {

	private List<GameListener> listeners = new ArrayList<GameListener>();
	private static String[][] chessArray = new String[6][7];
	private static Scanner input = new Scanner(System.in);
	private static String initialSpace = "|   ";
	private static String O_player = "| O ";
	private static String X_player = "| X ";
	private static int count; //count of steps
	private static int winAbleColumn;
	private static boolean flagWinAble = false;
	private static int lastStepComputerRow;
	private static int lastStepComputerCoulumn;
	
//	public void increaseCount() {
//		count ++;
//	}
	
	public int getCount() {
		return count;
	}
	
	public void addGameListener(GameListener listener) {
		listeners.add(listener);
	}
	
	public ConnectFour() {
		initialization();
	}
	
	public void initialization() {
		count = 0;
		for(int i = 0;i < chessArray.length;i++)
			for(int j = 0;j < chessArray[0].length;j++)
				chessArray[i][j] = initialSpace;
	}
	
	public void changeGUIButton() {
		fireGameChangeButtont();
	}
	
	public void printWelcome() {
		fireGameStartEvent();
	}
	
	public void printWelcomeComputer() {
		fireGameStartEventComputer();
	}
	
	public void scroll() {
		fireGameScrollEvent();
	}
	
	public static String drawBoard() {
		String outputString = "";
		for(int i = chessArray.length - 1;i >= 0;i--){
			outputString += "- - - - - - - - - - - - - - -\n";
			System.out.println("- - - - - - - - - - - - - - -");
			for(int j = 0;j < chessArray[0].length;j++) {
				outputString += chessArray[i][j];
				System.out.print(chessArray[i][j]);
			}
			outputString += "|\n";
			System.out.println("|");
		}
		outputString += "-----------------------------\n";
		System.out.println("-----------------------------");
		for(int i = 0;i < chessArray[0].length;i++) {
			outputString += "| "+(i+1)+" ";
			System.out.print("| "+(i+1)+" ");
		}
		outputString += "|\n\n";
		System.out.println("|\n");
//		System.out.println("1231231");
//		System.out.println(outputString);
		return outputString;
	}
	
	public static String whosTurn() {
		if (count % 2 == 0) {
			return "Player O please put a chess!\n\n";
		}else {
			return "Player X please put a chess!\n\n";
		}
	}
	
	public static String whosTurnComputer() {
		if (count % 2 == 0) {
			return "Player please put a chess!\n\n";
		}else {
			return "Computer move a step!\n\n";
		}
	}
	
	public static boolean checkAvailable(int row, int column) {
		if (column >= 0 && column <= 6) {// available
			for (row = 0; row < chessArray.length; row++) {
				if (chessArray[row][column] == initialSpace) {
					return true;
				}
			}
			if (row == chessArray.length) {
				System.out.print("The column of you enter is full,"
						+ "please reEnter! : ");
				return false;
			}else {
				return true;
			}
		} else{
			System.out.print("You enter the wrong column,"
					+ "please reEnter! : ");
			return false;
		}
	}
	
	public static boolean checkWin(int row, int column, String whoArePlaying) {
		// check the row	
		if (column <= 3) {
			for (int jj = 0; jj <= column; jj++)
				if (chessArray[row][jj] == whoArePlaying 
				        && chessArray[row][jj + 1] == whoArePlaying
						&& chessArray[row][jj + 2] == whoArePlaying
						&& chessArray[row][jj + 3] == whoArePlaying) 
					return true;
		} 
		else {
			for (int jj = column - 3; jj <= 3; jj++)
				if (chessArray[row][jj] == whoArePlaying 
				        && chessArray[row][jj + 1] == whoArePlaying
						&& chessArray[row][jj + 2] == whoArePlaying
						&& chessArray[row][jj + 3] == whoArePlaying)
					return true;
		}
		
		// check the column
		if (row >= 3) {
			if (chessArray[row][column] == whoArePlaying 
					&& chessArray[row - 1][column] == whoArePlaying
					&& chessArray[row - 2][column] == whoArePlaying
					&& chessArray[row - 3][column] == whoArePlaying)
				return true;
		}

		// check the diagonal
		if(row >= 3){ // first item
			if(column <= 3){// bottom right
				if (checkSameSpot(chessArray[row][column],chessArray[row - 1][column + 1], 
						chessArray[row - 2][column + 2],chessArray[row - 3][column + 3],
						whoArePlaying))
					return true;
			}
			if(column >= 3){ //bottom left
				if (checkSameSpot(chessArray[row][column],chessArray[row - 1][column - 1], 
						chessArray[row - 2][column - 2],chessArray[row - 3][column - 3],
						whoArePlaying))
					return true;
			}
		}
		if (row >= 2 && row<=4) { // second item
			if(column <= 4 && column >= 1){// bottom right
				if (checkSameSpot(chessArray[row+1][column-1],chessArray[row][column], 
						chessArray[row - 1][column + 1], chessArray[row - 2][column + 2],
						whoArePlaying))
					return true;
			}
			if(column >= 2 && column <= 5){ //bottom left
				if (checkSameSpot(chessArray[row+1][column+1],chessArray[row][column], 
						chessArray[row - 1][column - 1], chessArray[row - 2][column - 2],
						whoArePlaying))
					return true;
			}
		}
		if (row >= 1 && row<=3) { // third item
			if(column <= 5 && column >= 2){// bottom right
				if (checkSameSpot(chessArray[row+2][column-2],chessArray[row+1][column-1], 
						chessArray[row][column], chessArray[row - 1][column + 1],
						whoArePlaying))
					return true;
			}
			if(column >= 1 && column <= 4){ //bottom left
				if (checkSameSpot(chessArray[row+2][column+2],chessArray[row+1][column+1], 
						chessArray[row][column], chessArray[row - 1][column - 1],
						whoArePlaying))
					return true;
			}
		}
		if (row >= 0 && row<=2){ // fourth item
			if(column >= 3){// top left
				if (checkSameSpot(chessArray[row+3][column-3],chessArray[row+2][column-2], 
						chessArray[row+1][column-1], chessArray[row][column],
						whoArePlaying))
					return true;
			}
			if(column <= 3){ //top right
				if (checkSameSpot(chessArray[row+3][column+3],chessArray[row+2][column+2], 
						chessArray[row+1][column+1], chessArray[row][column],
						whoArePlaying))
					return true;
			}
		}
		
//		if(row >= 3){ // first spot
//			if(column < 3){// bottom right
//				if (chessArray[row][column] == whoArePlaying 
//						&& chessArray[row - 1][column + 1] == whoArePlaying
//						&& chessArray[row - 2][column + 2] == whoArePlaying
//						&& chessArray[row - 3][column + 3] == whoArePlaying)
//					return true;
//			}
//			else if(column > 3){
//				if (chessArray[row][column] == whoArePlaying 
//						&& chessArray[row - 1][column - 1] == whoArePlaying
//						&& chessArray[row - 2][column - 2] == whoArePlaying
//						&& chessArray[row - 3][column - 3] == whoArePlaying)
//					return true;
//			}
//			else{
//				if ((chessArray[row][column] == whoArePlaying 
//						&& chessArray[row - 1][column + 1] == whoArePlaying
//						&& chessArray[row - 2][column + 2] == whoArePlaying
//						&& chessArray[row - 3][column + 3] == whoArePlaying) 
//						|| (chessArray[row][column] == whoArePlaying 
//						&& chessArray[row - 1][column - 1] == whoArePlaying	
//						&& chessArray[row - 2][column - 2] == whoArePlaying		
//						&& chessArray[row - 3][column - 3] == whoArePlaying))
//					return true;
//			}
//		}
		return false; // No one wins the game.
	}
	
	private static boolean checkSameSpot(String a, String b, String c, String d, String whoArePlaying) {
		if (a == whoArePlaying && b == whoArePlaying 
				&& c == whoArePlaying && d == whoArePlaying) 
			return true;
		return false;
	}
	
	private static int checkAvailableSpot(String a, String b, String c, String d, String whoArePlaying) {
		if (a == initialSpace && b == whoArePlaying 
				&& c == whoArePlaying && d == whoArePlaying) 
			return 1;
		if (a == whoArePlaying && b == initialSpace 
				&& c == whoArePlaying && d == whoArePlaying) 
			return 2;
		if (a == whoArePlaying && b == whoArePlaying 
				&& c == initialSpace && d == whoArePlaying) 
			return 3;
		if (a == whoArePlaying && b == whoArePlaying 
				&& c == whoArePlaying && d == initialSpace) 
			return 4;
		return -1;
	}
	
	public static boolean checkWinAble(int row, int column, String whoArePlaying) {
		// check the row	
		if (column <= 3) {
			for (int jj = 0; jj <= column; jj++) {
				int columnToWin = checkAvailableSpot(chessArray[row][jj],chessArray[row][jj+1], chessArray[row][jj+2],chessArray[row][jj+3],whoArePlaying);
				if(columnToWin !=-1) {
					winAbleColumn = jj+columnToWin-1;
					System.out.println("1-winAbleColumn-1="+winAbleColumn);
					return true;
				}
			}
		} 
		else {
			for (int jj = column - 3; jj <= 3; jj++) {
				int columnToWin = checkAvailableSpot(chessArray[row][jj],chessArray[row][jj+1], chessArray[row][jj+2],chessArray[row][jj+3],whoArePlaying);
				if(columnToWin !=-1) {
					winAbleColumn = jj+columnToWin-1;
					System.out.println("2-winAbleColumn-1="+winAbleColumn);
					return true;
				}
			}
		}
		
		// check the column
		if (row >= 2 && row<=4) {
			if (chessArray[row+1][column] == initialSpace
					&& chessArray[row][column] == whoArePlaying
					&& chessArray[row - 1][column] == whoArePlaying
					&& chessArray[row - 2][column] == whoArePlaying) {
				winAbleColumn = column;
				return true;
			}
		}
		
		// check the diagonal
		if(row >= 3){ // first item
			if(column <= 3){// bottom right
				int indexToWin = checkAvailableSpot(chessArray[row][column],
						chessArray[row-1][column+1],chessArray[row - 2][column +2],
						chessArray[row-3][column+3],whoArePlaying);
				if(indexToWin !=-1) {
					//  indexToWin + x_index = 1 + row
					int x_index = 1+row-indexToWin;
					// indexToWin - y_index = 1 - column
					int y_index = indexToWin-1+column;
					if (x_index == 1 || chessArray[x_index-1][y_index]!=initialSpace) {
						winAbleColumn = y_index;
						System.out.println("Diagonal: 1-winAbleColumn-1="+winAbleColumn);
						return true;
					}
				}
			}
			if(column >= 3){ //bottom left
				int indexToWin = checkAvailableSpot(chessArray[row][column],
						chessArray[row-1][column-1],chessArray[row - 2][column-2],
						chessArray[row-3][column-3],whoArePlaying);
				if(indexToWin !=-1) {
					//  indexToWin + x_index = 1 + row
					int x_index = 1+row-indexToWin;
					// indexToWin + y_index = 1 + column
					int y_index = 1+column-indexToWin;
					if (x_index == 1 || chessArray[x_index-1][y_index]!=initialSpace) {
						winAbleColumn = y_index;
						System.out.println("Diagonal: 1-winAbleColumn-2="+winAbleColumn);
						return true;
					}
				}
			}
		}
		
		
		if(row >= 2 && row<=4){// second item
			if(column <= 4 && column >=1){ // bottom right
				int indexToWin = checkAvailableSpot(chessArray[row+1][column-1],chessArray[row][column],chessArray[row - 1][column + 1],chessArray[row - 2][column + 2],whoArePlaying);
				if(indexToWin !=-1) {
					// indexToWin + x_index = 2 + row
					int x_index = 2+row-indexToWin;
					// indexToWin - y_index = 2 - column
					int y_index = indexToWin-2+column;
					if (x_index == 1 || chessArray[x_index-1][y_index]!=initialSpace) {
						winAbleColumn = y_index;
						System.out.println("Diagonal: 2-winAbleColumn-1="+winAbleColumn);
						return true;
					}
				}
			}
			if(column <= 5 && column >=2){ //bottom left 
				int indexToWin = checkAvailableSpot(chessArray[row+1][column+1],chessArray[row][column],chessArray[row - 1][column - 1],chessArray[row - 2][column - 2],whoArePlaying);
				if(indexToWin !=-1) {
					// indexToWin+x_index = 2+row
					int x_index = 2+row-indexToWin;
					// indexToWin + y_index = 2 + column
					int y_index = 2+column-indexToWin;
					if (x_index == 1 || chessArray[x_index-1][y_index]!=initialSpace) {
						winAbleColumn = y_index;
						System.out.println("Diagonal: 2-winAbleColumn-2="+winAbleColumn);
						return true;
					}
				}
			}
		}
		if(row >= 1 && row<=3){// third item
			if(column <= 5 && column >=2){ // bottom right
				int indexToWin = checkAvailableSpot(chessArray[row+2][column-2],chessArray[row+1][column-1],chessArray[row][column],chessArray[row - 1][column + 1],whoArePlaying);
				if(indexToWin !=-1) {
					// indexToWin + x_index = 3 + row
					int x_index = 3+row-indexToWin;
					// indexToWin - y_index = 3 - column
					int y_index = indexToWin-3+column;
					if (x_index == 1 || chessArray[x_index-1][y_index]!=initialSpace) {
						winAbleColumn = y_index;
						System.out.println("Diagonal: 3-winAbleColumn-1="+winAbleColumn);
						return true;
					}
				}
			}
			if(column <= 4 && column >=1){ //bottom left 
				int indexToWin = checkAvailableSpot(chessArray[row+2][column+2],chessArray[row+1][column+1],chessArray[row][column],chessArray[row - 1][column - 1],whoArePlaying);
				if(indexToWin !=-1) {
					// indexToWin+x_index = 3+row
					int x_index = 3+row-indexToWin;
					// indexToWin + y_index = 3 + column
					int y_index = 3+column-indexToWin;
					if (x_index == 1 || chessArray[x_index-1][y_index]!=initialSpace) {
						winAbleColumn = y_index;
						System.out.println("Diagonal: 3-winAbleColumn-2="+winAbleColumn);
						return true;
					}
				}
			}
		}
				
		if(row >= 0 && row<=2){// fourth item
			if(column >=3){ // top left
				int indexToWin = checkAvailableSpot(chessArray[row+3][column-3],chessArray[row+2][column-2],chessArray[row+1][column-1],chessArray[row][column],whoArePlaying);
				if(indexToWin !=-1) {
					// indexToWin + x_index = 4 + row
					int x_index = 4+row-indexToWin;
					// indexToWin - y_index = 4 - column
					int y_index = indexToWin-4+column;
					if (x_index == 1 || chessArray[x_index-1][y_index]!=initialSpace) {
						winAbleColumn = y_index;
						System.out.println("Diagonal: 4-winAbleColumn-1="+winAbleColumn);
						return true;
					}
				}
			}
			if(column <= 3){ //top right 
				int indexToWin = checkAvailableSpot(chessArray[row+3][column+3],chessArray[row+2][column+2],chessArray[row+1][column+1],chessArray[row][column],whoArePlaying);
				if(indexToWin !=-1) {
					// indexToWin+x_index = 4+row
					int x_index = 4+row-indexToWin;
					// indexToWin + y_index = 4 + column
					int y_index = 4+column-indexToWin;
					if (x_index == 1 || chessArray[x_index-1][y_index]!=initialSpace) {
						winAbleColumn = y_index;
						System.out.println("Diagonal: 4-winAbleColumn-2="+winAbleColumn);
						return true;
					}
				}
			}
		}
				
//			if(column <= 4 && column >=1){
//				if (chessArray[row+1][column-1] == initialSpace 
//						&& chessArray[row][column] == whoArePlaying
//						&& chessArray[row - 1][column + 1] == whoArePlaying
//						&& chessArray[row - 2][column + 2] == whoArePlaying
//						&& chessArray[row][column-1] != initialSpace) {
//					winAbleColumn = column-1;
//					System.out.println("3-winAbleColumn-1="+winAbleColumn);
//					return true;
//				}
//			}
//			if(column <= 5 && column >=2){
//				if (chessArray[row+1][column+1] == initialSpace 
//						&& chessArray[row][column] == whoArePlaying
//						&& chessArray[row - 1][column - 1] == whoArePlaying
//						&& chessArray[row - 2][column - 2] == whoArePlaying
//						&& chessArray[row][column+1] != initialSpace) {
//					winAbleColumn = column+1;
//					System.out.println("4-winAbleColumn-1="+winAbleColumn);
//					return true;
//				}
//			}
		
		return false; // not winable.
	}
	
		
	public boolean moveAStep(int column) {
		int row = 0;
		boolean effective = false;
		if (count % 2 == 0) { //O Player put the chess
			if(checkAvailable(row, column)){
				for (row = 0; row < chessArray.length; row++) {
					if (chessArray[row][column] == initialSpace) {
						chessArray[row][column] = O_player;
						effective = true;
						break;
					}
				}
			}
		}else{ //X Player put the chess
			if(checkAvailable(row, column)){
				for (row = 0; row < chessArray.length; row++) {
					if (chessArray[row][column] == initialSpace) {
						chessArray[row][column] = X_player;
						effective = true;
						break;
					}
				}
			}
		}

		// Draw the chess board
		if (effective == true) {
			drawBoard();
			fireGameDraw();
			count ++;
			
			// check if we have a winner
			if (count % 2 == 1) {//O player win?
				if (checkWin(row, column, O_player)) {
					fireOPlayerWin();
					return true;
				}
			}else{ // X player win?
				if (checkWin(row, column, X_player)) {
					fireXPlayerWin();
					return true;
				}
			}
		}else {
			fireGameReEnterOrFull();
		}
		
		fireGameWhosTurn();
		
		return false;

	}
	
	public boolean moveAStepComputer(int column) {
		int row = 0;
		boolean effective = false;
//		if (count % 2 == 0) { 
		
		//O Player put the chess
		if(checkAvailable(row, column)){
			for (row = 0; row < chessArray.length; row++) {
				if (chessArray[row][column] == initialSpace) {
					chessArray[row][column] = O_player;
					effective = true;
					break;
				}
			}
		}
//		}else{ 
//		}
		
		// Draw the chess board
		if (effective == true) {
			drawBoard();
			fireGameDraw();
			count ++;
			
			// check if we have a winner
			if (count % 2 == 1) {//O player win?
				if (checkWin(row, column, O_player)) {
					fireOPlayerWin();
					return true;
				}
			}else{ // X player win?
				if (checkWin(row, column, X_player)) {
					fireXPlayerWin();
					return true;
				}
			}
			
			//Computer put the chess
			fireGameWhosTurnComputer();
			
			// check next step winable
			flagWinAble = checkWinAble(lastStepComputerRow, lastStepComputerCoulumn, X_player);
			System.out.println("flagWinAble: "+flagWinAble);
			
//			row = 0;
			if (flagWinAble == false) {
				row = 0;
				column = getAvailableColumn(row);
			}
			else
				column = winAbleColumn;
			
			if(column == -1)
				return false;
			
//			if(checkAvailable(row, column)){
			for (row = 0; row < chessArray.length; row++) {
				if (chessArray[row][column] == initialSpace) {
					chessArray[row][column] = X_player;
					lastStepComputerRow = row;
					lastStepComputerCoulumn = column;
//					// check next step winable
//					flagWinAble = checkWinAble(row, column, X_player);
					
					drawBoard();
					fireGameDraw();
					count ++;
					if (checkWin(row, column, X_player)) {
						fireXPlayerWin();
						return true;
					}
					break;
				}
			}
			
			fireGameWhosTurnComputer();
//			}
			
		}else {
			fireGameReEnterOrFull();
		}
		
		return false;
	}
	
	
	private int getAvailableColumn(int row) {
		HashSet<Integer> seen = new HashSet<Integer>();
		int column = (int)(Math.random()*7);
		seen.add(column);
		if(checkAvailable(row, column)){
			return column;
		}else {
			while(true){
				column = (int)(Math.random()*7);
				if(checkAvailable(row, column))
					return column;
				seen.add(column);
				if(seen.size() == 7) {
					System.out.println("No one wins the game!");
					break;
				}
			}
		}
		return -1;
		
	}

	private void fireGameDraw() {
		for (GameListener listener: listeners) {
			listener.draw(drawBoard());
		}
	}
	
	private void fireGameWhosTurn() {
		for (GameListener listener: listeners) {
			listener.draw(whosTurn());
		}
	}
	
	private void fireGameWhosTurnComputer() {
		for (GameListener listener: listeners) {
			listener.draw(whosTurnComputer());
		}
	}
	
	private void fireOPlayerWin() {
		for (GameListener listener: listeners) {
			listener.draw("The O player wins the game!\n");
			listener.draw("you finished the game.");
		}
	}
	
	private void fireXPlayerWin() {
		for (GameListener listener: listeners) {
			listener.draw("The X player wins the game!\n");
			listener.draw("you finished the game.");
		}
	}
	
	private void fireGameReEnterOrFull() {
		for (GameListener listener: listeners) {
			listener.draw("You enter the wrong column or the column is full, please REENTER!\n");
		}
	}
	
	private void fireGameStartEvent() {
		for (GameListener listener: listeners) {
			listener.clear();
			listener.gameStart();
		}
		fireGameDraw();
		fireGameWhosTurn();
	}
	
	private void fireGameChangeButtont() {
		for (GameListener listener: listeners) {
			listener.changeButton();
		}
	}
	
	private void fireGameStartEventComputer() {
		for (GameListener listener: listeners) {
			listener.clear();
			listener.gameStartComputer();
		}
		fireGameDraw();
		fireGameWhosTurnComputer();
	}
	
	private void fireGameScrollEvent() {
		for (GameListener listener: listeners) {
			listener.scrollToBottom();
		}
	}

}
