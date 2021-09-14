package duke;

import duke.tasks.Task;

/**
 * This class encapsulates the responses that will be displayed to the user
 * when running the application. This class is not to be confused with the Ui class.
 * Ui class is the responses shown in the terminal and is also used in debugging.
 */
public class Response {
    /**
     * Method to show message to warn user that task number is missing.
     */
    public static String showWarningMissingNumber() {
        return "OOPS!!! To delete a task, the task number must be stated.";
    }

    /**
     * Method to show message to warn user that keyword is missing.
     */
    public static String showWarningMissingKeyword() {
        return "OOPS!!! To find a task, a keyword must be stated.";
    }

    /**
     * Method that warns user that there is a missing description for the task.
     *
     * @param command The command keyword in the user input.
     * @return The warning when there is a missing description.
     */
    public static String showWarningMissingDescription(String command) {
        switch (command) {
        case "todo":
            return "OOPS!!! The description of a todo cannot be empty.";
        case "deadline":
            return "OOPS!!! The description of a deadline cannot be empty.";
        case "event":
            return "OOPS!!! The description of an event cannot be empty.";
        default:
            return "";
        }
    }

    /**
     * Method to show goodbye message when user exits programme with command bye.
     *
     * @return The goodbye message.
     */
    public static String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Method to show message prompting user that command is invalid or wrong.
     *
     * @return The message when a user provides an invalid input.
     */
    public static String showInvalidInputMessage() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Method to show message after successfully marking task as complete.
     *
     * @return The message when task has been completed.
     */
    public static String showCompletedMessage(Task task) {
        String message = "Nice! I've marked this task as done:\n";
        return message + task.toString();
    }

    /**
     * Method to show added task, along with updated number of tasks in list.
     * @param task The task to be added.
     * @param numTasks Number of tasks in the arraylist.
     * @return Message after successfully adding task.
     */
    public static String showAddedTask(Task task, int numTasks) {
        String successfulMessage = "Got it. I've added this task:\n";
        String updatedListNumber = "Now you have " + numTasks + " task(s) in the list.";
        return successfulMessage + task.toString() + "\n" + updatedListNumber;
    }

    /**
     * Shows list of all the tasks that have not been deleted.
     *
     * @param tasklist The arraylist of tasks.
     * @return Message listing all tasks in the list.
     */
    public static String showList(TaskList tasklist) {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasklist.getLength(); i++) {
            int j = i + 1;
            output += j +". " + tasklist.getIndividualTask(i).toString() + "\n";
        }
        return output;
    }

    /**
     * Shows message if number does not correspond to any task.
     *
     * @return Message when there are no such tasks.
     */
    public static String showNoTask() {
        return "No such task exists.";
    }

    /**
     * Show message upon successfully deleting a task.
     *
     * @return Message when task has been removed.
     */
    public static String showSuccessfulDelete(Task task, int num) {
        return "Noted I've removed this task:\n"
                + task.toString() + "\n"
                + "Now you have " +  num + " task(s) in the list.";
    }

    /**
     * Shows message that warns user that due date of a deadline cannot be empty.
     *
     * @return Message when due date of deadline is missing.
     */
    public static String showMissingDeadline() {
        return "OOPS!!! The due date of a deadline cannot be empty.\n" +
                "Use format \"/by yyyy-mm-dd {time}\" when providing due date.";
    }

    /**
     * Shows message that warns user that details of an event cannot be empty.
     *
     * @return Message when event details are missing.
     */
    public static String showMissingEventDetails() {
        return "OOPS!!! The details of an event cannot be empty.\n " +
                "Please input time, day, or - if details unknown.";
    }

    /**
     * Shows message upon successful search of keyword.
     *
     * @return Message header for finding tasks.
     */
    public static String showSuccessfulFind() {
        return "Here are the matching tasks in your list:";
    }

    /**
     * Shows message upon unsuccessful search of keyword.
     *
     * @return Message when no tasks can be found with the keyword.
     */
    public static String showUnsuccessfulFind() {
        return "There are no tasks with this keyword.";
    }

    /**
     * Shows all tasks with keyword.
     *
     * @return List of tasks with matching keyword.
     */
    public static String showResults(String output, int count, String description) {
        return output + count + ". " + description + "\n";
    }

}
