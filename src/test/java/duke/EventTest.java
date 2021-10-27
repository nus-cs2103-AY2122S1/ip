package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.Event;

public class EventTest {
    @Test
    public void todo_toString_success() {
        assertEquals("[E][ ] meditation (at: 2021-11-02)",
                new Event("meditation", "2021-11-02").toString());
    }

}
