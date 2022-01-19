package duchess.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * This class implements a JUnit Test for the Parser methods.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class ParserTest {
    /**
     * Tests the checkPrefix() method using valid input.
     * @throws DuchessException Exception thrown when the prefix is preceded by an empty string.
     */
    @Test
    public void checkPrefix_validInput_success() throws DuchessException {
        Parser p = new Parser();
        assertEquals(p.checkPrefix("bye").getDescription(), null);
        assertEquals(p.checkPrefix("list").getDescription(), null);
        assertEquals(p.checkPrefix("adsad").getDescription(), null);
        assertEquals(p.checkPrefix("todo homework").getDescription(), "homework");
        assertEquals(p.checkPrefix("deadline assignment /by 25/12/2021 3pm").getDescription(),
                "assignment /by 25/12/2021 3pm");
        assertEquals(p.checkPrefix("event lesson /at 1/1/2022 1pm-4pm").getDescription(),
                "lesson /at 1/1/2022 1pm-4pm");
        assertEquals(p.checkPrefix("delete 1").getDescription(), "1");
        assertEquals(p.checkPrefix("done 1").getDescription(), "1");
        assertEquals(p.checkPrefix("tasks /after today").getDescription(), "/after today");
        assertEquals(p.checkPrefix("tasks /before today").getDescription(), "/before today");
    }

    /**
     * Tests the checkPrefix() method using invalid input.
     * @throws DuchessException Exception thrown when the prefix is preceded by an empty string.
     */
    @Test
    public void checkPrefix_singleInvalidInput_exceptionThrown() {
        try {
            Parser p = new Parser();
            assertEquals(p.checkPrefix("done").getDescription(), null);
            fail();
        } catch (DuchessException e) {
            assertEquals("The description of done cannot be empty.", e.getMessage());
        }
    }
}
