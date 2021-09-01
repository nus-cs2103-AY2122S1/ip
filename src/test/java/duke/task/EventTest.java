package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void fileFormat_normalNoInput_properFormat() {
        assertEquals("D | 0 | report | 2/12/2021 1800",
                new Deadline("report", "2/12/2021 1800").fileFormat());
    }

    @Test
    public void toString_normalNoInput_expectedString() {
        assertEquals("[D][ ] report (by: Dec 2 2021 06:00 PM)",
                new Deadline("report", "2/12/2021 1800").toString());
    }
}