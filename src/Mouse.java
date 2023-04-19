/** The class Mouse represents a Mouse Piece.
  *
  * @author Ysabel Bondoc
  * @author Jaime Manalo
  * @version 1.0
*/
public class Mouse extends Piece
{
	/**
	 * This constructor sets the attributes of the Mouse piece
	 * @param red whether the Mouse is a red or blue piece
	 * @param r the initial row location of the Mouse on the board
	 * @param c the initial column location of the Mouse on the board
	 */
	public Mouse(boolean red, int r, int c) {
		super(red,"Mouse",1,r,c);
	}
	
	/**
	 * This method checks where the Mouse can move when there is no other piece on their destination
	 * @param r the row coordinate the piece is moving to
	 * @param c the column coordinate the piece is moving to
	 * @return whether the piece can move to this tile or not
	 */
    public boolean canMove(int r, int c)
	{
    	
    	if (this.basicMove(r,c) == false)
    		return false;
    	
    	
		return true;
	}
	
    /**
     * This method sets the abstract riverJump method to return false
     */
	public boolean riverJump(int r, int c) {
		return false;
	}
	
	/**
	 * This method sets the abstract riverJumpMouse method to return false
	 */
	public boolean riverJumpMouse(int r, int c, Piece p) {
		return false;
	}	
    
	/**
     * This method sets the abstract returnRank method to set the rank of this piece to 1
     */
    public void returnRank() {
    	this.setRank(1);
    }
}