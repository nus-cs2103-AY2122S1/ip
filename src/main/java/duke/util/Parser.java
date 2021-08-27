package duke.util;

import duke.command.Command;
import duke.command.AddTaskCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.exception.DukeMissingDescriptionException;
import duke.exception.DukeNoSuchCommandException;
import duke.exception.DukeMissingIndexException;
import duke.exception.DukeInvalidIndexException;
import duke.exception.DukeDeadlineMissingDateException;
import duke.exception.DukeEventMissingDateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        switch (commandWord) {
        case "list":
            return new ListCommand();
        case "done":
            int doneIndex = parseIndex(input);
            return new DoneCommand(doneIndex);
        case "delete":
            int deleteIndex = parseIndex(input);
            return new DeleteCommand(deleteIndex);
        case "todo":
            String todoDetails = parseTodoDetails(input);
            Todo todo = new Todo(todoDetails);
            return new AddTaskCommand(todo);
        case "find":
            String keyword = parseFindKeyword(input);
            return new FindCommand(keyword);
        case "deadline":
            String[] deadlineDetails = parseDeadlineDetails(input);
            String dueDate = parseDateToString(deadlineDetails[1]);
            String deadlineName = deadlineDetails[0].replace("deadline", "").trim();
            Deadline deadline = new Deadline(deadlineName, dueDate);
            return new AddTaskCommand(deadline);
        case "event":
            String[] eventDetails = parseEventDetails(input);
            String eventDate = parseDateToString(eventDetails[1]);
            String eventName = eventDetails[0].replace("event", "").trim();
            Event event = new Event(eventName, eventDate);
            return new AddTaskCommand(event);
        case "bye":
            return new ExitCommand();
        default:
            throw new DukeNoSuchCommandException();
        }
    }

    /**
     * Extracts the index for commands that modify tasks.
     *
     * @param input Raw user input
     * @return Index of task to be modified.
     * @throws DukeMissingIndexException If user does not input index
     * @throws DukeInvalidIndexException If user inputs a number smaller than 1.
     */
    private static int parseIndex(String input) throws DukeMissingIndexException,DukeInvalidIndexException {
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
     * @param input Raw user input
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
     * Extracts the details for the deadline command.
     *
     * @param input Raw user input.
     * @return Details for deadline command.
     * @throws DukeMissingDescriptionException If the description is missing.
     * @throws DukeDeadlineMissingDateException If the due date is missing.
     */
    private static String[] parseDeadlineDetails(String input) throws DukeMissingDescriptionException, DukeDeadlineMissingDateException {
        String[] splitInput = input.split("\\s+");
        if (splitInput.length < 2) {
            throw new DukeMissingDescriptionException(splitInput[0]);
        }
        String[] deadlineDetails = input.split("\\s+/by\\s+", 2);
        if (deadlineDetails.length < 2 || deadlineDetails[1].isEmpty()) {
            throw new DukeDeadlineMissingDateException();
        } else {
            return deadlineDetails;
        }
    }

    /**
     * Extracts the details fo the event command.
     *
     * @param input Raw user input.
     * @return Details for event command
     * @throws DukeMissingDescriptionException If the description is missing.
     * @throws DukeEventMissingDateException If the event date is missing.
     */
    private static String[] parseEventDetails(String input) throws DukeMissingDescriptionException, DukeEventMissingDateException {
        String[] splitInput = input.split("\\s+");
        if (splitInput.length < 2) {
            throw new DukeMissingDescriptionException(splitInput[0]);
        }
        String[] eventDetails = input.split("\\s+/at\\s+");
        if (eventDetails.length < 2 || eventDetails[1].isEmpty()) {
            throw new DukeEventMissingDateException();
        } else {
            return eventDetails;
        }
    }

    /**
     * Extract the date for deadline and event commands.
     * Converts it to readable format.
     *
     * @param dateInput Date input by user.
     * @return Reformatted date in String representation.
     */
    private String parseDateToString(String dateInput) {
        DateFormat sdf;
        DateFormat reformattedSdf;
        Date date;
        if (dateInput.split("\\s+").length == 2) {
            sdf = new SimpleDateFormat("yyyy-MM-dd hhmm");
            reformattedSdf = new SimpleDateFormat("dd MMM yyyy, h.mm aa");
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            reformattedSdf = new SimpleDateFormat("dd MMM yyyy");
        }
        try {
            date = sdf.parse(dateInput);
        } catch (ParseException e) {
            return dateInput;
        }
        return reformattedSdf.format(date);
    }
}