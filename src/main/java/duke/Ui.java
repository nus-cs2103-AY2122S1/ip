package duke;

import java.util.List;

/**
 * Represents the user interface and displays messages for Duke.
 */
public class Ui {
    public Ui() {

    }

    /**
     * Displays welcome message.
     */
    public String showWelcome() {
        System.out.println("Welcome. I am your virtual assistant Duke. Sparkle up your day (TM).");
        return "Welcome. I am your virtual assistant Duke. Sparkle up your day (TM).";
    }

    /**
     * Displays the list of tasks.
     */
    void showList(List<String> list) {
        int c = 1;
        for (String line : list) {
            System.out.println(c + ". " + line);
            c++;
        }
        if (c == 1) {
            showEmptyListMsg();
        }
    }

    /**
     * Displays message for when the user retrieves an empty list.
     */
    void showEmptyListMsg() {
        System.out.println("The list is empty. Good job.");
    }

    /**
     * Displays the location of the list file.
     */
    void showFileLocation(String path) {
        System.out.println("Your task list is located in: " + path + ". SPARKLEOUS.");
    }

    /**
     * Displays the shut down message.
     */
    void sayBye() {
        System.out.println("Have a SPARKULAR day.");
    }

    /**
     * Displays the error message for when the user enters an invalid input for an integer.
     */
    void showNotANumberMsg() {
        System.err.println("JUST GIVE ME A NUMBER, WHY ARE YOU DOING THIS");
    }

    /**
     * Displays the error message for when the user enters an index that is out of bounds
     * for the task list.
     */
    void showOutOfBoundsMsg(int listsize) {
        System.err.println("hello sir there are only " + listsize + " tasks in the list sir");
    }

    /**
     * Displays the error message for when the user enters an index that is less than 0
     * for the task list.
     */
    void showLessThanZeroMsg(int number) {
        System.err.println("HOW CAN I COMPLETE THE TASK AT INDEX " + number + "? IT DOESNT MAKE ANY SENSE");
    }

    /**
     * Displays a message to inform the user that the task has been marked as done.
     *
     * @param task Task that the user marked as done.
     */
    void showMarkedAsDone(String task) {
        System.out.println(task + " has been marked as done");
    }

    /**
     * Displays the message for when the user tries to mark a task that is already
     * done as done.
     */
    void showAlreadyDone() {
        System.out.println("It is already done. How SPARKTACULAR.");
    }

    /**
     * Displays the message to inform the user that the task has been deleted and
     * how many items are left in the list.
     *
     * @param toBeDeleted Task that the user has deleted.
     * @param size        Number of items remaining in the task list.
     */
    void showDeletionMsg(String toBeDeleted, int size) {
        System.out.println("task " + toBeDeleted + " has been deleted.\n" +
                "There are " + size + " tasks left in the list.");
    }

    /**
     * Displays the error message for when the user adds a task with no name.
     */
    void showNoNameError() {
        System.err.println("I NEED A NAME SIR!!!");
    }

    /**
     * Displays the message to inform the user that the task has been added to
     * the task list.
     *
     * @param task Task that the user added.
     */
    void showTaskAdded(String task) {
        System.out.println(task + " added.");
    }

    /**
     * Displays number of items in the task list.
     *
     * @param size Number of items in the task list.
     */
    void showListSize(int size) {
        System.out.println("the list has " + size + " tasks now.");
    }

    /**
     * Displays the message for when the user tries to add a task that is already
     * inside the task list.
     *
     * @param task Task the user is trying to add.
     */
    void showAlreadyInList(String task) {
        System.out.println(task + " is already in the list sir");
    }

    /**
     * Displays the error message for when the user enters an event or deadline
     * without a date.
     */
    void showNoDateError() {
        System.err.println("BY WHEN? I DONT KNOW AHHHHHHHHHHH");
    }

    /**
     * Displays the error message for when the user enters an event or deadline
     * with an invalid date.
     */
    void showBadDateError() {
        System.err.println("hello sir I only understand YYYY-MM-DD format");
    }

    /**
     * Displays entries in the task list that contain the search query.
     *
     * @param list        The list of strings representing tasks.
     * @param searchQuery The search query entered by the user.
     */
    void showSearchResults(List<String> list, String searchQuery) {
        int c = 1;
        System.out.println("Here are the search results for \"" + searchQuery + "\":");
        for (String task : list) {
            if (task.contains(searchQuery)) {
                System.out.println(c + ". " + task);
            }
            c++;
        }
    }
}
