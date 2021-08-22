package duke.util;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.IndexFormatException;
import duke.exception.MissingIndexException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    private final ToDoList list;
    private final DataManager dataManager;
    private static final String ls = System.lineSeparator();

    public Parser(ToDoList list, DataManager dataManager) {
        this.list = list;
        this.dataManager = dataManager;
    }

    /**
     * Parses the user's input and returns the appropriate command to act on.
     *
     * @param input Raw user's input.
     * @return The corresponding Command.
     */
    public Command parse(String input) {
        String[] inputs = input.split(" ");

        String lowerCaseInput = inputs[0].toLowerCase();
        switch (lowerCaseInput) {
        case "list":
            return new ListCommand(list);
        case "done":
            return new DoneCommand(list, input);
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(list, dataManager, lowerCaseInput, input);
        case "exit":
            return new ExitCommand();
        case "delete":
            return new DeleteCommand(input, list);
        case "filter":
            return new FilterCommand(input, list);
        default:
            return new UnrecognisedCommand(input);
        }
    }

    /**
     * Extracts out index for commands that deals with modifying specific tasks.
     *
     * @param input Raw user's input.
     * @return Desired index specified by user.
     */
    public static int extractIndex(String input) throws DukeException {
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
