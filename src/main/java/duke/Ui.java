package duke;

import java.util.Scanner;

public class Ui {
    private Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Reads the next line of the input, or prompt the user to input a command.
     *
     * @return The next line of input as a String.
     */
    public String readNextLine() {
        String fullCommand = in.nextLine();
        return fullCommand;
    }

    /**
     * Shows a message informing the user of an added task.
     *
     * @param taskString String representation of the added task.
     * @param taskList TaskList the new task was added to.
     */
    public void showAddMessage(String taskString, TaskList taskList) {
        System.out.println("Got it. I've added this task:\n"
                + "  " + taskString + "\n"
                + "Now you have " + taskList.getSize()
                + " task" + (taskList.getSize() > 1 ? "s" : "") + " in the list.");
    }

    /**
     * Shows a message informing the user of a deleted task.
     *
     * @param taskString String representation of the deleted task.
     * @param taskList TaskList the task was deleted from.
     */
    public void showDeleteMessage(String taskString, TaskList taskList) {
        System.out.println("Noted. I've removed this task:\n"
                + "  "
                + taskString + "\n"
                + "Now you have " + taskList.getSize()
                + " task" + (taskList.getSize() > 1 ? "s" : "") + " in the list.");
    }

    /**
     * Shows a message informing the user of a task marked as done.
     *
     * @param taskString String representation of the task marked as done.
     */
    public void showDoneMessage(String taskString) {
        System.out.println("Nice! I've marked this task as done:\n"
                + "  "
                + taskString);
    }

    /**
     * Shows all the tasks in the list.
     *
     * @param taskList TaskList containing the list of tasks.
     */
    public void showListMessage(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList.toString());
    }

    public void showFindMessage(String foundTasks) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(foundTasks);
    }

    /**
     * Shows an error message.
     *
     * @param errorMessage Error message to show to the user.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Shows the welcome message when the user starts the program.
     */
    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Nukem\n"
                + "What can I do for you?");
    }

    /**
     * Shows the end message when the user closes the program.
     */
    public void showEndMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
