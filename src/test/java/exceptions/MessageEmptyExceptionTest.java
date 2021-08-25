package exceptions;

import duke.exception.MessageEmptyException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageEmptyExceptionTest {
    @Test
    public void testExceptionMessage() {
        assertEquals("You forgot to enter a message after the command!",
                new MessageEmptyException().getMessage());
    }
}