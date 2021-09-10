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
import duke.command.UpdateCommand;
import duke.exception.*;
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
        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand(taskList);
        case DoneCommand.COMMAND_WORD:
            return new DoneCommand(taskList, Integer.parseInt(words[1]));
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(taskList, Integer.parseInt(words[1]));
        case TodoCommand.COMMAND_WORD:
        case DeadlineCommand.COMMAND_WORD:
        case EventCommand.COMMAND_WORD:
        case UpdateCommand.COMMAND_WORD:
            rest = combine(words, words.length);
            return convertAddOrUpdateCommandStringToCommand(commandWord, rest, taskList);
        case FindCommand.COMMAND_WORD:
            if (words.length != 2) {
                throw new FindCommandWordSuppliedException();
            }
            return new FindCommand(taskList, words[1]);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand(taskList);
        default:
            throw new IncorrectCommandWordException();
        }
    }

    private String combine(String[] splitList, int end) throws TooLittleParametersSuppliedException {
        StringBuilder result = new StringBuilder();
        if (splitList.length < 2) {
            throw new TooLittleParametersSuppliedException();
        }
        for (int i = 1; i < end; i++) {
            result.append(splitList[i]);
            result.append(" ");
        }
        return result.substring(0, result.length() - 1);
    }

    private Command convertAddOrUpdateCommandStringToCommand(String commandWord, String commandOption,
                                                           TaskList taskList) {
        if (commandWord.equals(TodoCommand.COMMAND_WORD)) {
            return new TodoCommand(taskList, commandOption);
        }
        if (commandWord.equals(DeadlineCommand.COMMAND_WORD)) {
            return new DeadlineCommand(taskList, commandOption);
        }
        if (commandWord.equals(UpdateCommand.COMMAND_WORD)) {
            return new UpdateCommand(taskList, commandOption);
        }
        return new EventCommand(taskList, commandOption);
    }
}
