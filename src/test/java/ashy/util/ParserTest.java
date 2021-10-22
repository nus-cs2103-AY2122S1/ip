package ashy.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ParserTest {
    @Test
    void parseCommand_success() {
        String[] inputs = {"todo", "DeadLine", "TODO", "LIST", "DoNe", "LisT", "event"};
        AshyCommands[] expected = {AshyCommands.TODO, AshyCommands.DEADLINE,
            AshyCommands.TODO, AshyCommands.LIST, AshyCommands.DONE, AshyCommands.LIST, AshyCommands.EVENT};

        for (int i = 0; i < 7; i++) {
            assertEquals(expected[i], Parser.parseCommand(inputs[i]));
        }

    }

}
