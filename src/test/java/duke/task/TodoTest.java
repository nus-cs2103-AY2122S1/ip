package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TodoTest {

    @Test
    public void getStatusIconTest() {
        Deadline deadline = new Deadline("sleep", "5pm");
        deadline.markAsDone();
        assertEquals(deadline.getStatusIcon(), "\u2713");
    }
}
