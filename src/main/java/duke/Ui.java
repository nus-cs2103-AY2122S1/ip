package duke;

import java.io.File;

import commands.*;
import tasks.*;

/**
 * This class is responsible for the printing to console
 * and accepting responses from the user.
 *
 * Responses are passed on to a parser which will parse the user input
 */
public class Ui {

    private static final String SEP_LINE = "____________________________________________________________";

    private DukeParser parser;

    Ui() {
        this.parser = new DukeParser();
    }

    public void greet() {
        System.out.println("Hello! I'm Duke. \n"
                + "What can I do for you? \n"
                + SEP_LINE);
    }

    public void printHelp() {
        String helpMessage = "HELP \n" + SEP_LINE + "\n"
                + "Available commands: \n"
                + "'help' - Opens this dialog. \n"
                + "'list' - Opens your list of tasks. \n"
                + "'todo (desc)' - Adds a todo item with the given description. \n"
                + "'deadline (desc) /by (due date)' - Adds a deadline item to your task list "
                + "with the given description and due date. \n"
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
        System.out.println(helpMessage);
    }

    public void printSepLine() {
        System.out.println(SEP_LINE);
    }

    public void printAddTask(Task t) {
        System.out.println("The following task has been added! \n" + t);
    }

    public void printTaskDone(Task t, int index) {
        System.out.println("The task has been marked as done! \n" + (index + 1) + ". " + t);
    }

    public void printDeleteTask(Task t, int index) {
        System.out.println("The task has been removed! \n" + (index + 1) + ". " + t);
    }

    public void printUnknownCommand() {
        System.out.println("I did not understand that command. Type 'help' for more info.");
    }

    public void printFileWriteFail(File file) {
        System.out.println("Failed to write to file at " + file);
    }
    public Command parseInput(String s) {
        return this.parser.parse(s);
    }
}
