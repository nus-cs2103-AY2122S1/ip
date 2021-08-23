package task;

import main.java.duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    private ToDo t = new ToDo(false, "tutorial");

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
