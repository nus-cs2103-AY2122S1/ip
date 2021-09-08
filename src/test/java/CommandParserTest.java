import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.Duke;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskNumberException;
import duke.logic.CommandParser;
import duke.ui.Ui;
import stub.DukeStub;

public class CommandParserTest {
    private final Duke duke;
    private final Ui ui;

    CommandParserTest() throws IOException {
        duke = new DukeStub();
        ui = new Ui("Test user");
        ui.checkInput("todo hello", duke);
    }

    @Test
    void testInvalidCommand() {
        Assertions.assertThrows(InvalidCommandException.class, () -> {
            CommandParser c = new CommandParser("hello", duke.getTaskList(), duke.getStorage(), ui);
        });
    }

    @Test
    void testInvalidTaskCommand() {
        Assertions.assertThrows(InvalidCommandException.class, () -> {
            CommandParser c = new CommandParser("todo", duke.getTaskList(), duke.getStorage(), ui);
        });
    }

    @Test
    void testInvalidTasklistNumber() {
        Assertions.assertThrows(InvalidTaskNumberException.class, () ->
            new CommandParser("done 3", duke.getTaskList(), duke.getStorage(), ui));
    }
}
