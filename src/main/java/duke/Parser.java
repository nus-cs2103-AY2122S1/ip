package duke;

import duke.command.*;

public class Parser {

    public static Command parse(String command) {
        String[] inputValues = command.split(" ");
        if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (inputValues[0].equals("done") && inputValues.length == 2) {
            //treat as unknown command if there is more than 1 number after "done".
            return new DoneCommand();
        } else if (inputValues[0].equals("delete") && inputValues.length == 2) {
            //treat as unknown command if there is more than 1 number after "delete".
            return new DeleteCommand();
        } else if (inputValues[0].equals("deadline")) {
            return new DeadlineCommand();
        } else if (inputValues[0].equals("event")) {
            return new EventCommand();
        } else if (inputValues[0].equals("todo")) {
            return new TodoCommand();
        } else if (inputValues[0].equals("find")) {
            return new FindCommand();
        } else {
            return new InvalidCommand();
        }
    }
}
