package duke;

import duke.exceptions.DukeException;
import duke.exceptions.IncorrectInputException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.MissingInputException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class for parsing keywords, and handling unparsed Strings.
 */
public class Parser {

    /**
     * Initialises Parser.
     */
    public Parser() {
    }

    /**
     * Parses keywords.
     * @param str String to be parsed
     * @return String of keyword
     * @throws DukeException Occurs when anything goes wrong during method
     */
    public String parseCommand(String str) throws DukeException {
        if (str.equalsIgnoreCase("bye")) {
            return "bye";
        } else if (str.equalsIgnoreCase("help")) {
            return "help";
        } else if (str.equalsIgnoreCase("list")) {
            return "list";
        } else if (str.equalsIgnoreCase("todo")) {
            return "todo";
        } else if (str.equalsIgnoreCase("deadline")) {
            return "deadline";
        } else if (str.equalsIgnoreCase("event")) {
            return "event";
        } else if (str.equalsIgnoreCase("done")) {
            return "done";
        } else if (str.equalsIgnoreCase("delete")) {
            return "delete";
        } else if (str.equalsIgnoreCase("find")) {
            return "find";
        } else if (str.equalsIgnoreCase("clearall")) {
            return "clearall";
        } else if (str.equalsIgnoreCase("y")) {
            return "y";
        } else if (str.equalsIgnoreCase("n")) {
            return "n";
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * Joins together String for find keyword.
     * @param strParse Array of Strings to be joined together
     * @return Keyword to be found
     */
    public String parseFind(String[] strParse) {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 1; i < strParse.length; i++) {
            strBuilder.append(strParse[i]);
            if (i != strParse.length - 1) {
                strBuilder.append(" ");
            }
        }
        return strBuilder.toString();
    }

    /**
     * Joins Array of Strings together without the keyword Todo at the start.
     * @param strParse Array of Strings to be parsed
     * @return Todo task
     * @throws DukeException Errors that occur during parsing (incorrect commands, etc.)
     */
    public Todo parseTodo(String[] strParse) throws DukeException {
        StringBuilder taskb = new StringBuilder();
        if (strParse.length == 1) {
            throw new IncorrectInputException("Todo", "using 'todo feed my cat'");
        }
        for (int i = 1; i < strParse.length; i++) {
            taskb.append(strParse[i]);
            if (i != strParse.length - 1) {
                taskb.append(" ");
            }
        }
        return new Todo(taskb.toString());
    }

    /**
     * Examines String[] and produces a Deadline from it.
     * @param strParse Array of Strings to be parsed
     * @return Deadline Task
     * @throws DukeException Errors that occur during parsing (incorrect commands, etc.)
     */
    public Deadline parseDeadline(String[] strParse) throws DukeException {
        try {
            if (strParse.length == 1) {
                throw new MissingInputException("deadline");
            }
            StringBuilder strBuilder = new StringBuilder();
            int i = 1;
            while (i < strParse.length
                    && !strParse[i].equalsIgnoreCase("/by")) {
                strBuilder.append(strParse[i]);
                if (i < strParse.length - 1
                        && !strParse[i + 1].equalsIgnoreCase("/by")) {
                    strBuilder.append(" ");
                }
                i++;
            }
            i++;
            if (strBuilder.toString().equals("") || (i != strParse.length - 1 && i != strParse.length - 2)) {
                throw new IncorrectInputException("deadline",
                        "using 'deadline feed my cat /by 2020-01-01 10:30'. Time is optional!");
            }
            assert strParse[i - 1].equalsIgnoreCase("/by") : "Deadline missing /by keyword";
            LocalDate date = LocalDate.parse(strParse[i++]);
            if (i == strParse.length - 1) {
                LocalTime time = LocalTime.parse(strParse[i]);
                return new Deadline(strBuilder.toString(), date, time);
            } else {
                return new Deadline(strBuilder.toString(), date);
            }
        } catch (DateTimeParseException e) {
            throw new IncorrectInputException("deadline",
                    "using 'deadline feed my cat /by 2020-01-01 10:30'. Time is optional!");
        }
    }

    /**
     * Examines String[] and produces a Event from it.
     * @param strParse Array of Strings to be parsed
     * @return Event task
     * @throws DukeException Errors that occur during parsing (incorrect commands, etc.)
     */
    public Event parseEvent(String[] strParse) throws DukeException {
        try {
            if (strParse.length == 1) {
                throw new MissingInputException("event");
            }
            StringBuilder strBuilder = new StringBuilder();
            int i = 1;
            while (i < strParse.length
                    && !strParse[i].equalsIgnoreCase("/at")) {
                strBuilder.append(strParse[i]);
                if (i < strParse.length - 1
                        && !strParse[i + 1].equalsIgnoreCase("/at")) {
                    strBuilder.append(" ");
                }
                i++;
            }
            i++;
            if (strBuilder.toString().equals("") || (i != strParse.length - 1 && i != strParse.length - 2)) {
                throw new IncorrectInputException("event",
                        "using 'event feed neighbour's cat /at 2020-01-01 08:00'. Time is optional!");
            }
            assert strParse[i - 1].equalsIgnoreCase("/at") : "Event missing /at keyword";
            LocalDate date = LocalDate.parse(strParse[i++]);
            if (i == strParse.length - 1) {
                LocalTime time = LocalTime.parse(strParse[i]);
                return new Event(strBuilder.toString(), date, time);
            } else {
                return new Event(strBuilder.toString(), date);
            }
        } catch (DateTimeParseException e) {
            throw new IncorrectInputException("event",
                    "using 'event feed neighbour's cat /at 2020-01-01 08:00'. Time is optional!");
        }
    }

    /**
     * Converts date to readable String format.
     * @param date Date to be converted
     * @return String of readable Date
     */
    public String simplifyDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Converts time to readable String format.
     * @param time Time to be converted.
     * @return String of readable time.
     */
    public String simplifyTime(LocalTime time) {
        int hour = time.getHour();
        int minute = time.getMinute();
        String hourInString;
        String minuteInString;
        String amOrPm;
        if (hour < 12) {
            hourInString = String.valueOf(hour);
            amOrPm = "am";
        } else {
            hourInString = String.valueOf(hour - 12);
            amOrPm = "pm";
        }
        if (minute < 10) {
            minuteInString = "0" + String.valueOf(minute);
        } else {
            minuteInString = String.valueOf(minute);
        }
        return hourInString + "." + minuteInString + amOrPm;
    }
}
