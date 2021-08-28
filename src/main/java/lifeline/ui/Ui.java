package lifeline.ui;

import java.util.Scanner;

import lifeline.task.Task;
import lifeline.task.TaskList;

/**
 * The class UI handles all information to be displayed to the user
 */
public class Ui {

    private Scanner sc;

    /**
     * Initialises the scanner object to read input from user
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the input from the user
     *
     * @return Input from user
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Returns string representation of the list of tasks created by the user
     *
     * @param taskList Lists of tasks created by user
     * @return String representing list of tasks
     */
    public String showTaskList(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();
        if (taskList.size() == 0) {
            stringBuilder.append("You have no remaining tasks.\n");
        } else {
            int uncompletedTask = 0;
            stringBuilder.append("Here " + (taskList.size() > 1 ? "are" : "is")
                    + " your " + (taskList.size() > 1 ? "tasks:" : "task:") + "\n");
            for (int i = 0; i < taskList.size(); i++) {
                Task currTask = taskList.get(i);
                stringBuilder.append((i + 1) + ". " + currTask + "\n");
                if (!currTask.isDone()) {
                    uncompletedTask++;
                }
            }
            stringBuilder.append("You have " + uncompletedTask + " uncompleted " + (uncompletedTask > 1 ? "tasks"
                    : "task") + ".\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Returns information regarding a task
     *
     * @param task Task to get information from
     * @return Information about task
     */
    public String showTask(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(task + "\n");
        return stringBuilder.toString();
    }

    /**
     * Returns all tasks with specified keyword
     *
     * @param foundTasks List of tasks containing keyword
     * @param keyword Keyword used to find tasks
     * @return Tasks with specified keyword
     */
    public String showFoundTasks(TaskList foundTasks, String keyword) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((foundTasks.size() == 1 ? "Task" : "Tasks") + " with keyword " + keyword + ":\n");
        for (int i = 0; i < foundTasks.size(); i++) {
            stringBuilder.append(foundTasks.get(i) + "\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Returns information on deleted task
     *
     * @param task Deleted task
     * @return Information on deleted task
     */
    public String showDeletedTask(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("I have removed the task:\n" + task + "\n");
        return stringBuilder.toString();
    }

    /**
     * Returns information on added task
     *
     * @param task Addded task
     * @return Information on added task
     */
    public String showAddedTask(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("I have added this task for you:\n" + task + "\n");
        return stringBuilder.toString();
    }

    /**
     * Returns information on completed task
     *
     * @param task Completed task
     * @return Information on completed task
     */
    public String showCompletedTask(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("You have completed the " + task.getClass().getSimpleName().toLowerCase() + ":\n"
                + task.getName() + "\n");
        return stringBuilder.toString();
    }


    /**
     * Returns string representation of error message
     *
     * @param errorMessage Error to be displayed to user
     * @return Error message
     */
    public String showError(String errorMessage) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(errorMessage + "\n");
        return stringBuilder.toString();
    }

    /**
     * Returns welcome message when user starts the program
     *
     * @return Welcome message
     */
    public String consoleGreet() {
        String lifeline = " _      _____ ______ ______ _      _____ _   _ ______\n"
                + "| |    |_   _|  ____|  ____| |    |_   _| \\ | |  ____|\n"
                + "| |      | | | |__  | |__  | |      | | |  \\| | |__\n"
                + "| |      | | |  __| |  __| | |      | | | . ` |  __|\n"
                + "| |____ _| |_| |    | |____| |____ _| |_| |\\  | |____\n"
                + "|______|_____|_|    |______|______|_____|_| \\_|______|\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello! I am\n" + lifeline);
        stringBuilder.append("What can I help you with today?\n");
        return stringBuilder.toString();
    }

    public String guiGreet() {
        return "Hello I am Lifeline. What can I help you with today?";
    }

    /**
     * Returns goodbye message when user exits the program
     *
     * @return Goodbye message
     */
    public String exit() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Goodbye! Thanks for chatting with me!\n");
        return stringBuilder.toString();
    }

    /**
     * Displays message to the user
     *
     * @param response Message to be displayed to user
     */
    public void printToConsole(String response) {
        System.out.println(response);
    }
}
