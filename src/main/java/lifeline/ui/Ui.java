package lifeline.ui;

import java.util.Scanner;

import lifeline.task.Task;
import lifeline.task.TaskList;

/**
 * The class UI handles all information to be displayed to the user.
 */
public class Ui {

    private Scanner scanner;

    /**
     * Initialises the scanner object to read input from user.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the input from the user.
     *
     * @return Input from user.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Returns string representation of the list of tasks created by the user.
     *
     * @param taskList Lists of tasks created by user.
     * @return String representing list of tasks.
     */
    public String showTaskList(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();
        if (taskList.getSize() == 0) {
            stringBuilder.append("You have no remaining tasks.\n");
        } else {
            int uncompletedTask = 0;
            stringBuilder.append("Here " + (taskList.getSize() > 1 ? "are" : "is")
                    + " your " + (taskList.getSize() == 1 ? "task:" : "tasks:") + "\n");

            for (int i = 0; i < taskList.getSize(); i++) {
                Task currTask = taskList.getTask(i);
                stringBuilder.append((i + 1) + ". " + currTask + "\n");
                if (!currTask.isDone()) {
                    uncompletedTask++;
                }
            }

            stringBuilder.append("You have " + uncompletedTask + " uncompleted "
                    + (uncompletedTask == 1 ? "task" : "tasks") + ".\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Returns all tasks with specified keyword.
     *
     * @param foundTasks List of tasks containing keyword.
     * @param keyword Keyword used to find tasks.
     * @return Tasks with specified keyword.
     */
    public String showFoundTasks(TaskList foundTasks, String keyword) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((foundTasks.getSize() == 1 ? "Task" : "Tasks") + " with keyword " + keyword + ":\n");
        for (int i = 0; i < foundTasks.getSize(); i++) {
            stringBuilder.append(foundTasks.getTask(i) + "\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Informs user that tasks have been deleted and number of tasks left.
     *
     * @param taskList TaskList to inform user how many tasks are left.
     * @return Information on deleted tasks and number of tasks left.
     */
    public String showDeletedTaskMessage(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();
        int numberOfTasks = taskList.getSize();
        stringBuilder.append("You have " + numberOfTasks + (numberOfTasks == 1 ? " task" : " tasks") + " remaining");
        return stringBuilder.toString();
    }

    /**
     * Informs user that tasks have been completed and number of uncompleted tasks.
     *
     * @param taskList TaskList to inform user how many uncompleted tasks are left
     * @return Information on completed tasks and number of uncompleted tasks left.
     */
    public String showCompletedTaskMessage(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();
        int uncompletedTasks = taskList.getNumberOfUncompletedTasks();
        stringBuilder.append("You have " + uncompletedTasks + " uncompleted "
                + (uncompletedTasks == 1 ? "task" : "tasks"));
        return stringBuilder.toString();
    }

    /**
     * Returns information on added task.
     *
     * @param task Addded task.
     * @return Information on added task.
     */
    public String showAddedTask(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("I have added this task for you:\n" + task + "\n");
        return stringBuilder.toString();
    }

    /**
     * Tells user what commands are available and what the commmands do.
     *
     * @return Message to tell user what commands are available and what the commands do.
     */
    public String showHelpMessage() {
        return "Date should be specified as <dd/MM/yy> and time should be specified as <HHmm>\n"
                + "bye: Exits the program.\n"
                + "list: Lists your tasks.\n"
                + "done <INDEX>: Marks task at index as done. You can specify multiple indices separated by commas.\n"
                + "delete <INDEX>: Deletes task at index. You can specify multiple indices separated by commas.\n"
                + "find <KEYWORD>: Find all tasks containing keyword,\n"
                + "deadline <DESC> /by <date> <time>: Creates Deadline.\n"
                + "event <DESC> /at <date> <startTime> <endTime>: Creates Event.\n"
                + "todo <DESC>: Creates ToDo.\n"
                + "help: Shows this message.\n";
    }

    /**
     * Displays available alias to user.
     *
     * @return Aliases to user
     */
    public String showAlias() {
        return "alias - a, al\n"
                + "bye - b, bb, goodbye\n"
                + "deadline - d, dl\n"
                + "delete - del, rm\n"
                + "done - complete\n"
                + "event - e\n"
                + "find - f\n"
                + "help - h\n"
                + "list - l, ls\n"
                + "todo - t, td\n";
    }

    /**
     * Returns string representation of error message.
     *
     * @param errorMessage Error to be displayed to user.
     * @return Error message.
     */
    public String showError(String errorMessage) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(errorMessage + "\n");
        return stringBuilder.toString();
    }

    /**
     * Returns welcome message when user starts the program.
     *
     * @return Welcome message.
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

    /**
     * Returns greeting message for GUI when user starts program.
     *
     * @return Greeting message.
     */
    public String guiGreet() {
        return "Hello I am Lifeline. What can I help you with today?";
    }

    /**
     * Returns goodbye message when user exits the program.
     *
     * @return Goodbye message.
     */
    public String exit() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Goodbye! Thanks for chatting with me!\n");
        return stringBuilder.toString();
    }

    /**
     * Displays message to the user.
     *
     * @param response Message to be displayed to user.
     */
    public void printToConsole(String response) {
        System.out.println(response);
    }
}
