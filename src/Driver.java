/** The class Driver runs the game.
  *
  * @author Ysabel Bondoc
  * @author Jaime Manalo
  * @version 1.0
*/
public class Driver 
{
	/** This method is the main method of the program
	 * 
	 * @param args the command line arguments
	 */
	public static void main (String[] args)
	{
		//Calls the Shuffle GUI
		ShuffleGUI Gui = new ShuffleGUI();
		Gui.frame.setVisible(true);
	}
}
