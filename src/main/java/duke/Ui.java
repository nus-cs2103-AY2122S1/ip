package duke;

public class Ui {

    private static void printLine() {
        for (int i = 0; i < 20; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Prints initial headers and greetings.
     */
    public static void initialize() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printStatement("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    /**
     * Out puts a statement, formatted with dividing lines before and after.
     * @param statement Statement to be printed.
     */

    public static void printStatement(String statement) {
        System.out.println();
        printLine();
        System.out.println(statement);
        System.out.println();
        printLine();
        System.out.println();
    }

    /**
     * Alternative printing methods, which prepends OOPS! for printing of error messages
     * @param errorMsg Error message to be printed.
     */
    public static void printError(String errorMsg) {
        printStatement("OOPS! " + errorMsg);
    }
}
