import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/** The class BoardGUI represents the board of the game.
*
* @author Ysabel Bondoc
* @author Jaime Manalo
* @version 1.0
*/
public class BoardGUI extends JFrame
{
	/** The contents of the frame */
	private Container contents;
	/** The tiles or buttons of the board */
	private JButton[][] squares = new JButton[7][9];
	/** The color of the land used in the board */
	private Color colorLand = new Color(152,191,100); //LAND
	/** The color of the river used in the board */
	private Color colorRiver = new Color(0,128,255); //RIVER
	/** The color of the traps used in the board */
	private Color colorTraps = new Color(95,95,95); //TRAPS
	/** The file path for the red images used for the pieces */
	private final String RedDir = this.getClass().getResource("").getPath() + "\\Images\\Red\\";
	/** The file path for the blue images used for the pieces */
	private final String BlueDir = this.getClass().getResource("").getPath() + "\\Images\\Blue\\";
	/** The images used for the Icons of the Red pieces */
	private ArrayList<ImageIcon> RedIcons;
	/** The images used for the Icons of the Blue pieces */
	private ArrayList<ImageIcon> BlueIcons;
	/** The images used for the Icons of the Dens */
	private ArrayList<ImageIcon> OtherIcons;
	/** The array list of Red pieces */
    public ArrayList<Piece> Red;
    /** The array list of Blue pieces */
    public ArrayList<Piece> Blue;
	
	/**
	 * This constructor is a default constructor the frame and its contents.
	 */
	public BoardGUI()
	{	
		//The title of the window
		super("Animal Chess");
		
		//Layout of the buttons
		contents = getContentPane();
		contents.setLayout(new GridLayout(7,9));
		
		//Initialize the board
		init();
		
		//Size and display window:
		setSize(750,600);
		setResizable(false);
		setLocationRelativeTo(null); //centers the window when run
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * This method initializes the board and pieces
	 */
	private void init()
	{
		//ArrayList of Pieces
		Red = new ArrayList<Piece>();
		Blue = new ArrayList<Piece>();
		
		//ArrayList of Icons
		RedIcons = new ArrayList<ImageIcon>();
		BlueIcons = new ArrayList<ImageIcon>();
		OtherIcons = new ArrayList<ImageIcon>();
		
		//Create and add Board Components
		for (int i = 0; i < 7; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				//Creates the buttons
				squares[i][j] = new JButton();
				
				//LAND
				//Sets the color of the buttons to land
				squares[i][j].setBackground(colorLand);
				
				//RIVER
				//Sets the color of the buttons to blue
				if ((i == 1 || i == 2) && (j >= 3 && j <= 5))
					squares[i][j].setBackground(colorRiver);
				
				else if ((i == 4 || i == 5) && (j >= 3 && j <= 5))
					squares[i][j].setBackground(colorRiver);
				
				//TRAPS
				//Sets the color of the buttons to Gray
				if (((i == 2 || i == 4) && (j == 0 || j == 8)) || ((i == 3) && (j == 1 || j == 7)))
					squares[i][j].setBackground(colorTraps);
				
				//DEN
				//Sets the color of the buttons to red and blue
				if (i == 3 && j == 0)
					squares[i][j].setBackground(new Color(255,102,102));
				else if (i == 3 && j == 8)
					squares[i][j].setBackground(new Color(102,178,255));
				
				//Adds the buttons to the container and frame
				contents.add(squares[i][j]);
			}
		}
		
		//INITIALIZE THE RED PIECES
		Red.add(new Mouse(true, 6,2));
		Red.add(new Cat(true, 1,1));
		Red.add(new Wolf(true, 2,2));
		Red.add(new Dog(true, 5,1));
        Red.add(new Leopard(true, 4,2));
        Red.add(new Tiger(true, 0,0));
        Red.add(new Lion(true, 6,0));
        Red.add(new Elephant(true, 0,2));
        
        //INITIALIZE THE BLUE PIECES
		Blue.add(new Mouse(false, 0,6));
		Blue.add(new Cat(false, 5,7));
		Blue.add(new Wolf(false, 4,6));
		Blue.add(new Dog(false, 1,7));
        Blue.add(new Leopard(false, 2,6));
        Blue.add(new Tiger(false, 6,8));
        Blue.add(new Lion(false, 0,8));
        Blue.add(new Elephant(false, 6,6));
        
        //ADD THE RED ICONS
        RedIcons.add(new ImageIcon(RedDir + "Mouse.png"));
        RedIcons.add(new ImageIcon(RedDir + "Cat.png"));
        RedIcons.add(new ImageIcon(RedDir + "Wolf.png"));
        RedIcons.add(new ImageIcon(RedDir + "Dog.png"));
        RedIcons.add(new ImageIcon(RedDir + "Leopard.png"));
        RedIcons.add(new ImageIcon(RedDir + "Tiger.png"));
        RedIcons.add(new ImageIcon(RedDir + "Lion.png"));
        RedIcons.add(new ImageIcon(RedDir + "Elephant.png"));
        
        //ADD THE BLUE ICONS
        BlueIcons.add(new ImageIcon(BlueDir + "Mouse.png"));
        BlueIcons.add(new ImageIcon(BlueDir + "Cat.png"));
        BlueIcons.add(new ImageIcon(BlueDir + "Wolf.png"));
        BlueIcons.add(new ImageIcon(BlueDir + "Dog.png"));
        BlueIcons.add(new ImageIcon(BlueDir + "Leopard.png"));
        BlueIcons.add(new ImageIcon(BlueDir + "Tiger.png"));
        BlueIcons.add(new ImageIcon(BlueDir + "Lion.png"));
        BlueIcons.add(new ImageIcon(BlueDir + "Elephant.png"));
        
        //ADD THE DEN ICONS
        OtherIcons.add(new ImageIcon(RedDir + "Den.png"));
        OtherIcons.add(new ImageIcon(BlueDir + "Den.png"));
        
        //SET THE RED ICONS TO THE BUTTONS
        squares[6][2].setIcon(RedIcons.get(0));
        squares[1][1].setIcon(RedIcons.get(1));
        squares[2][2].setIcon(RedIcons.get(2));
        squares[5][1].setIcon(RedIcons.get(3));
        squares[4][2].setIcon(RedIcons.get(4));
        squares[0][0].setIcon(RedIcons.get(5));
        squares[6][0].setIcon(RedIcons.get(6));
        squares[0][2].setIcon(RedIcons.get(7));
        
        //SET THE BLUE ICONS TO THE BUTTONS
        squares[0][6].setIcon(BlueIcons.get(0));
        squares[5][7].setIcon(BlueIcons.get(1));
        squares[4][6].setIcon(BlueIcons.get(2));
        squares[1][7].setIcon(BlueIcons.get(3));
        squares[2][6].setIcon(BlueIcons.get(4));
        squares[6][8].setIcon(BlueIcons.get(5));
        squares[0][8].setIcon(BlueIcons.get(6));
        squares[6][6].setIcon(BlueIcons.get(7));
        
        ////SET THE DEN ICONS TO THE BUTTONS
        squares[3][0].setIcon(OtherIcons.get(0));
        squares[3][8].setIcon(OtherIcons.get(1));
	}
	
	/**
	 * This method sets the Action Listener to the buttons
	 * @param Listener receives action events
	 */
	public void setListener (ActionListener Listener)
    {
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                squares[i][j].addActionListener(Listener);
            }
        }   
    }
	
	/**
	 * This method returns the array of JButtons
	 * @return the JButton array squares
	 */
	public JButton[][] getButtons()
	{
		return squares;
	}
	
	/**
	 * This method returns the Red pieces
	 * @return the Array List Red
	 */
	public ArrayList<Piece> getRedPieces()
	{
		return Red;
	}
	
	/**
	 * This method returns the Blue pieces
	 * @return the Array List Blue
	 */
	public ArrayList<Piece> getBluePieces()
	{
		return Blue;
	}
	
	/**
	 * This method returns the Red Icons
	 * @return the Array List RedIcons
	 */
	public ArrayList<ImageIcon> getRedIcons()
	{
		return RedIcons;
	}
	
	/**
	 * This method returns the Blue Icons
	 * @return the Array List BlueIcons
	 */
	public ArrayList<ImageIcon> getBlueIcons()
	{
		return BlueIcons;
	}
	
	/**
	 * This method returns the Den Icons
	 * @return the Array List OtherIcons
	 */
	public ArrayList<ImageIcon> getOtherIcons()
	{
		return OtherIcons;
	}
}
