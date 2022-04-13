package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test

    public void equalTest() {
        assertEquals(new Event("event",
                        "place",
                        false).getAt(),
                "place");
    }
}

