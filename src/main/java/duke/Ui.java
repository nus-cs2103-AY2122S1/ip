package duke;

import java.util.Scanner;

/**
 * Class for user interactions
 */
public class Ui {

    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Gets user input.
     *
     * @return User input.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Displays the TaskList.
     *
     * @param taskList TaskList to be displayed.
     * @return String representation of TaskList.
     */
    public String showTaskList(TaskList taskList) {
        return taskList.toString();
    }

    /**
     * Displays the PlaceList.
     *
     * @param placeList PlaceList to be displayed.
     * @return String representation of PlaceList.
     */
    public String showPlaceList(PlaceList placeList) {
        return placeList.toString();
    }

    /**
     * Shows message for adding tasks.
     *
     * @param newTask Task to be added.
     * @param newList TaskList after adding Task.
     * @return String for adding Task.
     */
    public String showAddMessage(Task newTask, TaskList newList) {
        return "Got it. I've added this task:\n"
                + newTask.toString()
                + String.format("Now you have %s tasks in the list.\n", newList.getLength());
    }

    /**
     * Shows message for marking task as done.
     *
     * @param markedTask Task to be marked as done.
     * @return String for marking Task done.
     */
    public String showMarkDoneMessage(Task markedTask) {
        return "Nice! I've marked this task as done:\n"
                + markedTask.toString();
    }

    /**
     * Shows message for deleting task.
     *
     * @param deletedTask Task to be deleted.
     * @param newList TaskList after deletion.
     * @return String for deleting Task.
     */
    public String showDeleteTaskMessage(Task deletedTask, TaskList newList) {
        return "Noted. I've removed this task:\n"
                + deletedTask.toString()
                + String.format("Now you have %s tasks in the list.\n", newList.getLength());
    }

    /**
     * Shows message for deleting place.
     *
     * @param deletedPlace Place to be deleted.
     * @param newList PlaceList after deletion.
     * @return String for Deleting Place.
     */
    public String showDeletePlaceMessage(Place deletedPlace, PlaceList newList) {
        return "Noted. I've removed this place:\n"
                + deletedPlace.toString()
                + "\n"
                + String.format("Now you have %s places in the list.\n", newList.getLength());
    }

    /**
     * Shows message after search.
     * @param result String of all relevant Tasks.
     * @return String for search results.
     */
    public String showSearchResults(String result) {
        return "Here are the matching tasks in your list:\n" + result;
    }
}
