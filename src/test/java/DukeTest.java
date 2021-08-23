import duke.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void TodoNormalTest(){
        try {
            Todo a = new Todo("a");
            assertEquals("a", a.getName());
        } catch (DukeTodoException e) {
            assertEquals(null, e);
        }
    }

    @Test
    public void TodoExceptionTest(){
        try {
            Todo a = new Todo("");
            assertEquals("", a.getName());
        } catch (DukeTodoException e) {
            assertEquals("OOPS!!! The description of a deadline cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void EventNormalTest(){
        try {
            Event a = new Event("project meeting /at Mon 2-4pm");
            assertEquals("project meeting ", a.getName());
            assertEquals("Mon 2-4pm", a.getTime());
        } catch (DukeEventException e) {
            assertEquals(null, e);
        }
    }

    @Test
    public void DeadlineNormalTest(){
        try {
            Deadline a = new Deadline("return book /by 2019-10-15");
            assertEquals("return book ", a.getName());
            assertEquals("Oct 15 2019", a.getDate());
        } catch (DukeDeadlineException e) {
            assertEquals(null, e.getMessage());
        }
    }
}
