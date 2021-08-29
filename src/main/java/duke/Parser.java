package duke;

import java.util.stream.Stream;

import duke.command.Command;
import duke.command.CommandsTypes;
import duke.task.Task;



/**
 * Class to parse the input by user.
 */
public class Parser {
    /**
     * Parses input by user.
     *
     * @param input the input by user.
     * @return the command to be executed.
     * @throws DukeException if input is invalid.
     */
    public static Command parse(String input) throws DukeException {
        if (input.replaceAll("\\s+", "").toLowerCase().equals("bye")) {
            return Command.makeCommand(CommandsTypes.Exit);
        }
        if (input.replaceAll("\\s+", "").toLowerCase().equals("list")) {
            return Command.makeCommand(CommandsTypes.List);
        }
        String[] splitBySpace = input.split(" ");
        if (splitBySpace.length < 2) {
            throw new DukeException("Invalid input.");
        }
        if (splitBySpace[0].toLowerCase().equals("done")) {
            try {
                int index = Integer.parseInt(splitBySpace[1]);
                if (index < 1) {
                    throw new NumberFormatException();
                }
                return Command.makeCommand(CommandsTypes.MarkDone, index);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid index inputted after done. Please enter a positive integer");
            }
        }
        if (splitBySpace[0].toLowerCase().equals("delete")) {
            try {
                int index = Integer.parseInt(splitBySpace[1]);
                if (index < 1) {
                    throw new NumberFormatException();
                }
                return Command.makeCommand(CommandsTypes.Delete, index);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid index inputted after delete. Please enter a positive integer");
            }
        }

        if (splitBySpace[0].toLowerCase().equals("find")) {
            String keyword = splitBySpace[1];
            return Command.makeCommand(CommandsTypes.Find, keyword);
        }
        String type = splitBySpace[0].toLowerCase();
        String taskDescription = Stream.of(input.split(" "))
                .skip(1).reduce("", (x, y) -> x + " " + y);
        Task newTask = Task.makeTask(type, taskDescription);
        return Command.makeCommand(CommandsTypes.Add, newTask);
    }
}
