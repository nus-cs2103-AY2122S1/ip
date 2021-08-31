import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Event;
import duke.task.ToDo;


public class DukeTest {
    @Test
    public void toStringTest() {
        assertEquals("[T] [ ] submit iP ", new ToDo("submit iP").toString());
    }

    @Test
    public void toFileTest() {
        assertEquals("T | 0 | submit iP", new ToDo("submit iP").formatTask());
    }

    @Test
    public void eventDateTest() {
        assertEquals("[E] [ ] submission (at: Dec 12 2021)", new Event("submission", "12/12/2021").toString());
    }

}
