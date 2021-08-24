package duke.utils;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.exceptions.UnknownCommandException;

import java.util.Scanner;

public class Parser {

    public static Command parseNext(String input) throws DukeException {
        if (input.matches("bye")) {
            return new ByeCommand();
        } else if (input.matches("list")) {
            return new ListCommand();
        } else if (input.matches("deadline\\s.{1,}\\s\\/by\\s.{1,}")) {
            String[] keywords = extractKeywordsFromCommand(input, new String[] {"deadline", "/by"});
            return new AddDeadlineCommand(keywords);
        } else if (input.matches("event\\s.{1,}\\s\\/at\\s.{1,}")) {
            String[] keywords = extractKeywordsFromCommand(input, new String[] {"event", "/at"});
            return new AddEventCommand(keywords);
        } else if (input.matches("todo\\s.{1,}")) {
            String[] keywords = extractKeywordsFromCommand(input, new String[] {"todo"});
            return new AddTodoCommand(keywords);
        } else if (input.matches("done [0-9]{1,}")) {
            String[] keywords = extractKeywordsFromCommand(input, new String[] {"done"});
            return new MarkDoneCommand(keywords[0]);
        }  else if (input.matches("delete [0-9]{1,}")) {
            String[] keywords = extractKeywordsFromCommand(input, new String[] {"delete"});
            return new DeleteTaskCommand(keywords[0]);
        }

        throw new UnknownCommandException();
    }

    private static String[] extractKeywordsFromCommand(String command, String[] keywords) {
        if (keywords.length == 2) {
            String[] commands = command.split(keywords[1], 2);
            String taskDescription = commands[0].split(" ", 2)[1].trim();
            String timeInfo = commands[1].trim();
            return new String[]{taskDescription, timeInfo};
        } else {
            String taskDescription = command.split(keywords[0], 2)[1].trim();
            return new String[]{taskDescription};
        }
    }
}
