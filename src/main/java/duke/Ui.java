package duke;

import java.io.File;

import commands.*;
import tasks.*;

/**
 * This class is responsible for the printing to console
 * and accepting responses from the user.
 *
 * Responses are passed on to a parser which will parse the user input.
 */
public class Ui {

    private static final String SEP_LINE = "_________________________________________________";

    private DukeParser parser;

    Ui() {
        this.parser = new DukeParser();
    }

    /**
     * Prints the initial greeting upon starting an instance of Duke.
     *
     */
    public String greet() {
        return "Hello! I'm Duke. \n"
                + "What can I do for you? \n"
                + SEP_LINE;
    }

    /**
     * Prints the help guide when user types the command 'help'
     */
    public String getHelpMessage() {
        String helpMessage = "HELP \n" + SEP_LINE + "\n"
                + "Available commands: \n"
                + "'help' - Opens this dialog. \n"
                + "'list' - Opens your list of tasks. \n"
                + "'todo (desc)' - Adds a todo item with the given description. \n"
                + "'deadline (desc) /by (yyyy-mm-dd)' - Adds a deadline item to your task list "
                + "with the given description and due date (in yyyy-mm-dd format). \n"
                + "'event (desc) /at (timing)' - Adds a event item to your task list "
                + "with the given description and timing. \n"
                + "'done (x)' - Marks the task with number x as done "
                + "according to the list given by the command 'list' \n"
                + "'delete (x)' - Deletes the task with number x "
                + "according to the list given by the command 'list' \n"
                + "'bye' - Quits this program. \n"
                + SEP_LINE + "\n"
                + "To use any command, follow the structure as shown, entering your values \n"
                + "in place of anything in brackets. \n";
        return helpMessage;
    }

    public String printSepLine() {
        return SEP_LINE;
    }

    /**
     * Prints out the message when a task has been successfully added to the TaskList in Duke.
     * @param task The task that was added to the TaskList.
     */
    public String getAddTaskResponse(Task task) {
        return "The following task has been added! \n" + task.toString();
    }

    /**
     * Prints out the message when a task has been successfully marked as done.
     * @param task The task that was marked as done.
     * @param index The index of the task t in the TaskList.
     */
    public String getTaskDoneResponse(Task task, int index) {
        return "The task has been marked as done! \n" + (index + 1) + ". " + task.toString();
    }

    /**
     * Prints out the message when a task has been successfully deleted.
     * @param task The task that was deleted.
     * @param index The index of the task t in the TaskList.
     */
    public String getDeleteTaskResponse(Task task, int index) {
        return "The task has been removed! \n" + (index + 1) + ". " + task.toString();
    }

    public String getUnknownCommandResponse() {
       return "I did not understand that command. Type 'help' for more info.";
    }

    public String getFileWriteFailResponse(File file) {
        return "Failed to write to file at " + file.toString();
    }

    public String getSuccessfulTagResponse(Task task, int index, String[] tags) {
        return "The task has been tagged! \n" + (index + 1) + ". " + task.toString() + "\n";
    }

    /**
     * Passes the input to the parser and returns the corresponding Command returned by it.
     * @param s The user input string.
     * @return The Command corresponding to the user's input.
     */
    public Command parseInput(String s) {
        return this.parser.parse(s);
    }
}
