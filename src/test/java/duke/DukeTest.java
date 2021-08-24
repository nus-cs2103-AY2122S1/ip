package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DukeTest {
    @Test
    public void initTest() {
        Duke duke = new Duke(new duke.util.DukeDB("./data/dukeStore.txt"));
        assertNotNull(duke);
    }
}