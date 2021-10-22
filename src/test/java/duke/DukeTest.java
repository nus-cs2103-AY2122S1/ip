package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void testGetResponse() {
        Duke duke = new Duke("data/duke.txt");
        String TEST_INPUT = "todo buy groceries";
        assertEquals(duke.getResponse(TEST_INPUT), "Task successfully added:\n[T][ ] buy groceries");
    }
}
