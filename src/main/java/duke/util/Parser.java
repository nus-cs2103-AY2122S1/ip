package duke.util;

import duke.Command;
import duke.exception.DukeException;
import duke.exception.IndexFormatException;
import duke.exception.MissingDateTimeException;
import duke.exception.MissingDescriptionException;
import duke.exception.MissingIndexException;
import duke.exception.MultipleDateTimeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    private final ToDoList list;
    private final DataManager dataManager;

    public Parser(ToDoList list, DataManager dataManager) {
        this.list = list;
        this.dataManager = dataManager;
    }

    /**
     * Classifies the user's input into one of the Command.
     *
     * @param input Raw user's input.
     * @return The corresponding Command.
     */
    public Command detectCommand(String input) {
        String[] inputs = input.split(" ");
        return Command.convertInput(inputs[0]);
    }

    /**
     * Extracts out index for commands that deals with modifying specific tasks.
     *
     * @param input Raw user's input.
     * @return Desired index specified by user.
     */
    public int extractIndex(String input) throws DukeException {
        String[] inputs = input.split(" ", 2);

        // Check whether user input index
        if (inputs.length < 2) {
            throw new MissingIndexException();
        }

        if (!inputs[1].matches("\\d+")) {
            throw new IndexFormatException();
        }

        return Integer.parseInt(inputs[1]);
    }

    /**
     * Handler for ToDos task creation.
     *
     * @param input Raw user's input.
     */
    public void handleTodo(String input) throws DukeException {
        String[] extracted = input.split(" ", 2);

        // Check whether description is entered
        if (extracted.length < 2) {
            throw new MissingDescriptionException();
        }

        ToDo task = new ToDo(extracted[1]);
        list.addToList(task);
        dataManager.writeToFile(task);
    }

    /**
     * Handler for Deadline task creation.
     *
     * @param input Raw user's input.
     */
    public void handleDeadline(String input) throws DukeException {
        // Check whether description is entered
        if (input.split(" ").length < 2) {
            throw new MissingDescriptionException();
        }

        String[] extracted = input.split(" ", 2)[1].split(" /by ");

        // Check whether deadline is specified correctly
        if (extracted.length < 2) {
            throw new MissingDateTimeException("'/by'");
        } else if (extracted.length > 2) {
            throw new MultipleDateTimeException();
        }

        String description = extracted[0];
        String deadline = extracted[1];
        Deadline task = new Deadline(description, deadline);
        list.addToList(task);
        dataManager.writeToFile(task);
    }

    /**
     * Handler for Event task creation.
     *
     * @param input Raw user's input.
     */
    public void handleEvent(String input) throws DukeException {
        // Check whether description is entered
        if (input.split(" ").length < 2) {
            throw new MissingDescriptionException();
        }

        String[] extracted = input.split(" ", 2)[1].split(" /at ");

        // Check whether deadline is specified correctly
        if (extracted.length < 2) {
            throw new MissingDateTimeException("'/at'");
        } else if (extracted.length > 2) {
            throw new MultipleDateTimeException();
        }

        String description = extracted[0];
        String dateTime = extracted[1];
        Event task = new Event(description, dateTime);
        list.addToList(task);
        dataManager.writeToFile(task);
    }

    public static Date parseDateTime(String dateTime) {
        Date date;
        DateFormat inFormat;

        if (dateTime.split(" ").length == 2) {
            inFormat = new SimpleDateFormat("yyyy-MM-dd hhmm");
        } else {
            inFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        try {
            date = inFormat.parse(dateTime);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }
}
