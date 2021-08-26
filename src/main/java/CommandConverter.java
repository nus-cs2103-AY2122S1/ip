import exceptions.NoSuchCommandException;
import commands.Command;
import commands.DeleteCommand;
import commands.ToDoCommand;
import commands.EventCommand;
import commands.ByeCommand;
import commands.DeadlineCommand;
import commands.ListCommand;
import commands.DoneCommand;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class CommandConverter {
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";

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

    HashMap<Command, Boolean> batchConvertStorageCommands(HashMap<String, Boolean> inputMap) throws NoSuchCommandException {
        HashMap<Command, Boolean> outputCommands = new HashMap<>();
        for (Map.Entry<String, Boolean> entry : inputMap.entrySet()) {
            Command command = this.convertInputToCommand(entry.getKey());
            outputCommands.put(command, entry.getValue());
        }
        return outputCommands;
    }

    private Command extractSpecialCommand(String commandName,
                                          String fullCommandInput,
                                          String[] commandList) throws NoSuchCommandException {
        if (commandList.length == 1) {
            String errorMessage = "☹ OOPS!!! The description of a " + commandName + " cannot be empty.";
            throw new NoSuchCommandException(errorMessage);
        }
        String actualInputs = String.join(" ", Arrays.copyOfRange(commandList, 1, commandList.length));
        if (commandName.equals(TODO_COMMAND)) {
            return new ToDoCommand(actualInputs);
        } else if (commandName.equals(EVENT_COMMAND)) {
            return new EventCommand(actualInputs);
        }
        return new DeadlineCommand(actualInputs);
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
