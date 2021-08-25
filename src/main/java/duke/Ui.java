package duke;

import duke.task.Task;

/**
 * Handles all UI elements for duke.Duke.
 */
public class Ui {
    private static final String line = "    ____________________________________________________________";

    private void printDividerLine() {
        System.out.println(line);
    }

    private void say(String... lines) {
        printDividerLine();
        for (String line : lines) {
            System.out.println("    " + line);
        }
        printDividerLine();
    }

    public void greet() {
        this.say("Hello from",
                " ____        _        ",
                "|  _ \\ _   _| | _____ ",
                "| | | | | | | |/ / _ \\",
                "| |_| | |_| |   <  __/",
                "|____/ \\__,_|_|\\_\\___|",
                "Make me do something!");
    }

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

    public void notifyAdd(Task task, int index) {
        this.say(String.format("I have added a new %s!", task.getClass().getSimpleName().toLowerCase()),
                String.format("%d. %s", index, task));
    }

    public void notifyDelete(Task task, int taskCount) {
        this.say("I have removed this task!",
                String.format("   %s", task),
                String.format("You have %d task%s left.", taskCount, taskCount == 1 ? "" : "s"));
    }

    public void notifyMarkDone(Task task, int index) {
        this.say("I have marked the task as done!", String.format("%d. %s", index + 1, task));
    }

    public void notifyError(DukeException exception) {
        this.say(exception.getMessage());
    }

    public void goodbye() {
        this.say("Bye bye, see you next time.");
    }
}
