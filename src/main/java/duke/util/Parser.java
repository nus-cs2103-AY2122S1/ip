package duke.util;

import java.io.IOException;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.SortCommand;
import duke.command.HelpCommand;
import duke.command.DeleteCommand;
import duke.command.DisplayCommand;
import duke.command.MarkDoneCommand;

import duke.exception.DukeException;
import duke.exception.TaskException;
import duke.exception.UnknownCommandException;

/**
 * The Parser class encapsulates information
 * and methods pertaining to parsing user input commands in Duke.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public class Parser {
    /**
     * A static method to parse the command for creating ToDo tasks.
     *
     * @param line the input command to be parsed.
     * @throws TaskException When failed to parse.
     */
    private static ToDo parseToDo(String line) {
        if (line.length() <= 5) {
            throw new TaskException(new ToDo("?"));
        }

        String description = line.substring(5);
        return new ToDo(description);
    }

    /**
     * A static method to parse the command for creating Deadline tasks.
     *
     * @param line the input command to be parsed.
     * @return A new Deadline object.
     * @throws TaskException When failed to parse.
     */
    private static Deadline parseDeadline(String line) {
        int pos = line.indexOf("/by");
        if (pos == -1 || line.length() < pos + 5) {
            throw new TaskException(new Deadline("?", "?"));
        }

        String description = line.substring(9, pos - 1);
        String byDescription = line.substring(pos + 4);
        return new Deadline(description, byDescription);
    }

    /**
     * A static method to parse the command for creating Event tasks.
     *
     * @param line the input command to be parsed.
     * @return A new Event object.
     * @throws TaskException When failed to parse.
     */
    private static Event parseEvent(String line) {
        int pos = line.indexOf("/at");
        if (pos == -1 || line.length() < pos + 5) {
            throw new TaskException(new Event("?", "?"));
        }

        String description = line.substring(6, pos - 1);
        String atDescription = line.substring(pos + 4);
        return new Event(description, atDescription);
    }

    /**
     * A static method to parse the input task creation commands from the user.
     *
     * @param line the input command to be parsed as a task.
     * @return A new Command object that handles adding the parsed task.
     * @throws DukeException When failed to parse.
     */
    private static Command parseTask(String line) throws DukeException {
        Task task = null;
        String type = line.split(" ")[0];
        if (type.equals("todo")) {
            task = Parser.parseToDo(line);

        } else if (type.equals("deadline")) {
            task = Parser.parseDeadline(line);
        
        } else if (type.equals("event")) {
            task = Parser.parseEvent(line);

        } else {
            throw new UnknownCommandException(line);
        }

        return new AddCommand(task);
    }

    private static Command parseIndexCommand(String line) throws DukeException {
        String[] tokens = line.split(" ");
        if (tokens.length != 2) {
            throw new UnknownCommandException(line);
        }

        int index = -1;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
        } catch (NumberFormatException ex) {
            throw new UnknownCommandException(line);
        }

        if (tokens[0].equals("done")) {
            return new MarkDoneCommand(index);
        } else if (tokens[0].equals("delete")) {
            return new DeleteCommand(index);
        } else {
            throw new UnknownCommandException(line);
        }
    }

    /**
     * A static method to parse the input commands from user input.
     *
     * @param line the input command to be parsed.
     * @return A new Command object.
     * @throws DukeException When failed to parse.
     * @throws IOException On failed loading of files.
     */
    public static Command parse(String line) throws DukeException, IOException {
        if (line.equals("list")) {
            return new DisplayCommand();

        } else if (line.equals("bye")) {
            return new ExitCommand();

        } else if (line.equals("help")) {
            return new HelpCommand();

        } else if (line.split(" ")[0].equals("done")) {
            return Parser.parseIndexCommand(line);

        } else if (line.split(" ")[0].equals("delete")) {
            return Parser.parseIndexCommand(line);

        } else if (line.split(" ")[0].equals("find")) {
            return new FindCommand(line.substring(5));

        } else if (line.equals("sort")) {
            return new SortCommand();

        } else {
            return Parser.parseTask(line);
        }
    }
}