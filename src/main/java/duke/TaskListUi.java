package duke;

import java.util.ArrayList;

/**
 * The ui for TaskList that is in charge of displaying relevant messages to the user.
 */
public class TaskListUi extends Ui {

    /**
     * Warn the user that the ID for deleting a task/marking a task as done is absent.
     */
    public void absentIdMessage() {
        System.out.println(formatMessage("Oops, the ID of the task does not exist!\n"));
    }

    /**
     * Remind the user that a task has been successfully added.
     *
     * @param userInputRecords the saved record.
     * @param task             the added task.
     */
    public void addMessage(ArrayList<Task> userInputRecords, Task task) {
        System.out.println(formatMessage("Got it. I've added this task:\n" +
                getSubIndentation() + task + "\n" + getIndentation() +
                "Now you have " + userInputRecords.size() + " tasks in the list.\n"));
    }

    /**
     * Remind the user that the command cannot be interpreted.
     */
    public void cannotInterpretMessage() {
        System.out.println(formatMessage("OOPS!!! I'm sorry, but I don't know what that means :-(\n"));
    }

    /**
     * Remind the user that all messages in the record has been deleted.
     */
    public void deleteAllMessage() {
        System.out.println(formatMessage("All records deleted!\n"));
    }

    /**
     * Remind the user that a task has been deleted.
     *
     * @param userInputRecords the saved record.
     * @param itemDeleted      the task deleted, by index.
     */
    public void deleteMessage(ArrayList<Task> userInputRecords, Task itemDeleted) {
        System.out.println(formatMessage("Noted. I've removed this task:\n" +
                getSubIndentation() + itemDeleted + "\n" + getIndentation() +
                "Now you have " + userInputRecords.size() + " tasks in the list.\n"));
    }

    /**
     * Remind the user that a task description is required.
     *
     * @param taskType the type of task specified by the user.
     */
    public void nonEmptyDescriptionMessage(String taskType) {
        if (taskType.startsWith("a") || taskType.startsWith("e") || taskType.startsWith("i") ||
                taskType.startsWith("o") || taskType.startsWith("u")) {
            System.out.println(formatMessage("OOPS!!! The description of an " + taskType + " cannot be empty.\n"));
        } else {
            System.out.println(formatMessage("OOPS!!! The description of a " + taskType + " cannot be empty.\n"));
        }
    }

    /**
     * Warn the user that the date format is invalid (for deadline and event tasks only).
     */
    public void invalidDateFormMessage() {
        System.out.println(formatMessage("Please enter a valid date in the format:/at yyyy-mm-dd!\n"));
    }

    /**
     * Warn the user that the ID for deleting a task/marking a task as done is invalid.
     */
    public void invalidIdMessage() {
        System.out.println(formatMessage("Please enter a valid ID!\n"));
    }

    /**
     * Remind the user that a task has been marked done.
     *
     * @param userInputRecords the saved record.
     * @param itemToComplete   the task marked done, by index.
     */
    public void markAsDoneMessage(ArrayList<Task> userInputRecords, int itemToComplete) {
        System.out.println(formatMessage("Nice! I've marked this task as done:\n" +
                getSubIndentation() + userInputRecords.get(itemToComplete) + "\n"));
    }

    /**
     * Show the search result.
     */
    public void printSearchResult(ArrayList<Task> searchResult, String keyword) {
        if (searchResult.isEmpty()) {
            System.out.println(formatMessage("Oops,there is no record for the keyword " + keyword + "\n"));
        } else {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Here are the matching tasks in your list:");
            for (int i = 0; i < searchResult.size(); i++) {
                System.out.println("     " + (i + 1) + "." + searchResult.get(i));
            }
            System.out.println("    ____________________________________________________________");
        }
    }
}
