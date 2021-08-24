package duke.util;

import duke.command.*;
import duke.exception.*;
import duke.task.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    private final TaskHandler taskHandler;
    private final Storage storage;

    private static final String BLANK_DESCRIPTION_MESSAGE = "OOPS!!! The description of %s cannot be empty! x_x";
    private static final String INDEX_FORMAT_ERROR = "Please enter a positive number!";
    private static final String DEADLINE_ERROR_MESSAGE = "Invalid use of 'deadline' command!! @_@\n\tTo add a new deadline, use 'deadline <task> /by <due-date>'.";
    private static final String EVENT_ERROR_MESSAGE = "Invalid use of 'event' command!! @_@\n\tTo add a new event, use 'event <title> /at <time-stamp>'.";

    public Parser(TaskHandler th, Storage storage) {
        this.taskHandler = th;
        this.storage = storage;
    }

    public Command parse(String input) throws DukeException {
        String inputUpperCase = input.trim().toLowerCase();
        String commandWord = input.split("\\s+")[0];
        switch (commandWord) {
            case "list":
                taskHandler.printTasks();
                break;
            case "done":
                int doneIndex = extractIndex(input);
                taskHandler.markTaskAsDone(doneIndex);
                taskHandler.updateData();
                break;
            case "delete":
                int deleteIndex = extractIndex(input);
                taskHandler.deleteTask(deleteIndex);
                taskHandler.updateData();
                break;
            case "todo":
                String todoDetails = extractTodoDetails(input);
                Todo td = new Todo(todoDetails);
                taskHandler.addTask(td);
                storage.writeToFile(td);
                break;
            case "deadline":
                String[] deadlineDetails = extractDeadlineDetails(input);
                String dueDate = parseDueDateToString(deadlineDetails[1], commandWord);
                Deadline dl = new Deadline(deadlineDetails[0], dueDate);
                taskHandler.addTask(dl);
                storage.writeToFile(dl);
                break;
            case "event":
                String[] eventDetails = extractEventDetails(input);
                Event e = new Event(eventDetails[0], eventDetails[1]);
                taskHandler.addTask(e);
                storage.writeToFile(e);
                break;
            case "bye":
                Ui.printExitMessage();
                break;
            default:
                throw new DukeException(("I don't quite understand you. :-("));
        }
        return Command.evaluateInput(commandWord);
    }

    private static int extractIndex(String input) throws DukeException {
        if (input.split("\\s+").length < 2) {
            throw new DukeException(String.format(BLANK_DESCRIPTION_MESSAGE, input.split("\\s+")[0]));
        }
        String descExtractedRaw = input.split("\\s+")[1].trim();
        if (!descExtractedRaw.matches("\\d+")) {
            throw new DukeException(INDEX_FORMAT_ERROR);
        }
        return Integer.parseInt(descExtractedRaw);
    }

    private static String extractTodoDetails(String input) throws DukeException {
        if (input.split("\\s+").length < 2) {
            throw new DukeException(String.format(BLANK_DESCRIPTION_MESSAGE, input.split("\\s+")[0]));
        }
        return input.split("\\s+")[1].trim();
        }


    private static String[] extractDeadlineDetails(String input) throws DukeException {
        if (input.split("\\s+").length < 2) {
            throw new DukeException(String.format(BLANK_DESCRIPTION_MESSAGE, input.split("\\s+")[0]));
        }
        String[] deadlineDetails = input.split("\\s+/by\\s+", 2);
        if (deadlineDetails.length < 2) {
            throw new DukeException(DEADLINE_ERROR_MESSAGE);
        } else {
            return deadlineDetails;
        }
    }

    private static String[] extractEventDetails(String input) throws DukeException {
        if (input.split("\\s+").length < 2) {
            throw new DukeException(String.format(BLANK_DESCRIPTION_MESSAGE, input.split("\\s+")[0]));
        }
        String[] eventDetails = input.split("\\s+/at\\s+", 2);
        if (eventDetails.length < 2 || eventDetails[1].isEmpty()) {
            throw new DukeException(EVENT_ERROR_MESSAGE);
        } else {
            return eventDetails;
        }
    }

    private String parseDueDateToString(String dueDate, String taskType) throws DukeException {
        if (dueDate.isEmpty()) {
            if (taskType.equalsIgnoreCase("event")) {
                throw new DukeException(EVENT_ERROR_MESSAGE);
            } else {
                throw new DukeException(DEADLINE_ERROR_MESSAGE);
            }
        }
        DateFormat sdf;
        DateFormat reformattedSdf;
        Date date;
        if (dueDate.split("\\s+").length == 2) {
            sdf = new SimpleDateFormat("yyyy-MM-dd hhmm");
            reformattedSdf = new SimpleDateFormat("dd MMM yyyy, h.mm aa");
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            reformattedSdf = new SimpleDateFormat("dd MMM yyyy");
        }
        try {
            date = sdf.parse(dueDate);
        } catch (ParseException e) {
            return dueDate;
        }
        return reformattedSdf.format(date);
    }
}