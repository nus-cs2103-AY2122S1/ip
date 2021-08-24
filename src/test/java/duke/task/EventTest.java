package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EventTest {
    @Test

    public void initTest() {
        assertEquals(new Event("event",
                "place",
                false).at, "place");
    }
}
