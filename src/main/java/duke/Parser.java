package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Reads the input given and returns the appropriate Command to execute.
     *
     * @param input User input string to parse.
     * @return Command object.
     * @throws DukeException If input is not recognised or does not follow format.
     */
    public static Command parse(String input) throws DukeException {
        String[] wordArray = input.split(" ");
        String type = "";
        String description = "";
        String timeString = "";
        LocalDateTime time = null;

        if (wordArray.length == 0) {
            throw new DukeException("Please enter a command!");
        } else {
            type = wordArray[0];
        }

        assert wordArray.length > 0;

        int pointer = 1;
        while (pointer < wordArray.length
                && !wordArray[pointer].equals("/by")
                && !wordArray[pointer].equals("/at")) {
            description += wordArray[pointer] + " ";
            pointer++;
        }
        pointer++;
        description = description.trim(); // delete the last space

        assert pointer >= 2;

        while (pointer < wordArray.length) {
            timeString += wordArray[pointer] + " ";
            pointer++;
        }
        timeString = timeString.trim(); // delete the last space

        try {
            time = LocalDateTime.parse(timeString, FORMATTER);
        } catch (DateTimeParseException e) {
            // Does nothing and continue with time = null
        }

        switch (type) {
        case ("list"):
            description += timeString;
            if (description.equals("")) {
                return new ListCommand();
            } else {
                throw new DukeException("Command 'list' does not need additional arguments");
            }
        case ("done"):
            try {
                int index = Integer.parseInt(description) - 1;
                return new DoneCommand(index);
            } catch (NumberFormatException nfe) {
                throw new DukeException("Please follow command 'done' with an integer!");
            }
        case ("todo"):
            if (!description.equals("")) {
                Todo newTodo = new Todo(description);
                return new AddCommand(newTodo);
            } else {
                throw new DukeException("Please follow format: 'todo [description]'");
            }
        case ("deadline"):
            if (!description.equals("") && time != null) {
                Deadline newDeadline = new Deadline(description, time);
                return new AddCommand(newDeadline);
            } else {
                throw new DukeException("Please follow format: 'deadline [description] /by [yyyy-MM-dd HH:mm]'");
            }
        case ("event"):
            if (!description.equals("") && time != null) {
                Event newEvent = new Event(description, time);
                return new AddCommand(newEvent);
            } else {
                throw new DukeException("Please follow format: 'event [description] /at [yyyy-MM-dd HH:mm]'");
            }
        case ("delete"):
            try {
                int index = Integer.parseInt(description) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException nfe) {
                throw new DukeException("Please follow command 'delete' with an integer!");
            }
        case ("find"):
            if (!description.equals("")) {
                return new FindCommand(description);
            } else {
                throw new DukeException("Please indicate your search words after 'find'");
            }
        case ("bye"):
            description += timeString;
            if (description.equals("")) {
                return new ExitCommand();
            } else {
                throw new DukeException("Type 'bye' only to exit");
            }
        default:
            throw new DukeException("Command not recognised!");
        }
    }
}
