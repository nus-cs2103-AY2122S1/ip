package duke;

import duke.commands.*;

public class Parser {
    private final Command[] commandList = {
            new ListCommand(""),
            new ByeCommand(""),
            new TodoCommand(""),
            new FormatsCommand(""),
            new DoneCommand(""),
            new DeleteCommand(""),
    };

    public Command parse(String input) {
        for(Command c : commandList) {
            if(input.equals(c.startsWith())) {
                return c.of(input.substring(c.startsWith().length()));
            }
        }
        return null;
    }

}
