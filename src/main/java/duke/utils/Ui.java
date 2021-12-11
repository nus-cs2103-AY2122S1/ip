package duke.utils;

import java.util.ArrayList;
import java.util.HashMap;

import duke.exceptions.DukeException;
import duke.tasks.Deadlines;
import duke.tasks.Task;

/**
 * Deals with all the user-facing commands such as reading user input, and printing results.
 */
public class Ui {
    private static final String HORIZONTAL_LINES = "-----------------------------------------";

    /**
     * Prints a long horizontal line which acts as a separator.
     */
    public void showLines() {
        System.out.println(HORIZONTAL_LINES);
    }

    /**
     * Displays a welcome message.
     */
    public void showWelcome() {
        System.out.println(HORIZONTAL_LINES + "\nHello! I'm Naruto\nWhat can I do for you?\n" + HORIZONTAL_LINES);
    }

    /**
     * Displays the message indicating that a new task has been added to the TaskList.
     *
     * @param newTask the new task that has been added.
     * @param tasks the task list containing all the tasks.
     */
    public void showTaskAddedInteraction(Task newTask, TaskList tasks) {
        System.out.printf("Great, I've added this task:\n  %s%n", newTask.toString());
        System.out.printf("Now you have %d tasks in the list.%n", tasks.getSize());
    }

    /**
     * Prints all the tasks in the task list, and also displays how many tasks are currently
     * within.
     *
     * @param tasks the task list containing all the tasks.
     */
    public void showGettingAllTaskItemsInteraction(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        int taskNum;
        for (taskNum = 0; taskNum < tasks.getSize(); taskNum++) {
            Task task = tasks.getTask(taskNum);
            System.out.println(counter + "." + task.toString());
            counter += 1;
        }
        System.out.println(String.format("There are %d tasks in the list.", tasks.getSize()));
    }

    /**
     * Displays a message indicating that the given task has been marked as done.
     *
     * @param task the task that has been marked as done.
     */
    public void showTaskDoneInteraction(Task task) {
        System.out.println("Good job! I've marked this task as done:");
        System.out.println(task.toString());
    }

    /**
     * Displays a message indicating that the given task has been deleted from the task list.
     *
     * @param task the task that has been deleted.
     * @param tasks the task list containing all the tasks.
     */
    public void showTaskDeletedInteraction(Task task, TaskList tasks) {
        System.out.printf("Roger that Sensei, I've removed this task:\n  %s%n", task.toString());
        System.out.printf("Now you have %d tasks in the list.%n", tasks.getSize());
    }

    /**
     * Displays a standard message to indicate that the chat-bot is shutting down.
     */
    public void showByeMessage() {
        System.out.println("See ya! Hope to see you again!");
    }

    /**
     * Displays the message from the DukeException that was caught.
     *
     * @param e the DukeException that was caught.
     */
    public void showError(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Displays all the tasks that matches a certain keyword, where the matched tasks
     * are already given as a parameter to this method.
     *
     * @param matchingTasks the tasks that matched the keyword. These tasks are to be printed.
     * @param tasks the task list containing all the tasks.
     */
    public void showMessagePrintingAllMatchingTasks(HashMap<String, Task> matchingTasks, TaskList tasks) {
        System.out.println("Here are all the matching tasks in your list:");
        for (int counter = 1; counter <= tasks.getSize(); counter++) {
            String key = String.valueOf(counter);
            if (matchingTasks.containsKey(key)) {
                Task matchedTask = matchingTasks.get(key);
                assert (matchedTask != null) : "Matched task cannot be null.";
                System.out.println(key + "." + matchedTask.toString());
            }
        }
        System.out.println(String.format("There are %d matching tasks in the list.", matchingTasks.size()));
    }

    /**
     * Displays all the deadlines in the sorted deadlines list.
     *
     * @param allDeadlines the array list of deadlines already sorted in chronological order.
     * @param tasks the full task list.
     */
    public void showGettingAllSortedDeadlinesInteraction(ArrayList<Deadlines> allDeadlines, TaskList tasks) {
        System.out.println("Here are all the deadlines in your list, sorted in chronological order:");
        for (Deadlines deadline : allDeadlines) {
            int taskNum = tasks.getTaskNumberOf(deadline);
            assert (taskNum > 0) : "Invalid task number received for this deadline";
            System.out.println(taskNum + "." + deadline.toString());
        }
        System.out.println(String.format("There are %d deadlines in the list.", allDeadlines.size()));
    }
}
