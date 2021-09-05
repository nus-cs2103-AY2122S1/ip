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

    public String showTaskList(TaskList taskList) {
        return taskList.toString();
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
    public String showAddMessage(Task newTask, TaskList newList) {
        return "Got it. I've added this task:\n"
                + newTask.toString()
                + String.format("Now you have %s tasks in the list.\n", newList.getLength());
    }

    /**
     * Shows message for marking task as done.
     *
     * @param markedTask Task to be marked as done.
     */
    public String showMarkDoneMessage(Task markedTask) {

        return "Nice! I've marked this task as done:\n"
                + markedTask.toString();
    }

    /**
     * Shows message for deleting task.
     *
     * @param deletedTask Task to be deleted.
     * @param newList TaskList after deletion.
     */
    public String showDeleteMessage(Task deletedTask, TaskList newList) {
        return "Noted. I've removed this task:\n"
                + deletedTask.toString()
                + String.format("Now you have %s tasks in the list.\n", newList.getLength());
    }

    /**
     * Shows message after search.
     * @param result String of all relevant Tasks.
     */
    public String showSearchResults(String result) {
        return "Here are the matching tasks in your list:\n" + result;
    }
}
