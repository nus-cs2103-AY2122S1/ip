package task;

import main.java.duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the functions in Deadline
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class DeadlineTest {

    private final Deadline d = new Deadline(false, "tutorial", "2021-03-21 2103");

    @Test
    public void constructor() {
        assertEquals("[D][ ] tutorial (by: 21 Mar 2021 9.03pm)", d.toString());
    }

    @Test
    public void setDone() {
        d.setDone();
        assertEquals("[D][X] tutorial (by: 21 Mar 2021 9.03pm)", d.toString());
    }

    @Test
    public void onDate() {
        assertEquals(false, d.onDate(LocalDate.of(2021, 4, 1)));
        assertEquals(true, d.onDate(LocalDate.of(2021, 3, 21)));
    }

}
