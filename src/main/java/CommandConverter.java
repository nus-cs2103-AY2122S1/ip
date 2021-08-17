import exceptions.NoSuchCommandException;
import commands.Command;
import commands.ReadCommand;
import commands.ListCommand;
import commands.ByeCommand;
import commands.ReturnCommand;

public class CommandConverter {
    private static final String READ_COMMAND = "read";
    private static final String RETURN_COMMAND = "return";
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    
    public CommandConverter() {}

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
        } else {
            String errorMessage = "Command Not Found";
            throw new NoSuchCommandException(errorMessage);
        }
    }
    
    
}
