package duke.util;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FilterCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UnrecognisedCommand;
import duke.exception.DukeException;
import duke.exception.IndexFormatException;
import duke.exception.MissingIndexException;
import duke.exception.MissingKeywordException;
import duke.exception.MultipleKeywordsException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class encapsulates the parser which deals with making sense of user inputs.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class Parser {
    private final ToDoList list;
    private final DataManager dataManager;

    public Parser(ToDoList list, DataManager dataManager) {
        this.list = list;
        this.dataManager = dataManager;
    }

    /**
     * Extracts out index for commands that deals with modifying specific tasks.
     *
     * @param input Raw user's input.
     * @return Desired index specified by user.
     * @throws MissingIndexException if user did not input index.
     * @throws IndexFormatException if user inputs anything besides positive numerals.
     */
    public static int extractIndex(String input) throws DukeException {
        String[] inputs = input.split(" ", 2);

        // Check whether user input index
        if (inputs.length < 2) {
            throw new MissingIndexException();
        }

        // Checks whether user entered positive numeral
        if (!inputs[1].matches("\\d+")) {
            throw new IndexFormatException();
        }

        return Integer.parseInt(inputs[1]);
    }

    /**
     * Extracts out index for commands that deals with modifying specific tasks.
     *
     * @param dateTime DateTime entered by user.
     * @return Date object after parsing user's input.
     */
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

    /**
     * Extracts out the keyword for find command.
     *
     * @param input Raw user's input.
     * @return the keyword user is searching for.
     */
    public static String extractKeyword(String input) throws DukeException {
        if (input.split(" ").length < 2) {
            throw new MissingKeywordException();
        } else if (input.split(" ").length > 2) {
            throw new MultipleKeywordsException();
        }

        return input.split(" ")[1];
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
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            return new AddCommand(list, dataManager, lowerCaseInput, input);
        case "exit":
            return new ExitCommand();
        case "delete":
            return new DeleteCommand(input, list);
        case "filter":
            return new FilterCommand(input, list);
        case "find":
            return new FindCommand(list, input);
        default:
            return new UnrecognisedCommand(input);
        }
    }
}
