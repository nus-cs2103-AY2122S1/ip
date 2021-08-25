package duke;

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
     * Outputs the content of the task list.
     * @param lst refers to the current session's most updated task list
     */
    public void printList(TaskList lst) {
        boolean isEmptyList = false;
        int counter = 1;
        String output = "   Here are the tasks in your list: \n";

        while (!isEmptyList) {
            if (lst.getListOfTasks().isEmpty()) {
                output += "   Current List is empty...";
                isEmptyList = true;
            } else if (counter - lst.getListOfTasks().size() == 1) { // when there are no more tasks to iterate
                output = output.replaceAll("[\n\r]$", ""); // remove last newline
                isEmptyList = true;
            } else { // adds current task to the list based on counter index
                String lineAdded = String.format("   %d.%s \n", counter, lst.getListOfTasks().get(counter - 1));
                output += lineAdded;
                counter++;
            }
        }
        System.out.println(output);
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
