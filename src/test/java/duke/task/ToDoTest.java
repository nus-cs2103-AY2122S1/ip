package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the functions in ToDo
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class ToDoTest {

    private final ToDo t = new ToDo(false, "tutorial");

    @Test
    public void constructor() {
        assertEquals("[T][ ] tutorial", t.toString());
    }

    @Test
    public void setDone() {
        t.setDone();
        assertEquals("[T][X] tutorial", t.toString());
    }

    @Test
    public void onDate() {
        assertEquals(false, t.onDate(LocalDate.of(1999, 4, 1)));
    }

}
