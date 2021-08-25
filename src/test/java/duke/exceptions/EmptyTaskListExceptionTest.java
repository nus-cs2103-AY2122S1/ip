package duke.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmptyTaskListExceptionTest  {
    @Test
    public void constructor_success() {
        assertEquals("There are no tasks in your to-do list! ☹", new EmptyTaskListException().getMessage());
    }
}
