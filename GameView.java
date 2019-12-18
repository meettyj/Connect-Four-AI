
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameView implements GameListener {

	private ConnectFour game;
	private JTextArea textArea = new JTextArea();
	private JButton buttonEnter = new JButton("Enter");
	private JButton buttonRestart = new JButton("Restart");
	private JButton buttonComputer = new JButton("Play with Computer");
	private JTextField field = new JTextField();
	private Font font = new Font("Consolas",0,20);
	private boolean playWithComputer = false;
	
	
	public GameView(ConnectFour game) {
		this.game = game;
		game.addGameListener(this);
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		// button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(buttonEnter, BorderLayout.WEST);
		buttonPanel.add(buttonRestart, BorderLayout.CENTER);
		buttonPanel.add(buttonComputer, BorderLayout.EAST);
		
		// bottom panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(100, 50));
		bottomPanel.setLayout(new BorderLayout());
		field.setFont(font);
		textArea.setFont(font);
		bottomPanel.add(field, BorderLayout.CENTER);
		bottomPanel.add(buttonPanel, BorderLayout.EAST);
		
		panel.add(bottomPanel, BorderLayout.SOUTH);
		
		textArea.setCaretPosition(textArea.getText().length());
	    JScrollPane scrollPane = new JScrollPane(textArea);
		panel.add(scrollPane, BorderLayout.CENTER);
		frame.getContentPane().add(panel);
		
		frame.setSize(800,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		buttonEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				buttonEnterPressed();
			}
		});
		
		buttonRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				game.initialization();
//				game.printWelcome();
				if (playWithComputer == false) {
					game.printWelcome();
				}else {
					game.printWelcomeComputer();
				}
			}
		});
		
		buttonComputer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				game.initialization();
				game.changeGUIButton();
//				if (playWithComputer == false) {
//					playWithComputer = true;
//					buttonComputer.setText("Play with Human");
//					game.printWelcomeComputer();
//				}else {
//					playWithComputer = false;
//					buttonComputer.setText("Play with Computer");
//					game.printWelcome();
//				}
			}
		});
		
		game.printWelcome();
	}

	private void buttonEnterPressed() {
		
		int column = Integer.parseInt(field.getText())-1;
//		textArea.append("Guessing " + guess + "\n");
		if(playWithComputer == false) {
			if(game.moveAStep(column)) {
				System.out.println("you finished the game.");
	//			System.exit(0);
			}
		}else {
			if(game.moveAStepComputer(column)) {
				System.out.println("you finished the game.");
	//			System.exit(0);
			}
		}
		
		// tie
		if(game.getCount() == 6*7){
			System.out.println("No one wins the game!");
//			System.exit(0);
		}
		
		// clear the input text field
		field.setText("");
		game.scroll();
	}

	public void changeButton() {
		if (playWithComputer == false) {
			playWithComputer = true;
			buttonComputer.setText("Play with Human");
			game.printWelcomeComputer();
		}else {
			playWithComputer = false;
			buttonComputer.setText("Play with Computer");
			game.printWelcome();
		}
	}
	
	
	public void scrollToBottom() {
		textArea.setCaretPosition(textArea.getText().length());
	}
	
	@Override
	public void gameStart() {
		// draw
		textArea.append("Welcome to Yijun's Connect Four!\n");
		textArea.append("There are two players: player O and player X.\n"
				+ "Player O will put a chess first!\n\n");
	}
	
	public void gameStartComputer() {
		// draw
		textArea.append("Welcome to Yijun's Connect Four!\n");
		textArea.append("In this game, you are gonna to play with a computer\n"
				+ "You can go first!\n\n");
	}

	@Override
	public void draw(String output) {
		textArea.append(output);
	}	
	
	public void clear() {
		textArea.setText("");;
	}	
}

