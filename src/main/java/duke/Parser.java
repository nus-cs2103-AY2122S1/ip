package duke;

import java.time.format.DateTimeParseException;

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
        String[] command = input.split(splitRegex);
        if (command.length <= 1) {
            throw new ParserException("The description of a todo "
                    + "cannot be empty.");
        }
        int descriptionStartIndex = 5;
        assert (input.length() > descriptionStartIndex);
        return new ToDo(input.substring(descriptionStartIndex));
    }

    /**
     * Parses the input string to Deadline task.
     *
     * @param input Input string from user.
     * @return Deadline task.
     * @throws ParserException Exception generated while parsing the string.
     */
    private Task parseDeadline(String input) throws ParserException {
        String[] command = input.split(splitRegex);
        if (command.length <= 1 || command[1].equals("/by")) {
            throw new ParserException("The description of deadline task "
                    + "cannot be empty.");
        }
        int deadlineStartIndex = input.indexOf("/by") + 4;
        boolean isDeadlineEmpty = deadlineStartIndex >= input.length();
        if (!input.contains("/by") || isDeadlineEmpty) {
            throw new ParserException("Please enter '/by' followed by a task deadline.");
        }
        int descriptionStartIndex = 9;
        int descriptionEndIndex = input.indexOf("/by") - 1;
        assert (input.length() > descriptionStartIndex);
        String content = input.substring(descriptionStartIndex, descriptionEndIndex);
        String by = input.substring(deadlineStartIndex);
        try {
            return new Deadline(content, by);
        } catch (DateTimeParseException e) {
            throw new ParserException("Invalid date, "
                    + "please enter a valid date in the format: "
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
        String[] command = input.split(splitRegex);
        if (command.length <= 1 || command[1].equals("/at")) {
            throw new ParserException("The description of event "
                    + "cannot be empty.");
        }
        int eventStartIndex = input.indexOf("/at") + 4;
        boolean isEventTimeEmpty = eventStartIndex >= input.length();
        if (!input.contains("/at") || isEventTimeEmpty) {
            throw new ParserException("Please enter '/at' followed by an event time.");
        }
        int descriptionStartIndex = 6;
        int descriptionEndIndex = input.indexOf("/at") - 1;
        assert (input.length() > descriptionStartIndex);
        String content = input.substring(descriptionStartIndex, descriptionEndIndex);
        String at = input.substring(eventStartIndex);
        try {
            return new Event(content, at);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new ParserException("Invalid date, "
                    + "please enter a valid time period in the format: "
                    + "yyyy/MM/dd HH:mm--yyyy/MM/dd HH:mm");
        }
    }
}
