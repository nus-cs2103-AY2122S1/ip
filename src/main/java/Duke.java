import java.util.Scanner;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author Chng Zi Hao
 */
public class Duke {
    // Constants for the program
    static final String DIVIDER = "--------------------------------------------------------------------------------";
    static final String PROMPT = "Enter Command: ";
    static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static Scanner sc = new Scanner(System.in);
    static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        format(LOGO + "\nHello! I'm Duke\n" + "What can I do for you?");
        System.out.print(PROMPT);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            if (str.equals("list")) {
                taskList.printTaskList();
            } else{
                format(taskList.addTask(str));
            }
            System.out.print(PROMPT);
            str = sc.nextLine();
        }
        format("Bye. Hope to see you again soon!");
    }

    /**
     * Formats the input and prints it in a formatted version.
     *
     * @param input Input to be printed.
     */
    public static void format(String input) {
        System.out.println(DIVIDER);
        System.out.println(input);
        System.out.println(DIVIDER);
    }
}
