package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the interface that interacts with the user, receiving commands from the user and
 * responding to the user directly through the console.
 */
public class Ui {

    protected static final String FRIENDGREETING = "(*^_^*) Friend says: \n";
    private static final String LOGO =
         " _____     _                          _        \n"
        + "|  ___|  __  _  ___   _,___       | |       \n"
        + "| |_  |/  _|| |/ _ \\  |  __  |_____| |       \n"
        + "| __| | /   | |  __/  | / \\  |   __   |       \n"
        + "|_|   |_|   |_|\\____ |_|  |_|______|       \n";

    private final Scanner sc;
    private Duke duke;

    /**
     * Returns a Ui object.
     *
     * @param duke the Duke object that is the parent.
     */
    public Ui(Duke duke) {
        this.duke = duke;
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints on user interface the output for Welcome Message.
     */
    public String showWelcomeMessage() {
        return "Hi there! Start chatting with your new \n" + LOGO
            + FRIENDGREETING + "What would you like to do today?";
    }

    /**
     * Prints on user interface the output for Goodbye Message.
     */
    public String showGoodbyeMessage() {
        return FRIENDGREETING + "See you again, my friend!";
    }

    /**
     * Prints on user interface the output for Loading Error.
     */
    public String showLoadingError() {
        return FRIENDGREETING + "File not found";
    }

    /**
     * Prints on user interface the output for corresponding Duke Error.
     */
    public String showDukeError(DukeException error) {
        return error.toString();
    }

    /**
     * Prints on user interface the output for loaded list from .txt file.
     */
    public String showListLoad() {
        return FRIENDGREETING + "These are your existing tasks!\n"
            + duke.getTasks().printList();
    }

    /**
     * Prints on user interface the output for list updated.
     */
    public String showList() {
        return FRIENDGREETING + "Your to-do list has the following tasks: \n"
            + duke.getTasks().printList();
    }

    /**
     * Prints on user interface the output for Task Removed.
     *
     * @param removed String description of removed task.
     */
    public String showRemoveTask(String removed) {
        return FRIENDGREETING + "removed the following task from your to-do list: \n" + removed
            + "\nNow you have " + duke.getTasks().getList().size() + " tasks in the list.";
    }

    /**
     * Prints on user interface the output for Task Added.
     */
    public String showAddTask() {
        int listSize = duke.getTasks().getList().size();
        return FRIENDGREETING + "added: " + duke.getTasks().getList().get(listSize - 1).toString()
            + " to your to-do list!\n" + "Now you have " + listSize + " tasks in the list.";
    }

    /**
     * Prints on user interface the output for Task Done.
     *
     * @param description String description of done task.
     */
    public String showDoneTask(String description) {
        return FRIENDGREETING + "Hooray! You've completed task \n[X] " + description;
    }

    /**
     * Prints on user interface the output for Task Already Done.
     *
     * @param description String description of already done task.
     */
    public String showAlreadyDoneTask(String description) {
        return FRIENDGREETING + description + " has already been done! :)";
    }

    /**
     * Prints on user interface the output for Search results.
     *
     * @param resultsArray ArrayList of <code>Task</code> results from search.
     */
    public String showSearchResults(ArrayList<Task> resultsArray) {
        String result = FRIENDGREETING + "Here are the matching tasks in your list:\n";
        int listSize = resultsArray.size();
        for (int i = 0; i < listSize; i++) {
            Task currTask = resultsArray.get(i);
            int index = i + 1;
            result = result + index + "." + currTask.toString() + "\n";
        }
        return result;
    }

    /**
     * Prints on user interface the output for No Search Results.
     */
    public String showNoSearchResults() {
        return FRIENDGREETING + "There are no tasks that match your search.";
    }

    /**
     * Prints on user interface the output for list saved to .txt file.
     */
    public String saveList() {
        return "Saving file";
    }

    /**
     * Returns the message input from user.
     *
     * @return String which is input from user.
     */
    public String getUserCommand() {
        String message = "e";
        message = sc.nextLine();
        return message;
    }
}
