public class Parser {
    public Command getCommand(String userInput) throws DukeException {
        String[] words = userInput.split("\\s+");
        String commandKeyword = words[0].toLowerCase();

        switch (commandKeyword) {
            case DeleteCommand.KEYWORD:
                return new DeleteCommand(userInput);
            case AddEventCommand.KEYWORD:
                return new AddEventCommand(userInput);
            case AddDeadlineCommand.KEYWORD:
                return new AddDeadlineCommand(userInput);
            case AddToDoCommand.KEYWORD:
                return new AddToDoCommand(userInput);
            case MarkDoneCommand.KEYWORD:
                return new MarkDoneCommand(userInput);
            case ListCommand.KEYWORD:
                return new ListCommand();
            default:
                return new InvalidCommand();
        }
    }
}
