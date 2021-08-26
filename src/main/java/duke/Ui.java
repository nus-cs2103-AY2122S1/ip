package duke;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class which deals with the UI
 */

public class Ui {

    /** prints a line divider */
    private final String line;

    private final Scanner sc;

    Ui() {
        this.line = "   ----------------------------------------------------------------------------------------------";
        sc = new Scanner(System.in);
    }

    /**
     * Welcome message displayed upon app startup
     */
    public void showWelcomeMessage() {
        String openingMessage = line
                + "\n   Hello! This is Duke :)"
                + "\n   To use my AUTOSAVE feature, please type 'bye' when you're done!"
                + "\n   Otherwise, I am unable to save your tasks for future reference :("
                + "\n   Now, what can I do for you? \n"
                + line;
        System.out.println(openingMessage);
    }

    /**
     * Displays a loading error upon encountering an InvalidStorageFilePathException
     */
    public void showLoadingError() {
        System.out.println("   LOADING ERROR: Unable to load previously saved data...");
    }

    /**
     * Displays the error message of any checked/unchecked exceptions.
     */
    public void showError(String errorMessage) {
        if (errorMessage.equals("") || errorMessage == null) {
            System.out.println("   Unknown error was not handled by DukeException");
        } else {
            System.out.println(errorMessage);
        }
    }

    /**
     * Displays the farewell message upon invoke of the 'bye' command.
     */
    public void showByeMessage() {
        sc.close();
        String goodbyeMessage = "   Bye! Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }

    public void showLine() {
        System.out.println(this.line);
    }

    /**
     * Displays the description of the newly added task.
     */
    public void showAddTaskMessage(String taskDescription, int currentListSize) {
        String output = String.format("   Got it. I've added this task: \n      %s\n   Now you have %d tasks in the list.",
                taskDescription, currentListSize);
        System.out.println(output);
    }

    /**
     * Displays the description of the specified deleted task
     */
    public void showDeletedTask(String taskDescription, int currentListSize) {
        String output = String.format("   Noted. I've deleted this task: \n      %s\n   Now you have %d tasks in the list.",
                taskDescription, currentListSize);
        System.out.println(output);
    }

    /**
     * Displays the description of the task upon completion.
     */
    public void showCompletedTask(String taskInfo) {
        String output = String.format("   Nice! I've marked this task as done: \n      %s", taskInfo);
        System.out.println(output);
    }

    /**
     * Invoked when user inputs an invalid command
     */
    public void showInvalidCommand() {
        System.out.println("   INVALID INPUT: Start the sentence with either 'todo'/'deadline'/'event'/'list'/'done'/'delete'/'bye'");
    }

    /**
     * Helper method used by printList(TaskList lst) and printFoundTasks(ArrayList<Task> listOfTasks, String keyWord)
     * Processes the list and concatenates the toString() values of tasks into a readable format
     *
     * @param listOfTasks The list to be processed (filtered by prior conditions in other functions)
     */
    public String processList(ArrayList<Task> listOfTasks) {
        String output = "";
        boolean isEmptyList = false;
        int counter = 1;

        while (!isEmptyList) {
            if (counter - listOfTasks.size() == 1) {
                output = output.replaceAll("[\n\r]$", ""); // remove last newline
                isEmptyList = true;
            } else {
                String lineAdded = String.format("   %d.%s \n", counter, listOfTasks.get(counter - 1));
                output += lineAdded;
                counter++;
            }
        }
        return output;
    }

    /**
     * Prints out all the tasks in the list
     *
     * @param lst current session's most updated list
     */
    public void printList(TaskList lst) {
        if (lst.getListOfTasks().size() == 0) {
            System.out.println("   Current List is empty...");
        } else {
            String output = "   Here are the tasks in your list: \n";
            output += processList(lst.getListOfTasks());
            System.out.println(output);
        }
    }

    /**
     * Prints out all matching tasks in the list which contains the keyword
     *
     * @param listOfTasks ArrayList of current session's most updated list
     * @param keyWord user input
     */
    public void printFoundTasks(ArrayList<Task> listOfTasks, String keyWord) {
        if (listOfTasks.size() == 0) {
            System.out.println(String.format("  No matching tasks containing '%s' could be found :(", keyWord));
        } else {
            String output = "   Here are the matching tasks in your list: \n";
            output += processList(listOfTasks);
            System.out.println(output);
        }
    }

    /**
     * Reads the user input
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the message with information on the task type if the user input enters an empty description
     */
    public String printEmptyDescription(String taskType) {
        return String.format("   OOPS!!! The description of a %s cannot be empty.", taskType);
    }
}
