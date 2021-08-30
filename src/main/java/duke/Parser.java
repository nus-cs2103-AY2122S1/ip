package duke;

import java.time.LocalDate;
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
        for (int i = 1; i < strparse.length; i ++) {
            strb.append(strparse[i]);
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
                if (i != strparse.length - 1) {
                    strb.append(" ");
                }
                i++;
            }
            i++;
            if (strb.toString().equals("") || i != strparse.length - 1) {
                throw new IncorrectInputException("deadline", "using 'deadline (task) /by (yyyy-mm-dd format)'");
            }
            LocalDate deadline = LocalDate.parse(strparse[i]);
            return new Deadline(strb.toString(), deadline);
        } catch (DateTimeParseException e) {
            throw new IncorrectInputException("deadline", "using 'deadline (task) /by (yyyy-mm-dd format)'");
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
                if (i != strparse.length - 1) {
                    strb.append(" ");
                }
                i++;
            }
            i++;
            if (strb.toString().equals("") || i != strparse.length - 1) {
                throw new IncorrectInputException("event", "'event (event) /at (date)'");
            }
            LocalDate at = LocalDate.parse(strparse[i]);
            return new Event(strb.toString(), at);
        } catch (DateTimeParseException e) {
            throw new IncorrectInputException("deadline", "a cowwect (yyyy-mm-dd format)'");
        }

    }

}
