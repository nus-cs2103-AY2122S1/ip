package duke;

import duke.task.Task;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * This class contains some common utility methods regrading text ui.
 */
public class Ui {
    /** Text UI: horizontal line. */
    public static final String HORIZONTAL_LINE = "  -----------------------";
    /** Text UI: indentation. */
    public static final String INDENTATION = "    ";
    /** Text UI: date time format. */
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy");

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
     * @param s The given string.
     */
    public static void showMessage(String s) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(INDENTATION + s);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints add task message and the number of tasks in the list.
     *
     * @param task The task added.
     * @param size The number of tasks in the list.
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
     * @param task The task removed
     * @param size The number of tasks in the list.
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

    /**
     * Prints all the matching tasks in the given task list.
     * 
     * @param taskList The given task list.
     */
    public static void showFindTasks(List<Task> taskList) {
        System.out.println(Ui.HORIZONTAL_LINE);
        System.out.println(Ui.INDENTATION + "Here are the matching tasks in your list:");
        if (taskList.isEmpty()) {
            System.out.println(Ui.INDENTATION + "No matching tasks");
        } else {
            for (int i = 0; i < taskList.size(); ++i) {
                System.out.println(Ui.INDENTATION + (i + 1) + ". " + taskList.get(i).toString());
            }
        }
        System.out.println(Ui.HORIZONTAL_LINE);
    }

    /**
     * Prints all the tasks in the given task list.
     * 
     * @param tasks The given task list.
     */
    public static void showTasks(List<Task> tasks) {
        System.out.println(Ui.HORIZONTAL_LINE);
        System.out.println(Ui.INDENTATION + "Here are the tasks in your list:");
        if (tasks.isEmpty()) {
            System.out.println(Ui.INDENTATION + "No tasks");
        } else {
            for (int i = 0; i < tasks.size(); ++i) {
                System.out.println(Ui.INDENTATION + (i + 1) + ". " + tasks.get(i).toString());
            }
        }
        System.out.println(Ui.HORIZONTAL_LINE);
    }
}
