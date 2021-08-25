package duke.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnknownInputExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("I'm sorry, I don't know what that means! ☹", new UnknownInputException().getMessage());
    }
}

