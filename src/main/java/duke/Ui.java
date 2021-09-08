package duke;

import duke.task.Task;
import duke.task.TaskList;
import java.util.ArrayList;

/**
 * The User Interface of Duke.
 * @author Thomas Hogben
 */
public class Ui {
    /**
     * Displays a message in the Ui.
     *
     * @param msg The message to be displayed.
     */
    public void display(String msg) {
        System.out.println(msg);
    }

    /**
     * Displays an Exception in the Ui.
     *
     * @param e The Exception to be displayed.
     */
    public void display(Exception e) {
        display(e.getMessage());
    }

    /**
     * Displays the 'task added' message.
     *
     * @param task The task being added.
     * @param taskList The TaskList the task was added to.
     */
    public void addTask(Task task, TaskList taskList) {
        display("is added.");
        display(task.toString());
        display("now is have " + taskList.size() + " task" +
                (taskList.size() == 1 ? "" : "s") + ".");
    }

    /**
     * Displays the 'task completed' message.
     *
     * @param task The task being sompleted.
     */
    public void completeTask(Task task) {
        display("is done!");
        display(task.toString());
    }

    /**
     * Displays the 'task-deleted' message.
     *
     * @param task The task being deleted.
     * @param taskList The TaskList the task was deleted from.
     */
    public void deleteTask(Task task, TaskList taskList) {
        display("is deleted!");
        display(task.toString());
        display("now is have " + taskList.size() + " task" +
                (taskList.size() == 1 ? "" : "s") + ".");
    }

    /**
     * Lists out all Tasks numbered and on individual lines.
     * Calls the toString() method of each Task to display them
     * and their type/status.
     *
     * @param taskList The TaskList to be displayed.
     */
    public void listTasks(TaskList taskList) {
        if (taskList.size() == 0) {
            display("is no tasks today.");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                String taskDescription = taskList.getTask(i).toString();
                display((i + 1) + "." + taskDescription + ".");
            }
        }
    }

    /**
     * Finds and displays tasks from the given TaskList containing the given string.
     *
     * @param taskList The TaskList to search.
     * @param str The string to search for.
     */
    public void find(TaskList taskList, String str) {
        ArrayList<String> tasksFound = new ArrayList<>();

        for (int i = 0; i < taskList.size(); i++) {
            String taskDescription = taskList.getTask(i).toString();
            boolean hasKeyword = taskDescription.toUpperCase().contains(str.toUpperCase());
            if (hasKeyword) {
                tasksFound.add((i + 1) + "." + taskDescription + ".");
            }
        }

        if (tasksFound.size() == 0) {
            display("is didn't find matching task.");
        } else {
            for (int i = 0; i < tasksFound.size(); i++) {
                display(tasksFound.get(i));
            }
        }
    }

    /**
     * Displays the initialisation message for Duke.
     */
    public void init() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        display("Hello from\n" + logo);
        display("hello name is duke");
        display("how is help today; （´・｀ ）♡");
    }

    /**
     * Displays the exit message for Duke.
     */
    public void exit() {
        display("okay is bye!!");
    }
}
