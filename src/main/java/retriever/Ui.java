package retriever;

import java.util.ArrayList;
import java.util.Scanner;

import retriever.task.Task;

/**
 * The class handles the user interaction between the
 * user and the chatbot.
 */
public class Ui {
    private static String retrieverResponse;

    // Predetermined strings.
    private String dashedLine = "_______________________________________________";
    private String welcomeMessage = "Hello, I am Retriever\nHow Can I Help You Today?";
    private String goodByeMessage = "-> Sad To See You Go!";
    private String emptyListMessage = "My Memory Is Empty, Please Feed Items!";
    private String printListMessage = "-> Your Tasks, My Master:";
    private String goodBoyMessage = "Woof! Whose a Good Boy?";
    private String badBoyMessage = "Woof! Whose a Bad Boy?";
    private String logo = "  __      ^\n"
            + "o'')}____//\n"
            + " `_'      )\n"
            + "(_(_/-(_/\n";

    private Scanner scanner;

    /**
     * Sets up the scanner to take in user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the message received as an argument.
     *
     * @param message The message user would like to print.
     */
    public void printMessage(String message) {
        retrieverResponse = message;
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcomeMessage() {
        retrieverResponse = welcomeMessage;
    }

    /**
     * Prints the goodbye message.
     */
    public void printGoodByeMessage() {
        retrieverResponse = goodBoyMessage;
    }

    /**
     * Prints the error message.
     *
     * @param errorMessage The exception message to be printed.
     */
    public void printErrorMessage(String errorMessage) {
        retrieverResponse = errorMessage;
    }

    /**
     * Prints the task added message in a specified format.
     *
     * @param task The task that has been added.
     * @param taskListLength The length of the task list, after adding the task.
     */
    public void printTaskAdded(Task task, int taskListLength) {
        retrieverResponse = "Where's My Treat? I Added:\n"
                + task + "\nYou Owe Me " + taskListLength + " Treat(s), Master!";
    }

    /**
     * Prints the task deleted message in a specified format.
     *
     * @param task The task that has been deleted.
     * @param taskListLength The length of the task list, after deleting the task.
     */
    public void printTaskDeleted(Task task, int taskListLength) {
        retrieverResponse = badBoyMessage + "\n"
                + "Task Deleted!\n"
                + task
                + "\nYou Owe Me " + taskListLength + " Treat(s), Master!";
    }

    /**
     * Prints the task done message in a specified format.
     *
     * @param task The task that has been done.
     */
    public void printTaskMarkedAsDone(Task task) {
        retrieverResponse = goodBoyMessage + "\n"
                + "Task Done!\n"
                + task;
    }

    /**
     * Prints the tasks with a matching keyword.
     *
     * @param taskList The ArrayList containing tasks with matching keyword.
     */
    public void printTaskFoundByKeyword(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            retrieverResponse = "Sorry Master, I Couldn't Smell And Find WHat You Asked For!"
                    + "\n (Task With the Given Keyword Does Not Exist)\n";
            return;
        }

        retrieverResponse = "Woof! Look What I Found: /n";

        for (int i = 0; i < taskList.size(); i++) {
            retrieverResponse += (i + 1) + ". " + taskList.get(i) + "\n";
        }

        retrieverResponse += "You Owe Me " + taskList.size() + " Treat(s), Master!";
    }

    /**
     * Prints the tasks stored in the list.
     *
     * @param taskList The ArrayList containing the tasks to be printed.
     */
    public void printTaskList(ArrayList<Task> taskList) {
        // If the list is empty
        if (taskList.size() == 0) {
            retrieverResponse = "My Memory Is Empty, Please Feed Items!";
        } else {
            retrieverResponse = "Your Tasks, My Master:\n";
            for (int i = 0; i < taskList.size(); i++) {
                retrieverResponse += ((i + 1) + ". " + taskList.get(i)) + "\n";
            }
        }
    }

    /**
     * Returns the output that the command generated.
     *
     * @return The response to the command entered.
     */
    public String getRetrieverResponse() {
        return retrieverResponse;
    }
}

