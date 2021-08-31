package duke;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.command.Action;
import duke.command.Add;
import duke.command.Command;
import duke.command.End;
import duke.command.Find;
import duke.command.List;
import duke.exception.DukeException;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidFormatException;
import duke.exception.MissingDescriptionException;
import duke.exception.UnknownCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Class that handles the user input parsing to something meaningful.
 */
public class Parser {

    /**
     * Enum class to distinguish between done, delete, deadline and event commands.
     * Used within Parser and Action classes.
     */
    private enum Type {
        DEADLINE,
        EVENT
    }

    /**
     * Returns a relevant Command object {`End`, `List`, `Action`, `Add`} after parsing.
     *
     * @param command Full user input
     * @return Corresponding Command instance
     * @throws DukeException that indicates what the issue was in parsing
     */
    public static Command parse(String command) throws DukeException {
        Command c;

        if (command.equals("bye")) {
            c = new End();
        } else if (command.equals("list")) {
            c = new List();
        } else {
            String[] words = command.split(" ");
            String mainCommand = words[0];

            switch (mainCommand) {
            case "done":
                c = new Action(0, words);
                break;
            case "delete":
                c = new Action(1, words);
                break;
            case "find":
                c = new Find(words);
                break;
            case "todo":
                String[] split = command.split(" ");
                if (split.length < 2) {
                    throw new MissingDescriptionException();
                }
                c = new Add(new ToDo(command.substring(5).trim()));
                break;
            case "deadline":
                c = new Add(handleFormat(command.split("/by"),
                        "`deadline ${item} /by ${time}`", Type.DEADLINE));
                break;
            case "event":
                c = new Add(handleFormat(command.split("/at "),
                        "`event ${item} /at ${time}`", Type.EVENT));
                break;
            default:
                throw new UnknownCommandException();
            }
        }

        return c;
    }

    private static Task handleFormat(String[] split, String message, Type type) throws DukeException {
        Task t;
        if (split.length < 2) {
            throw new InvalidFormatException(message);
        }
        String[] datetimes = split[1].trim().split(" ");
        if (datetimes.length == 1) {
            try {
                LocalDate date = LocalDate.parse(datetimes[0].replace("/", "-"));
                if (type == Type.DEADLINE) {
                    t = new Deadline(split[0].substring(9).trim(), date);
                } else {
                    t = new Event(split[0].substring(5).trim(), date);
                }
            } catch (Exception e) {
                throw new InvalidDateTimeException();
            }
        } else if (datetimes.length == 2) {
            try {
                LocalDate date = LocalDate.parse(datetimes[0].replace("/", "-"));
                String hour = datetimes[1].replace(":", "").substring(0, 2);
                String minute = datetimes[1].replace(":", "").substring(2, 4);
                LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));

                if (type == Type.DEADLINE) {
                    t = new Deadline(split[0].substring(9).trim(), date, time);
                } else {
                    t = new Event(split[0].substring(5).trim(), date, time);
                }
            } catch (Exception e) {
                throw new InvalidDateTimeException();
            }
        } else {
            throw new InvalidDateTimeException();
        }
        return t;
    }
}
