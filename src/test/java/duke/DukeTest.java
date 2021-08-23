package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void ListTest() {
        Duke d = new Duke("data/save.txt");
        assertEquals(2, 2);
    }
}
