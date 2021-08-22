package duke.core;

import duke.command.*;
import duke.exception.DukeException;

public class Parser {
    public static Command identifyCommand(String input) throws DukeException {
        if(input.equals("")) {
            throw new DukeException("Go on, I'm all ears!");
        }
        String[] fullCommand = input.trim().split(" ", 2);
        final String command = fullCommand[0].trim();
        try {
            if(command.equals("bye")) {
                return new ExitCommand();
            }
            if(command.trim().equals("list")) {
                return new ListCommand();
            }
            if(command.equals("done")) {
                return new DoneCommand(fullCommand[1].trim());
            }
            if(command.equals("delete")) {
                return new DeleteCommand(fullCommand[1].trim());
            }
            if(command.equals("todo")) {
                return new AddTodoCommand(fullCommand[1].trim());
            }
            if(command.equals("deadline")) {
                return new AddDeadlineCommand(fullCommand[1].trim());
            }
            if(command.equals("event")) {
                return new AddEventCommand(fullCommand[1].trim());
            }
            return new InvalidCommand();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Don't be shy! I need more information to carry out " + command + " :(");
        }
    }
}
