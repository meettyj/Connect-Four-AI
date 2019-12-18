import static org.junit.jupiter.api.Assertions.*;

import java.time.MonthDay;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConnectFourTest {
	
	@Test
	void testConnectFour_getCount(){
		ConnectFour game = new ConnectFour();
		Assertions.assertEquals(0,game.getCount());
	}
	
	@Test
	void testConnectFour_drawBoard(){
		ConnectFour game = new ConnectFour();
		game.initialization();
		String O_player = "| O ";
		String X_player = "| X ";
		String outputString = game.drawBoard();
		Assertions.assertFalse(outputString.contains(O_player));
		Assertions.assertFalse(outputString.contains(X_player));
	}
	
	@Test
	void testConnectFour_whosTurn(){
		ConnectFour game = new ConnectFour();
		game.initialization();
		Assertions.assertEquals(0,game.getCount());
		Assertions.assertEquals("Player O please put a chess!\n\n", game.whosTurn());
	}
	
	@Test
	void testConnectFour_whosTurnComputer(){
		ConnectFour game = new ConnectFour();
		game.initialization();
		Assertions.assertEquals(0,game.getCount());
		Assertions.assertEquals("Player O please put a chess!\n\n", game.whosTurn());
	}
	
	@Test
	void testConnectFour_checkAvailable(){
		ConnectFour game = new ConnectFour();
		game.initialization();
		for (int i = 0; i < 6; i ++) {
			for (int j = 0; j < 7; j ++) {
				Assertions.assertTrue(game.checkAvailable(i,j));
			}
		}
		
		Assertions.assertFalse(game.checkAvailable(1,11));
		Assertions.assertFalse(game.checkAvailable(1,-11));
		Assertions.assertFalse(game.checkAvailable(1,7));
	}
	
	@Test
	void testConnectFour_moveAStep(){
		ConnectFour game = new ConnectFour();
		game.initialization();
		Assertions.assertFalse(game.moveAStep(3));
		System.out.println(game.getCount());
		Assertions.assertFalse(game.moveAStep(4));
		System.out.println(game.getCount());
		Assertions.assertFalse(game.moveAStep(5));
		Assertions.assertFalse(game.moveAStep(6));
		Assertions.assertFalse(game.moveAStep(3));
	}
	
	@Test
	void testConnectFour_checkAvailable_Full(){
		ConnectFour game = new ConnectFour();
		game.initialization();
		Assertions.assertFalse(game.moveAStep(3));
		Assertions.assertFalse(game.moveAStep(3));
		Assertions.assertFalse(game.moveAStep(3));
		Assertions.assertFalse(game.moveAStep(3));
		Assertions.assertFalse(game.moveAStep(3));
		Assertions.assertFalse(game.moveAStep(3));
		Assertions.assertFalse(game.checkAvailable(3,7));
	}

	
	@Test
	void testConnectFour_checkWin_O(){
		ConnectFour game = new ConnectFour();
		game.initialization();
		Assertions.assertFalse(game.moveAStep(0));
		Assertions.assertFalse(game.moveAStep(2));
		Assertions.assertFalse(game.moveAStep(0));
		Assertions.assertFalse(game.moveAStep(2));
		Assertions.assertFalse(game.moveAStep(0));
		Assertions.assertFalse(game.moveAStep(2));
		Assertions.assertTrue(game.moveAStep(0));
	}
	
	@Test
	void testConnectFour_checkWin_X(){
		ConnectFour game = new ConnectFour();
		game.initialization();
		Assertions.assertFalse(game.moveAStep(0));
		Assertions.assertFalse(game.moveAStep(2));
		Assertions.assertFalse(game.moveAStep(0));
		Assertions.assertFalse(game.moveAStep(2));
		Assertions.assertFalse(game.moveAStep(0));
		Assertions.assertFalse(game.moveAStep(2));
		Assertions.assertFalse(game.moveAStep(1));
//		System.out.println(game.moveAStep(2));
		Assertions.assertTrue(game.moveAStep(2));
	}
	
	@Test
	void testConnectFour_checkWinAble_column_O(){
		ConnectFour game = new ConnectFour();
		game.initialization();
		String O_player = "| O ";
		String X_player = "| X ";
		Assertions.assertFalse(game.moveAStep(0));
		Assertions.assertFalse(game.moveAStep(2));
		Assertions.assertFalse(game.moveAStep(0));
		Assertions.assertFalse(game.moveAStep(2));
		Assertions.assertFalse(game.moveAStep(0));
		Assertions.assertFalse(game.moveAStep(2));
		
		Assertions.assertTrue(game.checkWinAble(2,0,O_player));
	}
	
	@Test
	void testConnectFour_checkWinAble_column_X(){
		ConnectFour game = new ConnectFour();
		game.initialization();
		String O_player = "| O ";
		String X_player = "| X ";
		Assertions.assertFalse(game.moveAStep(0));
		Assertions.assertFalse(game.moveAStep(2));
		Assertions.assertFalse(game.moveAStep(0));
		Assertions.assertFalse(game.moveAStep(2));
		Assertions.assertFalse(game.moveAStep(0));
		Assertions.assertFalse(game.moveAStep(2));
		
		Assertions.assertTrue(game.checkWinAble(2,2,X_player));
	}
	
	@Test
	void testConnectFour_checkWinAble_row_O(){
		ConnectFour game = new ConnectFour();
		game.initialization();
		String O_player = "| O ";
		String X_player = "| X ";
		Assertions.assertFalse(game.moveAStep(0));
		Assertions.assertFalse(game.moveAStep(6));
		Assertions.assertFalse(game.moveAStep(1));
		Assertions.assertFalse(game.moveAStep(6));
		Assertions.assertFalse(game.moveAStep(2));
		Assertions.assertFalse(game.moveAStep(6));
		
		Assertions.assertTrue(game.checkWinAble(0,2,O_player));
	}
	
	@Test
	void testConnectFour_checkWinAble_row_moreThanThree(){
		ConnectFour game = new ConnectFour();
		game.initialization();
		String O_player = "| O ";
		String X_player = "| X ";
		Assertions.assertFalse(game.moveAStep(3));
		Assertions.assertFalse(game.moveAStep(1));
		Assertions.assertFalse(game.moveAStep(4));
		Assertions.assertFalse(game.moveAStep(1));
		Assertions.assertFalse(game.moveAStep(5));
		Assertions.assertFalse(game.moveAStep(1));
		
		Assertions.assertTrue(game.checkWinAble(0,5,O_player));
	}
	
	@Test
	void testConnectFour_checkWinAble_row_O_reverse(){
		ConnectFour game = new ConnectFour();
		game.initialization();
		String O_player = "| O ";
		String X_player = "| X ";
		Assertions.assertFalse(game.moveAStep(3));
		Assertions.assertFalse(game.moveAStep(6));
		Assertions.assertFalse(game.moveAStep(2));
		Assertions.assertFalse(game.moveAStep(6));
		Assertions.assertFalse(game.moveAStep(1));
		Assertions.assertFalse(game.moveAStep(6));
		
		Assertions.assertTrue(game.checkWinAble(0,0,O_player));
	}
	
	@Test
	void testConnectFour_checkWinAble_row_X(){
		ConnectFour game = new ConnectFour();
		game.initialization();
		String O_player = "| O ";
		String X_player = "| X ";
		Assertions.assertFalse(game.moveAStep(6));
		Assertions.assertFalse(game.moveAStep(0));
		Assertions.assertFalse(game.moveAStep(6));
		Assertions.assertFalse(game.moveAStep(1));
		Assertions.assertFalse(game.moveAStep(6));
		Assertions.assertFalse(game.moveAStep(2));
		
		Assertions.assertTrue(game.checkWinAble(0,2,X_player));
	}
	
	
	@Test
	void testConnectFour_checkWinAble_diagonal(){
		ConnectFour game = new ConnectFour();
		game.initialization();
		String O_player = "| O ";
		String X_player = "| X ";
		Assertions.assertFalse(game.moveAStep(0));
		Assertions.assertFalse(game.moveAStep(1));
		Assertions.assertFalse(game.moveAStep(1));
		Assertions.assertFalse(game.moveAStep(2));
		Assertions.assertFalse(game.moveAStep(2));
		Assertions.assertFalse(game.moveAStep(3));
		Assertions.assertFalse(game.moveAStep(2));
		Assertions.assertFalse(game.moveAStep(3));
		Assertions.assertFalse(game.moveAStep(3));
		Assertions.assertTrue(game.checkWinAble(3,3,O_player));
		Assertions.assertFalse(game.checkWinAble(3,4,O_player));
	}
	
	@Test
	void testConnectFour_moveAStepComputer(){
		ConnectFour game = new ConnectFour();
		game.initialization();
		String O_player = "| O ";
		String X_player = "| X ";
		Assertions.assertFalse(game.moveAStepComputer(0));
		Assertions.assertFalse(game.moveAStepComputer(1));
		Assertions.assertFalse(game.moveAStepComputer(1));
	}
	
	
	
	
	
	
	
	
	

}
