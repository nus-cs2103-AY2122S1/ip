package duke;

public class Parser {
    public Parser() {

    }

    public static Command parse(String userInput) {
        if (userInput.equals("bye")) { // if user enters "bye"
            return new ExitCommand();
        } else if (userInput.equals("list")) { // if user enters "list"
            return new ListCommand();
        } else if (userInput.startsWith("done")) {
            return new DoneCommand(userInput);
        } else if (userInput.startsWith("delete")) {
            return new DeleteCommand(userInput);
        } else if (userInput.startsWith("find")) {
            return new FindCommand(userInput);
        } else {
            return new AddCommand(userInput);
        }
    }
}
