package duke;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI class which handles all the interactions with the user.
 */
public class UI {

    private static String logo = " ____        _ _\n"
            + "|  _ \\ _   _(_|_)\n"
            + "| | | | | | | | |\n"
            + "| |_| | |_| | | |\n"
            + "|____/ \\__,_|_|_|\n";

    /**
     * Prints the welcome message to the user's terminal.
     */
    public void greet(User user) {
        System.out.println(logo);
        System.out.println(String.format("Hello %s! I'm Duii, your personal assistant!", user.getUsername()));
        System.out.println("What do you need help with?");
    }

    /**
     * Prints the error message which has occurred during loading of file.
     */
    public void showLoadingError() {
        System.out.println("Error starting up Duii.");
    }

    /**
     * Gets the input passed in by the user.
     *
     * @return The input keyed in by the user.
     */
    public String readCommand(Scanner sc) {
        return sc.nextLine().toLowerCase();
    }

    /**
     * Prints the seperating line to the terminal.
     */
    public void showLine() {
        System.out.println("___________________");
    }

    /**
     * Prints the error message from the thrown exception.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Alerts the user that a task has been marked as done.
     */
    public void notifyDone(Task doneTask) {
        System.out.println("You've finished the task? Good job!");
        System.out.println("This task has been marked as done:");
        System.out.println(doneTask.displayInfo());
    }

    /**
     * Alerts the user that a task has been deleted.
     */
    public void notifyDelete(Task removedTask) {
        System.out.println("Okay! Removing the task:");
        System.out.println(removedTask.displayInfo());
    }

    /**
     * Displays the list of tasks in the current active session.
     */
    public void notifyList(ArrayList<Task> taskArrList) {
        int listLength = taskArrList.size();
        System.out.println("Here's your current list:");
        for (int i = 0; i < listLength; i++) {
            System.out.println(String.format("%d. %s", i + 1, taskArrList.get(i).displayInfo()));
        }
    }

    /**
     * Updates the user that there are no matches in the list.
     */
    public void notifyNoMatching() {
        System.out.println("There were no keyword matches!");
    }

    /**
     * Updates the user of the tasks in the list matching the keywords.
     *
     * @param taskArrList The ArrayList of tasks with matching keywords.
     */
    public void notifyMatchingList(ArrayList<Task> taskArrList) {
        int listLength = taskArrList.size();
        System.out.println("Here's are the matching results:");
        for (int i = 0; i < listLength; i++) {
            System.out.println(String.format("%d. %s", i + 1, taskArrList.get(i).displayInfo()));
        }
    }

    public void notifyAdd(ArrayList<Task> taskArrList) {
        int listLength = taskArrList.size();
        Task newTask = taskArrList.get(listLength - 1);
        System.out.println("New Task? I've added it to the list:");
        System.out.println(newTask.displayInfo());
        System.out.println(String.format("Now you have %d task(s) in the list.", listLength));
    }

    /**
     * Shows the tasks recorded from the previous session.
     * If the user is a new user, nothing is printed to the terminal.
     */
    public void printPrevSession(TaskList tasks, User user) {
        if (!user.isNewUser()) {
            ArrayList<Task> taskArrList = tasks.getAllTasks();
            int listLength = taskArrList.size();
            if (listLength == 0) {
                System.out.println("You have no outstanding tasks from the previous session." +
                        " What a productivity master!");
            } else {
                System.out.println("These tasks are from the previous session:");
                for (int i = 0; i < taskArrList.size(); i++) {
                    System.out.println(String.format("%d. %s", i + 1, taskArrList.get(i).displayInfo()));
                }
            }
        } else {
            user.hasLoginBefore();
        }
    }

    /**
     * Prints the exit message to the user's terminal.
     */
    public void exit() {
        System.out.println("You're going already? Hope to see you again soon!");
    }
}
