package duke;
import duke.exceptions.NoSuchCommandException;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ByeCommand;
import duke.commands.TaskCommand;
import duke.commands.ListCommand;
import duke.commands.DoneCommand;

public class Parser {
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";

    public Parser() {}

    Command parse(String input) throws NoSuchCommandException {
        String formatted_input = input.trim();
        String[] commandItems = formatted_input.split(" ");
        String commandInput = commandItems[0];
        if (this.isNormalCommandType(commandInput)) {
            return this.extractNormalCommand(commandInput, formatted_input);
        }
        return this.extractSpecialCommand(commandInput, formatted_input, commandItems);

    }

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


    private boolean isNormalCommandType(String commandName) {
        return (!commandName.equals(TODO_COMMAND)) && 
        (!commandName.equals(DEADLINE_COMMAND)) && 
        (!commandName.equals(EVENT_COMMAND));
    }
}
