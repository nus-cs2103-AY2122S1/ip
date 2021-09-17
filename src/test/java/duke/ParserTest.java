package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.command.Command;

public class ParserTest {
    @Test
    void testParse_taskWithEmptyDescription_throwException() {
        Exception exception = assertThrows(DukeException.class, () -> Parser.parse("todo "));
        assertEquals("command is incorrect or incomplete", exception.getMessage());
    }

    @Test
    void testParse_parseDeadlineWithDate_deadlineWithProperDate() throws DukeException {
        Command c = Parser.parse("deadline unit tests /by 2021-11-11");
        TaskList tl = new TaskList();
        UiStub ui = new UiStub();
        Storage store = new Storage("", ui);
        c.execute(tl, ui, store);

        assertEquals("D", tl.get(0).getType());
        assertEquals(1, tl.size());
        assertEquals("unit tests", tl.get(0).getLabel());
    }
}
