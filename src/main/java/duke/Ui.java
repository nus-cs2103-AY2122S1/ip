package duke;

import java.util.ArrayList;

import duke.task.Task;


/**
 * UI class which handles all the interactions with the user.
 */
public class Ui {
    private final static String exitMessage = "You're going already? Hope to see you again soon!";
    private final static String loadingErrorMessage = "Error starting up Duii.\n";
    private final static String createFileErrorMessage = "Error creating local record file. This session may not be saved.\n";
    private final static String saveErrorMessage = "Error saving current session.\n";
    private final static String noMatchMessage = "There were no keyword matches!";
    private final static String botDescription = "Duii is a task manager which helps you keep track your upcoming tasks!\n";
    private final static String commandList = "The following commands are available:\n"
            + "list - Lists all the tasks in the current task list.\n"
            + "done - Marks the tasks with the specified IDs as done.\n"
            + "Eg. done 1,2,3 \n"
            + "delete - Deletes the tasks with the specified IDs.\n"
            + "Eg. delete 5,1,2 \n"
            + "tag add - Tags a certain task with the relevant keywords with a tag.\n"
            + "Eg. tag add book nerd\n"
            + "tag delete - Removes a certain tag from task(s) with the relevant keywords.\n"
            + "Eg. tag delete book nerd\n"
            + "todo - Adds a toDo activity to the list. Optional to specify duration in brackets.\n"
            + "Eg. todo read book (2h)\n"
            + "event - Adds an event activity to the list.\n"
            + "Eg. event Dinner /at 19/02/2021 1900\n"
            + "deadline - Adds a deadline activity to the list. \n"
            + "Eg. deadline Assignment 1 /by 19/03/2021 1500\n"
            + "find - Lists all the tasks with the specified keyword in its description.\n"
            + "Eg. find books \n"
            + "bye - Exits the program.";

    /**
     * Prints the welcome message to the user's terminal.
     */
    public String greet(TaskList tasks) {
        return "Hello! I'm Duii, your personal assistant!\n"
                + "Type in '/help' for the full list of supported commands. \n"
                + printPrevSession(tasks)
                + "What do you need help with?";
    }

    /**
     * Prints the error message which has occurred during loading of file.
     */
    public String showLoadingError() {
        return loadingErrorMessage;
    }

    /**
     * Prints the error message which has occurred during creation of file.
     */
    public static String showFileError() {
        return createFileErrorMessage;
    }

    /**
     * Prints the error message which has occurred during creation of file.
     */
    public static String saveSessionError() {
        return saveErrorMessage;
    }

    /**
     * Alerts the user that a task has been marked as done.
     */
    public String notifyDone(Task doneTask) {
        assert (doneTask != null);
        return "You've finished the task? Good job!\n"
                + "This task has been marked as done:\n"
                + doneTask.displayInfo();
    }

    /**
     * Alerts the user that multiple tasks have been marked as done.
     */
    public String notifyMultiDone(ArrayList<Task> doneTasks) {
        assert (doneTasks.size() > 1);
        String output = "You've finished the task? Good job!\n"
                + "This task has been marked as done:\n";
        for (int i = 0; i < doneTasks.size(); i++) {
            output += doneTasks.get(i).displayInfo() + "\n";
        }
        return output;
    }

    /**
     * Alerts the user that a task has been deleted.
     */
    public String notifyDelete(Task removedTask) {
        assert (removedTask != null);
        return "Okay! Removing the task:\n"
                + removedTask.displayInfo();
    }

    /**
     * Alerts the user that multiple tasks have been deleted.
     */
    public String notifyMultiDelete(ArrayList<Task> removedTasks) {
        assert (removedTasks.size() > 1);
        String output = "Okay! Removing the task:\n";
        for (int i = 0; i < removedTasks.size(); i++) {
            output += removedTasks.get(i).displayInfo() + "\n";
        }
        return output;
    }

    /**
     * Displays the list of tasks in the current active session.
     */
    public String notifyList(ArrayList<Task> taskArrList) {
        assert (taskArrList != null);
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
        return noMatchMessage;
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
     * Updates the user of the tasks in the list which have had the tag removed.
     *
     * @param taskArrList The ArrayList of tasks with matching keywords.
     */
    public String notifyRemovedTaggedList(ArrayList<Task> taskArrList, String keyword) {
        String output = "Here's are the tasks which have the tag #" + keyword + " removed:\n";
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
        assert (listLength > 0);
        Task newTask = taskArrList.get(listLength - 1);
        return "New Task? I've added it to the list:\n"
                + newTask.displayInfo()
                + "\n"
                + String.format("Now you have %d task(s) in the list.", listLength);
    }

    /**
     * Shows the tasks recorded from the previous session.
     */
    public String printPrevSession(TaskList tasks) {
        ArrayList<Task> taskArrList = tasks.getAllTasks();
        int listLength = taskArrList.size();
        if (listLength == 0) {
            return "You have no outstanding tasks from the previous session.\n";
        } else {
            String output = "These tasks are from the previous session:\n";
            for (int i = 0; i < taskArrList.size(); i++) {
                output += String.format("%d. %s", i + 1, taskArrList.get(i).displayInfo());
                output += "\n";
            }
            return output;
        }
    }

    /**
     * Displays the list of commands available for the user.
     */
    public static String displayHelp() {
        return botDescription + commandList;
    }

    /**
     * Prints the exit message to the user's terminal.
     */
    public String exit() {
        return exitMessage;
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
}
