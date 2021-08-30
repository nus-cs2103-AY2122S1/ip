package duke;

import duke.exceptions.NoSuchCommandException;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ByeCommand;
import duke.commands.TaskCommand;
import duke.commands.ListCommand;
import duke.commands.DoneCommand;

/**
 * Class that converts inputs by user to Command Object to be used.
 */
public class Parser {
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";

    /**
     * Instantiates a new Parser class
     */
    public Parser() {}


    /**
     * Reads the given string by the user and checks what type of command is it.
     * Returns the matching Command Object.
     * 
     * @param input String instructions given by the user 
     * @return Command object of the relating to the input given by user
     * @throws NoSuchCommandException If command do not fulfill the requirements of the bot
     * and thus not recognized
     */
    Command parse(String input) throws NoSuchCommandException {
        String formatted_input = input.trim();
        String[] commandItems = formatted_input.split(" ");
        String commandInput = commandItems[0];
        if (this.isNormalCommandType(commandInput)) {
            return this.extractNormalCommand(commandInput, formatted_input);
        }
        return this.extractSpecialCommand(commandInput, formatted_input, commandItems);
    }


    /**
     * returns TaskCommand that will contain ToDo, Event or Deadline inputs from user
     * 
     * @param commandName String of the name of the type of the command given
     * @param fullCommandInput String of the full instructions given by user
     * @param commandList array of the full command given by user
     * @return Command of the correct type
     * @throws NoSuchCommandException if the command given by user is not something that this bot is supposed to handle
     */

    private Command extractSpecialCommand(String commandName,
                                          String fullCommandInput,
                                          String[] commandList) throws NoSuchCommandException {
        if (commandList.length == 1) {
            String errorMessage = "☹ OOPS!!! The description of a " + commandName + " cannot be empty.";
            throw new NoSuchCommandException(errorMessage);
        }
        String actualInputs = String.join(" ", commandList);
        return new TaskCommand(actualInputs);
    }

    /**
     * returns the respective command of the user as a Command object 
     * 
     * @param commandName String of the name of the type of the command given
     * @param fullCommandInput String of the full instructions given by user
     * @return Command of the correct type
     * @throws NoSuchCommandException if the command given by user is not something that this bot is supposed to handle
     */
    private Command extractNormalCommand(String commandName, String fullCommandInput) throws NoSuchCommandException {
        if (commandName.equals(DELETE_COMMAND)) {
            return new DeleteCommand(fullCommandInput);
        } else if (commandName.equals(LIST_COMMAND)) {
            return new ListCommand(fullCommandInput);
        } else if (commandName.equals(BYE_COMMAND)) {
            return new ByeCommand(fullCommandInput);
        } else if (commandName.equals(DONE_COMMAND)) {
            return new DoneCommand(fullCommandInput);
        }
        String errorMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        throw new NoSuchCommandException(errorMessage);
    }

    /**
     * Checks if the respective command is of todo, deadline or event 
     * to distinguish how the command Object is to be initialized
     * 
     * @param commandName String of the command
     * @return boolean to indicate if the command given is a todo, event, deadline or otherwise
     */
    private boolean isNormalCommandType(String commandName) {
        return (!commandName.equals(TODO_COMMAND)) && 
        (!commandName.equals(DEADLINE_COMMAND)) && 
        (!commandName.equals(EVENT_COMMAND));
    }
}
