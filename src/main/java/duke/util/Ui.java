package duke.util;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.TodoCommand;
import duke.task.Task;

/**
 * Represents how Duke deals with interactions with the user.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class Ui {
    /**
     * Returns an error message as a string.
     *
     * @param errorMessage The error message to be returned.
     * @return A string representing the error message.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Returns an error message from failed file loading.
     *
     *  @return A string representing the error message.
     */
    public String showLoadingError() {
        return "Something went wrong! Seems like I'm unable to load your file!";
    }

    /**
     * Returns an error message from failed file saving.
     *
     * @return A string representing the error message.
     */
    public String showSavingError() {
        return "Something went wrong! Seems like I'm unable to save to your file!";
    }

    /**
     * Returns an exit message before Duke exits.
     *
     * @return A string representing the exit message.
     */
    public String showExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a message containing the added task and the current task count.
     *
     * @param taskToAdd The task to add.
     * @param taskList The taskList containing all tasks.
     * @return A string representing the added task and the current task count.
     */
    public String showAddTask(Task taskToAdd, TaskList taskList) {
        return "Got it. I have added this task:\n"
                + "  " + taskToAdd.toString()
                + "\n Now you have " + taskList.size()
                + (taskList.size() == 1 ? " task" : " tasks") + " in the list.";
    }

    /**
     * Returns a message containing the deleted task and the current task count.
     *
     * @param taskToDelete The task to delete.
     * @param taskList The taskList containing all tasks.
     * @return A string representing the deleted task and the current task count.
     */
    public String showDelete(Task taskToDelete, TaskList taskList) {
        return "Noted. I've removed this task:\n"
                + "  " + taskToDelete.toString()
                + "\nNow you have " + taskList.size()
                + (taskList.size() == 1 ? " task" : " tasks") + " in the list.";
    }

    /**
     * Returns a message to tell the user that a task is already completed.
     *
     * @return A string representing the message to tell the user that a task is already completed.
     */
    public String showAlreadyMarkedAsDone() {
        return "I have already marked this task as completed!";
    }

    /**
     * Returns a message to tell the user that a task has been marked as done.
     *
     * @param taskToComplete The task to complete.
     * @return A string to tell the user that a task has been marked as done.
     */
    public String showMarkedAsDone(Task taskToComplete) {
        return "Nice! I've marked this task as done:\n  " + taskToComplete.toString();
    }

    /**
     * Returns a message to notify the user of a failed search,
     *
     * @return A string representing the failed search message.
     */
    public String showFailedSearch() {
        return "There are no matching tasks in your list!";
    }

    /**
     * Returns a message to tell the user that the current taskList is empty.
     *
     * @return A string representing an empty taskList message.
     */
    public String showEmptyList() {
        return "There are no tasks in your list currently!";
    }

    /**
     * Returns the header message of the user's taskList.
     *
     * @return A string representing the header message of the user's taskList.
     */
    public String showListHeader() {
        return "Here are the tasks in your list:\n";
    }

    /**
     * Returns the header message of the search results.
     *
     * @return A string representing the header message of the search results.
     */
    public String showSearchHeader() {
        return "Here are the matching tasks in your list:\n";
    }

    /**
     * Returns a message to notify the user of an empty field error.
     *
     * @param c The command where the error occurred.
     * @return A string representing the empty field error.
     */
    public String showEmptyFieldError(Command c) {
        String errorMessage = "";
        if (c instanceof DeadlineCommand || c instanceof EventCommand) {
            errorMessage = "Error! The description and date/time cannot be empty.";
        }
        if (c instanceof FindCommand) {
            errorMessage = "Error! Please search for a keyword.";
        }
        if (c instanceof TodoCommand) {
            errorMessage = "Error! The description cannot be empty";
        }
        return errorMessage;
    }

    /**
     * Returns a message to notify the user of an invalid date time error.
     *
     * @return A string representing the invalid date time error.
     */
    public String showInvalidDateTimeError() {
        return "Error! Ensure your date and time is valid and formatted correctly!\n"
                + "Date: \"YYYY-MM-DD\" format, eg: 2021-08-23\n"
                + "Time: 24Hr format, \"HH:mm\", eg: 18:00";
    }

    /**
     * Returns a message to notify the user of an indexing (item number) error.
     *
     * @param index The wrong item number in the taskList.
     * @return A string representing the invalid index error.
     */
    public String showInvalidIndexError(int index) {
        if (index <= 0) {
            return "Error! Please specify a number greater than 0";
        } else if (index == 1) {
            return "Error! You do not have any tasks in the list";
        } else {
            return "Error! You do not have " + index + " tasks in the list";
        }
    }

    /**
     * Returns a message to notify the user of an invalid format error.
     *
     * @param c The command where the error occurred.
     * @return A string representing the invalid command format error.
     */
    public String showInvalidFormatError(Command c) {
        String errorMessage = "";
        if (c instanceof DeleteCommand) {
            errorMessage = "Invalid! Please ensure a number is entered after delete (eg: delete 2)";
        }
        if (c instanceof DoneCommand) {
            errorMessage = "Invalid! Please ensure a number is entered after done (eg: done 2)";
        }
        if (c instanceof DeadlineCommand) {
            errorMessage = "Invalid deadline format!\n"
                    + "Please ensure you specify your date and time after a \"/by\"\n"
                    + "Eg: deadline Submit Assignment /by 2021-08-29 15:00";
        }
        if (c instanceof EventCommand) {
            errorMessage = "Invalid event format!\n"
                    + "Please ensure you specify your date and time after a \"/at\"\n"
                    + "Eg: event Attend physical lessons /at 2021-08-29 15:00";
        }
        return errorMessage;
    }

    /**
     * Returns a message to notify the user that the input command is invalid.
     *
     * @return A string representing the invalid command message.
     */
    public String showInvalidCommandMessage() {
        return "I'm sorry, but I don't know what that means!";
    }

    /**
     * Returns a message to notify the user of an a duplicated task.
     *
     * @param taskString A string format of the duplicated task.
     * @return A string representing a duplicated task error.
     */
    public String showDuplicateTaskError(String taskString) {
        return "Oh no! There seems to be a duplicate task in your list\n" + taskString;
    }
}

