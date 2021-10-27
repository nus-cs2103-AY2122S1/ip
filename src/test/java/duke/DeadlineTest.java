package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.Deadline;

public class DeadlineTest {
    @Test
    public void deadline_toString_success() {
        assertEquals("[D][ ] assignment (by: 2021-08-29)",
                new Deadline("assignment", "2021-08-29").toString());
    }
    
}
