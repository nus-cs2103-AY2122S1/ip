package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void fileFormat_normalNoInput_properFormat() {
        assertEquals("D | 0 | H | report | 2021-12-20 1800",
                new Deadline("report", "H", "2021-12-20 1800").convertToFileFormat());
    }

    @Test
    public void toString_normalNoInput_expectedString() {
        assertEquals("[D][ ][H] report (by: Dec 2 2021 1800)",
                new Deadline("report", "H", "2021-12-02 1800").toString());
    }
}
