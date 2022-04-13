package duke;

import duke.exceptions.NoSuchCommandException;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ByeCommand;
import duke.commands.TaskCommand;
import duke.commands.ListCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.ScheduleCommand;

/**
 * Class that converts inputs by user
 * to Command Object to be used.
 */
public class Parser {
    /**
     * Bye command string structure.
     */
    private static final String BYE_COMMAND = "bye";

    /**
     * List command string structure.
     */
    private static final String LIST_COMMAND = "list";

    /**
     * Done command string structure.
     */
    private static final String DONE_COMMAND = "done";

    /**
     * To do command string structure.
     */
    private static final String TODO_COMMAND = "todo";

    /**
     * DeadLine command string structure.
     */
    private static final String DEADLINE_COMMAND = "deadline";

    /**
     * Event command string structure.
     */
    private static final String EVENT_COMMAND = "event";

    /**
     * Delete command string structure.
     */
    private static final String DELETE_COMMAND = "delete";

    /**
     * Find command string structure.
     */
    private static final String FIND_COMMAND = "find";

    /**
     * Schedule command string structure.
     */
    private static final String SCHEDULE_COMMAND = "schedule";

    /**
     * Instantiates a new Parser class.
     */
    public Parser() {
    }

    /**
     * Reads the given string by the user and
     * checks what type of command is it and
     * returns the matching Command Object.
     * @param input String instructions given by the user.
     * @return Command object of the relating to
     * the input given by user.
     * @throws NoSuchCommandException If command do
     * not fulfill the requirements of the bot and thus
     * not recognized.
     */
    Command parse(String input) throws NoSuchCommandException {
        String formattedInput = input.trim();
        String[] commandItems = formattedInput.split(" ");
        String commandInput = commandItems[0];
        if (this.isNormalCommandType(commandInput)) {
            return this.extractNormalCommand(commandInput, formattedInput);
        }
        return this.extractSpecialCommand(
                commandInput,
                formattedInput,
                commandItems);
    }

    private Command extractSpecialCommand(
            String commandName,
            String fullCommandInput,
            String[] commandList)
            throws NoSuchCommandException {
        if (commandList.length == 1) {
            String errorMessage = "☹ OOPS!!! The description of a "
                    + commandName
                    + " cannot be empty.";
            throw new NoSuchCommandException(errorMessage);
        }
        assert (commandList.length > 1) : "Commands should have at least a description!";
        String actualInputs = String.join(" ", commandList);
        return new TaskCommand(actualInputs);
    }


    /**
     * Returns the respective command of
     * the user as a Command object.
     * @param commandName String of the name of
     * the type of the command given.
     * @param fullCommandInput String of the
     * full instructions given by user.
     * @return Command of the correct type.
     * @throws NoSuchCommandException if the command given
     * by user is not something that this bot is supposed to handle.
     */
    private Command extractNormalCommand(
            String commandName,
            String fullCommandInput) throws NoSuchCommandException {
        if (commandName.equals(DELETE_COMMAND)) {
            return new DeleteCommand(fullCommandInput);
        } else if (commandName.equals(LIST_COMMAND)) {
            return new ListCommand(fullCommandInput);
        } else if (commandName.equals(BYE_COMMAND)) {
            return new ByeCommand(fullCommandInput);
        } else if (commandName.equals(DONE_COMMAND)) {
            return new DoneCommand(fullCommandInput);
        } else if (commandName.equals(FIND_COMMAND)) {
            return new FindCommand(fullCommandInput);
        } else if (commandName.equals(SCHEDULE_COMMAND)) {
            return new ScheduleCommand(fullCommandInput);
        }
        String errorMessage = "☹ OOPS!!! I'm sorry, "
                + "but I don't know what that means :-(";
        throw new NoSuchCommandException(errorMessage);
    }

    /**
     * Checks if the respective command is
     * of todo, deadline or event to distinguish
     * how the command Object is to be initialized.
     *
     * @param commandName String of the command
     * @return boolean to indicate if the command given
     * is a todo, event, deadline or otherwise
     */
    private boolean isNormalCommandType(String commandName) {
        boolean isToDo = !commandName.equals(TODO_COMMAND);
        boolean isDeadLine = !commandName.equals(DEADLINE_COMMAND);
        boolean isEvent = !commandName.equals(EVENT_COMMAND);
        return isToDo && isDeadLine && isEvent;
    }
}
