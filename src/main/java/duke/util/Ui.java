package duke.util;

import java.util.List;

import duke.task.Task;


/**
 * Deals with the interactions with the user. This class only
 * prints out messages to the user.
 *
 * @author marcuspeh
 * @version Level-9
 * @since 21 Aug 2021
 */
public class Ui {
    /**
     * Lists out all the task stored by the user.
     *
     * @param taskArray To list out all the task.
     */
    public void listTask(String[] taskArray) {
        if (taskArray.length == 0) {
            printMessage("You have no task.");
        } else {
            printMessage(taskArray);
        }
    }

    /**
     * Prints out a message when task is added.
     *
     * @param task duke.task.Task that is added.
     * @param totalTask Total number of task stored.
     */
    public void addMessage(Task task, int totalTask) {
        printMessage("Got it. I've added this task:",
                task.toString(),
                String.format("Now you have %o task(s).", totalTask));
    }

    /**
     * Prints out a message when the task is marked as done.
     *
     * @param task duke.task.Task that is marked as done.
     */
    public void doneSuccessMessage(Task task) {
        printMessage("Nice! I've did mark this task as done:", task.toString());
    }

    /**
     * Prints out a message when the user tries to mark a task as done when it
     * is already marked as done.
     *
     * @param task duke.task.Task to be marked as done.
     */
    public void doneFailedMessage(Task task) {
        printMessage("Ugh! This task was already done:", task.toString());
    }

    /**
     * Prints out a message when the user deletes the task.
     *
     * @param task duke.task.Task to be deleted.
     * @param totalTask Total number of task stored.
     */
    public void deleteMessage(Task task, int totalTask) {
        printMessage("Noted. I've removed this task:",
                task.toString(),
                String.format("Now you have %o task(s).", totalTask));
    }

    /**
     * Prints out all the task that is found.
     *
     * @param tasks Tasks to be printed.
     */
    public void searchOutputMessage(List<String> tasks) {
        if (tasks.size() == 0) {
            tasks.add(0, "There are no matching tasks found.");
            return;
        }
        tasks.add(0, "Here are the matching task(s) in your list:");
        printMessage(tasks.toArray(new String[0]));
    }

    /**
     * Prints out a message when a task is unable to be exported and saved.
     *
     * @param task duke.task.Task to be exported and saved.
     */
    public void exportTaskErrorMessage(Task task) {
        printMessage(String.format("Unable to save %s", task.toString()));
    }

    /**
     * Prints out a message when all the task is unable to be exported and saved.
     */
    public void exportTaskErrorMessage() {
        printMessage("Unable to save task.");
    }

    /**
     * Prints out a message when the task cannot be imported.
     *
     * @param description Description of task to be imported.
     */
    public void importTaskErrorMessage(String description) {
        printMessage(String.format("Cant import %s", description));
    }

    /**
     * Prints out a message if this is the first time the user is using duke.Duke.
     */
    public void importTaskErrorMessage() {
        printMessage("This are the commands that I recognised:",
                "bye - Ends the chat session.",
                "todo <description> - Adds a new todo to the task list.",
                "deadline <description> /by <date/time> - Adds a new deadline to the task list",
                "event <description> /at <date/time> - Adds a new event to the task list",
                "list - return a list of all the task",
                "done <number> - Sets the task to be done",
                "delete <number> - Delete the task");
    }

    /**
     * Prints out the greeting message used when the chat started.
     */
    public void greetMessage() {
        printMessage("Good day there! I'm DUKE\n", "What can I do for you?");
    }

    /**
     * Echos the message the user sends for level-1.
     *
     * @param s Message user sent.
     * @Deprecated Level-2
     */
    public void echoMessage(String s) {
        printMessage(s);
    }

    /**
     * Prints out the exit message when chat ends.
     */
    public void exitMessage() {
        printMessage("Farewell! Hope to see you again.");
    }

    /**
     * Prints out chat error message when command is not recognized.
     */
    public void chatErrorMessage() {
        printMessage("Ugh! Only the following commands are recognised.",
                "bye - Ends the chat session.",
                "todo <description> - Adds a new todo to the task list.",
                "deadline <description> /by <date/time> - Adds a new deadline to the task list",
                "event <description> /at <date/time> - Adds a new event to the task list",
                "list - return a list of all the task",
                "done <number> - Sets the task to be done",
                "delete <number> - Delete the task");
    }

    /**
     * Prints out error message if done message does not contains number.
     */
    public void doneErrorMessage() {
        printMessage("Ugh! The command should be in this format:",
                "done <number>");
    }

    /**
     * Prints out error message if done message is out of range.
     */
    public void doneIndexErrorMessage() {
        printMessage("Ugh! The command should be in this format:",
                "done <number>",
                "Note: number is based on the number from command 'list'");
    }

    /**
     * Prints out error message if todo message does not contains description.
     */
    public void todoErrorMessage() {
        printMessage("Ugh! The command should be in this format:",
                "todo <description>");
    }

    /**
     * Prints out error message if deadline message does not contains /by.
     */
    public void deadlineErrorMessage() {
        printMessage("Ugh! The command should be in this format:",
                "deadline <description> /by <date/time>");
    }

    /**
     * Prints out error message if delete message does not contains number.
     */
    public void deleteErrorMessage() {
        printMessage("Ugh! The command should be in this format:",
                "delete <number>");
    }

    /**
     * Prints out error message if delete message is out of range.
     */
    public void deleteIndexErrorMessage() {
        printMessage("Ugh! The command should be in this format:",
                "delete <number>",
                "Note: number is based on the number from command 'list'");
    }

    /**
     * Prints out error message if event message does not contains /at.
     */
    public void eventErrorMessage() {
        printMessage("Ugh! The command should be in this format:",
                "event <description> /at <date/time>");
    }

    /**
     * Prints out error message if dateTime format is invalid.
     */
    public void dateTimeErrorMessage() {
        printMessage("Date/Time format is wrong. Ensure that it is in the this format:",
                "dd/mm/yy hhmm (24hrs format)");
    }

    /**
     * Formats the sentences that will be printed out by the chatbot.
     *
     * @param strings Arbitrary number of strings to be printed out
     */
    private void printMessage(String... strings) {
        System.out.println("\t____________________________________________________________");
        for (String str: strings) {
            System.out.println("\t" + str);
        }
        System.out.println("\t____________________________________________________________");
    }

}
