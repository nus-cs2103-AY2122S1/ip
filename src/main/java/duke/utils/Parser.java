package duke.utils;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteTaskCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkDoneCommand;
import duke.exceptions.DukeException;
import duke.exceptions.UnknownCommandException;

/** Class to parse user inputs */
public class Parser {

    /**
     * Parses the input to determine what command should be issued.
     *
     * @param input Input string that user types in.
     * @return command Command representing the input.
     * @throws DukeException If any error relating to duke occurs.
     */
    public static Command parseNext(String input) throws DukeException {
        assert (input != null) : "input should not be null.";
        Command command = null;
        if (input.matches("bye")) {
            command = new ByeCommand();
        } else if (input.matches("list")) {
            command = new ListCommand();
        } else if (input.matches("deadline\\s.{1,}\\s\\/by\\s.{1,}")) {
            String[] keywords = extractKeywordsFromCommand(input, new String[] {"deadline", "/by"});
            command = new AddDeadlineCommand(keywords);
        } else if (input.matches("event\\s.{1,}\\s\\/at\\s.{1,}")) {
            String[] keywords = extractKeywordsFromCommand(input, new String[] {"event", "/at"});
            command = new AddEventCommand(keywords);
        } else if (input.matches("todo\\s.{1,}")) {
            String[] keywords = extractKeywordsFromCommand(input, new String[] {"todo"});
            command = new AddTodoCommand(keywords);
        } else if (input.matches("done [0-9]{1,}")) {
            String[] keywords = extractKeywordsFromCommand(input, new String[] {"done"});
            command = new MarkDoneCommand(keywords[0]);
        } else if (input.matches("delete [0-9]{1,}")) {
            String[] keywords = extractKeywordsFromCommand(input, new String[] {"delete"});
            command = new DeleteTaskCommand(keywords[0]);
        } else if (input.matches("find .{1,}")) {
            String[] keywords = extractKeywordsFromCommand(input, new String[]{"find"});
            return new FindCommand(keywords);
        }

        if (command != null) {
            return command;
        }

        throw new UnknownCommandException();
    }

    private static String[] extractKeywordsFromCommand(String command, String[] keywords) {
        if (keywords.length == 2) {
            String[] commands = command.split(keywords[1], 2);
            String taskDescription = commands[0].split(" ", 2)[1].trim();
            String timeInfo = commands[1].trim();
            assert (taskDescription.length() > 0) : "taskDescription should not be \"\"";
            assert (timeInfo.length() > 0) : "timeInfo should not be \"\"";
            return new String[]{taskDescription, timeInfo};
        } else {
            String taskDescription = command.split(keywords[0], 2)[1].trim();
            assert (taskDescription.length() > 0) : "taskDescription should not be \"\"";
            return new String[]{taskDescription};
        }
    }
}
