package duke.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidTaskExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("There is no such task in your to-do list! ☹", new InvalidTaskException().getMessage());
    }
}

