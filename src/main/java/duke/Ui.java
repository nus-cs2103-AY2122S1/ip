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
    public String getAddMessage(String taskString, TaskList taskList) {
        return "Got it. I've added this task:\n"
                + "  " + taskString + "\n"
                + "Now you have " + taskList.getSize()
                + " task" + (taskList.getSize() > 1 ? "s" : "") + " in the list.";
    }

    /**
     * Shows a message informing the user of a deleted task.
     *
     * @param taskString String representation of the deleted task.
     * @param taskList TaskList the task was deleted from.
     */
    public String getDeleteMessage(String taskString, TaskList taskList) {
        return "Noted. I've removed this task:\n"
                + "  "
                + taskString + "\n"
                + "Now you have " + taskList.getSize()
                + " task" + (taskList.getSize() > 1 ? "s" : "") + " in the list.";
    }

    /**
     * Shows a message informing the user of a task marked as done.
     *
     * @param taskString String representation of the task marked as done.
     */
    public String getDoneMessage(String taskString) {
        return "Nice! I've marked this task as done:\n"
                + "  "
                + taskString;
    }

    /**
     * Shows all the tasks in the list.
     *
     * @param taskList TaskList containing the list of tasks.
     */
    public String getListMessage(TaskList taskList) {
        return "Here are the tasks in your list:\n"
                + taskList.toString();
    }

    public String getFindMessage(String foundTasks) {
        return "Here are the matching tasks in your list:\n"
                + foundTasks;
    }

    /**
     * Shows an error message.
     *
     * @param errorMessage Error message to show to the user.
     */
    public String getError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Shows the welcome message when the user starts the program.
     */
    public String getWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo + "\n"
                + "Hello! I'm Nukem\n"
                + "What can I do for you?";
    }

    /**
     * Shows the end message when the user closes the program.
     */
    public void showEndMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
