package duke.parser;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.command.UpdateCommand;
import duke.exception.DukeException;
import duke.exception.FindCommandWordSuppliedException;
import duke.exception.IncorrectCommandWordException;
import duke.exception.TooLittleParametersSuppliedException;
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
        if (checkCommandWordWithCommand(commandWord, ListCommand.COMMAND_WORD)) {
            return new ListCommand(taskList);
        }
        if (checkCommandWordWithCommand(commandWord, DoneCommand.COMMAND_WORD)) {
            return new DoneCommand(taskList, Integer.parseInt(words[1]));
        }
        if (checkCommandWordWithCommand(commandWord, DeleteCommand.COMMAND_WORD)) {
            return new DeleteCommand(taskList, Integer.parseInt(words[1]));
        }
        if (checkIfCommandWordEqualsAddOrUpdateCommand(commandWord)) {
            String rest = combine(words, words.length);
            return convertAddOrUpdateCommandStringToCommand(commandWord, rest, taskList);
        }
        if (checkCommandWordWithCommand(commandWord, FindCommand.COMMAND_WORD)) {
            throwFindExceptionIfLengthDoesNotMatch(words);
            return new FindCommand(taskList, words[1]);
        }
        if (checkCommandWordWithCommand(commandWord, HelpCommand.COMMAND_WORD)) {
            return new HelpCommand(taskList);
        }
        if (checkCommandWordWithCommand(commandWord, ExitCommand.COMMAND_WORD)) {
            return new ExitCommand(taskList);
        }
        throw new IncorrectCommandWordException();
    }

    private boolean checkCommandWordWithCommand(String commandWord, String command) {
        return commandWord.equals(command);
    }

    private void throwFindExceptionIfLengthDoesNotMatch(String[] words) throws FindCommandWordSuppliedException {
        if (words.length != 2) {
            throw new FindCommandWordSuppliedException();
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

    private boolean checkIfCommandWordEqualsAddOrUpdateCommand(String commandWord) {
        return checkCommandWordWithCommand(commandWord, TodoCommand.COMMAND_WORD)
                || checkCommandWordWithCommand(commandWord, DeadlineCommand.COMMAND_WORD)
                || checkCommandWordWithCommand(commandWord, UpdateCommand.COMMAND_WORD)
                || checkCommandWordWithCommand(commandWord, EventCommand.COMMAND_WORD);
    }

    private Command convertAddOrUpdateCommandStringToCommand(String commandWord,
                                                             String commandOption,
                                                             TaskList taskList) {
        if (checkCommandWordWithCommand(commandWord, TodoCommand.COMMAND_WORD)) {
            return new TodoCommand(taskList, commandOption);
        }
        if (checkCommandWordWithCommand(commandWord, DeadlineCommand.COMMAND_WORD)) {
            return new DeadlineCommand(taskList, commandOption);
        }
        if (checkCommandWordWithCommand(commandWord, UpdateCommand.COMMAND_WORD)) {
            return new UpdateCommand(taskList, commandOption);
        }
        return new EventCommand(taskList, commandOption);
    }
}
