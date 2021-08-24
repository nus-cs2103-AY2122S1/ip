package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dukeTest() {
        //sample test
        String FILE_PATH = System.getProperty("user.dir");
        Duke duke = new Duke(FILE_PATH);
        assertEquals(2, 2);
    }
}
