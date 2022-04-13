package duke;
import java.util.Arrays;
import java.util.List;

public abstract class Parser {
    /**
     * Parses user input and returns the command with the supplied arguments
     * @param input a line of string from stdin
     * @return a pair Comprised of a Command and a List of arguments
     * @throws DukeException if the input is invalid
     */
    public static Pair parseInput(String input) throws DukeException {
        if (input.matches("bye")) {
            return new Pair(Command.EXIT, List.of());
        } else if (input.matches("find.*")) {
            if (!input.matches("find .+")) {
                throw new DukeException.MissingArgumentException("keyword to look for");
            }
            return new Pair(Command.FIND, List.of(input.substring("find ".length())));
        } else if (input.matches("list")) {
            return new Pair(Command.READ, List.of());
        } else if (input.matches("done.*")) {
            if (!input.matches("done \\d+")) {
                throw new DukeException.MissingArgumentException("index of task to be marked as done");
            }
            return new Pair(Command.UPDATE_MARKASDONE,
                    List.of(input.substring("done ".length())));
        } else if (input.matches("delete.*")) {
            if (!input.matches("delete \\d+")) {
                throw new DukeException.MissingArgumentException("index of task to be deleted");
            }
            return new Pair(Command.DELETE, List.of(
                    input.substring("delete ".length())));
        } else if (input.matches("snooze.*")) {
            if (!input.matches("snooze \\d+ \\d+")) {
                throw new DukeException.MissingArgumentException("index of task, number of days for task to be snoozed");
            }
            return new Pair(Command.SNOOZE, Arrays.asList(input.split(" ")).subList(1,3));
        } else if (input.matches("event.*")) {
            int k = input.indexOf("/at");
            if (k < 0) {
                throw new DukeException.MissingArgumentException("/at");
            }
            return new Pair(Command.CREATE_EVENT,
                    List.of(input.substring("event".length(), k).trim(),
                    input.substring(k + 3).trim()));
        } else if (input.matches("deadline.*")) {
            int k = input.indexOf("/by");
            if (k < 0) {
                throw new DukeException.MissingArgumentException("/by");
            }
            return new Pair(Command.CREATE_DEADLINE,
                    List.of(
                        input.substring("deadline".length(), k).trim(),
                        input.substring(k + 3).trim()));
        } else if (input.matches("todo.*")) {
            return new Pair(
                    Command.CREATE_TODO,
                    List.of(input.substring("todo".length())));
        } else {
            throw new DukeException.UnknownInputException();
        }
    }
}

