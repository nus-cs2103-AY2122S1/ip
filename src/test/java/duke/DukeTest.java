package duke;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void initTest() {
        Duke duke = new Duke(new duke.util.DukeDB("./data/dukeStore.txt"));
        assertNotNull(duke);
    }
}
