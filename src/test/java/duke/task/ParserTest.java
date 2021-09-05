package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.ChatbotUI;
import duke.DukeException;
import duke.Parser;

public class ParserTest {
    @Test
    public void parseStringFromCommands() {
        try {
            String instruction = Parser.getStringFrom("deadline", "deadline Return book /by: 2021-11-12");
            assertEquals("Return book /by: 2021-11-12", instruction);
        } catch (DukeException e) {
            ChatbotUI.printMessage(e.getMessage());
        }
    }

    @Test
    public void parseIntFromCommands() {
        try {
            int instruction = Parser.getIntFrom("done", "done 123");
            assertEquals(123, instruction);
        } catch (DukeException e) {
            ChatbotUI.printMessage(e.getMessage());
        }
    }
}
