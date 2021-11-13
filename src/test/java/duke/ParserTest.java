package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.exceptions.DukeException;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.MissingTaskNumberException;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testGetCommand() {
        assertEquals("deadline", Parser.getCommand("deadline homework /by 2021-12-09 10pm"));
    }

    @Test
    public void testTaskNumber() throws MissingTaskNumberException {
        assertEquals(2, Parser.taskNumber("delete 2"));
    }

    @Test
    public void testGetDescription() throws MissingDescriptionException {
        assertEquals("homework /by 2021-12-09 10pm",
                Parser.getDescription("deadline homework /by 2021-12-09 10pm"));
    }

    @Test
    public void testIdentifyType() throws DukeException {
        Todo t = new Todo("go shopping");
        assertEquals(t.toString(),
                Parser.identifyType("todo go shopping").toString());
    }

}
