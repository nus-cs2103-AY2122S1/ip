import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.core.Duke;

public class DukeTest {
    @Test
    public void initDukeTest() {
        try {
            Duke duke = new Duke("data/todoList2.txt");
        } catch (Exception e) {
            fail("Should not have thrown any exception");

        }

    }
}
