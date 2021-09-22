package fan.cs2103t.duke.parser;

import static fan.cs2103t.duke.commons.Messages.MESSAGE_EMPTY_DEADLINE_DEADLINE;
import static fan.cs2103t.duke.commons.Messages.MESSAGE_EMPTY_DEADLINE_DESCRIPTION;
import static fan.cs2103t.duke.commons.Messages.MESSAGE_EMPTY_DELETE_COMMAND;
import static fan.cs2103t.duke.commons.Messages.MESSAGE_EMPTY_DONE_COMMAND;
import static fan.cs2103t.duke.commons.Messages.MESSAGE_EMPTY_EVENT_DESCRIPTION;
import static fan.cs2103t.duke.commons.Messages.MESSAGE_EMPTY_EVENT_TIME;
import static fan.cs2103t.duke.commons.Messages.MESSAGE_EMPTY_SEARCH_COMMAND;
import static fan.cs2103t.duke.commons.Messages.MESSAGE_EMPTY_TODO_DESCRIPTION;
import static fan.cs2103t.duke.commons.Messages.MESSAGE_INVALID_INPUT;
import static fan.cs2103t.duke.commons.Messages.MESSAGE_WRONG_DEADLINE_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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

/**
 * Represents a parser for Duke to parse input from users.
 */
public class Parser {

    /**
     * Parses the specified input from users.
     * Returns the corresponding command interpreted from the specified input.
     *
     * @param input the one-line input entered by users.
     * @return the corresponding command interpreted from the specified input.
     * @throws DukeException if the specified input cannot be interpreted as any existing Duke's command.
     */
    public Command parseCommand(String input) throws DukeException {
        input = input.trim(); // to remove all leading and trailing spaces from user's input
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
                    throw new DukeException(MESSAGE_INVALID_INPUT);
                } else if (errorMessage.equals("empty todo description")) {
                    throw new DukeException(MESSAGE_EMPTY_TODO_DESCRIPTION);
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
                    throw new DukeException(MESSAGE_INVALID_INPUT);
                case "empty deadline description":
                    throw new DukeException(MESSAGE_EMPTY_DEADLINE_DESCRIPTION);
                case "empty deadline deadline":
                    throw new DukeException(MESSAGE_EMPTY_DEADLINE_DEADLINE);
                case "wrong deadline format":
                    throw new DukeException(MESSAGE_WRONG_DEADLINE_FORMAT);
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
                    throw new DukeException(MESSAGE_INVALID_INPUT);
                case "empty event description":
                    throw new DukeException(MESSAGE_EMPTY_EVENT_DESCRIPTION);
                case "empty event time":
                    throw new DukeException(MESSAGE_EMPTY_EVENT_TIME);
                default:
                }
            }
        } else if (input.startsWith("done")) {
            if (input.equals("done")) {
                throw new DukeException(MESSAGE_EMPTY_DONE_COMMAND);
            }
            ArrayList<Integer> numbers = areValidNumbers(input.substring(5));
            if (input.charAt(4) == ' ' && numbers != null) {
                return new DoneCommand(numbers);
            } else {
                throw new DukeException(MESSAGE_INVALID_INPUT);
            }
        } else if (input.startsWith("delete")) {
            if (input.equals("delete")) {
                throw new DukeException(MESSAGE_EMPTY_DELETE_COMMAND);
            }
            ArrayList<Integer> numbers = areValidNumbers(input.substring(7));
            if (input.charAt(6) == ' ' && numbers != null) {
                return new DeleteCommand(numbers);
            } else {
                throw new DukeException(MESSAGE_INVALID_INPUT);
            }
        } else if (input.startsWith("find")) {
            if (input.equals("find")) {
                throw new DukeException(MESSAGE_EMPTY_SEARCH_COMMAND);
            }
            if (input.charAt(4) == ' ') {
                return new FindCommand(input.substring(5).trim());
            } else {
                throw new DukeException(MESSAGE_INVALID_INPUT);
            }
        } else {
            throw new DukeException(MESSAGE_INVALID_INPUT);
        }
        throw new DukeException(MESSAGE_INVALID_INPUT);
    }

    private ArrayList<Integer> areValidNumbers(String s) {
        String[] strings = s.trim().split("\\s+");
        ArrayList<Integer> numbers = new ArrayList<>();
        for (String string : strings) {
            Integer i = isValidNum(string);
            if (i == null) {
                return null;
            } else {
                numbers.add(i);
            }
        }
        return numbers;
    }

    private Integer isValidNum(String s) {
        int i;
        try {
            i = Integer.parseInt(s.trim());
        } catch (Exception e) {
            return null;
        }
        return i;
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
                throw new DukeException("empty deadline deadline");
            }
            by = by.trim();
            if (by.equals("")) {
                throw new DukeException("empty deadline deadline");
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
                throw new DukeException("empty event time");
            }
            at = at.trim();
            if (at.equals("")) {
                throw new DukeException("empty event time");
            }
        }
        return new Event(description, at);
    }

}
