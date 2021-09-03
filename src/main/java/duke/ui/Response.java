package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.commands.Command.CommandType;
import duke.errors.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class Response {

    public static String pluralOrNo(int cap) {
        return cap <= 1 ? "" : "s";
    }

    public static String showWelcome() {
        return "Henlo, Duke here! How can I be of assistance?";
    }

    public String showError(DukeException e) {
        return e.getMessage();
    }

    /**
     * This method displays the message in the CLI when a
     * Task is added to the Task List.
     *
     * @param task The Task being added
     * @param taskList The Task List being added to
     */
    public String showAddTaskMessage(Task task, TaskList taskList) {
        int size = taskList.getCapacity();
        return String.format("Got it. I've added this task:\n    %s\n"
                + "Now you have %d task%s in the list\n", task , size, pluralOrNo(size));
    }

    /**
     * This method displays the message in the CLI when a
     * Task in the Task List is editted.
     *
     * @param task The Task being edited
     * @param taskList The Task List being edited
     */
    public String showEditTaskMessage(Task task, CommandType commandType, TaskList taskList) {
        switch(commandType) {
        case DELETE:
            int size = taskList.getCapacity();
            return String.format("Noted. Ive removed this task:\n    %s\n"
                    + "Now you have %d task%s in the list\n", task, size, pluralOrNo(size));

        case DONE:
            return String.format("Nice! I've marked this task as done:\n    %s\n", task);
        case UNDO:
            return String.format("I've marked this task as undone:\n    %s\n", task);
        default:
            return "";
        }
    }

    /**
     * This method displays the Tasks in the Task List
     * in the CLI.
     *
     * @param taskList The Task List to be displayed
     */
    public String showTaskList(TaskList taskList) {
        ArrayList<Task> arrayList = taskList.getArrayList();
        int currentCapacity = arrayList.size();
        if (currentCapacity > 0) {
            String toPrint = "Here are the tasks in your list:\n";
            for (int i = 0; i < currentCapacity; i++) {
                Task task = arrayList.get(i);
                toPrint += String.format("%d. %s\n", i + 1, task);
            }
            return toPrint;
        } else {
            return "You have no tasks!";
        }
    }

    /**
     *  This method displays the Tasks in the Task List matching
     *  the user's searchString.
     *
     * @param tasks The ArrayList containing the Tasks to be displayed.
     */
    public String showSearchMessage(ArrayList<Task> tasks) {
        int size = tasks.size();
        if (size < 1) {
            return "Sorry, no tasks match your search query!";
        } else {
            String searchString = "Here are the matching tasks in your list:\n";
            for (int i = 1; i <= size; i++) {
                searchString += String.format("    " + i + ". " + tasks.get(i - 1) + "\n");
            }
            return searchString;
        }
    }

    public String showExitMessage() {
        return "Good bye!";
    }

    /**
     * This method reads in the next user input via the CLI.
     *
     * @return The user input
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
