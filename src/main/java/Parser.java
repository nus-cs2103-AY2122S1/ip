/**
 * The Parser class deals with making sense of the user command.
 */
public class Parser {
    public static Command parse(String input) throws AisuException { // if return true, means exit
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ShowListCommand();
        } else if (input.startsWith("done ")) {
            return new MarkDoneCommand(Integer.parseInt(input.substring(5)));
        } else if (input.startsWith("todo ")) {
            return new AddCommand(Tasklist.TaskTypes.T, input.substring(5));
        } else if (input.startsWith("deadline ")) {
            return new AddCommand(Tasklist.TaskTypes.D, input.substring(9));
        } else if (input.startsWith("event ")) {
            return new AddCommand(Tasklist.TaskTypes.E, input.substring(6));
        } else if (input.startsWith("delete ")) {
            return new DeleteCommand(Integer.parseInt(input.substring(7)));
        } else {
            throw new AisuException("That's an invalid task format...");
        }
    }
}
