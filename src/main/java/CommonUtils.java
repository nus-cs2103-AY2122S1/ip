public class CommonUtils {
    protected static final String HORIZONTAL_LINE = "  -----------------------";
    protected static final String INDENTATION = "    ";

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

    public static void showMessage(String s) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + s);
        System.out.println(HORIZONTAL_LINE);
    }

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

    public static void bye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
