package duke.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.SortCommand;
import duke.exception.DukeDeadlineMissingDateException;
import duke.exception.DukeEventMissingDateException;
import duke.exception.DukeException;
import duke.exception.DukeInvalidIndexException;
import duke.exception.DukeMissingDescriptionException;
import duke.exception.DukeMissingIndexException;
import duke.exception.DukeNoSuchCommandException;
import duke.exception.DukeSortException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * This class encapsulates the parser which interprets user input.
 *
 * @author Teo Sin Yee
 */
public class Parser {

    /**
     * Parses the raw user input and returns the corresponding Command to execute.
     *
     * @param input Raw user input.
     * @return The corresponding Command.
     * @throws DukeException If user input is unexpected.
     */
    public Command parseRawInput(String input) throws DukeException {
        String[] splitInput = input.split("\\s+");
        String commandWord = splitInput[0];
        switch (commandWord.toLowerCase()) {
        case "list":
            return new ListCommand();
        case "sort":
            return new SortCommand(parseSortDetails(input));
        case "done":
            return new DoneCommand(parseIndex(input));
        case "find":
            return new FindCommand(parseFindKeyword(input));
        case "delete":
            return new DeleteCommand(parseIndex(input));
        case "todo":
            return new AddTaskCommand(new Todo(parseTodoDetails(input)));
        case "deadline":
            String[] deadlineDetails = parseDeadlineDetails(input);
            String dueDate = parseDateToString(deadlineDetails[1]);
            return new AddTaskCommand(new Deadline(deadlineDetails[0], dueDate));
        case "event":
            String[] eventDetails = parseEventDetails(input);
            String eventDate = parseDateToString(eventDetails[1]);
            return new AddTaskCommand(new Event(eventDetails[0], eventDate));
        case "bye":
            return new ExitCommand();
        default:
            assert !commandWord.matches("list|sort|done|find|delete|todo|deadline|event|bye");
            throw new DukeNoSuchCommandException();
        }
    }

    /**
     * Extracts the index for commands that modify tasks.
     *
     * @param input Raw user input.
     * @return Index of task to be modified.
     * @throws DukeMissingIndexException If user does not input index.
     * @throws DukeInvalidIndexException If user inputs a number smaller than 1.
     */
    private static int parseIndex(String input) throws DukeMissingIndexException, DukeInvalidIndexException {
        String[] splitInput = input.split("\\s+");
        if (splitInput.length == 1) {
            throw new DukeMissingIndexException(splitInput[0]);
        }
        String indexExtractedRaw = splitInput[1].trim();
        if (!indexExtractedRaw.matches("\\d+")) {
            throw new DukeInvalidIndexException();
        }
        return Integer.parseInt(indexExtractedRaw);
    }

    /**
     * Extracts the details for the todo command.
     *
     * @param input Raw user input.
     * @return Todo details.
     * @throws DukeMissingDescriptionException If the user does not specify the description.
     */
    private static String parseTodoDetails(String input) throws DukeMissingDescriptionException {
        String[] splitInput = input.split("\\s+");
        String todoDetails;
        if (splitInput.length < 2) {
            throw new DukeMissingDescriptionException(splitInput[0]);
        }
        todoDetails = input.replace(splitInput[0], "");
        return todoDetails.trim();
    }

    /**
     * Extracts the keyword for the find command.
     *
     * @param input Raw user input.
     * @return Keyword for find command.
     * @throws DukeMissingDescriptionException If the keyword is missing.
     */
    private static String parseFindKeyword(String input) throws DukeMissingDescriptionException {
        String[] splitInput = input.split("\\s+");
        String keyword;
        if (splitInput.length < 2) {
            throw new DukeMissingDescriptionException(splitInput[0]);
        }
        keyword = input.replace(splitInput[0], "");
        return keyword.trim();
    }

    /**
     * Checks if user specifies to sort in reverse.
     *
     * @param input Raw user input.
     * @return True if 'reverse' is entered, false otherwise.
     * @throws DukeSortException If there is an unexpected character.
     */
    private static boolean parseSortDetails(String input) throws DukeSortException {
        String[] splitInput = input.split("\\s+");
        boolean isReverse;
        if (splitInput.length == 1) {
            isReverse = false;
        } else if (splitInput.length == 2 && splitInput[1].matches("reverse")) {
            isReverse = true;
        } else {
            throw new DukeSortException();
        }
        return isReverse;
    }

    /**
     * Extracts the details for the deadline command.
     *
     * @param input Raw user input.
     * @return Details for deadline command.
     * @throws DukeMissingDescriptionException If the description is missing.
     * @throws DukeDeadlineMissingDateException If the due date is missing.
     */
    private static String[] parseDeadlineDetails(String input) throws DukeMissingDescriptionException,
            DukeDeadlineMissingDateException {
        String[] splitInput = input.split("\\s+");
        if (splitInput.length < 2) {
            throw new DukeMissingDescriptionException(splitInput[0]);
        }
        String[] deadlineDetails = input.split("\\s+/by\\s+");
        if (deadlineDetails.length < 2 | deadlineDetails[1].isEmpty()) {
            throw new DukeDeadlineMissingDateException();
        } else {
            deadlineDetails[0] = deadlineDetails[0].replace("deadline", "").trim();
            return deadlineDetails;
        }
    }

    /**
     * Extracts the details for the event command.
     *
     * @param input Raw user input.
     * @return Details for event command.
     * @throws DukeMissingDescriptionException If the description is missing.
     * @throws DukeEventMissingDateException If the event date is missing.
     */
    private static String[] parseEventDetails(String input) throws DukeMissingDescriptionException,
            DukeEventMissingDateException {
        String[] splitInput = input.split("\\s+");
        if (splitInput.length < 2) {
            throw new DukeMissingDescriptionException(splitInput[0]);
        }
        String[] eventDetails = input.split("\\s+/at\\s+");
        if (eventDetails.length < 2 || eventDetails[1].isEmpty()) {
            throw new DukeEventMissingDateException();
        } else {
            eventDetails[0] = eventDetails[0].replace("event", "").trim();
            return eventDetails;
        }
    }

    /**
     * Extracts the date for deadline and event commands.
     * Converts it to a readable format.
     *
     * @param dateInput Date input by user.
     * @return String representation of reformatted date.
     */
    private String parseDateToString(String dateInput) {
        DateFormat inputSdf;
        DateFormat outputSdf;
        Date date;
        if (dateInput.split("\\s+").length == 2) {
            inputSdf = new SimpleDateFormat("yyyy-MM-dd hhmm");
            outputSdf = new SimpleDateFormat("dd MMM yyyy, h.mm aa");
        } else {
            inputSdf = new SimpleDateFormat("yyyy-MM-dd");
            outputSdf = new SimpleDateFormat("dd MMM yyyy");
        }
        try {
            date = inputSdf.parse(dateInput);
        } catch (ParseException e) {
            return dateInput;
        }
        return outputSdf.format(date);
    }
}
