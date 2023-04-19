import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** The class ShuffleGUI displays the initialization menu.
*
* @author Ysabel Bondoc
* @author Jaime Manalo
* @version 1.0
*/
public class ShuffleGUI implements ActionListener
{	
	//Attributes
	
	/**The Frame of the GUI*/
	public JFrame frame;
	/**The panel where the buttons will be displayed*/
	private JPanel panel;
	/**The buttons that the user interacts with */
	private JButton[] jb = new JButton[8];
	/**The button that opens the next GUI */
	private JButton okayButton;
	/**The area where the selection will be displayed*/
	private JTextField textfield;
	/**The area where text will be displayed*/
	private JTextField textfield2;
	/**The Font style and size of the text*/
	private Font font = new Font("Times New Roman", Font.BOLD, 30);
	/**The color used in the GUI*/
	private Color jbColor = new Color(230,230,230);
	/**The array of player data*/
	private Player[] player = new Player[2];
	/**The array of the names of the pieces*/
	private String[] piece = new String[8];
	/**The counter that counts how many buttons have been pressed*/
	private int value = -1;
	
	/**
	 * This is a default constructor that initializes the ShuffleGUI.
	 */
	public ShuffleGUI() 
	{
		//Create a new frame
		frame = new JFrame("Select a Number");
		//Initialize the okay button Action Listener
		chooseColor chooseC = new chooseColor();
		
		frame.setSize(420,550);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null); //centers the window when run
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		//Create a new Text Field
		textfield = new JTextField();
		textfield.setBounds(50, 25, 300, 50);
		textfield.setFont(font);
		textfield.setEditable(false);
		
		//Create another Text Field
		textfield2 = new JTextField();
		textfield2.setBounds(50, 425, 300, 50);
		textfield2.setFont(font);
		textfield2.setEditable(false);
		textfield2.setText("Players select a Piece.");
		
		//Add the text fields to the frame
		frame.add(textfield);
		frame.add(textfield2);
		
		//Initialize the Buttons
		jb[0] = new JButton("1");
		jb[1] = new JButton("2");
		jb[2] = new JButton("3");
		jb[3] = new JButton("4");
		jb[4] = new JButton("5");
		jb[5] = new JButton("6");
		jb[6] = new JButton("7");
		jb[7] = new JButton("8");
		okayButton = new JButton("OK");
		
		//Initialize the pieces
		piece[0] = "Mouse";
		piece[1] = "Cat";
		piece[2] = "Wolf";
		piece[3] = "Dog";
		piece[4] = "Leopard";
		piece[5] = "Tiger";
		piece[6] = "Lion";
		piece[7] = "Elephant";
		
		//Shuffle the pieces
		piece = randomize(piece, piece.length);
		
		//Add color and an Action Listener to all the Pieces
		for (int i = 0; i < 8; i++)
		{
			jb[i].addActionListener(this);
			jb[i].setBackground(jbColor);
			jb[i].setFont(font);
			jb[i].setFocusable(false);
		}
		
		//Add an Action Listener to the okay Button and set the button as invisible
		okayButton.addActionListener(chooseC);
		okayButton.setVisible(false);
		
		//Create a new panel to place the buttons in
		panel = new JPanel();
		panel.setBounds(50,100,300,300);
		panel.setLayout(new GridLayout(3,3,10,10));
		panel.setBackground(jbColor);
		
		//Add the buttons to the panel
		panel.add(jb[0]);
		panel.add(jb[1]);
		panel.add(jb[2]);
		panel.add(jb[3]);
		panel.add(jb[4]);
		panel.add(jb[5]);
		panel.add(jb[6]);
		panel.add(jb[7]);
		panel.add(okayButton);
		
		//Add the panel to the frame
		frame.add(panel);
	}
	
	/**
	 * This is a method performs an action when a button is clicked.
	 */
	public void actionPerformed(ActionEvent e) 
	{
		//adds to the button clicked counter
		value++;
		//the index of the button clicked
		int clicked = -1;
		
		//If 2 or less buttons have been clicked get the index of the button and make it invisible
		if (value <= 1)
		{
			for (int i = 0; i < 8; i++)
			{
				if (e.getSource() == jb[i])
				{
					jb[i].setVisible(false);
					textfield.setText(piece[i]);
					clicked = i;
				}
			}
		}
		//If more than 2 buttons are clicked compare the rank of the pieces the players got and make okay button visible
		else
		{
			//1st player's number is lower than second
			if (player[0].getRankofPiece() < player[1].getRankofPiece())
			{
				//Player 2 goes first
				player[0].setNumber(2);
				player[1].setNumber(1);
			}
			else if (player[0].getRankofPiece() > player[1].getRankofPiece())
			{
				//Player 1 goes first
				player[0].setNumber(1);
				player[1].setNumber(2);
			}
				
			okayButton.setVisible(true);
		}
			
		//If a button has been clicked check the name and rank of the piece and give it to the player
		if (clicked > -1)
		{
			switch(piece[clicked])
			{
				case "Mouse":
					player[value] = new Player(1);
					break;
				case "Cat":
					player[value] = new Player(2);
					break;
				case "Wolf":
					player[value] = new Player(3);
					break;
				case "Dog":
					player[value] = new Player(4);
					break;
				case "Leopard":
					player[value] = new Player(5);
					break;
				case "Tiger":
					player[value] = new Player(7);
					break;
				case "Lion":
					player[value] = new Player(8);
					break;
				case "Elephant":
					player[value] = new Player(9);
					break;
			}
		}
	}
	
	/**
	 * This class chooseColor creates a new Action Listener
	 */
	private class chooseColor implements ActionListener
	{
		/*
		 * This method performs opens the color selection GUI when the Okay button is clicked.
		 */
		public void actionPerformed (ActionEvent e)
		{
			//Initialize ColorGUI
			ColorGUI color = new ColorGUI(player[0].getNumber(), player);
			color.frame.setVisible(true);
			
			//turn this frame invisible
			frame.setVisible(false);
		}
	}
	
	/**
	 * This method shuffles the elements in the string array piece
	 * @param a the array to be shuffled
	 * @param n the length of the array
	 * @return the shuffled array
	 */
	private static String[] randomize(String a[], int n)
    {
        //Instantiate a Random class
        Random r = new Random();
        // Swap elements one by one from back to front
        for (int i = n-1; i > 0; i--) {   
            //Randomly pick an index
            int j = r.nextInt(i+1);
            // Swap the element at i with the element at a random index
            String temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        //Return the shuffled array
        return a;
    }
}
