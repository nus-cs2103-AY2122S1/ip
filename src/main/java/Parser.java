public class Parser {
    public static Command parse(String userInput) throws UnsupportedCommandException, MalformedCommandException {
        String[] userInputSplit = userInput.split(" ", 2);
        String userCommand = userInputSplit[0];

        switch(userCommand){
        case ListCommand.COMMAND_IDENTIFIER:
            return ListCommand.create();
        case DoneCommand.COMMAND_IDENTIFIER:
            return DoneCommand.create(userInput);
        case DeleteCommand.COMMAND_IDENTIFIER:
            return DeleteCommand.create(userInput);
        case AddTodoCommand.COMMAND_IDENTIFIER:
            return AddTodoCommand.create(userInput);
        case AddDeadlineCommand.COMMAND_IDENTIFIER:
            return AddDeadlineCommand.create(userInput);
        case AddEventCommand.COMMAND_IDENTIFIER:
            return AddEventCommand.create(userInput);
        case ExitCommand.COMMAND_IDENTIFIER:
            return ExitCommand.create();
        default:
            throw new UnsupportedCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
