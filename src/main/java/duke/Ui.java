package duke;

import java.util.ArrayList;

import duke.controller.MainWindow;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The User Interface of Duke.
 * Handles the creation of Duke's responses.
 * @author Thomas Hogben
 */
public class Ui {
    private Duke duke;
    private MainWindow mainWindow;
    private ArrayList<Exception> startupErrorLog;
    private boolean isInitialised = false;

    public Ui(Duke duke) {
        this.duke = duke;
        startupErrorLog = new ArrayList<>();
    }

    /**
     * Displays a message in the Ui.
     *
     * @param msg The message to be displayed.
     */
    public void display(String msg) {
        if (!isInitialised) {
            return;
        }

        assert mainWindow != null : "Duke's MainWindow cannot be null";
        mainWindow.addDukeDialog(msg);
    }

    /**
     * Displays an error message in the Ui.
     *
     * @param e The exception to be displayed.
     */
    public void display(Exception e) {
        if (!isInitialised) {
            startupErrorLog.add(e);
            return;
        }

        String msg = formatError(e);
        display(msg);
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
        StringBuilder result = new StringBuilder();

        if (taskList.size() == 0) {
            result.append("is no tasks today.");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                String formattedTask = formatTaskWithNumber(i, taskList.getTask(i));
                result.append(formattedTask);
            }
        }
        return result.toString();
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
        StringBuilder result = new StringBuilder();
        ArrayList<String> tasksFound = new ArrayList<>();

        for (int i = 0; i < taskList.size(); i++) {
            String formattedTask = formatTaskWithNumber(i, taskList.getTask(i));
            boolean hasKeyword = formattedTask.toUpperCase().contains(str.toUpperCase());
            if (hasKeyword) {
                tasksFound.add(formattedTask);
            }
        }

        if (tasksFound.size() == 0) {
            result.append("is didn't find matching task.");
        } else {
            for (String s : tasksFound) {
                result.append(s);
            }
        }

        return result.toString();
    }

    /**
     * Intialises the Ui and displays the initialisation messages for Duke.
     */
    public void init(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        isInitialised = true;
        displayStartupErrorLog();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String result = "Behold; The GREAT, The MIGHTY\n" + logo;
        result += "\nhello name is duke\n";
        result += "how is help today;";
        display(result);
    }

    /**
     * Generates the exit message and tells Duke to shut down the program.
     * Also disables user input.
     */
    public String exit() {
        duke.exitProgram();
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

    /**
     * Formats the task suitable for display in a list.
     *
     * @param i The index of the task to be displayed.
     * @param task The task to be displayed.
     * @return The formatted task, ie. "1. [T] abc."
     */
    private String formatTaskWithNumber(int i, Task task) {
        return (i + 1) + ". " + task + ".\n";
    }

    /**
     * Displays all the error messages prior to Ui initialisation.
     */
    private void displayStartupErrorLog() {
        if (startupErrorLog.size() == 0) {
            return;
        }

        StringBuilder result = new StringBuilder();

        for (Exception e : startupErrorLog) {
            String errorMsg = e.getMessage();
            result.append(errorMsg)
                  .append("\n");
        }

        display(result.toString());
    }
}
