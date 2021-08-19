/**
 * Utility to help print out the responses of Duke
 */
public class PrintUtil {
    /**
     * Print out the separation line between elements of the program
     */
    public static void insertSeparateLine() {
        String separateLine = 
                "___________________________________________________________________________________";
        System.out.println("\t" + separateLine);
    }

    /**
     * Print out the formatted version of any string content
     *
     * @param content Content to display.
     */
    public static void displayContent(String content) {
        System.out.println("\t" + " " + content);
    }

    /**
     * Print out the formatted version of any string content between two horizontal lines
     *
     * @param content Content to display.
     */
    public static void displayContentBetweenLines(String content) {
        insertSeparateLine();
        System.out.println("\t" + " " + content);
        insertSeparateLine();
    }
}
