import exceptions.NoSuchCommandException;
import commands.Command;
import commands.ReadCommand;
import commands.ReturnCommand;
import commands.ToDoCommand;
import commands.EventCommand;
import commands.ByeCommand;
import commands.DeadlineCommand;
import commands.ListCommand;
import commands.DoneCommand;
public class CommandConverter {
    private static final String READ_COMMAND = "read";
    private static final String RETURN_COMMAND = "return";
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";


    
    public CommandConverter() {}

    //throws NoSuchCommandException
    Command convertInputToCommand(String input) throws NoSuchCommandException {
        String formatted_input = input.trim();
        String[] commandItems = formatted_input.split(" ");
        String commandInput = commandItems[0];


        if (this.isNormalCommandType(commandInput)) {
            return this.extractNormalCommand(commandInput, formatted_input);
        } else {
            return this.extractSpecialCommand(commandInput, formatted_input, commandItems);
        }

    }

    private Command extractSpecialCommand(String commandName, String fullCommandInput, String[] commandList) throws NoSuchCommandException {
        if (commandList.length == 1) {
            String errorMessage = "☹ OOPS!!! The description of a " + commandName + " cannot be empty.";
            throw new NoSuchCommandException(errorMessage);
        } else if (commandName.equals(TODO_COMMAND)) {
            return new ToDoCommand(fullCommandInput);
        } else if (commandName.equals(EVENT_COMMAND)) {
            return new EventCommand(fullCommandInput);
        }
        return new DeadlineCommand(fullCommandInput);
    }

    
    private Command extractNormalCommand(String commandName, String fullCommandInput) throws NoSuchCommandException {
        if (commandName.equals(READ_COMMAND)) {
            return new ReadCommand(fullCommandInput);
        } else if (commandName.equals(RETURN_COMMAND)) {
            return new ReturnCommand(fullCommandInput);
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
        return (!commandName.equals(TODO_COMMAND)) && (!commandName.equals(DEADLINE_COMMAND)) && (!commandName.equals(EVENT_COMMAND));
    }
}
