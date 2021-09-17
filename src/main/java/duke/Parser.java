package duke;

import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkAsDoneCommand;
import duke.exception.DukeException;
import duke.exception.ParserException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


/**
 * The class that Parses the command line from user.
 *
 * @author Luo Zhijie
 */
public class Parser {

    /**
     * Parses the input string from user to meaning command.
     *
     * @param input input string from user.
     * @return Command got from input string.
     * @throws DukeException Exception that duke could generate.
     */
    public Command parse(String input) throws DukeException {
        String[] commands = input.split(" ");
        if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("done")) {
            return parseMarkAsDoneCommand(input);
        } else if (input.startsWith("delete")) {
            return parseDeleteCommand(input);
        } else if (input.startsWith("find")) {
            return parseFindCommand(input);
        } else if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.startsWith("todo")) {
            return parseToDo(input);
        } else if (input.startsWith("deadline")) {
            return parseDeadline(input);
        } else if (input.startsWith("event")) {
            return parseEvent(input);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private Command parseMarkAsDoneCommand(String input) throws DukeException {
        try {
            int contentStartIndex = 5;
            int taskIndex = Integer.parseInt(input.substring(contentStartIndex));
            return new MarkAsDoneCommand(taskIndex - 1);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please enter a number for task index");
        }
    }

    private Command parseDeleteCommand(String input) throws DukeException {
        try {
            int contentStartIndex = 7;
            int deleteIndex = Integer.parseInt(input.substring(contentStartIndex));
            return new DeleteCommand(deleteIndex - 1);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please enter a number for task index");
        }
    }

    private Command parseFindCommand(String input) throws DukeException {
        if (input.equals("find")) {
            throw new DukeException("Please enter some keywords.");
        }
        int keywordStartIndex = 5;
        String keywords = input.substring(keywordStartIndex);
        return new FindCommand(keywords);
    }

    /**
     * Parses the input string to ToDo task.
     *
     * @param input Input string from user.
     * @return ToDo task.
     * @throws ParserException Exception generated while parsing the string.
     */
    private Command parseToDo(String input) throws ParserException {
        if (input.split("todo ").length < 2) {
            throw new ParserException("The description of a todo cannot be empty.");
        }

        String content = input.split("todo ")[1];
        if (content.contains("/reminder ")) {
            if (content.split("/reminder ").length < 2 || content.split("/reminder ")[0].length() == 0) {
                throw new ParserException("Please enter the reminder time");
            }
            String description = content.split("/reminder ")[0];
            String reminderTime = content.split("/reminder ")[1];
            try {
                Task task = new ToDo(description, reminderTime);
                return new AddCommand(task);
            } catch (DateTimeParseException e) {
                throw new ParserException("Invalid date, "
                        + "please enter a valid date in the format: "
                        + "yyyy/MM/dd HH:mm");
            }
        } else {
            Task task = new ToDo(content);
            return new AddCommand(task);
        }
    }

    /**
     * Parses the input string to Deadline task.
     *
     * @param input Input string from user.
     * @return Deadline task.
     * @throws ParserException Exception generated while parsing the string.
     */
    private Command parseDeadline(String input) throws ParserException {
        /* Gets description */
        if (input.split("deadline ").length < 2) {
            throw new ParserException("The description of a deadline cannot be empty.");
        }
        String content = input.split("deadline ")[1];
        if (!content.contains("/by")) {
            throw new ParserException("Please enter /by followed by a deadline.");
        }
        String description = content.substring(0, content.indexOf("/by"));
        if (description.equals("")) {
            throw new ParserException("The description of a deadline cannot be empty.");
        }

        /* Gets deadline */
        int deadlineStartIndex = content.indexOf("/by") + 4;
        int deadlineEndIndex = content.indexOf("/by") + 19;
        if (deadlineEndIndex >= content.length()) {
            throw new ParserException("Invalid date, "
                    + "please enter a valid time period in the format: "
                    + "yyyy/MM/dd HH:mm");
        }
        String deadline = content.substring(deadlineStartIndex, deadlineEndIndex + 1);

        /* Gets reminder time */
        String reminderTime = "";
        if (content.contains("/reminder ")) {
            int reminderTimeStartIndex = content.indexOf("/reminder ") + 10;
            int reminderTimeEndIndex = content.indexOf("/reminder ") + 25;
            if (reminderTimeEndIndex >= content.length()) {
                throw new ParserException("Invalid date, "
                        + "please enter a valid time period in the format: "
                        + "yyyy/MM/dd HH:mm");
            }
            reminderTime = content.substring(reminderTimeStartIndex, reminderTimeEndIndex + 1);
        }
        /* Creates deadline task */
        try {
            Task task;
            if (reminderTime.equals("")) {
                task = new Deadline(description, deadline);
                return new AddCommand(task);
            }
            task = new Deadline(description, deadline, reminderTime);
            return new AddCommand(task);
        } catch (DateTimeParseException e) {
            throw new ParserException("Invalid date, "
                    + "please enter a valid time period in the format: "
                    + "yyyy/MM/dd HH:mm");
        }
    }

    /**
     * Parses the input string to Event task.
     *
     * @param input Input string from user.
     * @return Event task.
     * @throws ParserException Exception generated while parsing the string.
     */
    private Command parseEvent(String input) throws ParserException {
        /* Gets event description */
        if (input.split("event ").length < 2) {
            throw new ParserException("The description of an event cannot be empty.");
        }
        String content = input.split("event ")[1];
        if (!content.contains("/at")) {
            throw new ParserException("Please enter /at followed by an event period.");
        }
        String description = content.substring(0, content.indexOf("/at"));
        if (description.equals("")) {
            throw new ParserException("The description of an event cannot be empty.");
        }

        /* Gets event time */
        int eventStartIndex = content.indexOf("/at") + 4;
        int eventEndIndex = content.indexOf("/at") + 37;
        if (eventEndIndex >= content.length()) {
            throw new ParserException("please enter a valid time in the format: "
                    + "yyyy/MM/dd HH:mm--yyyy/MM/dd HH:mm");
        }
        String eventPeriod = content.substring(eventStartIndex, eventEndIndex + 1);

        /* Gets reminder time */
        String reminderTime = "";
        if (content.contains("/reminder ")) {
            int reminderTimeStartIndex = content.indexOf("/reminder ") + 10;
            int reminderTimeEndIndex = content.indexOf("/reminder ") + 25;
            if (reminderTimeEndIndex >= content.length()) {
                throw new ParserException("please enter a valid time in the format: "
                        + "yyyy/MM/dd HH:mm--yyyy/MM/dd HH:mm");
            }
            reminderTime = content.substring(reminderTimeStartIndex, reminderTimeEndIndex + 1);
        }

        /* Creates an event task */
        try {
            Task task;
            String[] startingEndingTime = eventPeriod.split("--");
            String from = startingEndingTime[0];
            String to = startingEndingTime[1];
            if (reminderTime.equals("")) {
                task = new Event(description, from, to);
                return new AddCommand(task);
            }
            task = new Event(description, from, to, reminderTime);
            return new AddCommand(task);
        } catch (DateTimeParseException e) {
            throw new ParserException("Invalid date, "
                    + "please enter a valid time period in the format: "
                    + "yyyy/MM/dd HH:mm");
        }
    }
}
