public class Parser {
    
    public static Command parse(String fullCommand) throws DukeException{
        Command c;
        switch (fullCommand.split(" ")[0]) {
        case "bye":
            c = new ExitCommand();
            break;
        case "list":
            c = new ListCommand(fullCommand);
            break;
        case "done":
            c = new DoneCommand(fullCommand);
            break;
        case "delete":
            c = new DeleteCommand(fullCommand);
            break;
        default:
            c = new AddCommand(fullCommand);              
        }
        return c;
    }
}
