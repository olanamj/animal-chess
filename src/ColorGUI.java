import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** The class ColorGUI displays the color selection menu.
*
* @author Ysabel Bondoc
* @author Jaime Manalo
* @version 1.0
*/
public class ColorGUI implements ActionListener
{
	//Attributes
	
	/** The game board GUI */
	public BoardGUI gui;
	/** The controller of the board GUI */
	public Controller controller;
	/** The Frame of the this GUI */
	public JFrame frame;
	/** The text of this GUI */
	private JLabel Header;
	/** The panel of the text*/
	private JPanel panel;
	/** The Buttons in the GUI */
	private JButton blue, red;
	/** The data of the players */
	private Player[] player;
	/** The Font style and size of the text */
	private Font titleFont = new Font("Times New Roman", Font.PLAIN, 20);
	
	/**
	 * This constructor creates the display of the color GUI.
	 * @param n The player who goes first
	 * @param players The data of the players
	 */
	public ColorGUI(int n, Player[] players)
	{
		//Create a new frame and panel for the GUI
		frame = new JFrame("Choose a Color");
		panel = new JPanel();
		
		//Setup the frame
		frame.setSize(450,200);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); //centers the window when run
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create a Header text to display
		Header = new JLabel("Player " + n + " choose a color.");
		Header.setForeground(Color.BLACK);
		Header.setFont(titleFont);
		
		//Initialize the Buttons
		blue = new JButton("BLUE");
		red = new JButton("RED");
		
		//Set up the buttons and add Action Listeners
		blue.setFont(titleFont);
		blue.setBackground(Color.CYAN);
		blue.setBounds(100, 50, 100, 50);
		blue.addActionListener(this);
		
		red.setFont(titleFont);
		red.setBackground(Color.RED);
		red.setBounds(225, 50, 100, 50);
		red.addActionListener(this);
		
		//Transfer the player data to a local player variable
		player = players;
		
		//Set the location of panel and add Header to the panel
		panel.setBounds(100, 20, 150, 200);
		panel.add(Header);
		
		//add the buttons and the panel to the frame
		frame.add(blue);
		frame.add(red);
		frame.add(panel);
	}
	
	/**
	 * This method sets the color of the players and starts the board GUI when a button is clicked
	 */
	public void actionPerformed(ActionEvent e) 
	{
		//If the blue button is clicked the first player uses blue pieces
		if (e.getSource() == blue)
		{
			player[0].setColor(false);
			player[1].setColor(true);
		}
		//If the red button is clicked the first player uses the red pieces
		else if (e.getSource() == red)
		{
			player[0].setColor(true);
			player[1].setColor(false);
		}
		
		//Opens the board GUI and its controller
		gui = new BoardGUI();
		controller = new Controller(gui, player);
		//Set this window to invisible
		frame.setVisible(false);
	}
}