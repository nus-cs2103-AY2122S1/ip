package mango.task;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class TaskTest {
    @Test
    public void testDeadlineDescription() {
        assertEquals("abc (by: Oct 15 2009) #fun", new Deadline("abc", "2009-10-15", "fun").getDescription());
    }

    @Test
    public void testEventDescription() {
        assertEquals("abc (at: Mon) #urgent", new Event("abc", "Mon", "urgent").getDescription());
    }

    @Test
    public void testTodoDescription() {
        assertEquals("abc", new Todo("abc", "fun").getDescription());
    }

    @Test
    public void testDeadline_isValid_exceptionThrown() {
        try {
            assertEquals(false, Deadline.isValid(new String[] {"deadline"}));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("OOPS!!! The description of a deadline cannot be empty.", e.getMessage());
        }
    }
}
