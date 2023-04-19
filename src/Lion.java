/** The class Lion represents a Lion Piece.
  *
  * @author Ysabel Bondoc
  * @author Jaime Manalo
  * @version 1.0
*/
public class Lion extends Piece
{
	/**
	 * This constructor sets the attributes of the Lion piece
	 * @param red whether the Lion is a red or blue piece
	 * @param r the initial row location of the Lion on the board
	 * @param c the initial column location of the Lion on the board
	 */
	public Lion(boolean red, int r, int c) {
		super(red,"Lion",7,r,c);
	}
	
	/**
	 * This method checks where the Lion can move when there is no other piece on their destination
	 * @param r the row coordinate the piece is moving to
	 * @param c the column coordinate the piece is moving to
	 * @return whether the piece can move to this tile or not
	 */
    public boolean canMove(int r, int c)
	{
    	//river conditions
    	//top side river
    	if ((r == 1 || r == 2) && (c == 3 || c == 4 || c == 5))
    	{
    		return false;
    	}
    	
    	//bottom side river
    	if ((r == 4 || r == 5) && (c == 3 || c == 4 || c == 5))
    	{
    		return false;
    	}
    
    	
    	if (this.basicMove(r, c) == false)
    	{
    		return false;
    	}
    	
		return true;
		
	}
    
    /**
     * This method riverJump sets the conditions for the Lion to jump across the river
     */
	public boolean riverJump(int r, int c) 
	{
		//If the Lion is at the Left side of the River
		if (((this.getRow() == 1 || this.getRow() == 2 || this.getRow() == 4 || this.getRow() == 5) && (this.getCol() == 2)) && (r == this.getRow() && c == 6))
			return true;
		
		//If the Lion is at the Right side of the River
		if (((this.getRow() == 1 || this.getRow() == 2 || this.getRow() == 4 || this.getRow() == 5) && (this.getCol() == 6)) && (r == this.getRow() && c == 2))
			return true;
		
		//If the Lion is at the Top/Bottom of Map
		if (((this.getRow() == 0 || this.getRow() == 6) && (this.getCol() == 3 || this.getCol() == 4|| this.getCol() == 5)) && (r == 3 && c == this.getCol()))
			return true;
		
		//If the Lion is at the Middle Land
		if ((this.getRow() == 3 && (this.getCol() == 3 || this.getCol() == 4|| this.getCol() == 5)) && ((r == 0 || r ==  6) && c == this.getCol()))
			return true;
		
		return false;
	}
	
    /**
     * This method riverJumpMouse sets the conditions for the Lion to jump across the river if there is a mouse on the river
     */
	public boolean riverJumpMouse(int r, int c, Piece p) {
    	//river jump conditions
    	//On left side river
    	if (((this.getRow() == 1 || this.getRow() == 2 || this.getRow() == 4 || this.getRow() == 5) && (this.getCol() == 2)) && (r == this.getRow() && c == 6))
		{
    			//MOUSE CONDITIONS
        		if(this.getRow() == 1)
        		{
        			if (p.getRow() == 1 && (p.getCol() == 3 || p.getCol() == 4 || p.getCol() == 5))
        				return false;
        		}
        		
        		if (this.getRow() == 2)
        		{
        			if (p.getRow() == 2 && (p.getCol() == 3 || p.getCol() == 4 || p.getCol() == 5))
        				return false;
        		}
        		
        		if(this.getRow() == 4)
        		{
        			if (p.getRow() == 4 && (p.getCol() == 3 || p.getCol() == 4 || p.getCol() == 5))
        				return false;
        		}
        		
        		if (this.getRow() == 5)
        		{
        			if (p.getRow() == 5 && (p.getCol() == 3 || p.getCol() == 4 || p.getCol() == 5))
        				return false;
        		}
	
		    return true;
		}
	    	
    	//On right side river
    	if (((this.getRow() == 1 || this.getRow() == 2 || this.getRow() == 4 || this.getRow() == 5) && (this.getCol() == 6)) && (r == this.getRow() && c == 2))
    	{
    		//MOUSE CONDITIONS
        		if(this.getRow() == 1)
        		{
        			if (p.getRow() == 1 && (p.getCol() == 3 || p.getCol() == 4 || p.getCol() == 5))
        				return false;
        		}
        		
        		if (this.getRow() == 2)
        		{
        			if (p.getRow() == 2 && (p.getCol() == 3 || p.getCol() == 4 || p.getCol() == 5))
        				return false;
        		}
        		
        		if(this.getRow() == 4)
        		{
        			if (p.getRow() == 4 && (p.getCol() == 3 || p.getCol() == 4 || p.getCol() == 5))
        				return false;
        		}
        		
        		if (this.getRow() == 5)
        		{
        			if (p.getRow() == 5 && (p.getCol() == 3 || p.getCol() == 4 || p.getCol() == 5))
        				return false;
        		}
    		return true;
    	}
    	
    	//On top side or bottom side of river
    	if (((this.getRow() == 0 || this.getRow() == 6) && (this.getCol() == 3 || this.getCol() == 4|| this.getCol() == 5)) && (r == 3 && c == this.getCol()))
    	{
    		//MOUSE CONDITIONS
        		if (this.getCol() == 3 && this.getRow() == 0)
        		{
        			if (p.getCol() == 3 && (p.getRow() == 1 || p.getRow() == 2))
        				return false;
        		}
        		
        		if (this.getCol() == 3 && this.getRow() == 6)
        		{
  
        			if (p.getCol() == 3 && (p.getRow() == 4 || p.getRow() == 5))
        				return false;
        		}
        		
        		if (this.getCol() == 4 && this.getRow() == 0)
        		{
        			if (p.getCol() == 4 && (p.getRow() == 1 || p.getRow() == 2))
        				return false;
        		}
        		
        		if (this.getCol() == 4 && this.getRow() == 6)
        		{
        			if (p.getCol() == 4 && (p.getRow() == 4 || p.getRow() == 5))
        				return false;
        		}
        		
        		if (this.getCol() == 5 && this.getRow() == 0)
        		{
        			if (p.getCol() == 5 && (p.getRow() == 1 || p.getRow() == 2))
        				return false;
        		}
        		
        		if (this.getCol() == 5 && this.getRow() == 6)
            	{
        			if (p.getCol() == 5 && (p.getRow() == 4 || p.getRow() == 5))
        				return false;
        		}
        		
    		return true;
    	}
    	
    	//in the mid lands
    	if ((this.getRow() == 3 && (this.getCol() == 3 || this.getCol() == 4|| this.getCol() == 5)) && ((r == 0 || r ==  6) && c == this.getCol()))
    	{
    		//MOUSE CONDITIONS
        		if (this.getCol() == 3 && r == 0)
        		{
        			if (p.getCol() == 3 && (p.getRow() == 1 || p.getRow() == 2))
        				return false;
        		}
        		
        		if (this.getCol() == 3 && r == 6)
        		{
        			if (p.getCol() == 3 && (p.getRow() == 4 || p.getRow() == 5))
        				return false;
        		}
        		
        		if (this.getCol() == 4 && r == 0)
        		{
        			if (p.getCol() == 4 && (p.getRow() == 1 || p.getRow() == 2))
        				return false;
        		}
        		
        		if (this.getCol() == 4 && r == 6)
        		{
        			if (p.getCol() == 4 && (p.getRow() == 4 || p.getRow() == 5))
        				return false;
        		}
        		
        		if (this.getCol() == 5 && r == 0)
        		{
        			if (p.getCol() == 5 && (p.getRow() == 1 || p.getRow() == 2))
        				return false;
        		}
        		
        		if (this.getCol() == 5 && r == 6)
        		{
        			if (p.getCol() == 5 && (p.getRow() == 4 || p.getRow() == 5))
        				return false;
        		}
        		
    		return true;
    	}
    	
    	return false;
 
    }
    
	/**
     * This method sets the abstract returnRank method to set the rank of this piece to 7
     */
    public void returnRank() {
    	this.setRank(7);
    }
}