package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void createTest() {
        Deadline d = new Deadline("test", "2020-12-20");
        assertEquals("[D][ ] test (by: Dec 20 2020)", d.toString());
    }
}
