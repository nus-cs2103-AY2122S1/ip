package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.Command;
import duke.commands.CommandListCommand;
import duke.commands.ClearCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;

/**
 * Represents a translator that makes sense of user input commands.
 */
public class Parser {
    private static String detectedFormat;
    private static LocalTime startTime;
    private static LocalTime endTime;
    private static final String[] dateTimeFormats = {
        "dd/MM/yyyy HHmm",
        "dd-MM-yyyy HHmm",
        "dd/MM/yyyy hh:mm a",
        "dd-MM-yyyy hh:mm a",
        "dd/MM/yyyy HHmm",
        "dd-MM-yyyy HHmm",
        "yyyy/MM/dd HHmm",
        "yyyy-MM-dd HHmm",
        "yyyy/MM/dd hh:mm a",
        "yyyy-MM-dd hh:mm a",
        "yyyy/MM/dd HHmm",
        "yyyy-MM-dd HHmm",
        "dd MMM yyyy hh:mm a"
    };
    private static final String[] dateFormats = {
        "dd/MM/yyyy",
        "dd-MM-yyyy",
        "yyyy/MM/dd",
        "yyyy-MM-dd",
        "dd MMM yyyy"
    };
    private static final String[] timeFormats = {
        "HHmm",
        "hh:mm a"
    };

    /**
     * Takes in the user input as a String and runs checks on it to determine
     * the type of command it is.
     *
     * @param fullCommand String of user input.
     * @param taskList a list to store tasks the user adds or deletes.
     * @return Command which Duke bot can execute afterwards.
     * @throws DukeException if user input cannot be understood.
     */
    public static Command parse(String fullCommand, TaskList taskList) throws DukeException {

        String trimmedCommand = fullCommand.trim();
        // Check if it's empty string
        if (trimmedCommand.length() == 0) {
            throw new DukeException("empty command");
        }
        // Check if it's at least two words or not
        if (!trimmedCommand.contains(" ")) {
            switch (trimmedCommand) {
            case "bye":
                return new ExitCommand();
            case "list":
                if (taskList.taskCount() == 0) {
                    throw new DukeException("empty task list");
                }
                return new ListCommand();
            case "clear":
                return new ClearCommand(taskList);
            case "commands":
                return new CommandListCommand();
            default:
                throw new DukeException("invalid input");
            }
        }
        // Everything here on has user input of two words or more
        String userCommand = fullCommand.split(" ", 2)[0];
        String commandDescription = fullCommand.split(" ", 2)[1].trim();

        switch (userCommand) {
        case "done":
            return parseDone(commandDescription, taskList);
        case "delete":
            return parseDelete(commandDescription, taskList);
        case "find":
            return parseFind(commandDescription);
        case "todo":
            return parseToDo(commandDescription);
        case "deadline":
            return parseDeadline(commandDescription);
        case "event":
            return parseEvent(commandDescription);
        default:
            throw new DukeException("invalid input");
        }
    }
    private static Command parseDone(String input, TaskList taskList) throws DukeException {
        assert !input.isEmpty() : "Empty done event input";

        try {
            int index = Integer.parseInt(input);
            boolean isIndexAboveLowerBound = index > 0;
            boolean isIndexBelowUpperBound = index < taskList.taskCount() + 1;
            boolean isIndexInRange = isIndexAboveLowerBound && isIndexBelowUpperBound;

            if (isIndexInRange) {
                return new DoneCommand(index);
            } else {
                throw new DukeException("invalid task index");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("non-integer input");
        }
    }
    private static Command parseDelete(String input, TaskList taskList) throws DukeException {
        assert !input.isEmpty() : "Empty delete event input";
        try {
            int index = Integer.parseInt(input);
            boolean isIndexAboveLowerBound = index > 0;
            boolean isIndexBelowUpperBound = index < taskList.taskCount() + 1;
            boolean isIndexInRange = isIndexAboveLowerBound && isIndexBelowUpperBound;
            if (taskList.taskCount() == 0) {
                throw new DukeException("empty task list");
            }

            if (isIndexInRange) {
                return new DeleteCommand(index);
            } else {
                throw new DukeException("invalid task index");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("non-integer input");
        }
    }
    private static Command parseFind(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException("empty search term");
        } else {
            return new FindCommand(input);
        }
    }
    private static Command parseToDo(String input) throws DukeException {
        assert !input.isEmpty() : "Empty toDo event input";
        if (input.trim().isEmpty()) {
            throw new DukeException("empty todo description");
        } else {
            return new AddTodoCommand(input.trim());
        }
    }

    private static Command parseDeadline(String input) throws DukeException {
        String dateAndTime;
        Object dateTimeObj;
        int recurrenceNumber = 0;
        String recurrenceType = " ";
        boolean isRecurring = true;
        assert !input.isEmpty() : "Empty deadline event input";
        if (!input.contains("/by")) {
            throw new DukeException("empty deadline deadline");
        }
        String description = input.split("/by", 2)[0].trim();
        String afterDescription = input.split("/by", 2)[1].trim();
        if (description.isEmpty()) {
            throw new DukeException("empty deadline description");
        } else if (afterDescription.isEmpty()) {
            throw new DukeException("empty deadline deadline");
        }

        if (afterDescription.contains("/daily")) {
            recurrenceType = "/daily";
        } else if (afterDescription.contains("/weekly")) {
            recurrenceType = "/weekly";
        } else {
            isRecurring = false;
        }
        if (isRecurring) {
            recurrenceNumber = parseRecurrence(afterDescription, recurrenceType);
            dateAndTime = afterDescription.split(recurrenceType, 2)[0].trim();
        } else {
            dateAndTime = afterDescription;
        }
        if (isDateTime(dateAndTime)) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern(detectedFormat);
            dateTimeObj = LocalDateTime.parse(dateAndTime, format);
        } else if (isDate(dateAndTime)) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern(detectedFormat);
            dateTimeObj = LocalDate.parse(dateAndTime, format);
        } else {
            throw new DukeException("invalid deadline");
        }

        if (isRecurring) {
            return new AddDeadlineCommand(description, dateTimeObj, recurrenceType, recurrenceNumber);
        }
        return new AddDeadlineCommand(description, dateTimeObj);
    }
    private static int parseRecurrence(String input, String recurrenceType) throws DukeException {
        String maybeRecurrenceNumber = input.split(recurrenceType, 2)[1].trim();
        if (maybeRecurrenceNumber.isEmpty()) {
            throw new DukeException("empty recurrence number");
        }
        try {
            return Integer.parseInt(maybeRecurrenceNumber);
        } catch (NumberFormatException e) {
            throw new DukeException("non-integer input");
        }
    }
    /**
     * This method reads the stored String of text file in the Hard Disk which corresponds to a
     * Deadline type task and transforms it into a Deadline task object.
     *
     * @param input String from text file.
     * @return Task that is of Deadline type.
     */
    public static Task parseDeadlineFromFile(String input) {
        String description = input.split("\\(", 2)[0].trim();
        String returnDate = input.substring(input.indexOf(":") + 2, input.indexOf(")"));

        if (isDateTime(returnDate)) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern(detectedFormat);
            LocalDateTime dateTimeObj = LocalDateTime.parse(returnDate, format);
            return new Deadline(description, dateTimeObj);
        } else {
            isDate(returnDate);
            DateTimeFormatter format = DateTimeFormatter.ofPattern(detectedFormat);
            LocalDate dateObj = LocalDate.parse(returnDate, format);
            return new Deadline(description, dateObj);
        }
    }

    private static Command parseEvent(String input) throws DukeException {
        assert !input.isEmpty() : "Empty event event input";
        if (!input.contains("/at")) {
            throw new DukeException("empty event duration");
        }
        String description = input.split("/at", 2)[0].trim();
        String dateAndTimeDuration = input.split("/at", 2)[1].trim();
        if (description.isEmpty()) {
            throw new DukeException("empty event description");
        }
        if (!dateAndTimeDuration.contains(" ")) {
            throw new DukeException("empty event duration");
        }
        String date = dateAndTimeDuration.split(" ", 2)[0];
        String timeDuration = dateAndTimeDuration.split(" ", 2)[1];
        if (!isDate(date)) {
            throw new DukeException("invalid event date");
        }
        LocalDate eventDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(detectedFormat));
        if (isDuration(timeDuration)) {
            return new AddEventCommand(description, eventDate, startTime, endTime);
        } else {
            throw new DukeException("invalid event time");
        }
    }
    /**
     * This method reads the stored String of text file in the Hard Disk which corresponds to an
     * Event type task and transforms it into an Event task object.
     *
     * @param input String from text file.
     * @return Task that is of Event type.
     */
    public static Task parseEventFromFile(String input) {
        String description = input.split("\\(", 2)[0].trim();
        String dateTimeDuration = input.substring(input.indexOf(":") + 2, input.indexOf(")"));
        String date = dateTimeDuration.split(" from: ")[0];
        String timeDuration = dateTimeDuration.split(" from: ")[1];

        isDate(date);
        LocalDate eventDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(detectedFormat));
        isDuration(timeDuration);
        return new Event(description, eventDate, startTime, endTime);
    }

    private static boolean isDateTime(String dateTimeString) {
        boolean isDateAndTime = false;
        assert !dateTimeString.isEmpty() : "Empty dateTime input";

        for (String i : dateTimeFormats) {
            try {
                LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(i));
                detectedFormat = i;
                isDateAndTime = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return isDateAndTime;
    }

    private static boolean isDate(String dateString) {
        boolean isDate = false;
        assert !dateString.isEmpty() : "Empty dateString input";

        for (String i : dateFormats) {
            try {
                LocalDate.parse(dateString, DateTimeFormatter.ofPattern(i));
                detectedFormat = i;
                isDate = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return isDate;
    }

    private static boolean isDuration (String duration) {
        assert !duration.isEmpty() : "Empty duration input";
        if (duration.contains("-")) {
            String start = duration.split("-", 2)[0];
            String end = duration.split("-", 2)[1];
            boolean isStartATime = false;
            boolean isEndATime = false;
            for (String i : timeFormats) {
                try {
                    startTime = LocalTime.parse(start, DateTimeFormatter.ofPattern(i));
                    isStartATime = true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            for (String i : timeFormats) {
                try {
                    endTime = LocalTime.parse(end, DateTimeFormatter.ofPattern(i));
                    isEndATime = true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            return isStartATime && isEndATime;
        } else {
            return false;
        }
    }
}
