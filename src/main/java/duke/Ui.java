package duke;

import duke.task.Task;

/**
 * Handles all UI elements for duke.Duke.
 */
public class Ui {
    /**
     * Divider line to denote Duke's speech.
     */
    private static final String line = "    ____________________________________________________________";

    /**
     * Prints the divider line used by Duke.
     */
    private void printDividerLine() {
        System.out.println(line);
    }

    /**
     * Prints an array of Strings between 2 lines.
     * @param lines
     */
    private void say(String... lines) {
        printDividerLine();
        for (String line : lines) {
            System.out.println("    " + line);
        }
        printDividerLine();
    }

    /**
     * Greets the user when Duke is started.
     */
    public void greet() {
        this.say("Hello from",
                " ____        _        ",
                "|  _ \\ _   _| | _____ ",
                "| | | | | | | |/ / _ \\",
                "| |_| | |_| |   <  __/",
                "|____/ \\__,_|_|\\_\\___|",
                "Make me do something!");
    }

    /**
     * Prints the list of tasks stored by Duke.
     * @param tasks list of Tasks
     */
    public void printTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            // Inform user if nothing has been stored.
            this.say("The list is empty!");
            return;
        }

        String[] listItems = new String[tasks.size()];

        for (int i = 0; i < tasks.size(); ++i) {
            listItems[i] = String.format("%d. %s", i + 1, tasks.getTask(i));
        }

        this.say(listItems);
    }

    /**
     * Notifies the user when a task is added
     * @param task Task that was added
     * @param index Position of the task in the list
     */
    public void notifyAdd(Task task, int index) {
        this.say(String.format("I have added a new %s!", task.getClass().getSimpleName().toLowerCase()),
                String.format("%d. %s", index, task));
    }

    /**
     * Notifies the user when a task is deleted.
     * @param task Task that was deleted
     * @param taskCount Number of remaining tasks left
     */
    public void notifyDelete(Task task, int taskCount) {
        this.say("I have removed this task!",
                String.format("   %s", task),
                String.format("You have %d task%s left.", taskCount, taskCount == 1 ? "" : "s"));
    }

    /**
     * Notifies the user when a task is marked done.
     * @param task task marked as done
     * @param index position of the task in the list
     */
    public void notifyMarkDone(Task task, int index) {
        this.say("I have marked the task as done!", String.format("%d. %s", index + 1, task));
    }

    /**
     * Relays an exception's message to the user.
     * @param exception DukeException thrown by Duke.
     */
    public void notifyError(DukeException exception) {
        this.say(exception.getMessage());
    }

    /**
     * Says goodbye to the user.
     *
     * Used after the bye command.
     */
    public void goodbye() {
        this.say("Bye bye, see you next time.");
    }
}
