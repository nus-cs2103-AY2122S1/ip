package duke.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmptyTaskDescriptionExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("Please provide a task description! ☹", new EmptyTaskDescriptionException().getMessage());
    }
}

