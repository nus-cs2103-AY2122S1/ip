package fan.cs2103t.duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import fan.cs2103t.duke.command.AddCommand;
import fan.cs2103t.duke.command.Command;
import fan.cs2103t.duke.command.DeleteCommand;
import fan.cs2103t.duke.command.DoneCommand;
import fan.cs2103t.duke.command.ExitCommand;
import fan.cs2103t.duke.command.FindCommand;
import fan.cs2103t.duke.command.ListCommand;
import fan.cs2103t.duke.exception.DukeException;
import fan.cs2103t.duke.task.Deadline;
import fan.cs2103t.duke.task.Event;
import fan.cs2103t.duke.task.Task;
import fan.cs2103t.duke.task.Todo;
import fan.cs2103t.duke.ui.Ui;

/**
 * Represents a parser for Duke to parse input from users.
 */
public class Parser {

    private static final String INVALID_INPUT = Ui.INVALID_INPUT;
    private static final String SPACE = Ui.SPACE;

    /**
     * Parses the specified input from users.
     * Returns the corresponding command interpreted from the specified input.
     *
     * @param input the one-line input entered by users.
     * @return the corresponding command interpreted from the specified input.
     * @throws DukeException if the specified input cannot be interpreted as any existing Duke's command.
     */
    public Command parseCommand(String input) throws DukeException {
        input = input.trim(); // to remove all leading and trailing space of user's input
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("todo")) {
            try {
                Task t = parseTodo(input);
                return new AddCommand(t);
            } catch (DukeException e) {
                String errorMessage = e.getMessage();
                if (errorMessage.equals("wrong input format")) {
                    throw new DukeException(INVALID_INPUT);
                } else if (errorMessage.equals("empty todo description")) {
                    throw new DukeException(SPACE + "OOPS!!! The description of a todo cannot be empty.");
                }
            }
        } else if (input.startsWith("deadline")) {
            try {
                Task t = parseDeadline(input);
                return new AddCommand(t);
            } catch (DukeException e) {
                String errorMessage = e.getMessage();
                switch (errorMessage) {
                case "wrong input format":
                    throw new DukeException(INVALID_INPUT);
                case "empty deadline description":
                    throw new DukeException(SPACE + "OOPS!!! The description of a deadline cannot be empty.");
                case "empty deadline by":
                    throw new DukeException(SPACE + "OOPS!!! The deadline of a deadline cannot be empty.");
                case "wrong deadline format":
                    throw new DukeException(SPACE + "OOPS!!! The deadline is in the wrong format.");
                default:
                }
            }
        } else if (input.startsWith("event")) {
            try {
                Task t = parseEvent(input);
                return new AddCommand(t);
            } catch (DukeException e) {
                String errorMessage = e.getMessage();
                switch (errorMessage) {
                case "wrong input format":
                    throw new DukeException(INVALID_INPUT);
                case "empty event description":
                    throw new DukeException(SPACE + "OOPS!!! The description of an event cannot be empty.");
                case "empty event at":
                    throw new DukeException(SPACE + "OOPS!!! The start/end time of an event cannot be empty.");
                default:
                }
            }
        } else if (input.startsWith("done")) {
            if (input.equals("done")) {
                throw new DukeException(SPACE + "OOPS!!! Please tell me which task you have done.");
            }
            if (input.charAt(4) == ' ' && isValidNum(input.substring(5))) {
                return new DoneCommand(Integer.parseInt(input.substring(5).trim()));
            } else {
                throw new DukeException(INVALID_INPUT);
            }
        } else if (input.startsWith("delete")) {
            if (input.equals("delete")) {
                throw new DukeException(SPACE + "OOPS!!! Please tell me which task you want to delete.");
            }
            if (input.charAt(6) == ' ' && isValidNum(input.substring(7))) {
                return new DeleteCommand(Integer.parseInt(input.substring(7).trim()));
            } else {
                throw new DukeException(INVALID_INPUT);
            }
        } else if (input.startsWith("find")) {
            if (input.equals("find")) {
                throw new DukeException(SPACE + "OOPS!!! The search query cannot be empty.");
            }
            if (input.charAt(4) == ' ') {
                return new FindCommand(input.substring(5).trim());
            } else {
                throw new DukeException(INVALID_INPUT);
            }
        } else {
            throw new DukeException(INVALID_INPUT);
        }
        throw new DukeException(INVALID_INPUT);
    }

    private boolean isValidNum(String s) {
        try {
            Integer.parseInt(s.trim());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private Todo parseTodo(String s) throws DukeException {
        String temp;
        try {
            if (s.charAt(4) != ' ') {
                throw new DukeException("wrong input format");
            }
            temp = s.substring(5);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("empty todo description");
        }
        temp = temp.trim();
        if (temp.equals("")) {
            throw new DukeException("empty todo description");
        }
        return new Todo(temp);
    }

    private Deadline parseDeadline(String s) throws DukeException {
        String temp;
        String description;
        String by;
        LocalDate deadline;

        try {
            if (s.charAt(8) != ' ') {
                throw new DukeException("wrong input format");
            }
            temp = s.substring(9);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("empty deadline description");
        }
        temp = temp.trim();

        int m = temp.lastIndexOf("/by");
        if (m == -1) {
            throw new DukeException("wrong input format");
        } else {
            description = temp.substring(0, m);
            description = description.trim();
            if (description.equals("")) {
                throw new DukeException("empty deadline description");
            }

            by = temp.substring(m);
            try {
                if (by.charAt(3) != ' ') {
                    throw new DukeException("wrong input format");
                }
                by = by.substring(4);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("empty deadline by");
            }
            by = by.trim();
            if (by.equals("")) {
                throw new DukeException("empty deadline by");
            }

            try {
                deadline = LocalDate.parse(by);
            } catch (DateTimeParseException e) {
                throw new DukeException("wrong deadline format");
            }
        }
        return new Deadline(description, deadline);
    }

    private Event parseEvent(String s) {
        String temp;
        String description;
        String at;

        try {
            if (s.charAt(5) != ' ') {
                throw new DukeException("wrong input format");
            }
            temp = s.substring(6);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("empty event description");
        }
        temp = temp.trim();

        int m = temp.lastIndexOf("/at");
        if (m == -1) {
            throw new DukeException("wrong input format");
        } else {
            description = temp.substring(0, m);
            description = description.trim();
            if (description.equals("")) {
                throw new DukeException("empty event description");
            }

            at = temp.substring(m);
            try {
                if (at.charAt(3) != ' ') {
                    throw new DukeException("wrong input format");
                }
                at = at.substring(4);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("empty event at");
            }
            at = at.trim();
            if (at.equals("")) {
                throw new DukeException("empty event at");
            }
        }
        return new Event(description, at);
    }

}
