package duke;

import duke.command.ExitCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parse() {
        try {
            Parser parser = new Parser();
            assertEquals(new ExitCommand(), Parser.parse("bye"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

}