package duke;

import duke.Storage;
import duke.TaskList;
import duke.command.Command;
import duke.Parser;
import duke.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;


public class ParserTest {
    @Test
    void testParse_taskWithEmptyDescription_throwException() {
        Exception exception = assertThrows(DukeException.class, () -> Parser.parse("todo "));
        assertEquals("Command has empty description", exception.getMessage());
    }

    @Test
    void testParse_parseDeadlineWithDate_DeadlineWithProperDate() throws DukeException {
        Command c = Parser.parse("deadline unit tests /by 2021-11-11");
        TaskList tl = new TaskList();
        UiStub ui = new UiStub();
        Storage store = new Storage("", ui);
        c.execute(tl, ui, store);

        assertAll("checking created command",
                () -> assertEquals("D",tl.get(0).getType()),
                () -> assertEquals(1, tl.size()),
                () -> assertEquals("unit tests", tl.get(0).getLabel())
        );
    }
}
