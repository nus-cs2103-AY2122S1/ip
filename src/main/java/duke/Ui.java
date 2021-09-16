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
     * Formats an error message suitable for display.
     *
     * @param e The Exception to be formatted.
     * @return The generated message to display.
     */
    public String formatError(Exception e) {
        String result = "[ERROR]\n";
        result += e.getMessage();
        return result;
    }

    /**
     * Generates the 'task added' message.
     *
     * @param task The task being added.
     * @param taskList The TaskList the task was added to.
     * @return The generated message to display.
     */
    public String addTask(Task task, TaskList taskList) {
        String numOfTasks = formatNumberOfTasks(taskList.size());
        String result = "is added.\n";
        result += task.toString() + "\n";
        result += "now is have " + numOfTasks + ".";
        return result;
    }

    /**
     * Generates the 'task completed' message.
     *
     * @param task The task being completed.
     * @return The generated message to display.
     */
    public String completeTask(Task task) {
        String result = "is done!\n";
        result += task.toString();
        return result;
    }

    /**
     * Generates the 'task deleted' message.
     *
     * @param task The task being deleted.
     * @param taskList The TaskList the task was deleted from.
     * @return The generated message to display.
     */
    public String deleteTask(Task task, TaskList taskList) {
        String numOfTasks = formatNumberOfTasks(taskList.size());
        String result = "is deleted!\n";
        result += task.toString() + "\n";
        result += "now is have " + numOfTasks + ".";
        return result;
    }

    /**
     * Generates the 'task edited' message.
     *
     * @param task The task being edited.
     * @return The generated message to display.
     */
    public String editTask(Task task) {
        String result = "is got it!\n";
        result += task.toString();
        return result;
    }

    /**
     * Lists out all Tasks numbered and on individual lines.
     * Calls the toString() method of each Task to display them
     * and their type/status.
     *
     * @param taskList The TaskList to be displayed.
     * @return The generated message to display.
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
     * Finds tasks from the given TaskList containing the given string, and generates
     * a message to display.
     *
     * @param taskList The TaskList to search.
     * @param str The string to search for.
     * @return The generated message to display.
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
     * Generates the initialisation message for Duke.
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
     * Generates the exit message for Duke.
     */
    public String exit() {
        return "okay is bye!!";
    }

    /**
     * Formats the number of tasks.
     * For example, if i = 1 then "1 task" is returned.
     * If i = 2 then "2 tasks" is returned.
     *
     * @param i The number of tasks.
     * @return The number of tasks in string format.
     */
    private String formatNumberOfTasks(int i) {
        boolean isPlural = i != 1;
        String noun = isPlural
                      ? "tasks"
                      : "task";

        return i + " " + noun;
    }
}
