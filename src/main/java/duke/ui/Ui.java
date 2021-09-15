package duke.ui;

import duke.task.TaskList;
import duke.task.Task;

public class Ui {
    private static final String line = "____________________________________________________________";

    /**
     * Send a greeting to the user.
     */
    public static void Greet() {
        System.out.println( line + "\n" +
                "Hello I'm Duke\n" +
                "What can I do for you?\n" +
                line);
    }

    /**
     * Say goodbye to the user.
     */
    public static void bye() {
        System.out.println(line +"\nBye. Hope to see you again soon!\n" + line);
    }

    /**
     * Tell user the task info of tasks in the given TaskList
     *
     * @param tasks List of tasks.
     */
    public static void list(TaskList tasks) {
        System.out.println(line);
        System.out.println("Here are tasks in your list:");
        for(int index = 1; index <= tasks.size(); index++) {
            System.out.println(index + "." + tasks.get(index - 1).toString());
        }
        System.out.println(line);
    }

    /**
     * Tell user the task info of the completed task.
     *
     * @param task A Task be done by user.
     */
    public static void done(Task task) {
        System.out.println(line);
        System.out.println("done:\n" + task.toString());
        System.out.println(line);
    }

    /**
     * Tell user the task info of the deleted task.
     *
     * @param task A Task be deleted by user.
     */
    public static void delete(TaskList tasks, Task task) {
        System.out.println(line);
        System.out.println("removed:\n" + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Tell user the task info of the newly added task.
     * Tell user the size of TaskList after adding a new task.
     *
     * @param tasks A list of task.
     * @param task A Task be added by user.
     */
    public static void add(TaskList tasks, Task task) {
        System.out.println(line);
        System.out.println("added: " + task
                + "\nNow you have " + tasks.size() + ""
                + " tasks in the list.");
        System.out.println(line);
    }

    public void showLoadingError() {
        System.out.println(line);
        System.out.println("Cannot Load From Data.\n");
        System.out.println(line);
    }

}
