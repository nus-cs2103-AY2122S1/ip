package duke;

import duke.task.Task;

public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private static final String LOGO = "\t____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING_TEXT = "\tHello from \n"
            + LOGO
            + "\tHow can I help you?";

    private static final String FAREWELL_TEXT = "\t☹☹☹ Why do you choose to leave me!";

    public static void printMessage(String message) {
        System.out.println("\t" + HORIZONTAL_LINE + "\n" + message + "\n\t" + HORIZONTAL_LINE);
    }

    public static void greet() {
        printMessage(GREETING_TEXT);
    }

    public static void farewell() {
        printMessage(FAREWELL_TEXT);
    }

    public static void addTaskMessage(TaskList tasks, Task task) {
        String message = "\tGot it. I've added this task:\n\t\t"
                + task
                + "\n\tduke.task.Task(s) remaining in the list: "
                + tasks.size();
        printMessage(message);
    }

    public static void removeTaskMessage(TaskList tasks, int item) {
        int tasksSize = tasks.size() - 1;
        String message = "\tNoted. I've removed this task:\n\t\t"
                + tasks.getTask(item - 1)
                + "\n\tduke.task.Task(s) remaining in the list: "
                + tasksSize;
        printMessage(message);
    }

    public static void taskDoneMessage(TaskList tasks, int item) {
        String message = "\tNice! I've marked this task as done:\n\t\t"
                + tasks.getTask(item - 1);
        printMessage(message);
    }

    public static void reportError(String error) {
        printMessage("\t" + error);
    }

    public static void reportError(Exception e) {
        printMessage("\t" + e.getMessage());
    }
}
