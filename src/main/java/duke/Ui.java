package duke;

public class Ui {
    /**
     * Prints a response to the user.
     *
     * @param lines A variable number of lines to be printed as a repsonse to the user.
     */
    public static void printResponse(String ... lines) {
        String LINE = "    --------------------------------------------------";
        String INDENTATION = "      ";
        System.out.println(LINE);
        for (String line : lines) {
            System.out.println(INDENTATION + line);
        }
        System.out.println(LINE);
    }
}
