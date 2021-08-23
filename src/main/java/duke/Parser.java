package duke;

import duke.command.Command;

public class Parser {
    public static Command parse(String input) throws DukeException {
        String command = input.split("\\s", 2)[0];

        for (Command.Type type : Command.Type.values()) {
            if (command.matches(type.commandRegex)) {
                return Command.createCommand(type, input);
            }
        }

        throw new DukeException();
    }
}
