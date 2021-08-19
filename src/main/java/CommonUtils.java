/**
 * This class contains some common utility methods regrading text ui.
 */
public class CommonUtils {
    /** Text UI: horizontal line. */
    protected static final String HORIZONTAL_LINE = "  -----------------------";
    /** Text UI: indentation. */
    protected static final String INDENTATION = "    ";

    /**
     * Prints greeting message.
     */
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Hello, I am Duke.");
        System.out.println(INDENTATION + "What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the given string with horizontal line and indentation.
     *
     * @param s the given string.
     */
    public static void showMessage(String s) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + s);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints add task message and the number of tasks in the list.
     *
     * @param task the task added.
     * @param size the number of tasks in the list.
     */
    public static void showAddTaskMessage(Task task, int size) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + task.toString());
        if (size > 1) {
            System.out.println(INDENTATION + "Now you have " + size + " tasks in the list.");
        } else {
            System.out.println(INDENTATION + "Now you have " + size + " task in the list.");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints remove task message and the number of tasks in the list.
     *
     * @param task the task removed
     * @param size the number of tasks in the list.
     */
    public static void showRemoveTaskMessage(Task task, int size) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Got it. I've removed this task:");
        System.out.println(INDENTATION + task.toString());
        if (size > 1) {
            System.out.println(INDENTATION + "Now you have " + size + " tasks in the list.");
        } else {
            System.out.println(INDENTATION + "Now you have " + size + " task in the list.");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints bye message.
     */
    public static void bye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
