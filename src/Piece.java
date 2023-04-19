/** The abstract class Piece represents a Piece.
  *
  * @author Ysabel Bondoc
  * @author Jaime Manalo
  * @version 1.0
*/
public abstract class Piece
{	
	//Attributes
	
	/** The Color of the piece*/
	private boolean isRed;
	/** Boolean whether the piece is trapped or not*/
	private boolean isTrapped = false;
	/** How long the piece has been trapped*/
	private int trapCtr = 0;
	/** The rank of the piece*/
	private int rank;
	/** The row coordinate of the piece*/
	private int row;
	/** The col coordinate of the piece*/
	private int col;
	/** The name of the piece*/
	private String name;
	
	//Methods
	
	/**
	 * This constructor sets the piece's basic attributes
	 * @param red The color of the piece
	 * @param n the name of the piece
	 * @param rk the rank of the piece
	 * @param r the row coordinate of the piece
	 * @param c the column coordinate of the piece
	 */
	public Piece(boolean red, String n, int rk, int r, int c) 
	{
		isRed = red;
		name = n;
		rank = rk;
		this.row = r;
		this.col = c;
		
	}
	
	/**
	 * This method returns the name of the piece.
	 * @return name of the piece
	 */
	public String toString() 
	{
		return name;
	}
	
	/**
	 * This method returns the color of the piece
	 * @return the color of the piece
	 */
	public boolean getColor() 
	{
		return isRed;
	}
	
	/**
	 * This method returns the rank of the piece
	 * @return the rank of the piece
	 */
	public int getRank() 
	{
		return rank;
	}
	
	/**
	 * This method returns the row coordinate of the piece
	 * @return row coordinate of the piece
	 */
	public int getRow()
	{
		return row;
	}
	
	/**
	 * This method returns the column coordinate of the piece
	 * @return column coordinate of the piece
	 */
	public int getCol()
	{
		return col;
	}
	
	/**
	 * This method returns whether the piece is trapped or not
	 * @return the boolean whether the piece is trapped or not
	 */
	public boolean getTrapped()
	{
		return isTrapped;
	}
	
	/**
	 * This method returns the trap counter of the piece
	 * @return the trap counter of the piece
	 */
	public int getTrapCtr()
	{
		return trapCtr;
	}
	
	/**
	 * This method adds 1 to the piece's trap counter
	 */
	public void addTrapCtr()
	{
		trapCtr++;
	}
	
	/**
	 * This method sets the boolean isTrapped to either true or false
	 * @param t boolean whether the piece is trapped or not
	 */
	public void setTrapped(boolean t)
	{
		this.isTrapped = t;
	}
	
	/**
	 * This method sets the rank of the piece
	 * @param rk the new rank of the piece
	 */
	public void setRank(int rk)
	{
		this.rank = rk;
	}
	
	/**
	 * This method sets the row coordinate of the piece
	 * @param r the new row coordinate of the piece
	 */
	public void setRow (int r)
	{
		this.row = r;
	}
	
	/**
	 * This method sets the column coordinate of the piece
	 * @param c the new column coordinate of the piece
	 */
	public void setCol (int c)
	{
		this.col = c;
	}
	
	/**
	 * This method sets the conditions for the basic movement of the piece
	 * @param r the row coordinate the piece is moving to
	 * @param c the column coordinate the piece is moving to
	 * @return whether the piece can move to this tile or not
	 */
	public boolean basicMove(int r, int c)
	{	
		//Piece cannot move on itself
		if (this.row == r && this.col == c)
		{
			return false;
		}
		
		//Piece cannot move more than 1 tile UP, DOWN, LEFT, or RIGHT
    	if (c > this.getCol() + 1 || c < this.getCol() - 1)
    	{
    		return false;
    	}
	
    	if (r > this.getRow() + 1 || r < this.getRow() - 1)
    	{
    		return false;
    	}
    	
    	//Piece cannot move diagonally
    	if ((r == this.getRow() - 1 && c == this.getCol() - 1) || (r == this.getRow() - 1 && c == this.getCol() + 1) 
    			|| (r == this.getRow() + 1 && c == this.getCol() + 1) || (r == this.getRow() + 1 && c == this.getCol() - 1))
    				return false;
    	
    	//Piece cannot move to it's own Den
    	if ((this.getColor() == true && (r == 3 && c == 0)) || (this.getColor() == false && (r == 3 && c == 8)))
    	{
    		return false;
    	}
    	
    	//return true if the piece follows all the conditions
    	return true;
    	
	}
	
	/**
	 * This method returns whether the piece is on an opponent's trap
	 * @param r the row coordinate the piece is moving to
	 * @param c the column coordinate the piece is moving to
	 * @return whether the piece is on a trap or not
	 */
	public boolean onTrap(int r, int c)
    {
        //If a blue piece steps on the red teams trap
        if ((this.getColor() == false) && ((c == 0 && (this.getRow() == 2 || r == 4)) || (c == 1 && r == 3))) 
        {
            return true;
        }
        
        //If a red piece steps on the blue teams trap
        if ((this.getColor() == true) && ((c == 8 && (r == 2 || r == 4)) || (c == 7 && r == 3))) 
        {
            return true;
        }
        
        //If the piece does not step on a trap return false
        return false;
    }
	
	/**
	 * This method checks whether this piece can capture another piece
	 * @param piece the piece to be captured
	 * @return whether the piece can be captured or not
	 */
	public boolean canCapture (Piece piece) 
	{
		//If the piece is not your own
		if (this.getColor() != piece.getColor())
		{
			//If the piece is an elephant
			if (this.toString().equalsIgnoreCase("Elephant"))
			{
				//If the piece's rank is lower than yours and it is not a mouse you can capture it
				if (piece.getRank() <= 8 && piece.getRank() != 1)
					return true;
				else
					return false;
			}
			//If the piece is a mouse
			else if (this.toString().equalsIgnoreCase("Mouse"))
			{
				//If the piece is an elephant and it's rank is lower than yours, you can capture it
				if (piece.getRank() == 8 || piece.getRank() <= 1)
					return true;
				else
					return false;
			}
			
			//If your piece is not a mouse nor elephant capture any piece with a lower rank
			if (piece.getRank() <= this.getRank())
				return true;
		}
		
		//If the piece is one of your own or it's rank is higher than your piece, you cannot capture it
		return false;
	}
	
	/**
	 * This method checks if the piece can move to the location where another piece is currently located
	 * @param r the row coordinate the piece is moving to
	 * @param c the column coordinate the piece is moving to
	 * @param p the piece that is on the coordinate this piece is moving to
	 * @return whether the piece can move and capture the piece on the coordinate it is moving to
	 */
	public boolean canMovePiece(int r, int c, Piece p)
	{
		if (this.toString().equalsIgnoreCase("Mouse"))
		{
			//If this mouse can move to the coordinate selected
	    	if (this.canMove(r, c) == true)
	    	{
	    		//If the other piece is on the coordinate selected
	    		if (p.getRow() == r && p.getCol() == c)
				{
	    			//If this cannot capture the other piece return false
					if (this.canCapture(p) == false) 
					{
						return false;
					}
					//If the mouse can capture the other piece
					if (this.canCapture(p) == true)
					{
						//Check whether this mouse is on the top river, if so, this mouse cannot capture the piece
				    	if ((this.getRow() == 1 || this.getRow() == 2) && (this.getCol() == 3 || this.getCol() == 4 || this.getCol() == 5))
				    	{
				    		//If piece to be captured is on land
				    		if ((p.getCol() >= 2 || p.getCol() <= 6) && (p.getRow() == 0 || p.getRow() == 3))
				    			return false;
				    	}
				    	
				    	//Check whether this mouse is on the bottom river, if so, this mouse cannot capture the piece
				    	if ((this.getRow() == 4 || this.getRow() == 5) && (this.getCol() == 3 || this.getCol() == 4 || this.getCol() == 5))
				    	{
				    		//If piece to be captured is on land
				    		if ((p.getCol() >= 2 || p.getCol() <= 6) && (p.getRow() == 3 || p.getRow() == 6))
				    			return false;
				    	}
				    	
				    	//Check if this mouse is on land near the river
				    	if ((this.getCol() >= 2 || this.getCol() <= 6) && (this.getRow() == 0 || this.getRow() == 3 || this.getRow() == 6))
				    	{
				    		//If opponent is in the top river
				    		if ((p.getRow() == 1 || p.getRow() == 2) && (p.getCol() == 3 || p.getCol() == 4 || p.getCol() == 5))
				    			return false;
				    		
				    		//If opponent is in the bottom river
				    		if ((p.getRow() == 4 || p.getRow() == 5) && (p.getCol() == 3 || p.getCol() == 4 || p.getCol() == 5))
				    			return false;
				    	}
			    			
					}
				}
	    		
	    	}
		}
		else if (this.toString().equalsIgnoreCase("Tiger") || (this.toString().equalsIgnoreCase("Lion")))
		{
			//if piece is on potential position
	    	if (this.canMove(r, c) == true || this.riverJumpMouse(r, c, p) == true)
	    	{
	    		if (p.getRow() == r && p.getCol() == c)
				{
					if (this.canCapture(p) == false) 
					{
						return false;
					}
				}
	    	}
		}
		else
		{
			//If this can move to the coordinate selected
	    	if (this.canMove(r, c) == true)
	    	{
	    		//If the other piece is on the coordinate selected
	    		if (p.getRow() == r && p.getCol() == c)
				{
	    			//If this cannot capture the other piece return false
					if (this.canCapture(p) == false) 
					{
						return false;
					}
				}
	    	}
		}
		return true;
	}

	/**
	 * This abstract method returns the rank of the piece to it's original rank
	 */
	public abstract void returnRank();
	
	/**
	 * This abstract method checks where the piece can move when there is no other piece on their destination
	 * @param r the row coordinate the piece is moving to
	 * @param c the column coordinate the piece is moving to
	 * @return whether the piece can move to this tile or not
	 */
	public abstract boolean canMove(int r, int c);
	
	/**
	 * This abstract method checks whether the piece can jump through an empty river
	 * @param r the row coordinate the piece is moving to
	 * @param c the column coordinate the piece is moving to
	 * @return whether the piece can jump the river
	 */
	public abstract boolean riverJump(int r, int c);
	
	/**
	 * This abstract method checks whether the piece can jump through the river if there is a piece on the river
	 * @param r the row coordinate the piece is moving to
	 * @param c the column coordinate the piece is moving to
	 * @param p the piece that is on the river
	 * @return whether the piece can jump through the river
	 */
	public abstract boolean riverJumpMouse(int r, int c, Piece p);
}