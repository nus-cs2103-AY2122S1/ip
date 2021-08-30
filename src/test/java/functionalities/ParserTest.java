package functionalities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bern.functionalities.Parser;
import bern.model.ToDo;

/**
 * A class to encapsulates tests for Parser functions.
 */
public class ParserTest {
    /**
     * A test for toDoFromString method.
     */
    @Test
    public void toDoFromStringTest() {
        ToDo temp = new Parser().toDoFromString("[T][ ] Do 2103 project");
        ToDo compare = new ToDo("Do 2103 project");
        assertEquals(compare.toString(), temp.toString());
    }
}
