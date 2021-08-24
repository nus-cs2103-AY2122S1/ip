package duke;

/**
 * Class handles printing message to user
 */
public class Ui {
    public Ui() {
    }

    /**
     * Print the line
     */
    public static void printLine() {
        System.out.println("--------------------------------------------------");
    }

    public static void printBigIcon() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
    }

    /**
     * Print the welcome message
     */
    public static void printWelcome() {
        Ui.printLine();
        System.out.println("Hello! I'm Duke.Duke\nWhat can I do for you?");
        printLine();
    }

}
