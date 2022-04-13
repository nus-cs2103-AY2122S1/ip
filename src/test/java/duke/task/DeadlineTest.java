package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        Deadline deadline = new Deadline("Finish Bread", "2021/09/11");
        assertEquals("[D][ ] Finish Bread (by: 11 Sep 2021)", deadline.toString());
    }

    @Test
    public void saveDataTest() {
        Deadline deadline = new Deadline("Finish Bread", "2021/09/11");
        assertEquals("deadline 0 Finish Bread /by 2021-09-11", deadline.saveData());
    }
}
