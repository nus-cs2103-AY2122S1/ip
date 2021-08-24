package duke;

import java.util.ArrayList;

public class Ui {
    public String println(String inputString) {
        return "     --------------------\n     " + inputString + "\n     --------------------";
    }

    /**
     * Uses a line-wise input array of Strings and formats it for output.
     * @param inputStrings
     * @return String of output.
     */
    public String println(ArrayList<String> inputStrings) {
        String returnBuffer = "     --------------------\n";
        for (String line : inputStrings) {
            returnBuffer = returnBuffer.concat("     " + line + "\n");
        }
        return returnBuffer + "     --------------------";
    }
}
