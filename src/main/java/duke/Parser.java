package duke;

import duke.command.*;

public class Parser {

    public static Command parse(String command) throws DukeException {
        String[] parsedArr = command.split("\\s", 2);

        switch(parsedArr[0]) {
            case "list":
                if (parsedArr.length > 1) {
                    throw new DukeException();
                }
                return new ListCommand();
            case "todo":
                if (parsedArr.length < 2) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                return new AddCommand(parsedArr[0], parsedArr[1]);
            case "deadline":
                if (parsedArr.length < 2) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }

                if (!parsedArr[1].contains("/by")) {
                    throw new DukeException("Please specify a date using the /by keyword.");
                }
                return new AddCommand(parsedArr[0], parsedArr[1]);
            case "event":
                if (parsedArr.length < 2) {
                    throw new DukeException("The description of an event cannot be empty.");
                }

                if (!parsedArr[1].contains("/at")) {
                    throw new DukeException("Please specify a date using the /at keyword.");
                }
                return new AddCommand(parsedArr[0], parsedArr[1]);
            case "delete":
                if (parsedArr.length < 2) {
                    throw new DukeException("Please enter a number after delete!");
                }
                return parseFurther(parsedArr[0], parsedArr[1]);
            case "done":
                if (parsedArr.length < 2) {
                    throw new DukeException("Please enter a number after done!");
                }
                return parseFurther(parsedArr[0], parsedArr[1]);
            case "bye":
                if (parsedArr.length > 1) {
                    throw new DukeException();
                }
                return new ExitCommand();
            default:
                throw new DukeException();
        }
    }

    public static Command parseFurther(String command, String rest) throws DukeException {
        int index;

        try {
            index = Integer.parseInt(rest) - 1;
            if (index < 0) {
                throw new DukeException("This entry does not exist!");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a number after delete!");
        }

        if (command.equals("done")) {
            return new DoneCommand(index);
        } else if (command.equals("delete")){
            return new DeleteCommand(index);
        } else {
            throw new DukeException("An unknown error has occurred.");
        }
    }
}
