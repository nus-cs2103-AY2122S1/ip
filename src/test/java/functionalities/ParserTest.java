package functionalities;

import bern.functionalities.Parser;
import bern.model.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void toDoFromStringTest() {
        ToDo temp = new Parser().toDoFromString("[T][ ] Do 2103 project");
        ToDo compare = new ToDo("Do 2103 project");
        assertEquals(compare.toString(), temp.toString());
    }
}
