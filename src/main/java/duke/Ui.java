package duke;

import java.util.ArrayList;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The User Interface of Duke.
 * Handles the creation of Duke's responses.
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
    public String formatError(Exception e) {
        String result = "[ERROR]\n";
        result += e.getMessage();
        return result;
    }

    /**
     * Displays the 'task added' message.
     *
     * @param task The task being added.
     * @param taskList The TaskList the task was added to.
     */
    public String addTask(Task task, TaskList taskList) {
        String result = "is added.\n";
        result += task.toString() + "\n";
        result += "now is have " + taskList.size() + " task"
                + (taskList.size() == 1 ? "" : "s") + ".";
        return result;
    }

    /**
     * Displays the 'task completed' message.
     *
     * @param task The task being sompleted.
     */
    public String completeTask(Task task) {
        String result = "is done!\n";
        result += task.toString();
        return result;
    }

    /**
     * Displays the 'task-deleted' message.
     *
     * @param task The task being deleted.
     * @param taskList The TaskList the task was deleted from.
     */
    public String deleteTask(Task task, TaskList taskList) {
        String result = "is deleted!\n";
        result += task.toString() + "\n";
        result += "now is have " + taskList.size() + " task"
                + (taskList.size() == 1 ? "" : "s") + ".";
        return result;
    }

    public String editTask(Task task) {
        String result = "is got it\n";
        result += task.toString();
        return result;
    }

    /**
     * Lists out all Tasks numbered and on individual lines.
     * Calls the toString() method of each Task to display them
     * and their type/status.
     *
     * @param taskList The TaskList to be displayed.
     */
    public String listTasks(TaskList taskList) {
        String result = "";
        if (taskList.size() == 0) {
            result = "is no tasks today.";
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                String taskDescription = taskList.getTask(i).toString();
                result += (i + 1) + "." + taskDescription + ".\n";
            }
        }
        return result;
    }

    /**
     * Finds and displays tasks from the given TaskList containing the given string.
     *
     * @param taskList The TaskList to search.
     * @param str The string to search for.
     */
    public String find(TaskList taskList, String str) {
        String result = "";
        ArrayList<String> tasksFound = new ArrayList<>();

        for (int i = 0; i < taskList.size(); i++) {
            String taskDescription = taskList.getTask(i).toString();
            boolean hasKeyword = taskDescription.toUpperCase().contains(str.toUpperCase());
            if (hasKeyword) {
                tasksFound.add((i + 1) + "." + taskDescription + ".");
            }
        }

        if (tasksFound.size() == 0) {
            result = "is didn't find matching task.";
        } else {
            for (int i = 0; i < tasksFound.size(); i++) {
                result += tasksFound.get(i).toString() + "\n";
            }
        }

        return result;
    }

    /**
     * Displays the initialisation message for Duke.
     */
    public String init() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String result = "Behold; The Great, The Mighty\n" + logo;
        result += "\nhello name is duke\n";
        result += "how is help today; （´・｀ ）♡";
        return result;
    }

    /**
     * Displays the exit message for Duke.
     */
    public String exit() {
        return "okay is bye!!";
    }
}
