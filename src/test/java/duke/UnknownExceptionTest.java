package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.exceptions.UnknownException; 

public class UnknownExceptionTest {
    @Test
    public void unknownException_msg_success() {
        assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(",
                new UnknownException().getMessage());
    }
}
