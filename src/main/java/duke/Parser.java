package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;


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
     * This method takes in the user input as a String and runs checks on it to determine
     * the type of command it is, also throwing exception if the input is invalid.
     * It also takes in a TaskList that Duke bot uses to load and
     * store tasks the user adds or deletes.
     * @param fullCommand String of user input.
     * @param taskList a list to store tasks the user adds or deletes.
     * @return Command which Duke bot can execute afterwards.
     * @throws DukeException if user input cannot be understood.
     */
    public static Command parse(String fullCommand, TaskList taskList) throws DukeException {

        if (fullCommand.startsWith("bye")) {
            return new ExitCommand();
        } else if (fullCommand.startsWith("list")) {
            return new ListCommand();
        } else if (!fullCommand.contains(" ")) {
            throw new DukeException("☹ OOPS!!! Please enter something after the command.");
        } else if (fullCommand.startsWith("done")) {
            try {
                int index = Integer.parseInt(fullCommand.split(" ", 2)[1].trim());

                if (index > 0 && index < taskList.taskCount() + 1) {
                    return new DoneCommand(index);
                } else {
                    throw new DukeException("☹ OOPS!!! Please enter a valid task index.");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! Please enter an integer.");
            }
        } else if (fullCommand.startsWith("delete")) {
            try {
                int index = Integer.parseInt(fullCommand.split(" ", 2)[1].trim());

                if (index > 0 && index < taskList.taskCount() + 1) {
                    return new DeleteCommand(index);
                } else {
                    throw new DukeException("☹ OOPS!!! Please enter a valid task index.");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! Please enter an integer.");
            }
        } else if (fullCommand.startsWith("find")) {
            String searchString = fullCommand.split(" ", 2)[1].trim();

            if (searchString.isEmpty()) {
                throw new DukeException("☹ OOPS!!! Please enter the term you want to search.");
            } else {
                return new FindCommand(searchString);
            }
        } else {
            if (fullCommand.startsWith("todo")) {
                String content = fullCommand.split(" ", 2)[1].trim();
                return parseToDo(content);
            } else if (fullCommand.startsWith("deadline")) {
                String content = fullCommand.split(" ", 2)[1].trim();
                return parseDeadline(content);
            } else if (fullCommand.startsWith("event")) {
                String content = fullCommand.split(" ", 2)[1].trim();
                return parseEvent(content);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    private static Command parseToDo(String input) throws DukeException {

        if (input.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            return new AddCommand(input.trim(), "todo");
        }
    }

    private static Command parseDeadline(String input) throws DukeException {
        if (input.contains("/by")) {
            String description = input.split("/by", 2)[0].trim();
            String dateAndTime = input.split("/by", 2)[1].trim();

            if (description.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            } else if (dateAndTime.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The deadline of a... deadline cannot be empty.");
            }

            if (isDateTime(dateAndTime)) {
                LocalDateTime dateTimeObj = LocalDateTime.parse(dateAndTime,
                        DateTimeFormatter.ofPattern(detectedFormat));
                return new AddCommand(description, dateTimeObj, "deadline");
            } else if (isDate(dateAndTime)) {
                LocalDate dateObj = LocalDate.parse(dateAndTime, DateTimeFormatter.ofPattern(detectedFormat));
                return new AddCommand(description, dateObj, "deadline");
            } else {
                throw new DukeException("☹ OOPS!!! Please enter a valid deadline!");
            }
        } else {
            throw new DukeException("☹ OOPS!!! The deadline of a... deadline cannot be empty.");
        }
    }

    /**
     * This method reads the stored String of text file in the Hard Disk which corresponds to a
     * Deadline type task and transforms it into a Deadline task object.
     * @param input String from text file.
     * @return Task that is of Deadline type.
     */
    public static Task parseDeadlineFromFile(String input) {
        String description = input.split("\\(", 2)[0].trim();
        String returnDate = input.substring(input.indexOf(":") + 2, input.indexOf(")"));

        if (isDateTime(returnDate)) {
            LocalDateTime dateTimeObj = LocalDateTime.parse(returnDate,
                    DateTimeFormatter.ofPattern(detectedFormat));
            return new Deadline(description, dateTimeObj);
        } else {
            isDate(returnDate);
            LocalDate dateObj = LocalDate.parse(returnDate, DateTimeFormatter.ofPattern(detectedFormat));
            return new Deadline(description, dateObj);
        }
    }

    private static Command parseEvent(String input) throws DukeException {
        if (input.contains("/at")) {
            String description = input.split("/at", 2)[0].trim();
            String dateAndTimeDuration = input.split("/at", 2)[1].trim();

            if (description.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            } else if (dateAndTimeDuration.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The duration of an event cannot be empty.");
            }

            if (dateAndTimeDuration.contains(" ")) {
                String date = dateAndTimeDuration.split(" ", 2)[0];
                String timeDuration = dateAndTimeDuration.split(" ", 2)[1];

                if (isDate(date)) {
                    LocalDate eventDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(detectedFormat));

                    if (isDuration(timeDuration)) {
                        return new AddCommand(description, eventDate, startTime, endTime, "event");
                    } else {
                        throw new DukeException("☹ OOPS!!! Please enter a valid time duration!"
                            + " Valid formats are (HHmm-HHmm or hh:mm a-hh:mm a)");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! Please enter a valid date in duration!");
                }
            } else {
                throw new DukeException("☹ OOPS!!! The duration of an event cannot be empty.");
            }
        } else {
            throw new DukeException("☹ OOPS!!! The duration of an event cannot be empty.");
        }
    }

    /**
     * This method reads the stored String of text file in the Hard Disk which corresponds to a
     * Event type task and transforms it into an Event task object.
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

        for (String i : dateTimeFormats) {
            try {
                LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(i));
                detectedFormat = i;
                isDateAndTime = true;
            } catch (Exception e) {
                String exception = e.getMessage();
            }
        }
        return isDateAndTime;
    }

    private static boolean isDate(String dateString) {
        boolean isDate = false;

        for (String i : dateFormats) {
            try {
                LocalDate.parse(dateString, DateTimeFormatter.ofPattern(i));
                detectedFormat = i;
                isDate = true;
            } catch (Exception e) {
                String exception = e.getMessage();
            }
        }
        return isDate;
    }

    private static boolean isDuration (String duration) {
        if (duration.contains("-")) {
            String start = duration.split("-", 2)[0];
            String end = duration.split("-", 2)[1];
            boolean startIsTime = false;
            boolean endIsTime = false;
            for (String i : timeFormats) {
                try {
                    startTime = LocalTime.parse(start, DateTimeFormatter.ofPattern(i));
                    startIsTime = true;
                } catch (Exception e) {
                    String exception = e.getMessage();
                }
            }

            for (String i : timeFormats) {
                try {
                    endTime = LocalTime.parse(end, DateTimeFormatter.ofPattern(i));
                    endIsTime = true;
                } catch (Exception e) {
                    String exception = e.getMessage();
                }
            }

            return startIsTime && endIsTime;
        } else {
            return false;
        }
    }
}

