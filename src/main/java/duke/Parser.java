package duke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.AddCommand;
import duke.command.AddCommandType;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.TaskList;

/**
 * The type Parser that parses user input and returns the appropriate command.
 */
public class Parser {

    /** User-inputted string */
    private final String userInput;
    /** List of tasks to pass to the commands */
    private final TaskList tasks;

    /**
     * Instantiates a new Parser.
     *
     * @param userInput the user-inputted string.
     * @param tasks     list of tasks to pass to the commands generated.
     */
    public Parser(String userInput, TaskList tasks) {
        this.userInput = userInput;
        this.tasks = tasks;
    }

    /**
     * Checks operation command to be executed.
     *
     * @return the command to be executed.
     * @throws DukeException the duke exception for unrecognised commands.
     */
    public final Command checkOperation() throws DukeException {
        // if it is "bye", we return false to indicate operation stoppage
        if (checkBye()) {
            return new ExitCommand();
        } else if (checkList()) {
            return new ListCommand(this.tasks);
        } else if (checkDone()) {
            return new DoneCommand(userInput, tasks);
        } else if (checkDelete()) {
            return new DeleteCommand(userInput, tasks);
        } else if (checkFind()) {
            return new FindCommand(userInput, tasks);
        } else if (checkTodo()) {
            return new AddCommand(AddCommandType.todo, userInput, tasks);
        } else if (checkEvent()) {
            return new AddCommand(AddCommandType.event, userInput, tasks);
        } else if (checkDeadline()) {
            return new AddCommand(AddCommandType.deadline, userInput, tasks);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    /**
     * Checks if the exit command is to be executed.
     *
     * @return boolean.
     */
    private boolean checkBye() {
        return this.userInput.equals("bye");
    }

    /**
     * Checks if the list command is to be executed.
     *
     * @return boolean.
     */
    private boolean checkList() {
        return this.userInput.equals("list");
    }

    /**
     * Checks if the done command is to be executed.
     *
     * @return boolean.
     */
    private boolean checkDone() {
        Pattern donePattern = Pattern.compile("^done\\h\\d+$");
        Matcher doneMatcher = donePattern.matcher(this.userInput);
        return doneMatcher.find();
    }

    /**
     * Checks if the delete command is to be executed.
     *
     * @return boolean.
     */
    private boolean checkDelete() {
        Pattern deletePattern = Pattern.compile("^delete\\h\\d+$");
        Matcher deleteMatcher = deletePattern.matcher(this.userInput);
        return deleteMatcher.find();
    }

    /**
     * Checks if the add command is to be executed for a Todo task.
     *
     * @return boolean.
     */
    private boolean checkTodo() {
        Pattern todoPattern = Pattern.compile("^todo\\h\\w.*");
        Matcher todoMatcher = todoPattern.matcher(this.userInput);
        return todoMatcher.find();
    }

    /**
     * Checks if the add command is to be executed for an Event task.
     *
     * @return boolean.
     */
    private boolean checkEvent() {
        Pattern eventPattern = Pattern.compile("^event\\h\\w.*/at\\h\\w.*");
        Matcher eventMatcher = eventPattern.matcher(this.userInput);
        return eventMatcher.find();
    }

    /**
     * Checks if the add command is to be executed for a Deadline task.
     *
     * @return boolean.
     */
    private boolean checkDeadline() {
        Pattern deadlinePattern = Pattern.compile("^deadline\\h\\w.*/by\\h\\w.*");
        Matcher deadlineMatcher = deadlinePattern.matcher(this.userInput);
        return deadlineMatcher.find();
    }

    /**
     * Checks if the find command is to be executed for finding tasks with matching substrings.
     *
     * @return boolean.
     */
    private boolean checkFind() {
        Pattern findPattern = Pattern.compile("^find\\h\\w.*");
        Matcher findMatcher = findPattern.matcher(this.userInput);
        return findMatcher.find();
    }

}
