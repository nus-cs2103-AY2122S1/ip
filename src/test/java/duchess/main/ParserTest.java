package duchess.main;

import duchess.command.ByeCommand;
import duchess.command.ListCommand;
import duchess.command.TodoCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void checkPrefix_validInput_success() throws DuchessException {
        Parser p = new Parser();
        assertEquals(p.checkPrefix("bye").getName(), null);
        assertEquals(p.checkPrefix("list").getName(), null);
        assertEquals(p.checkPrefix("adsad").getName(), null);
        assertEquals(p.checkPrefix("todo homework").getName(), "homework");
        assertEquals(p.checkPrefix("deadline assignment /by 25/12/2021 3pm").getName(),
                "assignment /by 25/12/2021 3pm");
        assertEquals(p.checkPrefix("event lesson /at 1/1/2022 1pm-4pm").getName(),
                "lesson /at 1/1/2022 1pm-4pm");
        assertEquals(p.checkPrefix("delete 1").getName(), "1");
        assertEquals(p.checkPrefix("done 1").getName(), "1");
        assertEquals(p.checkPrefix("tasks /after today").getName(), "/after today");
        assertEquals(p.checkPrefix("tasks /before today").getName(), "/before today");
    }

    @Test
    public void checkPrefix_singleInvalidInput_exceptionThrown() {
        try {
            Parser p = new Parser();
            assertEquals(p.checkPrefix("done").getName(), null);
            fail();
        } catch (DuchessException e) {
            assertEquals("The description of done cannot be empty.", e.getMessage());
        }
    }
}
