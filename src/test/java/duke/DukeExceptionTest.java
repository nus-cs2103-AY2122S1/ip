package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeExceptionTest {

    @Test
    public void exceptionTest() {
        assertEquals("test", new DukeException("test").getMessage());
    }

    @Test
    public void invalidTest() {
        assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", new InvalidArguement().getMessage());
        assertEquals("☹ OOPS!!! The description of a test cannot be empty.", new InvalidDescription("test").getMessage());
    }
}
