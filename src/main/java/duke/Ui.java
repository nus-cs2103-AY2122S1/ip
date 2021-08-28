package duke;

import java.util.ArrayList;

/**
 * Represents the interface that a user uses to interact with
 * a <code>Duke</code> object.
 */
public class Ui {
    /**
     * Prints to output. Styles the input before printing.
     * 
     * @param inputString Input String to be printed.
     */
    public void println(String inputString) {
        System.out.println("     --------------------\n     " + inputString + "\n     --------------------");
    }

    /**
     * Uses a line-wise input array of Strings and styles it for output.
     * 
     * @param inputStrings ArrayList of Strings to be printed.
     */
    public void println(ArrayList<String> inputStrings) {
        String returnBuffer = "     --------------------\n";
        for (String line : inputStrings) {
            returnBuffer = returnBuffer.concat("     " + line + "\n");
        }
        System.out.println(returnBuffer + "     --------------------");
    }
}
