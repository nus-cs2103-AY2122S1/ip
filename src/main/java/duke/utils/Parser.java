package duke.utils;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.exceptions.UnknownCommandException;

import java.util.Scanner;

public class Parser {

    private Scanner scanner;

    public Parser() {
        this.scanner = new Scanner(System.in);
    }

    public Command parseNext(TaskList tasks) throws DukeException {
        String command = this.scanner.nextLine();
        if (command.matches("bye")) {
            return new ByeCommand();
        } else if (command.matches("list")) {
            return new ListCommand();
        } else if (command.matches("deadline\\s.{1,}\\s\\/by\\s.{1,}")) {
            String[] keywords = extractKeywordsFromCommand(command, new String[] {"deadline", "/by"});
            return new AddDeadlineCommand(keywords);
        } else if (command.matches("event\\s.{1,}\\s\\/at\\s.{1,}")) {
            String[] keywords = extractKeywordsFromCommand(command, new String[] {"event", "/at"});
            return new AddEventCommand(keywords);
        } else if (command.matches("todo\\s.{1,}")) {
            String[] keywords = extractKeywordsFromCommand(command, new String[] {"todo"});
            return new AddTodoCommand(keywords);
        } else if (command.matches("done [0-9]{1,}")) {
            String[] keywords = extractKeywordsFromCommand(command, new String[] {"done"});
            return new MarkDoneCommand(keywords[0]);
        }  else if (command.matches("delete [0-9]{1,}")) {
            String[] keywords = extractKeywordsFromCommand(command, new String[] {"delete"});
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
