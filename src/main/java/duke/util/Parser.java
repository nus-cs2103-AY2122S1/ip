package duke.util;

import duke.command.*;
import duke.exception.*;
import duke.task.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    private static final String BLANK_DESCRIPTION_MESSAGE = "OOPS!!! The description of %s cannot be empty! x_x";
    private static final String INDEX_FORMAT_ERROR = "Please enter a positive number!";
    private static final String DEADLINE_ERROR_MESSAGE = "Invalid use of 'deadline' command!! @_@\n\tTo add a new deadline, use 'deadline <task> /by <due-date>'.";
    private static final String EVENT_ERROR_MESSAGE = "Invalid use of 'event' command!! @_@\n\tTo add a new event, use 'event <title> /at <time-stamp>'.";

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
            case "deadline":
                String[] deadlineDetails = parseDeadlineDetails(input);
                String dueDate = parseDateToString(deadlineDetails[1]);
                Deadline deadline = new Deadline(deadlineDetails[0], dueDate);
                return new AddTaskCommand(deadline);
            case "event":
                String[] eventDetails = parseEventDetails(input);
                String eventDate = parseDateToString(eventDetails[1]);
                Event event = new Event(eventDetails[0], eventDate);
                return new AddTaskCommand(event);
            case "bye":
                return new ExitCommand();
            default:
                throw new DukeException(("I don't quite understand you. :-("));
        }
    }

    private static int parseIndex(String input) throws DukeException {
        String[] splitInput = input.split("\\s+");
        if (splitInput.length == 1) {
            throw new DukeException(String.format(BLANK_DESCRIPTION_MESSAGE, splitInput[0]));
        }
        String indexExtractedRaw = splitInput[1].trim();
        if (!indexExtractedRaw.matches("\\d+")) {
            throw new DukeException(INDEX_FORMAT_ERROR);
        }
        return Integer.parseInt(indexExtractedRaw);
    }

    private static String parseTodoDetails(String input) throws DukeException {
        String[] splitInput = input.split("\\s+");
        if (splitInput.length < 2) {
            throw new DukeException(String.format(BLANK_DESCRIPTION_MESSAGE, splitInput[0]));
        }
        return splitInput[1].trim();
    }


    private static String[] parseDeadlineDetails(String input) throws DukeException {
        String[] splitInput = input.split("\\s+");
        if (splitInput.length == 1) {
            throw new DukeException(String.format(BLANK_DESCRIPTION_MESSAGE, splitInput[0]));
        }
        String[] deadlineDetails = input.split("\\s+/by\\s+", 2);
        if (deadlineDetails.length < 2 || deadlineDetails[1].isEmpty()) {
            throw new DukeException(DEADLINE_ERROR_MESSAGE);
        } else {
            return deadlineDetails;

        }
    }

    private static String[] parseEventDetails(String input) throws DukeException {
        String[] splitInput = input.split("\\s+");
        if (splitInput.length == 1) {
            throw new DukeException(String.format(BLANK_DESCRIPTION_MESSAGE, splitInput[0]));
        }
        String[] eventDetails = input.split("\\s+/at\\s+");
        if (eventDetails.length < 2 || eventDetails[1].isEmpty()) {
            throw new DukeException(EVENT_ERROR_MESSAGE);
        } else {
            return eventDetails;
        }
    }

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