package duke;

import java.util.Scanner;

/**
 * To deal with interactions with the user.
 */
public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);

    }

    /**
     * Prints the welcome message.
     */
    public String showWelcome() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Reads the user input.
     *
     * @return returns the user input
     */
    public String readCommand() {
        String command = sc.nextLine();
        return command;
    }
    
    public String printTask(Task task) {
        return task.toString() + "\n";
    }

    public String listTaskNumber(TaskList tasks) {
        return "Now you have " + tasks.size() + " tasks in the list.\n";
    }

    public String printDeleteMessage(Task task) {
        return "Noted. I've removed this task:\n" + task;
    }

    public String printDoneTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }
    
    public String printNoKeyword(String keyword) {
        return "There were no tasks found with keyword: " + keyword;
    }

    public String printFoundKeyword(String message) {
        return "Here are the tasks in your list that match the keyword:\n" + message;
    }
    
    public String listTasks(TaskList tasks) {
        String returnMessage = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            int j = i + 1;
            returnMessage += (j + ". " + tasks.get(i) + "\n");
        }
        return returnMessage;        
    }
    
    public String printErrorMessage(String errorMessage) {
        return "Invalid input!\n" + errorMessage;
    }
    
    /**
     * Prints the closing message.
     * @return
     */
    public String goodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the Loading Error Message.
     */
    public void showLoadingError() {
        System.out.println("There was an error reading the saved file :(");
    }


}
