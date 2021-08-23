package duke;

import duke.command.*;

public class Parser {
    public static Command parse(String inputText) throws DukeException {
        if (inputText.equals("bye")) {
            return new EndCommand();
        } else {
            String[] inputArr = inputText.split(" ", 2);
            if (inputArr.length == 1) {
                switch (inputArr[0]) {
                case "list":
                    return new ListCommand();
                case "delete":
                    throw new DukeException("empty delete");
                case "todo":
                    throw new DukeException("empty todo");
                case "deadline":
                    throw new DukeException("empty deadline");
                case "event":
                    throw new DukeException("empty event");
                default:
                    throw new DukeException("invalid input");
                }
            } else {
                switch (inputArr[0]) {
                case "todo" :
                case "deadline":
                case "event":
                    return new AddCommand(inputArr[1], inputArr[0]);
                case "done":
                    return new DoneCommand(inputArr[1]);
                case "delete":
                    return new DeleteCommand(inputArr[1]);
                default:
                    throw new DukeException("invalid input");
                }
            }
        }
    }
}
