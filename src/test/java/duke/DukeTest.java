package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dukeTest() {
        Duke duke = new Duke();
        assertEquals("I'm Duke, a simple chatbot to help you remember tasks!", duke.toString());
    }
}
