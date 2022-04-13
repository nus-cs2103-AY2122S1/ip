package duke;

import java.util.ArrayList;

/**
 * Represents the interface that a user uses to interact with
 * a <code>Duke</code> object.
 */
public class Ui {
    static final String STYLE_LINE = "--------------------\n";
    static final String STYLE_INDENT = "     ";
    private String buffer;

    /**
     * Prints to output. Styles the input before printing.
     * 
     * @param inputString Input String to be printed.
     */
    public void println(String inputString) {
        this.buffer = inputString;
    }

    /**
     * Uses a line-wise input array of Strings and styles it for output.
     * 
     * @param inputStrings ArrayList of Strings to be printed.
     */
    public void println(ArrayList<String> inputStrings) {
        assert inputStrings.size() != 0;
        String returnBuffer = "";
        for (String line : inputStrings) {
            returnBuffer = returnBuffer.concat("     " + line + "\n");
        }
        this.buffer=  returnBuffer;
    }

    public String flushBuffer() {
        assert this.buffer.length() != 0;
        String save = this.buffer;
        this.buffer = "";
        return save;
    }
}
