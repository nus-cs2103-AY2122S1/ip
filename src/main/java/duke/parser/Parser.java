package duke.parser;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;
import duke.tasklist.TaskList;

/**
 * A class that parses the user input.
 */
public class Parser {

    private final TaskList taskList;

    /**
     * A constructor method which takes in a taskList
     * @param taskList the list of tasks to be passed on to the various commands.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses the string for command and returns the command.
     *
     * @param input that is keyed in by user.
     * @return A Command that can be executed using execute().
     * @throws DukeException if the command is not recognised.
     */
    public Command parse(String input) throws DukeException {
        String[] words = input.split(" ");
        String commandWord = words[0];
        String rest;
        try {
            switch (commandWord) {
            case ListCommand.COMMAND_WORD:
                return new ListCommand(taskList);
            case DoneCommand.COMMAND_WORD:
                return new DoneCommand(taskList, Integer.parseInt(words[1]));
            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand(taskList, Integer.parseInt(words[1]));
            case TodoCommand.COMMAND_WORD:
                if (words.length == 1) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                return new TodoCommand(taskList, combine(words, words.length));
            case DeadlineCommand.COMMAND_WORD:
                rest = combine(words, words.length);
                return new DeadlineCommand(taskList, rest);
            case EventCommand.COMMAND_WORD:
                rest = combine(words, words.length);
                return new EventCommand(taskList, rest);
            case FindCommand.COMMAND_WORD:
                if (words.length < 2) {
                    throw new DukeException("You need to include a search word.");
                }
                if (words.length > 2) {
                    throw new DukeException("You can only include 1 search word.");
                }
                return new FindCommand(taskList, words[1]);
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand(taskList);
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (java.lang.NumberFormatException e) {
            throw new DukeException("OOPS!!! "
                    + e.getLocalizedMessage()
                    + " was input instead of an integer.");
        }
    }

    private String combine(String[] splitList, int end) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < end; i++) {
            result.append(splitList[i]);
            result.append(" ");
        }
        return result.substring(0, result.length() - 1);
    }

}
