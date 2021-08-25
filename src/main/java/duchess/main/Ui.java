package duchess.main;

/**
 * This class handles the UI specific elements of Duchess.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */


public class Ui {
    /** The horizontal bars to add style in the output.*/
    private static final String HORIZONTAL_BARS = "\n____________________________________________________________\n";

    /**
     * Prints to System.out fancily including horizontal bars ontop and bottom.
     * @param input String to be printed fancily.
     */
    public void prettyPrint(String input)
    {
        System.out.println(HORIZONTAL_BARS + input + HORIZONTAL_BARS);
    }

    /**
     * Prints a message given for invalid inputs
     */
    public void printError()
    {
        prettyPrint("Apologies, I didn't catch that.");
    }
}
