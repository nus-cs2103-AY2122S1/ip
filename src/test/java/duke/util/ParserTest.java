package duke.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    void parseCommand_success() {
        String[] inputs = {
                "todo",
                "DeadLine",
                "TODO",
                "LIST",
                "bYe",
                "DoNe",
                "LisT",
                "event"
        };
        DukeCommands[] expected = {
                DukeCommands.TODO,
                DukeCommands.DEADLINE,
                DukeCommands.TODO,
                DukeCommands.LIST,
                DukeCommands.BYE,
                DukeCommands.DONE,
                DukeCommands.LIST,
                DukeCommands.EVENT
        };

        for (int i = 0; i < 8; i++) {
            assertEquals(expected[i], Parser.parseCommand(inputs[i]));
        }

    }

}
