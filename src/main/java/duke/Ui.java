package duke;

import java.util.ArrayList;

/**
 * Represents the interface that a user uses to interact with
 * a <code>Duke</code> object.
 */
public class Ui {
    static final String STYLE_LINE = "--------------------\n";
    static final String STYLE_INDENT = "     ";

    /**
     * Prints to output. Styles the input before printing.
     * 
     * @param inputString Input String to be printed.
     */
    public void println(String inputString) {
        System.out.print(STYLE_INDENT + STYLE_LINE +
                inputString + "\n" + STYLE_INDENT + STYLE_LINE);
    }

    /**
     * Uses a line-wise input array of Strings and styles it for output.
     * 
     * @param inputStrings ArrayList of Strings to be printed.
     */
    public void println(ArrayList<String> inputStrings) {
        String returnBuffer = STYLE_INDENT + STYLE_LINE;
        for (String line : inputStrings) {
            returnBuffer = returnBuffer.concat("     " + line + "\n");
        }
        System.out.print(returnBuffer + STYLE_INDENT + STYLE_LINE);
    }
}
