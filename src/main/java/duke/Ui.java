package duke;

import java.util.Scanner;

/**
 * Encapsulates UI-related functionality of Iris
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Gets Iris to say a message
     * 
     * @param message message to say
     */
    public void say(String message) {
        say(message, true);
    }

    /**
     * Gets Iris to say a message
     *
     * @param message message to say
     * @param isFirst say Iris's name only if first message
     */
    public void say(String message, boolean isFirst) {
        String name = isFirst ? "Iris:" : "     ";
        System.out.printf("%s %s%n", name, message);
    }

    /**
     * Gets Iris to notify user about error
     *
     * @param exception exception to convey to user
     */
    public void sayError(IrisException exception) {
        say(exception.getMessage());
    }

    /**
     * Gets Iris to notify user about new Task added
     *
     * @param tasks TaskList object representing current list of tasks
     */
    public void sayTaskAdded(TaskList tasks) {
        int count = tasks.getCount();
        say("Got it. I've added this task:");
        say(tasks.get(count - 1).toString(), false);
        say(String.format("Now you have %d %s in the list.",
                count, count == 1 ? "task" : "tasks"), false);
    }

    /**
     * Gets Iris to list out all Tasks in the TaskList
     *
     * @param tasks TaskList object representing current list of tasks
     */
    public void listTasks(TaskList tasks) {
        for (int i = 0; i < tasks.getCount(); i++) {
            say(String.format("%d. %s", i + 1, tasks.get(i)), i == 0);
        }
    }

    /**
     * Prompts user for a command
     *
     * @return user's command
     */
    public String prompt() {
        System.out.print("me: ");
        return scanner.nextLine();
    }
}
