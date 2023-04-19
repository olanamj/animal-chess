import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**The class Controller represents the controller of the Board/Game
 * 
 * @author Ysabel Bondoc
 * @author Jaime Manalo
 * @version 1.0
 */
public class Controller 
{
	/** The game board GUI */
	private BoardGUI Gui;
	/** The tiles or buttons of the board */
	private JButton[][] squares;
	/** The integer that keeps track of the turns */
	private int turnCounter;
	/** Boolean checks whether the game is over or not */
	private boolean Over = false;
	/** The piece on the coordinate a piece is going to */
	private Piece DestinationPiece;
	/** The piece selected by the player*/
	private Piece SelectedPiece;
	/** The Icon of the piece selected by the player*/
	private ImageIcon SelectedIcon;
	/** The pieces in the game*/
    private ArrayList<Piece> Red;
    private ArrayList<Piece> Blue;
    /** The Icons of the pieces*/
    private ArrayList<ImageIcon> RedIcons;
    private ArrayList<ImageIcon> BlueIcons;
    private ArrayList<ImageIcon> OtherIcons;
    /** The player data*/
    private Player[] player; 
    /** The winner of the game*/
    private String winner;
    
    /**
     * This constructor initializes the controller
     * @param gui initializes the board and gets the data of the board
     * @param players the data of the players
     */
    public Controller (BoardGUI gui, Player[] players)
    {
    	//Initialize the board gui
    	Gui = gui;
    	//Initialize an action listener
    	clickButton click = new clickButton();
    	//Transfer Gui button data to JButtons in the controller
    	squares = Gui.getButtons();
    	
    	//The red pieces and their icons
    	Red = Gui.getRedPieces();
    	RedIcons = Gui.getRedIcons();
    	
    	//The blue pieces and their icons
    	Blue = Gui.getBluePieces();
    	BlueIcons = Gui.getBlueIcons();
    	
    	//The den icons
    	OtherIcons = Gui.getOtherIcons();
    	
    	//Transfer player data to a local variable
    	player = players;
    	
    	//Initialize turnCounter based on which player is first
    	if (player[0].getColor() == true)
    		turnCounter = 0;
    	else if (player[0].getColor() == false)
    		turnCounter = 1;
    	
    	//Set the Action Listener of all the buttons to detect a click
    	Gui.setListener(click);
    }

	/**
	 * This class implements an action listener
	 * It detects whether a button has been clicked and checks whether its a piece or not
	 */
	private class clickButton implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			movePiece moveP = new movePiece();
			Object source = e.getSource();
			int x = 0, y = 0;
			boolean isRedTurn = true;
			
			if (turnCounter % 2 == 0)
				System.out.println("\nRED TURN");
			else if (turnCounter % 2 == 1)
				System.out.println("\nBLUE TURN");
			
			System.out.println("Turn: " + turnCounter);
	  		
	  		//Check if its red or blue's turn
			if (turnCounter % 2 == 1)
				isRedTurn = false;
			
			//Get the coordinates of the piece selected
			for (int i = 0; i < 7; i++)
	    	{
	    		for (int j = 0; j < 9; j++)
	    		{
	    			if (source == squares[i][j])
	    			{
	    				x = i;
	    				y = j;				
	    			}
	    		}
	    	}
			
			//Set the piece you can selected 
			if (isRedTurn == true)
			{
				SelectedPiece = getSelectedP (Red, x,y);
				SelectedIcon = getSelectedIcon (RedIcons, x,y);
			}
			else
			{
				SelectedPiece = getSelectedP (Blue, x,y);
				SelectedIcon = getSelectedIcon (BlueIcons, x,y);
			}
			
			//A Piece is selected
			if (SelectedPiece != null)
			{	
				Turns(source, moveP);
			}
			
			//GAME OVER CONDITIONS
			//If red or blue have no more pieces
			if (Red.isEmpty() || Blue.isEmpty())
			{
				Over = true;
				if (Red.isEmpty())
					winner = "BLUE";
				else if (Blue.isEmpty())
					winner = "RED";
			}
				
			//If a piece is on the den
			if (squares[3][8].getIcon() != OtherIcons.get(1) && squares[3][8].getIcon() != null)
			{
				Over = true;
				winner = "RED";
			}
			if (squares[3][0].getIcon() != OtherIcons.get(0) && squares[3][0].getIcon() != null)
			{
				Over = true;
				winner = "BLUE";
			}
			
			//If game is over
			if (Over == true)
			{
				OverGUI gameover = new OverGUI(winner);
				gameover.frame.setVisible(true);
			}
		}
	}

	/**
	 * This class implements an action listener.
	 * It is implemented after a piece has been clicked.
	 * This class moves the piece to the location you click.
	 */
	private class movePiece implements ActionListener
	{
		/*
		 * This method is the action performed when a button is clicked
		 */
		public void actionPerformed (ActionEvent e)
		{	
			int X = -1, Y = -1;
			
			//Get clicked button location
			for (int i = 0; i < 7; i++)
			{
				for (int j = 0; j < 9; j++)
				{	
					//If button clicked == 
					if (e.getSource() == squares[i][j])
					{
						if (SelectedPiece != null)
						{
							//If its a valid move set the new coordinates to X and Y
							if (checkMove(SelectedPiece, i,j) == true)
							{
								X = i;
								Y = j;
							}
							else
								System.out.println("checkMove is false");
						}
					}
				}
			}
			
			//If there is a selected piece and it can move
			if (SelectedPiece != null && (X > -1 || Y > -1))
			{
					System.out.println("Selected piece: " + SelectedPiece.toString() + ": " + SelectedPiece.getRow() + "," + SelectedPiece.getCol());
					//Move the piece
					processClick(SelectedPiece, SelectedIcon, X,Y);
					//If there is a piece on the destination, remove it from the board
					if (DestinationPiece != null)
				  		removePiece(SelectedPiece,X,Y);
					return;
			}
			
			//Remove this action listener so that the piece doesn't move when another piece is selected
			for (int i = 0; i < 7; i++)
			{
					for (int j = 0; j < 9; j++)
					{	
						squares[i][j].removeActionListener(this);
					}
			}
		}
	}
	
	/**
	 * This method processes your chosen move by moving the image icon
     * and changing the coordinates of your piece
	 * @param a The piece selected
	 * @param b The icon of the selected piece
	 * @param i the row coordinate the piece moves to
	 * @param j the column coordinate the piece moves to
	 */
  	public void processClick (Piece a, ImageIcon b, int i, int j)
  	{	
  		squares[a.getRow()][a.getCol()].setIcon(null);
  		squares[i][j].setIcon(b);
  		a.setRow(i);
  		a.setCol(j);
  		
  		//End Turn
	  	turnCounter++;
  	}
  	
  	/**
  	 * This method checks whether a move is valid
  	 * @param a The piece selected
  	 * @param i the row coordinate the piece will move to
  	 * @param j the column coordinate the piece will move to
  	 * @return whether the move is valid or not
  	 */
  	public boolean checkMove (Piece a, int i, int j)
  	{
  		//get the piece on the coordinate the selected piece is moving to
  		DestinationPiece = getPiece(i, j);
	  	
  		//If there is no piece on the coordinate selected
  		if (DestinationPiece == null)
  		{
  			//Check if it is a Lion or Tiger and whether it can jump over a river or not
  			if (isLionOrTiger ( a,  i,  j) == true)
  				return true;
  			//Check if the move is valid
  			if (a.canMove(i, j) == false)
  	  	  	  	return false;	
  		}
  		//If there is a piece on the coordinate selected
  		else
  		{
  	  		//Check if the piece can move to the coordinate and that the piece there can be captured
  			if (a.canMovePiece(i, j, DestinationPiece) == false)
  	  	  		return false;
  			
  			//Check if it is a Lion or Tiger and whether it can jump over a river or not
  			if (isLionOrTiger ( a,  i,  j) == true)
  				return true;
  			
  			//Check if the move is valid
  	  		if (a.canMove(i, j) == false)
  	  	  	  	return false;	
  		}
  		
  		//Check if the piece steps on a trap
  		if (a.onTrap(i,j) == true)
  		{
  			System.out.println("!!!TRAPPED!!!");
            a.setRank(0);
            a.setTrapped(true);
  		}
  		if (a.getTrapped() == true && a.onTrap(i,j) == false)
  		{
  			System.out.println("!!!RELEASED!!!");
            a.returnRank();
            a.setTrapped(false);
  		}
  		
  		//It is a valid move
  		return true;
  	}
  	
  	/**
  	 * This method returns whether a tiger or lion can jump over a river
  	 * @param a The piece selected
  	 * @param i The row coordinate the piece will move to
  	 * @param j the column coordinate the piece will move to
  	 * @return whether the piece can move or not
  	 */
  	public boolean isLionOrTiger (Piece a, int i, int j)
  	{
  		//Boolean to check if the piece can jump
  		boolean jump1 = false;
  		boolean jump2 = false;
  		//Boolean to check if there is a mouse on the board
  		boolean noMouse1 = noMouse(Red);
  		boolean noMouse2 = noMouse(Blue);
  		
  		if (a.getRank() == 6 || a.getRank() == 7)
		{
  			if (noMouse1 == false)
  	  		{
  				for (int k = 0; k < Red.size(); k++)
  		  		{
  					if (Red.get(k).toString().equalsIgnoreCase("Mouse"))
  	  				{	  					
  		  				if (a.riverJumpMouse(i, j, Red.get(k)) == true)
  		  					jump1 = true;
  		  			}
  		  		}
  	  		}
  			else
  			{
  				if (a.riverJump(i, j) == true)
  					jump1 = true;
  			}
  			
  			if (noMouse2 == false)
  			{
  				for (int k = 0; k < Blue.size(); k++)
  				{
  					if (Blue.get(k).toString().equalsIgnoreCase("Mouse"))
  					{
  						if (a.riverJumpMouse(i, j, Blue.get(k)) == true)
  	  						jump2 = true;
  					}	
  				}
  			}
  			else
  			{
  				if (a.riverJump(i, j) == true)
  					jump2 = true;
  			}

			if (jump1 == true && jump2 == true)
	  			return true;
		}
  		return false;
  	}
  	
  	/**
  	 * This method checks whether there is a mouse on the board
  	 *  @param p The array list of pieces
  	 *  @return whether there is a mouse or not
  	 */
  	public boolean noMouse(ArrayList<Piece> p)
  	{
  		for (int i = 0; i < p.size(); i++)
  		{
  			if (p.get(i).toString().equalsIgnoreCase("Mouse"))
  				return false;
  		}
  			
  		return true;
  	}
  	
  	/**
  	 * This method removes a piece from the board
  	 * @param a the piece selected
  	 * @param i the row coordinate the piece selected is moving to
  	 * @param j the column coordinate the piece selected is moving to
  	 */
  	public void removePiece(Piece a, int i, int j)
  	{
  		//Check the last index
  		int removePfinalIndex;
  		
  		//If the piece can capture the piece on the coordinate it is moving to
  		if (a.canMovePiece(i, j, DestinationPiece) == true)
  		{
  			System.out.println("Destination piece: " + DestinationPiece.toString());
  			
  			//If the piece to capture is red
  			if (DestinationPiece.getColor() == true)
  			{
  				//Search for the piece
  				for (int k = 0; k < Red.size() - 1; k++)
  		        {
  					//If this is the piece on the destination
  		            if (Red.get(k) == DestinationPiece)
  		            {
  		            	//Remove the piece
  		                Red.remove(k);
  		                RedIcons.remove(k);
  		                break;
  		            }
  		        }
  				//Check the last index
  				removePfinalIndex = Red.size() - 1;
  				//If it is the piece on the destination
  				if (Red.get(removePfinalIndex) == DestinationPiece)
		            {	
  						//Remove the piece
		                Red.remove(removePfinalIndex);
		                RedIcons.remove(removePfinalIndex);
		            }
  			}
  			//If the piece to capture is blue
  			else
  			{
  				//Search for the piece
  		  		for (int k = 0; k < Blue.size() - 1; k++)
  		        {
  		  			//If this is the piece on the destination
  		  			if (Blue.get(k) == DestinationPiece)
  		            {
  		  				//Remove the piece
  		            	Blue.remove(k);
  		            	BlueIcons.remove(k);
  		            	break;
  		            }
  		        }
  		  		//Check the last index
  				removePfinalIndex = Blue.size() - 1;
  				//If this is the piece on the destination
  				if (Blue.get(removePfinalIndex) == DestinationPiece)
		            {
  						//Remove the piece
		                Blue.remove(removePfinalIndex);
		                BlueIcons.remove(removePfinalIndex);
		            }
  			 }
  		}
  	}
  	
  	/**
  	 * This method gets the piece selected
  	 * @param a The array list of pieces
  	 * @param x the row coordinate selected
  	 * @param y the column coordinate selected
  	 * @return the piece selected
  	 */
  	public Piece getSelectedP (ArrayList<Piece> a, int x, int y)
  	{
  		//Check all the pieces
  		for (int i = 0; i < a.size(); i++)
        {
  			//If this piece is on the selected coordinate
            if (a.get(i).getRow() == x && a.get(i).getCol() == y)
            {
            	//return the piece selected
                return a.get(i);
            }
        }
  		//There is no piece on the coordinate selected
        return null;
  	}
  	
  	/**
  	 * This method gets the piece selected
  	 * @param a The array list of Image Icons
  	 * @param x the row coordinate selected
  	 * @param y the column coordinate selected
  	 * @return the icon selected
  	 */
  	public ImageIcon getSelectedIcon (ArrayList<ImageIcon> a, int x, int y)
  	{
  		//Check all icons
  		for (int i = 0; i < a.size(); i++)
        {
  			//If the icon is in the button selected
            if (a.get(i) == squares[x][y].getIcon())
            {
            	//return the icon
            	return a.get(i);
            }
        }
  		
  		//There is no icon on the coordinate selected
        return null;
  	}
  	
  	/**
  	 * This method gets the piece that is on the coordinate that the selected piece is moving to
  	 * @param x The row coordinate the selected piece is moving to
  	 * @param y the column coordinate the selected piece is moving to
  	 * @return the piece on the coordinate
  	 */
  	public Piece getPiece (int x, int y)
  	{
  		//Integer to check the final index
  		int finalIndex;
  		
  		//Check Red pieces
  		for (int i = 0; i < Red.size() - 1; i++)
        {
  			//If a red piece is on this coordinate
            if (Red.get(i).getRow() == x && Red.get(i).getCol() == y)
            	//Return the piece
                return Red.get(i);
        }
  		
  		//Check the last index
  		finalIndex = Red.size() - 1;
  		if (Red.get(finalIndex).getRow() == x && Red.get(finalIndex).getCol() == y)
  			return Red.get(finalIndex);
  		
  		//Check Blue pieces
  		for (int i = 0; i < Blue.size() - 1; i++)
        {
  			//If a blue piece is on this coordinate
            if (Blue.get(i).getRow() == x && Blue.get(i).getCol() == y)
            	//return the blue piece
                return Blue.get(i);
        }
  		
  		//Check the last index
  		finalIndex = Blue.size() - 1;
  		if (Blue.get(finalIndex).getRow() == x && Blue.get(finalIndex).getCol() == y)
  			return Blue.get(finalIndex);
  		
  		return null;
  	}
  	
  	/**
  	 * This method adds the action listener movePiece
  	 * @param source the button clicked by the user
  	 * @param moveP the action listener movePiece
  	 */
  	public void Turns (Object source, movePiece moveP)
  	{
		//Tile with a piece is clicked
  		for (int i = 0; i < 7; i++)
  		{
  			for (int j = 0; j < 9; j++)
  			{	
  				//If this is the button clicked
  				if (source == squares[SelectedPiece.getRow()][SelectedPiece.getCol()])
				{
  					//add the action listener move
  					squares[i][j].addActionListener(moveP);
				}
  			}
  		}

  	}
}
