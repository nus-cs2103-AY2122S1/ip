package duke;

import exception.DukeException;
import task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Deals with user interactions, mostly printing messages to the console following user commands.
 */
public class Ui {

    /**
     * Displays the Duke welcome message.
     *
     * @return The welcome message string.
     */
    public String showWelcome() {
        return showLine() + "  Hello! I'm Duke.\n  What's up?\n" + showLine();
    }

    /**
     * Displays a line to represent the top or bottom of the Duke message box.
     *
     * @return The line string.
     */
    public String showLine() {
        return "____________________________________________\n";
    }

    /**
     * Displays the user input prompt.
     *
     * @return The input prompt string.
     */
    public String showInputPrompt() {
        return "> ";
    }

    /**
     * Reads the given user input.
     *
     * @param input The Scanner used to take in user input.
     * @return A string of the given user input.
     */
    public String readCommand(Scanner input) {
        return input.nextLine().trim();
    }

    /**
     * Displays the program closing message.
     *
     * @return The program closing message.
     */
    public String showBye() {
        return "See you next time!";
    }

    /**
     * Displays the current task list.
     *
     * @param taskList The task list to be displayed.
     * @return The task list in string representation.
     */
    public String showList(ArrayList<Task> taskList) {
        return "Here are the tasks in your list:\n"
                + IntStream.range(0, taskList.size())
                .mapToObj(i -> (i + 1) + "." + taskList.get(i).listEntry() + "\n")
                .collect(Collectors.joining());
    }

    /**
     * Displays a message to show that the given task has been set to done.
     *
     * @param toSetDone The task that has been set to done.
     * @return String representing the task being done.
     */
    public String showDone(Task toSetDone) {
        return "Nice! I've marked this task as done:\n"
                + toSetDone.listEntry() + "\n";
    }

    /**
     * Displays a message to show that the given task has been deleted.
     *
     * @param deleted The task that has been deleted.
     * @param listLength The new length of the task list after the deletion.
     * @return String representing the task being deleted.
     */
    public String showDelete(Task deleted, int listLength) {
        return "Noted. I've removed this task:\n"
                + deleted.listEntry() + "\n"
                + "Now you have " + listLength + " tasks in the list.\n";
    }

    /**
     * Displays a list of tasks in the current task list that takes place on the given date.
     *
     * @param taskList The task list.
     * @param desiredDate The date used to find tasks.
     * @return A list of tasks taking place/to be done on the given date.
     */
    public String showDateFind(ArrayList<Task> taskList, LocalDate desiredDate) {
        return "Here are the tasks taking place on that date:\n"
                + IntStream.range(0, taskList.size())
                .filter(i -> taskList.get(i).isTodayTask(desiredDate))
                .mapToObj(i -> (i + 1) + "." + taskList.get(i).listEntry() + "\n")
                .collect(Collectors.joining());
    }

    /**
     * Displays a list of tasks in the current task list that contain the given keyword.
     *
     * @param taskList The task list.
     * @param keyword The keyword to look for in the task names.
     * @return A list of tasks containing the given keyword.
     */
    public String showKeywordFind(ArrayList<Task> taskList, String keyword) {
        return "Here are the tasks with that keyword:\n"
                + IntStream.range(0, taskList.size())
                .filter(i -> taskList.get(i).containsKeyword(keyword))
                .mapToObj(i -> (i + 1) + "." + taskList.get(i).listEntry() + "\n")
                .collect(Collectors.joining());
    }

    /**
     * Displays a message to show that the given task has been added to the task list.
     *
     * @param newTask The task that has been added to the task list.
     * @param listLength The new length of the task list after the addition.
     * @return String representing the task being added.
     */
    public String showAdd(Task newTask, int listLength) {
        return "Got it. I've added this task:\n"
                + newTask.listEntry() + "\n"
                + "Now you have " + listLength + " tasks in the list.\n";
    }

    /**
     * Displays the error message of the given DukeException.
     *
     * @param e The DukeException whose error message is to be displayed.
     * @return The DukeException error message.
     */
    public String showException(DukeException e) {
        return e.getMessage();
    }

    /**
     * Displays the Duke initialisation message.
     *
     * @return The initialisation message string.
     */
    public String showInitialise() {
        return "Loading Duke...";
    }

    /**
     * Displays a message to show that the hard disk's directory did not exist and has been created.
     *
     * @return String representing the creation of the data directory.
     */
    public String showNewDataDirectory() {
        return "Data directory does not exist, it has been created!";
    }

    /**
     * Displays a message to show that the hard disk did not exist and has been created.
     *
     * @return String representing the creation of the hard disk.
     */
    public String showNewHardDisk() {
        return "Hard disk does not exist, a new one has been created!";
    }
}
