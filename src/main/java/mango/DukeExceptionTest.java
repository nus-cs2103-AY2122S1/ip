package mango;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DukeExceptionTest {
    @Test
    public void testException() {
        assertEquals("hello", new DukeException("hello").getMessage());
    }
}
