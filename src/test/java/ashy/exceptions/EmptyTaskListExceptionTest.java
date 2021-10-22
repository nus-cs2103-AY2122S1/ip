package ashy.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EmptyTaskListExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("Oh no! There are no tasks in your to-do list! ☹", new EmptyTaskListException().getMessage());
    }
}
