package duke;

import java.util.Scanner;

/**
 * Class for user interactions
 */
public class Ui {

    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void showTaskList(TaskList taskList) {
        System.out.println(taskList.toString());
    }

    /**
     * Shows a greeting message.
     */
    public void showGreetMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Echoes the given command.
     * @param t Command to be given.
     */
    public void echo(String t) {
        System.out.println("-----------------------------------------\n"
                + String.format("%s\n", t)
                + "-----------------------------------------\n");
    }

    /**
     * Shows exit message.
     */
    public void showExitMessage() {
        String exitMessage = "-----------------------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "-----------------------------------------\n";
        System.out.println(exitMessage);
    }

    /**
     * Shows message for adding tasks.
     *
     * @param newTask Task to be added.
     * @param newList TaskList after adding Task.
     */
    public void showAddMessage(Task newTask, TaskList newList) {
        System.out.println("-----------------------------------------\n"
                + "Got it. I've added this task:\n"
                + newTask.toString()
                + String.format("Now you have %s tasks in the list.\n", newList.getLength())
                + "-----------------------------------------\n");
    }

    /**
     * Shows message for marking task as done.
     *
     * @param markedTask Task to be marked as done.
     */
    public void showMarkDoneMessage(Task markedTask) {
        System.out.println("-----------------------------------------\n"
                + "Nice! I've marked this task as done:\n"
                + markedTask.toString()
                + "-----------------------------------------\n");
    }

    /**
     * Shows message for deleting task.
     *
     * @param deletedTask Task to be deleted.
     * @param newList TaskList after deletion.
     */
    public void showDeleteMessage(Task deletedTask, TaskList newList) {
        System.out.println("-----------------------------------------\n"
                + "Noted. I've removed this task:\n"
                + deletedTask.toString()
                + String.format("Now you have %s tasks in the list.\n", newList.getLength())
                + "-----------------------------------------\n");
    }

    /**
     * Shows message after search.
     * @param result String of all relevant Tasks.
     */
    public void showSearchResults(String result) {
        System.out.println("-----------------------------------------\n"
                + "Here are the matching tasks in your list:\n"
                + result
                + "-----------------------------------------\n");

    }
}
