package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * A class which deals with the interaction
 * with the user.
 */
public class Ui {

    /** The INDENTation for 4 spaces */
    private final static String INDENT = "  ";
    
    /** The scanner to read user input */
    private Scanner scanner;

    /**
     * A public constructor to initialize the scanner.
     */
    public Ui() {

        scanner = new Scanner(System.in);
    }

    public String getIndent() {
        return INDENT;
    }
    
    /**
     * Prints the greeting message.
     */
    public static String greetUser() {
        return "Hello! I'm Duke :)\n" 
                + "What can I do for you?";
    }

    /**
     * Prints the goodbye message.
     */
    public String sayBye() {
        scanner.close();
        return  "Bye! Hope to see you again soon :)";
    }

    /**
     * Prints the task with numbering.
     *
     * @param num The index of the task.
     * @param temp The task to be printed.
     */
    public String listNumber(int num, Task temp) {

        return INDENT + (num + 1) + ". " + temp.toString() + System.lineSeparator();
    }

    /**
     * Prints the message for listing tasks.
     */
    public String showList() {
        return "Here are the tasks in your list:";
    }

    /**
     * Prints the message for completing of a task.
     *
     * @param temp The task which is done.
     */
    public String doneTask(Task temp) {
        return  "YAY good job for completing the task :)\n"
                + "I've marked it as done:\n" + INDENT
                        + temp.toString();
    }

    /**
     * Prints the message for removal of a task.
     *
     * @param task The task to be removed.
     */
    public String removeTask(Task task) {
        return "Sure! I've removed this task:\n"
                + INDENT + task.toString();
    }

    /**
     * Prints the message for finding matching tasks.
     */
    public String findTask() {

        return "I have found these matching tasks!!";
    }

    /**
     * Prints message for failure of finding any matching tasks.
     */
    public String noSuchTask() {
        return "I cannot find any matching tasks :(";
    }

    /**
     * Prints the message for adding a task.
     *
     * @param task The task to be added.
     */
    public String addTask(Task task) {
        return  "Sure! I've added this task:\n"
                + INDENT + task.toString();
    }

    /**
     * Prints the number of tasks in the TaskList.
     *
     * @param taskList The list of tasks.
     */
    public String numberOfTasks(TaskList taskList) {
        String numberOfTasks =  "Now you have " + taskList.size()
                + (taskList.size() == 1 ? " task" : " tasks")
                        + " in the list.";
        return numberOfTasks;
    }

    /**
     * Reads the user input and returns it as a string.
     *
     * @return The user command.
     */
    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            return null;
        }
    }

    /**
     * Returns the error message for unrecognised command.
     *
     * @return The string containing error message.
     */
    public String commandError() {
        return "OH NO I'm sorry, but I don't "
                + "know what that means :-(";
    }

    /**
     * Returns the error message for incorrect date format.
     *
     * @return The string containing the error message.
     */
    public String dateError() {
        return  "OH NO :( I can't seem to understand "
                + "the date you have entered.\n"
                        + "I can only understand if it "
                                + "is in  the yyyy-mm-dd format..";
    }

    /**
     * Returns the error message for incorrect date and time format.
     *
     * @return The string containing the error message.
     */
    public String dateTimeError() {
        return  "OH NO :( I can't seem "
                +"to understand the date and time you have entered.\n"
                        + "I can only understand if it is in "
                                + "yyyy-MM-dd HH:mm format..";
    }

    /**
     * Returns the error message for incorrect command for
     * deadline and event tasks, where the /at or /by is
     * not used.
     *
     * @param taskType The type of task.
     * @return The string containing the error message.
     */
    public String incorrectAtOrBy(String taskType) {
        return "OOPS!!! The "
                + (taskType.equals("event")
                        ? "period which the event occurs"
                        : "deadline") + " is not inputted correctly. Use "
                                + (taskType.equals("event") ? "/at" : "/by")
                                        + " to indicate ;)";
    }

    /**
     * Returns the error message for having no description of the
     * task.
     * @param taskType The type of the task.
     * @return The string containing the error message.
     */
    public String noDescription(String taskType) {
        return "OOPS!!! The description of "
                + (taskType.equals("event") ? "an " : "a ")
                        + taskType + " cannot be empty.";
    }

    /**
     * Returns the error message for inputting an invalid
     * index for the task to be deleted.
     *
     * @return The string containing the error message.
     */
    public String deleteInvalidError() {
        return "OOPS!!! There is no "
                + "corresponding task to be deleted.";
    }

    /**
     * Returns the error message for not indicating which
     * task to delete.
     *
     * @return The string containing the error message.
     */
    public String deleteNoNumError() {
        return "OOPS!!! The task to be deleted"
                + "is not indicated!!";
    }

    /**
     * Prints the given string.
     *
     * @param message The message to be printed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Returns the error message for inputting an
     * invalid index for the task to be deleted.
     *
     * @return The string containing the error message.
     */
    public String doneError() {
        return "OOPS!!! There is no corresponding task to be "
                + "marked done.";
    }

    /**
     * Returns the error message for not indicating
     * which task to delete.
     *
     * @return The string containing the error message.
     */
    public String doneNoNumError() {

        return "OOPS!!! The task to be marked done is not indicated!!";
    }

    /**
     * Returns the error message for not being able to
     * find the file.
     *
     * @return The string containing the error message.
     */
    public String loadingError() {

        return "OH NO :( The file cannot be found...";
    }





}
