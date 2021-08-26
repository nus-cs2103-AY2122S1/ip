package duke.util;

import duke.command.*;
import duke.exception.*;
import duke.task.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
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

    private static int parseIndex(String input) throws DukeException {
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

    private static String parseTodoDetails(String input) throws DukeException {
        String[] splitInput = input.split("\\s+");
        String todoDetails;
        if (splitInput.length < 2) {
            throw new DukeMissingDescriptionException(splitInput[0]);
        }
        todoDetails = input.replace(splitInput[0], "");
        return todoDetails.trim();
    }

    private static String[] parseDeadlineDetails(String input) throws DukeException {
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

    private static String[] parseEventDetails(String input) throws DukeException {
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