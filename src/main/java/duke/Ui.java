package duke;

import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Event;
import duke.tasks.Deadline;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Prints out the relevant messages and responds to user's inputs on the user interface
 */
public class Ui {
    private String command;

    /**
     * Prints out the error that has occurred.
     *
     * @param error Error that has occurred.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Prints out the welcome message and asks the user to input his/her command.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String message = "Hello! I'm Duke \n" + "What can I do for you?\n";
        showLine();
        System.out.println(message);
        showLine();
        /**if(size() == 0) {
            System.out.println("There are no tasks");
            return null;
        } else {
            System.out.println("im here!!");
            printFile("data/duke.txt");
            return nill;
        }*/
    }

    /**
     * Prints out a horizontal dashed line on the user interface.
     */
    public void showLine() {
        System.out.println("\n______________________________________________________________\n");
    }

    /**
     * Reads the command or inputs entered by the user.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        this.command = input;
        return this.command;
    }

    /**
     * Prints the list of tasks in the application when user asks for it.
     *
     * @param tasks Collection of tasks in the task manager application.
     */
    public void respondToList(ArrayList<Task> tasks) {
        System.out.println(("\tHere are the tasks in your list:"));
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + Integer.toString(i + 1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * Prints the tasks that has been completed by the user.
     *
     * @param tasks Collection of tasks in the task manager application.
     * @param taskNumber Number of the task that has been completed in the list of tasks.
     */
    public void respondToDone(ArrayList<Task> tasks, Integer taskNumber) {
        System.out.println("\tNice! I've marked this task as done: \n \t \t" +
                " [" + tasks.get(taskNumber - 1).getStatusIcon() + "] " + tasks.get(taskNumber - 1).getDescription());
    }

    /**
     * Prints the task that has been deleted by the user.
     *
     * @param tasks Collection of tasks in the task manager application.
     * @param taskNumber Number of the task that has been completed in the list of tasks.
     */
    public void respondToDelete(ArrayList<Task> tasks, Integer taskNumber) {
        System.out.println("\tNoted. I've removed this task: \n\t\t" + tasks.get(taskNumber).toString() +
                "\n\tNow you have " + Integer.toString(tasks.size() - 1) + " tasks in the list.");
    }

    /**
     * Prints the Todo task that has been created by the user.
     *
     * @param tasks Collection of tasks in the task manager application.
     * @param todo The Todo task that has been created.
     */
    public void respondToTodo(ArrayList<Task> tasks, ToDo todo) {
        System.out.println("\t" + "Got it. I've added this task: " + "\n\t\t" + todo.toString() +
                "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.");
    }

    /**
     * Prints the Event task that has been created by the user.
     *
     * @param tasks Collection of tasks in the task manager application.
     * @param event The Event task that has been created.
     */
    public void respondToEvent(ArrayList<Task> tasks, Event event) {
        System.out.println("\tGot it. I've added this task: " + "\n\t\t" + event.toString() +
                "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.");
    }

    /**
     * Prints the Deadline task that has been created by the user.
     *
     * @param tasks Collection of tasks in the task manager application.
     * @param deadline The Deadline task that has been created.
     */
    public void respondToDeadline(ArrayList<Task> tasks, Deadline deadline) {
        System.out.println("\t" + "Got it. I've added this task: " + "\n\t\t" + deadline.toString() +
                "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.");
    }

    /**
     * Prints the response on the user interface to the blah input by user.
     */
    public void respondToBlah() {
        System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints the response on the user interface to the bye input by user.
     */
    public void respondToBye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Prints the response on the user interface to the find input by user.
     */
    public void respondToFind(ArrayList<Task> tasks) {
        System.out.println("\tHere are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + Integer.toString(i + 1) + "." + tasks.get(i).toString());
        }
    }

}
