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
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message listTask(String[] taskArray) {
        if (taskArray.length == 0) {
            return new Message("You have no task.");
        } else {
            return new Message(taskArray);
        }
    }

    /**
     * Prints out a message when task is added.
     *
     * @param task duke.task.Task that is added.
     * @param totalTask Total number of task stored.
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatAddMessage(Task task, int totalTask) {
        return new Message("Got it. I've added this task:",
                task.toString(),
                String.format("Now you have %o task(s).", totalTask));
    }

    /**
     * Prints out a message when the task is marked as done.
     *
     * @param task duke.task.Task that is marked as done.
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatDoneSuccessMessage(Task task) {
        return new Message("Nice! I've did mark this task as done:", task.toString());
    }

    /**
     * Prints out a message when the user tries to mark a task as done when it
     * is already marked as done.
     *
     * @param task duke.task.Task to be marked as done.
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatDoneFailedMessage(Task task) {
        return new Message("Ugh! This task was already done:", task.toString());
    }

    /**
     * Prints out a message when the user deletes the task.
     *
     * @param task duke.task.Task to be deleted.
     * @param totalTask Total number of task stored.
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatDeleteMessage(Task task, int totalTask) {
        return new Message("Noted. I've removed this task:",
                task.toString(),
                String.format("Now you have %o task(s).", totalTask));
    }

    /**
     * Prints out all the task that is found.
     *
     * @param tasks Tasks to be printed.
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatSearchOutputMessage(List<String> tasks) {
        if (tasks.size() == 0) {
            tasks.add(0, "There are no matching tasks found.");
        } else {
            tasks.add(0, "Here are the matching task(s) in your list:");
        }
        return new Message(tasks.toArray(new String[0]));
    }

    /**
     * Prints out a message when a task is unable to be exported and saved.
     *
     * @param task duke.task.Task to be exported and saved.
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatExportTaskErrorMessage(Task task) {
        return new Message(String.format("Unable to save %s", task.toString()));
    }

    /**
     * Prints out a message when all the task is unable to be exported and saved.
     *
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatExportTaskErrorMessage() {
        return new Message("Unable to save task.");
    }

    /**
     * Prints out a message if task from previous interactions is loaded.
     *
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatLoadTaskSuccessMessage() {
        return new Message("I have loaded in task(s) saved from our previous interactions.");
    }

    /**
     * Prints out a message when the task cannot be imported.
     *
     * @param description Description of task to be imported.
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatImportTaskErrorMessage(String description) {
        return new Message(String.format("Cant import %s", description));
    }


    /**
     * Prints out a message if this is the first time the user is using duke.Duke.
     *
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatHelpMessage() {
        return new Message("This are the commands that I recognised",
                "bye - Ends the chat session.",
                "todo <description> - Adds a new todo to the task list.",
                "deadline <description> /by <date/time> - Adds a new deadline to the task list",
                "event <description> /at <date/time> - Adds a new event to the task list",
                "list - return a list of all the task",
                "done <number> - Sets the task to be done",
                "find <search field> - Find task containing search field",
                "delete <number> - Delete the task");
    }

    /**
     * Prints out the greeting message used when the chat started.
     *
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatGreetMessage() {
        return new Message("Good day there! I'm DUKE\n", "What can I do for you?");
    }

    /**
     * Echos the message the user sends for level-1.
     *
     * @param s Message user sent.
     * @return message to be used by either the graphic UI or command line UI.
     * @Deprecated Level-2
     */
    public Message formatEchoMessage(String s) {
        return new Message(s);
    }

    /**
     * Prints out the exit message when chat ends.
     *
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatExitMessage() {
        return new Message("Farewell! Hope to see you again.");
    }

    /**
     * Prints out chat error message when command is not recognized.
     *
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatChatErrorMessage() {
        return new Message("Ugh! I don't recognize your message. Enter 'help' to "
                + "see a list of commands that you can use.");
    }

    /**
     * Prints out error message if done message does not contains number.
     *
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatDoneErrorMessage() {
        return new Message("Ugh! The command should be in this format:",
                "done <number>");
    }

    /**
     * Prints out error message if done message is out of range.
     *
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatDoneIndexErrorMessage() {
        return new Message("Ugh! The command should be in this format:",
                "done <number>",
                "Note: number is based on the number from command 'list'");
    }

    /**
     * Prints out error message if todo message does not contains description.
     *
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatTodoErrorMessage() {
        return new Message("Ugh! The command should be in this format:",
                "todo <description>");
    }

    /**
     * Prints out error message if find message does not contains words.
     *
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatFindIndexErrorMessage() {
        return new Message("Ugh! The command should be in this format:",
                "find <description>");
    }


    /**
     * Prints out error message if deadline message does not contains /by.
     *
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatDeadlineErrorMessage() {
        return new Message("Ugh! The command should be in this format:",
                "deadline <description> /by <date/time>");
    }

    /**
     * Prints out error message if delete message does not contains number.
     *
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatDeleteErrorMessage() {
        return new Message("Ugh! The command should be in this format:",
                "delete <number>");
    }

    /**
     * Prints out error message if delete message is out of range.
     *
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatDeleteIndexErrorMessage() {
        return new Message("Ugh! The command should be in this format:",
                "delete <number>",
                "Note: number is based on the number from command 'list'");
    }

    /**
     * Prints out error message if event message does not contains /at.
     *
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatEventErrorMessage() {
        return new Message("Ugh! The command should be in this format:",
                "event <description> /at <date/time>");
    }

    /**
     * Prints out error message if dateTime format is invalid.
     *
     * @return message to be used by either the graphic UI or command line UI.
     */
    public Message formatDateTimeErrorMessage() {
        return new Message("Date/Time format is wrong. Ensure that it is in the this format:",
                "dd/mm/yy hhmm (24hrs format)");
    }

    /**
     * Formats the sentences that will be printed out by the chatbot.
     *
     * @param strings Arbitrary number of strings to be printed out
     */
    protected static void printMessage(String... strings) {
        System.out.println("\t____________________________________________________________");
        for (String str: strings) {
            System.out.println("\t" + str);
        }
        System.out.println("\t____________________________________________________________");
    }

}
