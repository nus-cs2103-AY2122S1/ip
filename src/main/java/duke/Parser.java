package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class for parsing keywords, and handling unparsed Strings.
 */
public class Parser {
    Parser() {
    }

    /**
     * Parses keywords.
     * @param str String to be parsed.
     * @return String of keyword.
     * @throws DukeException Occurs when anything goes wrong during method.
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
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * Joins together String for find keyword.
     * @param strparse Array of Strings to be joined together.
     * @return Keyword to be found.
     */
    public String parseFind(String[] strparse) {
        StringBuilder strb = new StringBuilder();
        for (int i = 1; i < strparse.length; i++) {
            strb.append(strparse[i]);
            if (i != strparse.length - 1) {
                strb.append(" ");
            }
        }
        return strb.toString();
    }

    /**
     * Joins Array of Strings together without the keyword todo at the start.
     * @param strparse Array of Strings to be parsed.
     * @return Todo task.
     * @throws DukeException Errors that occur during parsing (incorrect commands, etc.).
     */
    public Todo parseTodo(String[] strparse) throws DukeException {
        StringBuilder taskb = new StringBuilder();
        if (strparse.length == 1) {
            throw new IncorrectInputException("todo", "using 'todo (taskw)'");
        }
        for (int i = 1; i < strparse.length; i++) {
            taskb.append(strparse[i]);
            if (i != strparse.length - 1) {
                taskb.append(" ");
            }
        }
        return new Todo(taskb.toString());
    }

    /**
     * Examines String[] and produces a Deadline from it.
     * @param strparse Array of Strings to be parsed.
     * @return Deadline Task.
     * @throws DukeException Errors that occur during parsing (incorrect commands, etc.).
     */
    public Deadline parseDeadline(String[] strparse) throws DukeException {
        try {
            if (strparse.length == 1) {
                throw new MissingInputException("deadline");
            }
            StringBuilder strb = new StringBuilder();
            int i = 1;
            while (i < strparse.length
                    && !strparse[i].equalsIgnoreCase("/by")) {
                strb.append(strparse[i]);
                if (i < strparse.length - 1
                        && !strparse[i + 1].equalsIgnoreCase("/by")) {
                    strb.append(" ");
                }
                i++;
            }
            i++;
            if (strb.toString().equals("") || (i != strparse.length - 1 && i != strparse.length - 2)) {
                throw new IncorrectInputException("deadline",
                        "using 'deadline (task) /by yyyy-mm-dd (date) xx:xx (time, optional)'");
            }
            LocalDate date = LocalDate.parse(strparse[i++]);
            if (i == strparse.length - 1) {
                LocalTime time = LocalTime.parse(strparse[i]);
                return new Deadline(strb.toString(), date, time);
            } else {
                return new Deadline(strb.toString(), date);
            }
        } catch (DateTimeParseException e) {
            throw new IncorrectInputException("deadline",
                    "using 'deadline (task) /by yyyy-mm-dd (date) xx:xx (time, optional)'");
        }
    }

    public Event parseEvent(String[] strparse) throws DukeException {
        try {
            if (strparse.length == 1) {
                throw new MissingInputException("event");
            }
            StringBuilder strb = new StringBuilder();
            int i = 1;
            while (i < strparse.length
                    && !strparse[i].equalsIgnoreCase("/at")) {
                strb.append(strparse[i]);
                if (i < strparse.length - 1
                        && !strparse[i + 1].equalsIgnoreCase("/at")) {
                    strb.append(" ");
                }
                i++;
            }
            i++;
            if (strb.toString().equals("") || (i != strparse.length - 1 && i != strparse.length - 2)) {
                throw new IncorrectInputException("event",
                        "using 'event (task) /at yyyy-mm-dd (date) xx:xx (time, optional)'");            }
            LocalDate date = LocalDate.parse(strparse[i++]);
            if (i == strparse.length - 1) {
                LocalTime time = LocalTime.parse(strparse[i]);
                return new Event(strb.toString(), date, time);
            } else {
                return new Event(strb.toString(), date);
            }
        } catch (DateTimeParseException e) {
            throw new IncorrectInputException("event",
                    "using 'event (task) /at yyyy-mm-dd (date) xx:xx (time, optional)'");
        }
    }

    /**
     * Converts date to readable String format.
     * @param date Date to be converted.
     * @return String of readable Date.
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
        String AmOrPm;
        if (hour < 12) {
            hourInString = String.valueOf(hour);
            AmOrPm = "am";
        } else {
            hourInString = String.valueOf(hour - 12);
            AmOrPm = "pm";
        }
        if (minute < 10) {
            minuteInString = "0" + String.valueOf(minute);
        } else {
            minuteInString = String.valueOf(minute);
        }
        return hourInString + "." + minuteInString + AmOrPm;
    }
}
