package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;


/**
 * UI class which handles all the interactions with the user.
 */
public class Ui {

    private static final String LOGO = " ____        _ _\n"
            + "|  _ \\ _   _(_|_)\n"
            + "| | | | | | | | |\n"
            + "| |_| | |_| | | |\n"
            + "|____/ \\__,_|_|_|\n";

    /**
     * Prints the welcome message to the user's terminal.
     */
    public static String greet(User user) {
        return LOGO
                + String.format("Hello %s! I'm Duii, your personal assistant!\n", user.getUsername())
                + "What do you need help with?";
    }

    /**
     * Prints the error message which has occurred during loading of file.
     */
    public String showLoadingError() {
        return "Error starting up Duii.";
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
    public String showError(String error) {
        return error;
    }

    /**
     * Alerts the user that a task has been marked as done.
     */
    public String notifyDone(Task doneTask) {
        assert(doneTask != null);
        return "You've finished the task? Good job!\n"
                + "This task has been marked as done:\n"
                + doneTask.displayInfo();
    }

    /**
     * Alerts the user that multiple tasks have been marked as done.
     */
    public String notifyMultiDone(ArrayList<Task> doneTasks) {
        assert(doneTasks.size() > 1);
        String output =  "You've finished the task? Good job!\n"
                + "This task has been marked as done:\n";
        for (int i = 0 ; i < doneTasks.size(); i++) {
            output += doneTasks.get(i).displayInfo() + "\n";
        }
        return output;
    }

    /**
     * Alerts the user that a task has been deleted.
     */
    public String notifyDelete(Task removedTask) {
        assert(removedTask != null);
        return "Okay! Removing the task:\n"
            + removedTask.displayInfo();
    }

    /**
     * Alerts the user that multiple tasks have been deleted.
     */
    public String notifyMultiDelete(ArrayList<Task> removedTasks) {
        assert(removedTasks.size() > 1);
        String output =  "Okay! Removing the task:\n";
        for (int i = 0; i < removedTasks.size(); i++) {
            output += removedTasks.get(i).displayInfo() + "\n";
        }
        return output;
    }

    /**
     * Displays the list of tasks in the current active session.
     */
    public String notifyList(ArrayList<Task> taskArrList) {
        assert(taskArrList != null);
        String output = "Here's your current list:\n";
        int listLength = taskArrList.size();
        for (int i = 0; i < listLength; i++) {
            output += String.format("%d. %s\n", i + 1, taskArrList.get(i).displayInfo());
        }
        return output;
    }

    /**
     * Updates the user that there are no matches in the list.
     */
    public String notifyNoMatching() {
        return "There were no keyword matches!";
    }

    /**
     * Updates the user of the tasks in the list matching the keywords.
     *
     * @param taskArrList The ArrayList of tasks with matching keywords.
     */
    public String notifyMatchingList(ArrayList<Task> taskArrList) {
        String output = "Here's are the matching results:\n";
        int listLength = taskArrList.size();
        for (int i = 0; i < listLength; i++) {
            output += String.format("%d. %s\n", i + 1, taskArrList.get(i).displayInfo());
        }
        return output;
    }

    /**
     * Updates the user of the tasks in the list which have been tagged.
     *
     * @param taskArrList The ArrayList of tasks with matching keywords.
     */
    public String notifyTaggedList(ArrayList<Task> taskArrList, String keyword) {
        String output = "Here's are the tasks which are tagged with #" + keyword + ":\n";
        int listLength = taskArrList.size();
        for (int i = 0; i < listLength; i++) {
            output += String.format("%d. %s\n", i + 1, taskArrList.get(i).displayInfo());
        }
        return output;
    }

    /**
     * Updates the user of the tasks in the list, after adding the new task.
     *
     * @param taskArrList The ArrayList of tasks in the current list.
     */
    public String notifyAdd(ArrayList<Task> taskArrList) {
        int listLength = taskArrList.size();
        assert(listLength > 0);
        Task newTask = taskArrList.get(listLength - 1);
        return "New Task? I've added it to the list:\n"
                + newTask.displayInfo()
                + "\n"
                + String.format("Now you have %d task(s) in the list.", listLength);
    }

    /**
     * Shows the tasks recorded from the previous session.
     * If the user is a new user, nothing is printed to the terminal.
     */
    public String printPrevSession(TaskList tasks, User user) {
        assert(user != null);
        if (!user.isNewUser()) {
            ArrayList<Task> taskArrList = tasks.getAllTasks();
            int listLength = taskArrList.size();
            if (listLength == 0) {
                return "You have no outstanding tasks from the previous session."
                        + " What a productivity master!";
            } else {
                String output = "These tasks are from the previous session:\n";
                for (int i = 0; i < taskArrList.size(); i++) {
                    output += String.format("%d. %s", i + 1, taskArrList.get(i).displayInfo());
                }
                return output;
            }
        } else {
            user.hasLoginBefore();
            return "This is your first session!\n";
        }
    }

    /**
     * Prints the directory not found message to the user's terminal.
     */
    public static void notifyDirNotFound() {
        System.out.println("'data' directory not found. Creating the directory...");
    }

    /**
     * Prints the directory created message to the user's terminal.
     */
    public static void notifyCreatedDir() {
        System.out.println("Directory created.");
    }

    /**
     * Prints the file not found message to the user's terminal.
     */
    public static void notifyFileNotFound() {
        System.out.println("Data file not found. Creating a new file...");
    }

    /**
     * Prints the file created message to the user's terminal.
     */
    public static void notifyCreatedFile() {
        System.out.println("File created.");
    }

    /**
     * Displays the list of commands available for the user.
     */
    public static String displayHelp() {
        String botDescription = "Duii is a task manager which helps you keep track your upcoming tasks!\n";
        String commandList = "The following commands available:\n"
                + "list - Lists all the tasks in the current task list.\n"
                + "done - Marks the tasks with the specified IDs as done.\n"
                + "Eg. done 1,2,3 \n"
                + "delete - Deletes the tasks with the specified IDs.\n"
                + "Eg. delete 5,1,2 \n"
                + "tag - Tags a certain task with the relevant keywords with a tag.\n"
                + "Eg. tag book nerd\n"
                + "todo - Adds a toDo activity to the list. Optional to specify duration in brackets.\n"
                + "Eg. todo read book (2h)\n"
                + "event - Adds an event activity to the list.\n"
                + "Eg. event Dinner /at 19/02/2021 19:00\n"
                + "deadline - Adds a deadline activity to the list. \n"
                + "Eg. deadline Assignment 1 /by 19/03/2021 15:00\n"
                + "find - Lists all the tasks with the specified keyword in its description.\n"
                + "Eg. find books \n"
                + "bye - Exits the program.";
        return botDescription + commandList;
    }

    /**
     * Prints the exit message to the user's terminal.
     */
    public String exit() {
        return "You're going already? Hope to see you again soon!";
    }
}
