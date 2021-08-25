package duke;

import java.util.ArrayList;

/**
 * Encapsulates the TodoList which is the overall list of all Tasks in Duke.
 */
public class TaskList {

    private static ArrayList<Task> dukeList;
    private static Data data;

    public TaskList(ArrayList<Task> dukeList, Data data) {
        TaskList.dukeList = dukeList;
        TaskList.data = data;
    }

    public static void add(Task task) {
        dukeList.add(task);
    }

    /**
     * Shows all Tasks in the list that the user has given to Duke to store.
     * Tasks are ordered from least recent to most recent. If no tasks have been given to Duke,
     * the appropriate message is shown.
     */
    public static void showList() {
        String showListText = "Here are the tasks in your list:";
        String emptyListText = "☹ Oops! Looks like you have no tasks in your list!";

        if (dukeList.size() == 0) {
            System.out.println(emptyListText);
        } else {
            for (int i = 0; i < dukeList.size(); i++) {
                showListText += "\n" + (i + 1) + "." + dukeList.get(i).toString();
            }
            System.out.println(showListText);
        }
    }

    /**
     * Updates the marking of a certain Task as 'done'.
     * @param input The entire String that the user has input i.e. "done 2".
     * @throws DukeException If an incorrect input is entered.
     */
    public static void markDone(String input) throws DukeException{
        int itemNumber;
        if (input.split(" ", 2).length == 1) {
            throw new DukeException("☹ Oops! Looks like you are missing the task number you wish to mark as done!"
                                    + " Try again :-)");
        }
        String numberInput = input.split(" ", 2)[1];
        try {
            itemNumber = Integer.parseInt(numberInput);
        } catch (NumberFormatException e) {
            throw new DukeException("☹ You may have entered something incorrectly. Try adding a number behind 'done'!");
        }
        String message = "☹ Oops! I cannot seem to find that task number. Try again!";
        if (dukeList.isEmpty()) {
            message = "☹ Oops! Your list is empty! Try adding a Task first!";
        } else if (itemNumber <= dukeList.size()){
            Task targetItem = dukeList.get(itemNumber - 1);
            targetItem.markDone();
            message = "Nice! I've marked this task as done:\n" + " " + targetItem.toString();
        }
        System.out.println(message);
    }

    /**
     * Deletes a specific task that Duke has stored.
     * @param input The entire String that the user has input i.e. "delete 2".
     * @throws DukeException If an incorrect input is entered.
     */
    public static void delete(String input) throws DukeException{
        int itemNumber;
        if (input.split(" ", 2).length == 1) {
            throw new DukeException("☹ Oops! Looks like you are missing the number of the task you wish to delete! "
                                    + "Try again :-)");
        }
        String numberInput = input.split(" ", 2)[1];
        try {
            itemNumber = Integer.parseInt(numberInput);
        } catch (NumberFormatException e) {
            throw new DukeException("You may have entered something incorrectly. Try adding a number behind 'delete'!");
        }
        String message = "☹ Oops! You may have incorrectly entered a number. Try again!";
        if (itemNumber > dukeList.size()) {
            throw new DukeException("☹ Oops! Looks like you are trying to delete something that is not in your list! "
                                    + "Try again!");
        }
        Task removed = dukeList.remove(itemNumber - 1);
        System.out.println("Noted. I've removed this task:\n"
                            + removed.toString()
                            + "\nNow you have "
                            + (dukeList.size())
                            + " tasks in the list");
    }

    /**
     * Updates the stored Data.
     * @throws DukeException If file not found or error updating data.
     */
    public static void update() throws DukeException{
        Data.updateData(dukeList);
    }

    /**
     * Gets the number of Tasks in the TodoList.
     * @return An integer representing the number of Tasks.
     */
    public static int numberOfTasks() {
        return TaskList.dukeList.size();
    }

}
