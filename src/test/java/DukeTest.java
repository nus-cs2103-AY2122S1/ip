
import duke.Duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    Duke dukeTest = new Duke("data/alexa.txt");

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }
}
