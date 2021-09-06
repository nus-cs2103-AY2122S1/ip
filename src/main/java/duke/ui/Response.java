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

    /**
     * Returns the Welcome Message.
     *
     * @return Welcome Message String
     */
    public static String showWelcome() {
        final String welcomeMessage = "Henlo, Duke here! How can I be of assistance?";
        return welcomeMessage;
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
        final String addTaskMessage = "Got it. I've added this task:\n    %s\n"
                + "Now you have %d task%s in the list\n";
        return String.format(addTaskMessage, task , size, pluralOrNo(size));
    }

    /**
     * This method displays the message in the CLI when a
     * Task in the Task List is editted.
     *
     * @param task The Task being edited
     * @param taskList The Task List being edited
     */
    public String showEditTaskMessage(Task task, CommandType commandType, TaskList taskList) {
        final String deleteMessage = "Noted. Ive removed this task:\n    %s\n"
                    + "Now you have %d task%s in the list\n";
        final String doneMessage = "Nice! I've marked this task as done:\n    %s\n";
        final String undoMessage = "I've marked this task as undone:\n    %s\n";
        final String blank = "";
        switch(commandType) {
        case DELETE:
            int size = taskList.getCapacity();
            return String.format(deleteMessage, task, size, pluralOrNo(size));

        case DONE:
            return String.format(doneMessage, task);
        case UNDO:
            return String.format(undoMessage, task);
        default:
            return blank;
        }
    }

    /**
     * This method displays the Tasks in the Task List
     * in the CLI.
     *
     * @param taskList The Task List to be displayed
     */
    public String showTaskList(TaskList taskList) {
        final String noTasksMessage = "You have no tasks!";
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
            return noTasksMessage;
        }
    }

    /**
     *  This method displays the Tasks in the Task List matching
     *  the user's searchString.
     *
     * @param tasks The ArrayList containing the Tasks to be displayed.
     */
    public String showSearchMessage(ArrayList<Task> tasks) {
        final String noTasksFoundMessage = "Sorry, no tasks match your search query!";
        final String searchStringFriend = "    %s. %s\n";
        int size = tasks.size();
        if (size < 1) {
            return noTasksFoundMessage;
        } else {
            String searchString = "Here are the matching tasks in your list:\n";
            for (int i = 1; i <= size; i++) {
                searchString += String.format(searchStringFriend, i, tasks.get(i - 1));
            }
            return searchString;
        }
    }

    /**
     * This method returns the Exit Message.
     *
     * @return Exit Message
     */
    public String showExitMessage() {
        final String byeMessage = "Good bye!";
        return byeMessage;
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
