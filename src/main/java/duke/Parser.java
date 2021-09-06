package duke;

import java.time.format.DateTimeParseException;
import java.util.List;

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
        String[] command = input.split(splitRegex);
        if (command[0].equals("list")) {
            return new ListCommand();
        } else if (command[0].equals("done")) {
            assert (command.length > 1);
            int doneIndex = Integer.parseInt(command[1]);
            return new MarkAsDoneCommand(doneIndex - 1);
        } else if (command[0].equals("delete")) {
            assert (command.length > 1);
            int deleteIndex = Integer.parseInt(command[1]);
            return new DeleteCommand(deleteIndex - 1);
        } else if (command[0].equals("find")) {
            assert (input.length() >= 5);
            String keyWords = input.substring(5);
            return new FindCommand(keyWords);
        } else if (command[0].equals("bye")) {
            return new ExitCommand();
        } else if (command[0].equals("todo")) {
            Task task = parseToDo(input);
            return new AddCommand(task);
        } else if (command[0].equals("deadline")) {
            Task task = parseDeadline(input);
           return new AddCommand(task);
        } else if (command[0].equals("event")) {
            Task task = parseEvent(input);
           return new AddCommand(task);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private Task parseToDo(String input) throws ParserException{
        String[] command = input.split(splitRegex);
        if (command.length <= 1) {
            throw new ParserException("The description of a todo "
                    + "cannot be empty.");
        }
        assert (input.length() > 5);
        return new ToDo(input.substring(5));
    }

    private Task parseDeadline(String input) throws ParserException{
        String[] command = input.split(splitRegex);
        if (command.length <= 1 || command[1].equals("/by")) {
            throw new ParserException("The description of deadline task "
                    + "cannot be empty.");
        }
        boolean isDeadlineEmpty = input.indexOf("/by") + 4 >= input.length();
        if (!input.contains("/by") || isDeadlineEmpty) {
            throw new ParserException("Please enter '/by' followed by a task deadline.");
        }
        assert (input.length() > 9);
        String content = input.substring(9, input.indexOf("/by") - 1);
        String by = input.substring(input.indexOf("/by") + 4);
        try {
            Task task = new Deadline(content, by);
            return task;
        } catch (DateTimeParseException e) {
            throw new ParserException("Invalid date, "
                    + "please enter a valid date in the format: "
                    + "yyyy/MM/dd HH:mm");
        }
    }

    private Task parseEvent(String input) throws ParserException {
        String[] command = input.split(splitRegex);
        if (command.length <= 1 || command[1].equals("/at")) {
            throw new ParserException("The description of event "
                    + "cannot be empty.");
        }
        boolean isEventTimeEmpty = input.indexOf("/at") + 4 >= input.length();
        if (!input.contains("/at") || isEventTimeEmpty) {
            throw new ParserException("Please enter '/at' followed by an event time.");
        }
        assert (input.length() > 6);
        String content = input.substring(6, input.indexOf("/at") - 1);
        String at = input.substring(input.indexOf("/at") + 4);
        try {
            Task task = new Event(content, at);
            return task;
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new ParserException("Invalid date, "
                    + "please enter a valid time period in the format: "
                    + "yyyy/MM/dd HH:mm--yyyy/MM/dd HH:mm");
        }
    }
}
