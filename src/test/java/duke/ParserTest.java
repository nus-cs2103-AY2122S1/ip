package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.ExitCommand;

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
