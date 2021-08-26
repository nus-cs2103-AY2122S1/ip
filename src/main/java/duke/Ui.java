package duke;

import java.util.ArrayList;

/**
 * Handles interactions with user through message outputs.
 */
public class Ui {
    
    /**
     * Prints error message.
     * @param error message that is returned by an exception.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Prints greeting to user when program starts.
     */
    public void printGreeting() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Prints the task that has been marked done.
     * @param doneTask task that has been marked done.
     */
    public void printDoneMessage(Task doneTask) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(doneTask);
    }

    /**
     * Prints the task that has been deleted from the task list.
     * @param deletedTask task that has been deleted from the task list.
     */
    public void printDeleteMessage(Task deletedTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
    }

    public void printAddMessage(Task addedTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(addedTask);
    }

    /**
     * Prints the number of tasks in the task list.
     * @param size number of tasks in the task list.
     */
    public void printTaskListSize(int size) {
        if (size == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.printf("Now you have %d tasks in the list.%n", size);
        }
    }

    /**
     * Prints goodbye message to user when 'bye' command is entered.
     */
    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out all the tasks present in the task list.
     * @param taskList ArrayList of tasks user has.
     */
    public void showTasks(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println("There are no tasks in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            int num = 1;
            for (Task task : taskList) {
                System.out.printf("%d.%s%n", num, task);
                num++;
            }
        }
    }

}
