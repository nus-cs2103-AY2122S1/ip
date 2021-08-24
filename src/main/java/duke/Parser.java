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
            new DeadlineCommand(""),
            new EventCommand("")
    };

    public Command parse(String input) {
        for(Command c : commandList) {
            if(input.startsWith(c.startsWith())) {
                if (input.equals(c.startsWith())) {
                    return c.of("");
                }
                return c.of(input.substring(c.startsWith().length()+1));
            }
        }
        return null;
    }

}
