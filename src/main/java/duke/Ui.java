package duke;

import java.util.ArrayList;

public class Ui {
    static final String STYLE_LINE = "--------------------\n";
    static final String STYLE_INDENT = "     ";

    public void println(String inputString) {
        System.out.print(STYLE_INDENT + STYLE_LINE +
                inputString + "\n" + STYLE_INDENT + STYLE_LINE);
    }

    /**
     * Uses a line-wise input array of Strings and formats it for output.
     * @param inputStrings
     * @return String of output.
     */
    public void println(ArrayList<String> inputStrings) {
        String returnBuffer = STYLE_INDENT + STYLE_LINE;
        for (String line : inputStrings) {
            returnBuffer = returnBuffer.concat("     " + line + "\n");
        }
        System.out.print(returnBuffer + STYLE_INDENT + STYLE_LINE);
    }
}
