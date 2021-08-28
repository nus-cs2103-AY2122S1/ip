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
         " _____       _                    _        \n"
        + "|  ___|  __  _  ___   _,____     | |       \n"
        + "| |_  |/  _|| |/ _ \\ |  __  |____| |       \n"
        + "| __| | /   | |  __/ | / \\  |  __  |       \n"
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
    public void showWelcomeMessage() {
        System.out.println("Hi there! Start chatting with your new \n" + LOGO);
        System.out.println(FRIENDGREETING + "What would you like to do today?");
    }

    /**
     * Prints on user interface the output for Goodbye Message.
     */
    public void showGoodbyeMessage() {
        System.out.println(FRIENDGREETING + "See you again, my friend!");
    }

    /**
     * Prints on user interface the output for Loading Error.
     */
    public void showLoadingError() {
        System.out.println(FRIENDGREETING + "File not found");
    }

    /**
     * Prints on user interface the output for loaded list from .txt file.
     */
    public void showListLoad() {
        System.out.println(FRIENDGREETING + "These are your existing tasks!");
        System.out.println(duke.getTasks().printList());
    }

    /**
     * Prints on user interface the output for list updated.
     */
    public void showList() {
        System.out.println(FRIENDGREETING + "Your to-do list has the following tasks: \n");
        System.out.println(duke.getTasks().printList());
    }

    /**
     * Prints on user interface the output for Task Removed.
     *
     * @param removed String description of removed task.
     */
    public void showRemoveTask(String removed) {
        System.out.println(FRIENDGREETING + "removed the following task from your to-do list: \n" + removed);
        System.out.println("Now you have " + duke.getTasks().getList().size() + " tasks in the list.");
    }

    /**
     * Prints on user interface the output for Task Added.
     */
    public void showAddTask() {
        int listSize = duke.getTasks().getList().size();
        System.out.println(FRIENDGREETING + "added: " + duke.getTasks().getList().get(listSize - 1).toString()
            + " to your to-do list!");
        System.out.println("Now you have " + listSize + " tasks in the list.");
    }

    /**
     * Prints on user interface the output for Task Done.
     *
     * @param description String description of done task.
     */
    public void showDoneTask(String description) {
        System.out.println(FRIENDGREETING + "Hooray! You've completed task \n[X] " + description);
    }

    /**
     * Prints on user interface the output for Task Already Done.
     *
     * @param description String description of already done task.
     */
    public void showAlreadyDoneTask(String description) {
        System.out.println(FRIENDGREETING + description + " has already been done! :)");
    }

    /**
     * Prints on user interface the output for Search results.
     *
     * @param resultsArray ArrayList of <code>Task</code> results from search.
     */
    public void showSearchResults(ArrayList<Task> resultsArray) {
        System.out.println(FRIENDGREETING + "Here are the matching tasks in your list:");
        int listSize = resultsArray.size();
        for (int i = 0; i < listSize; i++) {
            Task currTask = resultsArray.get(i);
            int index = i + 1;
            System.out.println(index + "." + currTask.toString());
        }
    }

    /**
     * Prints on user interface the output for No Search Results.
     */
    public void showNoSearchResults() {
        System.out.println(FRIENDGREETING + "There are no tasks that match your search.");

    }

    /**
     * Prints on user interface the output for list saved to .txt file.
     */
    public void saveList() {
        System.out.println("Saving file");
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
