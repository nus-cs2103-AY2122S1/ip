package duke;

public class Ui {
    /**
     * Prints a response to the user.
     *
     * @param lines A variable number of lines to be printed as a repsonse to the user.
     */
    public static String getResponse(String ... lines) {
        final String LINE = "    --------------------------------------------------\n";
        final String INDENTATION = "      ";
        String stringToPrint = "";
        stringToPrint += LINE;
        for (String line : lines) {
            stringToPrint += (INDENTATION + line + "\n");
        }
        stringToPrint += LINE;
        return stringToPrint;
    }
}
