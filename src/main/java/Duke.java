import java.util.Scanner;

public class Duke {
    /**
     * Print out the separation line between elements of the program
     */
    private static void insertSeparateLine() {
        String separateLine = "____________________________________________________________";
        System.out.println("\t" + separateLine);
    }

    /**
     * Print out the formatted version of any string content
     *
     * @param content Content to display.
     */
    private static void displayContent(String content) {
        System.out.println("\t" + " " + content);
    }

    /**
     * Print out the formatted version of any string content between two horizontal lines
     *
     * @param content Content to display.
     */
    private static void displayContentBetweenLines(String content) {
        insertSeparateLine();
        System.out.println("\t" + " " + content);
        insertSeparateLine();
    }

    /**
     * Print out the greetings to the user
     */
    public static void greetings() {
        String logo = "\t  ____        _        \n"
                + "\t |  _ \\ _   _| | _____ \n"
                + "\t | | | | | | | |/ / _ \\\n"
                + "\t | |_| | |_| |   <  __/\n"
                + "\t |____/ \\__,_|_|\\_\\___|\n";

        insertSeparateLine();
        System.out.println(logo);
        displayContent("Hello! I'm Duke");
        displayContent("What can I do for you?");
        insertSeparateLine();
    }

    public static void main(String[] args) {
        Duke.greetings();

        Scanner sc = new Scanner(System.in);
        String currentCommand = sc.nextLine();
        while (!currentCommand.equals("bye")) {
            displayContentBetweenLines(currentCommand);
            currentCommand = sc.nextLine();
        }

        displayContentBetweenLines("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
