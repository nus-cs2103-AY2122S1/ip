package duke;

import duke.classes.TaskList;
import duke.tasks.Task;

import java.util.List;

public class DukeUI {
    public DukeUI() {}

    //Logo
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Nullary Function that prints a divider when called
     */
    static void printDivider() {
        String divider = "____________________________________________________________";
        System.out.println(divider);
    }

    /**
     * Initial Greeting from Duke
     * Nullary Function that prints the intro message when called
     */
    public static void greetings() {
        System.out.println(logo);
        printDivider();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        printDivider();
    }

    /**
     * Goodbye from Duke
     * Nullary Function that prints a closing message when called
     */
    static void exit() {
        printDivider();
        System.out.println("Bye. Hope to see you again soon!\n");
        printDivider();
        System.exit(0);
    }

    /**
     * Unary Function that prints list of task
     *
     * @param taskList List of Duke.tasks to print
     */
    static void printList(TaskList taskList) {
        printDivider();
        System.out.println("Here are the tasks in your list:");
        List<Task> lst = taskList.getTaskList();
        for (int i = 0; i < lst.size(); i++) {
            Task task = lst.get(i);
            System.out.println((i + 1) + "." + task.toString());
        }
        printDivider();
    }

    /**
     * Unary Function that marks task as Done and prints a confirmation message
     *
     * @param task Task that is marked as complete
     */
    static void completeTask(Task task) {
        printDivider();
        System.out.println("Nice! I've marked this task as done:"
                + "\n\t" + task.toString());
        printDivider();
    }

    /**
     * Binary Function that deletes a task and prints a confirmation message
     *
     * @param task Task that was removed
     * @param size Remaining size of the list
     */
    static void removeTask(Task task, int size) {
        printDivider();
        System.out.println("Noted. I've removed this task: \n"
                + "\t" + task.toString() + "\n"
                + "Now you have " + size + " tasks in the list.");
        printDivider();
    }

    /**
     * Unary Function that prints the most recently added Task
     *
     * @param lst List of Task that was added to
     */
    static void printTask(TaskList lst) {
        int lastItem = lst.last();
        printDivider();
        System.out.println("Got it. I've added this task: \n"
                + "\t" + lst.get(lastItem).toString() + "\n"
                + "Now you have " + lst.size() + " tasks in the list.");
        printDivider();
    }

    /**
     * Prints the String input, used for Errors
     * @param str String to be printed
     */
    public static void printError(String str) {
        printDivider();
        System.out.println(str);
        printDivider();
    }

    /**
     * Prints the error when the date is input in the wrong format
     */
    public static void printDateTimeError() {
        printDivider();
        System.out.println("!!! Provide Date in yyyy-MM-dd format. !!!");
        printDivider();
    }
}
