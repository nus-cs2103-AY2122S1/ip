import exceptions.NoSuchCommandException;
import commands.*;

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

        if (commandInput.equals(READ_COMMAND)) {
            return new ReadCommand(formatted_input);
        } else if (commandInput.equals(RETURN_COMMAND)) {
            return new ReturnCommand(formatted_input);
        } else if (commandInput.equals(LIST_COMMAND)) {
            return new ListCommand(formatted_input);
        } else if (commandInput.equals(BYE_COMMAND)) {
            return new ByeCommand(formatted_input);
        } else if (commandInput.equals(DONE_COMMAND)) {
            return new DoneCommand(formatted_input);
        } else if (commandInput.equals(TODO_COMMAND)) {
            return new ToDoCommand(formatted_input);
        } else if (commandInput.equals(DEADLINE_COMMAND)) {
            return new DeadlineCommand(formatted_input);
        } else if (commandInput.equals(EVENT_COMMAND)) {
            return new EventCommand(formatted_input);
        } else {
            String errorMessage = "Command Not Found";
            throw new NoSuchCommandException(errorMessage);
        }

        /*
        if (this.isNormalCommandType(commandInput)) {
            return this.extractNormalCommand(commandInput, formatted_input);
        } else {
            return this.extractSpecialCommand(commandInput)
        }       
        */ 
    }

    /*
    private Command extractNormalCommand(String commandName, String fullCommandInput) {
        if (commandName.equals(READ_COMMAND)) {
            return new ReadCommand(fullCommandInput);
        } else if (commandName.equals(RETURN_COMMAND)) {
            return new ReturnCommand(fullCommandInput);
        } else if (commandName.equals(LIST_COMMAND)) {
            return new ListCommand(fullCommandInput);
        } else if (commandName.equals(BYE_COMMAND)) {
            return new ByeCommand(fullCommandInput);
        } else {
            return new DoneCommand(fullCommandInput);
        }
        (commandName.equals(DONE_COMMAND))
        else {
            String errorMessage = "Command Not Found";
            throw new NoSuchCommandException(errorMessage);
        }
    }
    

    private boolean isNormalCommandType(String commandName) {
        return (!commandName.equals(TODO_COMMAND)) && (!commandName.equals(DEADLINE_COMMAND)) && (!commandName.equals(EVENT_COMMAND));
    }

    */
}
