
import java.util.Scanner;

public class Draft{
	private static String[][] chessArray = new String[6][7];
	private static Scanner input = new Scanner(System.in);
	private static String initialSpace = "|   ";
	private static String O_player = "| O ";
	private static String X_player = "| X ";
	private static int count = 0; //count of steps
	
	private static void drawBoard() {
//		System.out.println("- - - - - - - - - - - - - - -");
		for(int i = chessArray.length - 1;i >= 0;i--){
			System.out.println("- - - - - - - - - - - - - - -");
			for(int j = 0;j < chessArray[0].length;j++)
				System.out.print(chessArray[i][j]);
			System.out.println("|");
		}
		System.out.println("-----------------------------");
		for(int i = 0;i < chessArray[0].length;i++)
			System.out.print("| "+(i+1)+" ");
		System.out.println("|");
	}
	
	private static boolean checkAvailable(int row, int column) {
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
	
	private static boolean checkWin(int row, int column, String whoArePlaying) {
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
		if(row >= 3){
			if(column < 3){
				if (chessArray[row][column] == whoArePlaying 
						&& chessArray[row - 1][column + 1] == whoArePlaying
						&& chessArray[row - 2][column + 2] == whoArePlaying
						&& chessArray[row - 3][column + 3] == whoArePlaying)
					return true;
			}
			else if(column > 3){
				if (chessArray[row][column] == whoArePlaying 
						&& chessArray[row - 1][column - 1] == whoArePlaying
						&& chessArray[row - 2][column - 2] == whoArePlaying
						&& chessArray[row - 3][column - 3] == whoArePlaying)
					return true;
			}
			else{
				if ((chessArray[row][column] == whoArePlaying 
						&& chessArray[row - 1][column + 1] == whoArePlaying
						&& chessArray[row - 2][column + 2] == whoArePlaying
						&& chessArray[row - 3][column + 3] == whoArePlaying) 
						|| (chessArray[row][column] == whoArePlaying 
						&& chessArray[row - 1][column - 1] == whoArePlaying	
						&& chessArray[row - 2][column - 2] == whoArePlaying		
						&& chessArray[row - 3][column - 3] == whoArePlaying))
					return true;
			}
		}
		return false; // No one has win the game.
	}
	

	
	private static boolean moveAStep(int row, int column, int count) {
//		String[][] chessArrayTemp = chessArray.clone();
		if (count % 2 == 0) { //O Player put the chess
			System.out.print("\n\nO player please go:");
			while (true) {
				column = input.nextInt() - 1;		

				if(checkAvailable(row, column)){
					for (row = 0; row < chessArray.length; row++) {
						if (chessArray[row][column] == initialSpace) {
							chessArray[row][column] = O_player;
							break;
						}
					}
					break;
				}
			}
		}else{ //X Player put the chess
			System.out.print("\n\nX player please go:");
			while (true) {
				column = input.nextInt() - 1;

				if(checkAvailable(row, column)){
					for (row = 0; row < chessArray.length; row++) {
						if (chessArray[row][column] == initialSpace) {
							chessArray[row][column] = X_player;
							break;
						}
					}
					break;
				}
			}
		}
		
		// Draw the chess board
		drawBoard();
		
		// check if we have a winner
		if (count % 2 == 0) {//O player win?
			if (checkWin(row, column, O_player)) {
				System.out.println("The O player win the game!");
				return true;
			}
		}else{ // X player win?
			if (checkWin(row, column, X_player)) {
				System.out.println("The X player player win the game!");
				return true;
			}
		}
		return false;
	}
	

	

	
	public static void main(String[]args){
		
		// Initialization
		int row = 0,column = 0;
		for(int i = 0;i < chessArray.length;i++)
			for(int j = 0;j < chessArray[0].length;j++)
				chessArray[i][j] = initialSpace;
		
		// Draw the chess board
		drawBoard();

		// start of the game
		while (true) {
			row = 0;
			column = 0;
			
			if (moveAStep(row, column, count)) 
				break;

			count++;
			
			// tie
			if(count == 6*7){
				System.out.println("No one wins the game!");
				break;
			}
		}
	}
}




//if (count % 2 == 0) { //O Player put the chess
//	System.out.print("\n\nO player please go:");
//	while (true) {
//		column = input.nextInt() - 1;		
//
//		if(checkAvailable(row, column, chessArray, initialSpace)){
//			for (row = 0; row < chessArray.length; row++) {
//				if (chessArray[row][column] == initialSpace) {
//					chessArray[row][column] = O_player;
//					break;
//				}
//			}
//			break;
//		}
//	}
//}else{ //X Player put the chess
//	System.out.print("\n\nX player please go:");
//	while (true) {
//		column = input.nextInt() - 1;
//
//		if(checkAvailable(row, column, chessArray, initialSpace)){
//			for (row = 0; row < chessArray.length; row++) {
//				if (chessArray[row][column] == initialSpace) {
//					chessArray[row][column] = X_player;
//					break;
//				}
//			}
//			break;
//		}
//	}
//}
//
//// Draw the chess board
//drawBoard(chessArray);
//
//// check if we have a winner
//if (count % 2 == 0) {//O player win?
//	if (checkWin(row, column, chessArray, O_player)) {
//		System.out.println("The O player win the game!");
//		break;
//	}
//}else{ // X player win?
//	if (checkWin(row, column, chessArray, X_player)) {
//		System.out.println("The X player player win the game!");
//		break;
//	}
//}