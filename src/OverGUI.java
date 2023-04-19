import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

/** The class OverGUI represents the game over screen.
*
* @author Ysabel Bondoc
* @author Jaime Manalo
* @version 1.0
*/
public class OverGUI
{
	//Attributes
	
	/** Frame of the GUI */
	public JFrame frame;
	/** Header Text */
	private JLabel Header;
	/** Font style and size of Header */
	private  Font titleFont = new Font("Times New Roman", Font.PLAIN, 30);
	
	//Methods
	
    /** This constructor is a displays the winner.
     * 
     * @param w is the name of the winner.
     */
	public OverGUI(String w)
	{
		//Create the frame
		frame = new JFrame();
		frame.setSize(450,200);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setLocationRelativeTo(null); 
		
		//Create the text to display
		Header = new JLabel("GAME OVER " + w + " WINS!");
		Header.setForeground(Color.BLACK);
		Header.setFont(titleFont);
		frame.add(Header);
	}
}