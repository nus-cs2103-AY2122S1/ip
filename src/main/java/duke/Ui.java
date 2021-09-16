package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Represents the Ui of Duke.
 */
public class Ui {
    public static final String LINE = "____________________________________________________________";
    private final Scanner sc;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints message in same style as other outputs of Duke.
     *
     * @param message Message to be printed in the same style as other
     *                outputs of Duke.
     */
    public void print(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    /**
     * Prints out TaskList in a form of a list for users to see.
     *
     * @param dukeList DukeList to be printed.
     */
    public void print(DukeList dukeList) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your " + dukeList.type() + ":");

        for (int i = 0; i < dukeList.getSize(); i++) {
            System.out.println((i + 1) + "." + dukeList.taskToString(i));

        }
        System.out.println(LINE);
    }

    /**
     * Gets String representation of what is printed out when print is called.
     *
     * @param dukeList DukeList to be printed.
     * @return String representation of what gets printed out of print method.
     */
    public String formatPrintString(DukeList dukeList) {
        if (dukeList.getSize() == 0) {
            return "You do not have any tasks in your " + dukeList.type() + ".";
        } else {
            String output = "Here are the tasks in your " + dukeList.type() + ":";

            for (int i = 0; i < dukeList.getSize(); i++) {
                output += "\n" + (i + 1) + "." + dukeList.taskToString(i);
            }
            return output;
        }
    }

    /**
     * Prints add message in same style as other outputs of Duke whenever
     * a new Task has been added.
     *
     * @param toAdd Task to be added.
     * @param size Size of TaskList.
     */
    public void printAdd(Task toAdd, int size) {
        String message = "Got it. I've added this task:\n" + "  " + toAdd
                + "\nNow you have " + size + " tasks in the list.";

        print(message);
    }

    /**
     * Gets String representation of what is printed when printAdd is called.
     *
     * @param toAdd Task to be added.
     * @param size Size of TaskList.
     * @return String representation of what is printed from printAdd.
     */
    public String formatPrintAddString(Task toAdd, int size) {
        return "Got it. I've added this task:\n" + "  " + toAdd
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Builds message in same form as other outputs of Duke.
     *
     * @param message Message to be built in the same form as other outputs.
     * @return Message which has been built in the same form as other outputs.
     */
    public String buildMessage(String message) {
        // to build error messages
        return LINE + "\n" + message + "\n" + LINE;
    }

    /**
     * Reads next line of user's input.
     *
     * @return String representation of user's input.
     */
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Welcomes user to Duke.
     */
    public void welcome() {
        print("Hello! I'm duke.Duke\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "What can I do for you?");
    }

    /**
     * Prints out TaskList of tasks with given keyword for users to see.
     *
     * @param tasksWithKeyword TaskList of tasks with given keyword.
     * @param keyword Keyword which tasks in tasksWithKeyword contains.
     */
    public void printKeywordTasks(TaskList tasksWithKeyword, String keyword) {
        if (tasksWithKeyword.getSize() > 0) {
            System.out.println(LINE);
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasksWithKeyword.getSize(); i++) {
                System.out.println((i + 1) + "." + tasksWithKeyword.taskToString(i));
            }
            System.out.println(LINE);
        } else {
            print("You do not have any tasks with keyword: " + keyword + ".");
        }
    }

    /**
     * Gets String representation of what is printed out when printKeywordTasks is called.
     *
     * @param tasksWithKeyword TaskList of tasks with given keyword.
     * @param keyword Keyword which tasks in tasksWithKeyword contains.
     * @return String representation of what is printed out from printKeywordTasks.
     */
    public String formatPrintKeywordTasksString(TaskList tasksWithKeyword, String keyword) {
        if (tasksWithKeyword.getSize() > 0) {
            String output = "Here are the matching tasks in your list:";
            for (int i = 0; i < tasksWithKeyword.getSize(); i++) {
                output += "\n" + (i + 1) + "." + tasksWithKeyword.taskToString(i);
            }
            return output;
        } else {
            return "You do not have any tasks with keyword: " + keyword + ".";
        }
    }

    /**
     * Says bye to user and closes scanner.
     */
    public void exit() {
        print("Bye. Hope to see you again soon!");

        sc.close();
    }

    /**
     * Gets String representation of what gets printed out when exit is called.
     *
     * @return String representation of what gets printed out when exit is called.
     */
    public String formatExitString() {
        return "Bye. Hope to see you again soon!";
    }

}
