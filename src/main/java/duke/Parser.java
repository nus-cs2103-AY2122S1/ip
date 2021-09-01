package duke;

import duke.command.*;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String[] params = fullCommand.split(" ", 2);
        String keyword = params[0];

        switch (keyword) {
        case ("list"):
            return new ListCommand();
        case ("done"):
            if (params.length == 1) {
                throw new DukeException("☹ OOPS!!! Please enter the task you'd like to" +
                        "mark as done in the following format: \n\t done [task number]");
            }
            return new DoneCommand(Integer.parseInt(params[1]) - 1);
        case ("delete"):
            if (params.length == 1) {
                throw new DukeException("☹ OOPS!!! Please enter the task you'd like to" +
                        "delete in the following format: \n\t delete [task number]");
            }
            return new DeleteCommand(Integer.parseInt(params[1]) - 1);
        case ("todo"):
        case ("event"):
        case ("deadline"):
            return new AddCommand(params);
        case ("bye"):
            return new ByeCommand();
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
