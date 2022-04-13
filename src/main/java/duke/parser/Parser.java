package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

import duke.DukeException;
import duke.commands.*;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into command for execution.
     *
     * @param input Full user input string.
     * @return The command based on the user input.
     * @throws DukeException if user uses the wrong keyword.
     */
    public static Command parseCommand(String input) throws DukeException {
        String text = input.trim();
        if (text.equals("q")) {
            return new ExitCommand();
        } else if (text.equals("ls")) {
            return new ListCommand();
        } else if (text.startsWith("done")) {
            return new DoneCommand(text);
        } else if (text.startsWith("delete")) {
            return new DeleteCommand(text);
        } else if (text.equals("undo")) {
            return new UndoCommand();
        } else if (text.startsWith("find")) {
            return new FindCommand(text);
        } else if (text.equals("undo")) {
            return new UndoCommand();
        } else if (text.startsWith("todo") || text.startsWith("deadline") || text.startsWith("event")) {
            return parseAddCommand(text);
        } else {
            throw new DukeException("Please use the keyword --todo, deadline or event--");
        }
    }

    /**
     * Parses command to add a task.
     * @param input Full user input string.
     * @return The command based on the user input.
     * @throws DukeException if user uses the wrong format.
     */
    public static Command parseAddCommand(String input) throws DukeException {
        input = input.trim();
        String description = "";
        String time = "";
        try {
            if (input.contains("todo")) {
                description = input.split(" ", 2)[1];
                return new TodoCommand(description);
            } else if (input.contains("deadline")) {
                try {
                    description = input.split(" ", 2)[1].split(" /by ", 2)[0];
                    time = input.split(" ", 2)[1].split(" /by ", 2)[1];
                    return new DeadlineCommand(description, time);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Use the format --deadline xx /by (yyyy-MM-dd) (HH:mm)--");
                }
            } else if (input.contains("event")) {
                try {
                    description = input.split(" ", 2)[1].split(" /at ", 2)[0];
                    time = input.split(" ", 2)[1].split(" /at ", 2)[1];
                    return new EventCommand(description, time);
                } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                    throw new DukeException("Use the format --event xx /at (yyyy-MM-dd) (HH:mm)--");
                }
            } else {
                throw new DukeException("Please use the keyword --todo, deadline or event--");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The description of a task cannot be empty.");
        }
    }

    /**
     * Parses the text from the file which contains the task list.
     *
     * @param input The text from the file containing the task list.
     * @return A Map containing the type, description and time (if applicable) of a Task.
     */
    public static Map<String, String> parseFromFile(String input) throws DukeException {
        String type = input.substring(1, 2);
        String status = input.substring(4, 5);
        String description = input.split("\\(")[0].substring(7).trim();
        String time = "";
        String finalText = "";
        if (type.equals("D") || type.equals("E")) {
            String temp = input.split(":", 2)[1];
            time = temp.substring(1, temp.length() - 1);
            if (time.length() > 11) {
                String date = LocalDate.parse(time.substring(0, 11),
                        DateTimeFormatter.ofPattern("dd MMM yyyy")).toString();
                time = date + " " + time.substring(12);
            } else if (time.contains(":")) {
                // do nothing
            } else {
                time = LocalDate.parse(time, DateTimeFormatter.ofPattern("dd MMM yyyy")).toString();
            }
        }

        switch (type) {
        case "T":
            finalText = "todo " + description;
            break;
        case "D":
            finalText = "deadline " + description + " /by " + time;
            break;
        case "E":
            finalText = "event " + description + " /at " + time;
            break;
        default:
            throw new DukeException("Wrong formatting in duke.txt");
        }

        return Map.of("finalText", finalText, "status", status);
    }
}
