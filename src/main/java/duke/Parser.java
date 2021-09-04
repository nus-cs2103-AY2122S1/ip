package duke;
import java.util.List;

public class Parser {

    public static Pair parseInput(String input) throws DukeException {
        if (input.matches("bye")) {
            return new Pair(Command.EXIT, List.<String>of());
        } else if (input.matches("list")) {
            return new Pair(Command.READ, List.<String>of());
        } else if (input.matches("done.*")) {
            if (!input.matches("done \\d+")) {
                throw new DukeException.MissingArgumentException("index of task to be deleted");
            }
            return new Pair(Command.UPDATE_MARKASDONE,
                    List.<String>of(input.substring("done ".length())));
        } else if (input.matches("delete.*")) {
            if (!input.matches("delete \\d+")) {
                throw new DukeException.MissingArgumentException("index of task to be deleted");
            }
            return new Pair(Command.DELETE, List.<String>of(
                    input.substring("delete ".length())));
        } else if (input.matches("event.*")) {
            int k = input.indexOf("/at");
            if (k < 0) {
                throw new DukeException.MissingArgumentException("/at");
            }
            return new Pair(Command.CREATE_EVENT,
                    List.<String>of(input.substring("event".length(), k).trim(),
                    input.substring(k + 3).trim()));
        } else if (input.matches("deadline.*")) {
            int k = input.indexOf("/by");
            if (k < 0) {
                throw new DukeException.MissingArgumentException("/by");
            }
            return new Pair(Command.CREATE_DEADLINE,
                    List.<String>of(
                        input.substring("deadline".length(), k).trim(),
                        input.substring(k + 3).trim()));
        } else if (input.matches("todo.*")) {
            return new Pair(
                    Command.CREATE_TODO,
                    List.<String>of(input.substring("todo".length())));
        } else {
            throw new DukeException.UnknownInputException();
        }
    }
}

