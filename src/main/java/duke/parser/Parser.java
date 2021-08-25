package duke.parser;

import duke.commands.Command;
import duke.commands.AddCommand;
import duke.commands.ExitCommand;
import duke.commands.DoneCommand;
import duke.commands.ListCommand;
import duke.commands.DeleteCommand;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Parses user input.
 */
public class Parser {

    public static Command parse(String input) throws DukeException {
        if (input.equals("bye") || input.equals("exit")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.contains("done")) {
            String[] inputArr = input.split(" ");
            if (inputArr.length <= 1) {
                throw new DukeException("OOPS!!! You need to indicate a task for me to mark as done.");
            }
            int index = Integer.parseInt(inputArr[1]);
            return new DoneCommand(index);
        } else if (input.contains("todo")) {
            String[] inputArr = input.split(" ");
            if (inputArr.length <= 1) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            String description = input.substring(5);
            return new AddCommand(new ToDo(description));
        } else if (input.contains("deadline")) {
            String[] inputArr = input.split(" ");
            if (inputArr.length <= 1) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            String arg = input.substring(9);
            String[] argArr = arg.split("/by");
            if (argArr.length <= 1) {
                throw new DukeException("Please provide both the task description and date");
            }
            String description = argArr[0].trim();
            try {
                LocalDate date = LocalDate.parse(argArr[1].trim());
                return new AddCommand(new Deadline(description, date));
            } catch (DateTimeParseException e) {
                throw new DukeException("Date must be in the form YYYY-MM-DD");
            }
        } else if (input.contains("event")) {
            String[] inputArr = input.split(" ");
            if (inputArr.length <= 1) {
                throw new DukeException("OOPS!!! The description of an event cannot be empty.");
            }
            String arg = input.substring(6);
            String[] argArr = arg.split("/at");
            if (argArr.length <= 1) {
                System.out.println(Arrays.toString(argArr));
                throw new DukeException("Please provide both the description and date");
            }
            String description = argArr[0].trim();
            try {
                LocalDate date = LocalDate.parse(argArr[1].trim());
                return new AddCommand(new Event(description, date));
            } catch (DateTimeParseException e) {
                throw new DukeException("Date must be in the format YYYY-MM-DD");
            }
        } else if (input.contains("delete")) {
            String[] inputArr = input.split(" ");
            if (inputArr.length <= 1) {
                throw new DukeException("You need to indicate a task for me to delete");
            }
            try {
                int index = Integer.parseInt(inputArr[1]);
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Please provide a number");
            }
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
