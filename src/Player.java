/** The class Player represents the player's data.
*
* @author Ysabel Bondoc
* @author Jaime Manalo
* @version 1.0
*/
public class Player 
{
	//Attributes
	
	/** Color of the player*/
	private boolean isRed;
	/** Number of the player*/
	private int number;
	/** Rank of the piece they got during shuffling*/
	private int rankOfPiece;
	
	/**
	 * This constructor sets the rank of the piece the player got during initialization
	 * @param n rank of the piece
	 */
	public Player(int n)
	{
		rankOfPiece = n;
	}
	
	/**
	 * This method sets the number of the player
	 * @param n the number of the player
	 */
	public void setNumber(int n)
	{
		number = n;
	}
	
	/**
	 * This method gets the number of the player
	 * @return the number of the player
	 */
	public int getNumber()
	{
		return number;
	}
	
	/**
	 * This method sets the color of the player
	 * @param r the color of the player
	 */
	public void setColor(boolean r)
	{
		isRed = r;
	}
	
	/**
	 * This method gets the color of the player
	 * @return the color of the player
	 */
	public boolean getColor()
	{
		return isRed;
	}
	
	/**
	 * This method returns the rank of the piece the player got
	 * @return the rank of the piece of the player
	 */
	public int getRankofPiece()
	{
		return rankOfPiece;
	}
}
