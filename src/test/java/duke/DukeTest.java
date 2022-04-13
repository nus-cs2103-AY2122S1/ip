package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void dukeTest() {
        //sample test
        String filePath = System.getProperty("user.dir");
        Duke duke = new Duke(filePath);
        assertEquals(2, 2);
    }
}
