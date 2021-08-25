package duke;

import duke.command.*;
import task.TaskList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses any command that requires inputs
 */
public class DukeParser {

    final private TaskList taskList;

    /**
     * Patterns for the Parser to look out for in the input
     */
    final private Pattern listPattern = Pattern.compile("list( .+)?", Pattern.CASE_INSENSITIVE);
    final private Pattern donePattern = Pattern.compile("done (\\d+)", Pattern.CASE_INSENSITIVE);
    final private Pattern deletePattern = Pattern.compile("delete (\\d+)", Pattern.CASE_INSENSITIVE);
    final private Pattern todoPattern = Pattern.compile("todo (.+)", Pattern.CASE_INSENSITIVE);
    final private Pattern deadlinePattern = Pattern.compile(
            "deadline (.+) /by (\\d{1,2}/\\d{1,2}/\\d{4}+)( \\d{4}+)?",
            Pattern.CASE_INSENSITIVE);
    final private Pattern eventPattern = Pattern.compile(
            "event (.+) /at (\\d{1,2}/\\d{1,2}/\\d{4}+)( \\d{4}+)?",
            Pattern.CASE_INSENSITIVE);

    /**
     * Constructor; instantiates the task list to be edited
     *
     * @param tasks Task list to edit using this Parser object
     */
    public DukeParser(TaskList tasks) {
        this.taskList = tasks;
    }

    /**
     * Matches input with any regex, and passes it to the correct function
     *
     * @param input String input from the Listener given by the User
     */
    public Command parseInput(String input) {
        if (input.equals("gubbai")) {
            return new CommandExit();
        } else if (input.equals("help")) {
            return new CommandHelp();
        }

        final Matcher checkList = listPattern.matcher(input);
        final Matcher checkDone = donePattern.matcher(input);
        final Matcher checkDelete = deletePattern.matcher(input);
        final Matcher checkTodo = todoPattern.matcher(input);
        final Matcher checkDeadline = deadlinePattern.matcher(input);
        final Matcher checkEvent = eventPattern.matcher(input);

        if (checkList.matches()) {
            return new CommandList(taskList, checkList.group(1));
        } else if (checkDone.matches()) {
            return new CommandDone(taskList, Integer.parseInt(checkDone.group(1)));
        } else if (checkDelete.matches()) {
            return new CommandDelete(taskList, Integer.parseInt(checkDelete.group(1)));
        } else if (checkTodo.matches()) {
            return new CommandAddTodo(taskList, checkTodo.group(1));
        } else if (checkDeadline.matches()) {
            return new CommandAddDeadline(taskList, checkDeadline);
        } else if (checkEvent.matches()) {
            return new CommandAddEvent(taskList, checkEvent);
        }  else {
            return new CommandInvalid(input);
        }
    }


}
