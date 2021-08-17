import java.util.Scanner;

public class Duke {
    // Saved tasks
    private static final String[] tasks = new String[100];

    // Index in the tasks array of the next task
    private static int nextTaskIndex = 0;

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
    private static void greetings() {
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
            if (currentCommand.equals("list")) {
                insertSeparateLine();
                for (int i = 0; i < 100; i++) {
                    if (!(tasks[i] == null)) {
                        displayContent((i + 1) + ". " + tasks[i]);
                    } else {
                        break;
                    }
                }
                insertSeparateLine();
            } else {
                tasks[nextTaskIndex] = currentCommand;
                nextTaskIndex++;
                displayContentBetweenLines("added: " + currentCommand);
            }
            currentCommand = sc.nextLine();
        }

        displayContentBetweenLines("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
