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
    public String showError(String error) {
        System.out.println(error);
        return error;
    }

    /**
     * Prints out the welcome message and asks the user to input his/her command.
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        /**System.out.println("Hello from\n" + logo);
        String message = "Hello! I'm Duke \n" + "What can I do for you?\n";
        showLine();
        System.out.println(message);
        showLine();*/
        String welcomeMessage =  "Hello from\n" + logo + showLine() + "Hello! I'm Duke \n" + "What can I do for you?\n"
                + showLine();
        System.out.println(welcomeMessage);
        return welcomeMessage;
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
    public String showLine() {
        String prettyLine = "\n______________________________________________________________\n";
        System.out.println(prettyLine);
        return prettyLine;
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
    public String respondToList(ArrayList<Task> tasks) {
        String responseToList = "Here are the tasks in your list:";
        //System.out.println(("\tHere are the tasks in your list:"));
        for (int i = 0; i < tasks.size(); i++) {
            //System.out.println("\t" + Integer.toString(i + 1) + "." + tasks.get(i).toString());
            if(i == tasks.size() - 1) {
                responseToList = responseToList + "\n" + Integer.toString(i + 1) + "." + tasks.get(i).toString();
            } else {
                responseToList = responseToList + "\n" + Integer.toString(i + 1) + "." + tasks.get(i).toString();
            }
        }
        System.out.println(responseToList);
        //return responseToList + "\n____________________";
        return responseToList;
    }

    /**
     * Prints the tasks that has been completed by the user.
     *
     * @param tasks Collection of tasks in the task manager application.
     * @param taskNumber Number of the task that has been completed in the list of tasks.
     */
    public String respondToDone(ArrayList<Task> tasks, Integer taskNumber) {
        String responseToDone = "\tNice! I've marked this task as done: \n \t \t" + " ["
                + tasks.get(taskNumber - 1).getStatusIcon() + "] " + tasks.get(taskNumber - 1).getDescription();
        //System.out.println("\tNice! I've marked this task as done: \n \t \t" +
                //" [" + tasks.get(taskNumber - 1).getStatusIcon() + "] " + tasks.get(taskNumber - 1).getDescription());
        System.out.println(responseToDone);
        return responseToDone;
    }

    /**
     * Prints the task that has been deleted by the user.
     *
     * @param tasks Collection of tasks in the task manager application.
     * @param task task that has been completed in the list of tasks.
     */
    public String respondToDelete(ArrayList<Task> tasks, Task task) {
        String responseToDelete = "\tNoted. I've removed this task: \n\t\t" + task.toString() +
                "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.";
        System.out.println(responseToDelete);
        return responseToDelete;
    }

    /**
     * Prints the Todo task that has been created by the user.
     *
     * @param tasks Collection of tasks in the task manager application.
     * @param todo The Todo task that has been created.
     */
    public String respondToTodo(ArrayList<Task> tasks, ToDo todo) {
        String responseToToDo = "\t" + "Got it. I've added this task: " + "\n\t\t" + todo.toString() +
                "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.";
        System.out.println(responseToToDo);
        return responseToToDo;
    }

    /**
     * Prints the Event task that has been created by the user.
     *
     * @param tasks Collection of tasks in the task manager application.
     * @param event The Event task that has been created.
     */
    public String respondToEvent(ArrayList<Task> tasks, Event event) {
        String responseToEvent = "\tGot it. I've added this task: " + "\n\t\t" + event.toString() +
                "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.";
        System.out.println(responseToEvent);
        return responseToEvent;
    }

    /**
     * Prints the Deadline task that has been created by the user.
     *
     * @param tasks Collection of tasks in the task manager application.
     * @param deadline The Deadline task that has been created.
     */
    public String respondToDeadline(ArrayList<Task> tasks, Deadline deadline) {
        String respondToDeadline = "\t" + "Got it. I've added this task: " + "\n\t\t" + deadline.toString() +
                "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.";
        System.out.println(respondToDeadline);
        return respondToDeadline;
    }

    /**
     * Prints the response on the user interface to the blah input by user.
     */
    public String respondToBlah() {
        String responseToBlah = "\tOOPS!!! I'm sorry, but I don't know what that means :-(";
        System.out.println(responseToBlah);
        return responseToBlah;
    }

    /**
     * Prints the response on the user interface to the bye input by user.
     */
    public String respondToBye() {
        String respondToBye = "\tBye. Hope to see you again soon!";
        System.out.println(respondToBye);
        return respondToBye;
    }

    /**
     * Prints the response on the user interface to the find input by user.
     */
    public String respondToFind(ArrayList<Task> tasks) {
        String responseToFind = "\tHere are the matching tasks in your list:";
        //System.out.println("\tHere are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            responseToFind = responseToFind + "\t" + Integer.toString(i + 1) + "." + tasks.get(i).toString();
            //System.out.println("\t" + Integer.toString(i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println(responseToFind);
        return responseToFind;
    }

}
