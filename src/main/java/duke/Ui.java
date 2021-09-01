package duke;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the user interface which deals with interactions with the user
 */
public class Ui {
    Scanner sc;
    private static TaskList taskList;
    private static Storage storage;

    /**
     * Constructor for the Ui class where variables are initialized
     *
     * @param taskList TaskList that stores the tasks.
     * @param storage Storage that deals with loading tasks from the file and saving tasks in the file.
     */
    public Ui(TaskList taskList, Storage storage) {
        sc = new Scanner(System.in);
        this.taskList = taskList;
        this.storage = storage;
    }
    
    /**
     * Returns the command typed by the user.
     * If the user doesn't type any command and clicks enter, an empty string is returned.
     *
     * @return Command entered by the user.
     */
    public String getCommand() {
        if (sc.hasNextLine()) {
            String str = sc.nextLine();
            return str;
        }
        return "";
    }

    /**
     * Prints the welcome commands and current task list which are displayed when Duke starts up.
     */
    public void showWelcome() {
        System.out.println("Hello from duke.Duke!");
        System.out.println("");
        storage.loadTaskListData(taskList);
        System.out.println("");
        System.out.println("Hope you are doing well. How can I help you?");
    }

    /**
     * Prints 'Bye. Have a great day!' when the user enters bye.
     */
    public static void sayBye() {
        System.out.println("Bye. Have a great day!");
    }

    /**
     * Prints a dashed line to improve user interface.
     */
    public static void showLine() {
        System.out.println("-----------------------------------------------");
    }

    /**
     * Prints a string value argument on the screen.
     *
     * @param msg String value to be printed on the screen.
     */
    public static void printMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Prints the task that is added by the user as well as the number of tasks in the task list.
     *
     * @param task Task class.
     */
    public static void taskResponse(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
    }

    /**
     * Prints the task that is done by the user.
     *
     * @param task Task class.
     */
    public static void doneResponse(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Prints the task that is deleted by the user as well as the number of tasks remaining in the task list.
     *
     * @param task Task class.
     */
    public static void deleteResponse(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
    }
}
