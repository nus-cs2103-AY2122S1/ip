package duke;

/**
 * Deals with interpreting the user command.
 */
public class Parser {

    /**
     * Returns the type of command user has entered if valid, or throws an exception.
     *
     * @param command entered by user in the terminal.
     * @return CommandType that denotes the type of command corresponding to the user input.
     * @throws DukeException if there is an invalid command or an incomplete command.
     */

    public static CommandType parse(String command) throws DukeException {

        if (command.equals("list")) {
            return CommandType.LIST;
        } else if (command.equals("bye")) {
            return CommandType.BYE;
        } else if (command.startsWith("find")) {
            if(command.equals("find")) {
                throw new DukeException("☹ OOPS!!! The find command needs a search term after it in the following format: "
                        + "find searchterm");
            }
            return CommandType.FIND;
        } else if (command.startsWith("delete")) {
            if (command.equals("delete")) {
                throw new DukeException("☹ OOPS!!! The delete command needs a number after it in the following format:"
                        + " delete number");
            }
            return CommandType.DELETE;
        } else if (command.startsWith("done")) {
            if (command.equals("done")) {
                throw new DukeException("☹ OOPS!!! The done command needs a number after it in the following format:"
                        + " done number");
            }
            return CommandType.DONE;
        } else if (command.startsWith("todo")) {
            if (command.equals("todo")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            return CommandType.TODO;
        } else if (command.startsWith("deadline")) {
            if (command.equals("deadline")) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            return CommandType.DEADLINE;
        } else if (command.startsWith("event")) {
            if (command.equals("event")) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            return CommandType.EVENT;
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}