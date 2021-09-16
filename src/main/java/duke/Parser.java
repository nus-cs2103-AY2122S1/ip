package duke;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * The class that Parses the command line from user.
 *
 * @author Luo Zhijie
 */
public class Parser {
    private String splitRegex;

    public Parser(String splitRegex) {
        this.splitRegex = splitRegex;
    }

    /**
     * Parses the input string from user to meaning command.
     *
     * @param input input string from user.
     * @return Command got from input string.
     * @throws DukeException Exception that duke could generate.
     */
    public Command parse(String input) throws DukeException {
        String[] commands = input.split(splitRegex);
        if (commands[0].equals("list")) {
            return new ListCommand();
        } else if (commands[0].equals("done")) {
            assert (commands.length > 1);
            int doneIndex = Integer.parseInt(commands[1]);
            return new MarkAsDoneCommand(doneIndex - 1);
        } else if (commands[0].equals("delete")) {
            assert (commands.length > 1);
            int deleteIndex = Integer.parseInt(commands[1]);
            return new DeleteCommand(deleteIndex - 1);
        } else if (commands[0].equals("find")) {
            int keywordStartIndex = 5;
            assert (input.length() >= keywordStartIndex);
            String keywords = input.substring(keywordStartIndex);
            return new FindCommand(keywords);
        } else if (commands[0].equals("bye")) {
            return new ExitCommand();
        } else if (commands[0].equals("todo")) {
            Task task = parseToDo(input);
            return new AddCommand(task);
        } else if (commands[0].equals("deadline")) {
            Task task = parseDeadline(input);
            return new AddCommand(task);
        } else if (commands[0].equals("event")) {
            Task task = parseEvent(input);
            return new AddCommand(task);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses the input string to ToDo task.
     *
     * @param input Input string from user.
     * @return ToDo task.
     * @throws ParserException Exception generated while parsing the string.
     */
    private Task parseToDo(String input) throws ParserException {
        if (input.split("todo ").length < 1) {
            throw new ParserException("The description of a todo cannot be empty.");
        }
        String content = input.split("todo ")[1];
        if (content.contains("/reminder ")) {
            if (content.split("/reminder ").length < 1 || content.split("/reminder ")[0].length() == 0) {
                throw new ParserException("The description of a todo cannot be empty.");
            }
            String description = content.split("/reminder ")[0];
            String reminderTime = content.split("/reminder ")[1];
            try {
                return new ToDo(description, reminderTime);
            } catch (DateTimeParseException e) {
                throw new ParserException("Invalid date, "
                        + "please enter a valid date in the format: "
                        + "yyyy/MM/dd HH:mm");
            }
        } else {
            return new ToDo(content);
        }
    }

    /**
     * Parses the input string to Deadline task.
     *
     * @param input Input string from user.
     * @return Deadline task.
     * @throws ParserException Exception generated while parsing the string.
     */
    private Task parseDeadline(String input) throws ParserException {
        assert (input.contains("deadline "));
        String content = input.split("deadline ")[1];
        String description = content.substring(0, content.indexOf("/by"));
        if (description.length() == 0) {
            throw new ParserException("The description of deadline task cannot be empty.");
        }
        int deadlineStartIndex = content.indexOf("/by") + 4;
        int deadlineEndIndex = content.indexOf("/by") + 19;
        if (deadlineEndIndex >= content.length()) {
            throw new ParserException("please enter a valid time in the format: "
                    + "yyyy/MM/dd HH:mm");
        }
        String deadline = content.substring(deadlineStartIndex, deadlineEndIndex + 1);
        String reminderTime = "";
        if (content.contains("/reminder ")) {
            int reminderTimeStartIndex = content.indexOf("/reminder ") + 10;
            int reminderTimeEndIndex = content.indexOf("/reminder ") + 25;
            if (reminderTimeEndIndex >= content.length()) {
                throw new ParserException("please enter a valid time in the format: "
                        + "yyyy/MM/dd HH:mm");
            }
            reminderTime = content.substring(reminderTimeStartIndex, reminderTimeEndIndex + 1);
        }
        try {
            if (reminderTime.equals("")) {
                return new Deadline(description, deadline);
            }
            return new Deadline(description, deadline, reminderTime);
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
    private Task parseEvent(String input) throws ParserException {

        /* Get event description */
        assert (input.contains("event "));
        String content = input.split("event ")[1];
        String description = content.substring(0, content.indexOf("/at"));
        if (description.length() == 0) {
            throw new ParserException("The description of event cannot be empty.");
        }

        System.out.println("hello");
        /* Get event time */
        int eventStartIndex = content.indexOf("/at") + 4;
        int eventEndIndex = content.indexOf("/at") + 37;
        if (eventEndIndex >= content.length()) {
            throw new ParserException("please enter a valid time in the format: "
                    + "yyyy/MM/dd HH:mm--yyyy/MM/dd HH:mm");
        }
        String eventPeriod = content.substring(eventStartIndex, eventEndIndex + 1);

        /* Get reminder time */
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

        try {
            String[] startingEndingTime = eventPeriod.split("--");
            String from = startingEndingTime[0];
            String to = startingEndingTime[1];
            if (reminderTime.equals("")) {
                return new Event(description, from, to);
            }
            return new Event(description, from, to, reminderTime);
        } catch (DateTimeParseException e) {
            throw new ParserException("Invalid date, "
                    + "please enter a valid time period in the format: "
                    + "yyyy/MM/dd HH:mm");
        }
    }
}
