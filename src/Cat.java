/** The class Cat represents a Cat Piece.
  *
  * @author Ysabel Bondoc
  * @author Jaime Manalo
  * @version 1.0
*/
public class Cat extends Piece
{
	/**
	 * This constructor sets the attributes of the Cat piece
	 * @param red whether the cat is a red or blue piece
	 * @param r the initial row location of the cat on the board
	 * @param c the initial column location of the cat on the board
	 */
	public Cat(boolean red, int r, int c) 
	{
		//Set piece's name to Cat and rank to 2
		super(red,"Cat",2,r,c);
	}
	
	/**
	 * This method checks where the Cat can move when there is no other piece on their destination
	 * @param r the row coordinate the piece is moving to
	 * @param c the column coordinate the piece is moving to
	 * @return whether the piece can move to this tile or not
	 */
    public boolean canMove(int r, int c)
	{
    	//Check basic movement
    	if (this.basicMove(r,c) == false)
    		return false;
    	
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
    	
		return true;
	}
    
    /**
     * This method sets the abstract riverJump method to return false
     */
	public boolean riverJump(int r, int c) 
	{
		//Cats cannot jump through the river
		return false;
	}
	
	/**
	 * This method sets the abstract riverJumpMouse method to return false
	 */
	public boolean riverJumpMouse(int r, int c, Piece p) {
		//Cats cannot jump through the river
		return false;
	}	
	
    /**
     * This method sets the abstract returnRank method to set the rank of this piece to 2 
     */
    public void returnRank() {
    	this.setRank(2);
    }
}