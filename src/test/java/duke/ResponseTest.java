package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ResponseTest {
    private static final String LINE = "    ____________________________________________________________";
    private static final String MESSAGE_INDENT = "     ";

    @Test
    public void toString_success() {
        assertEquals(
                ResponseTest.LINE + System.lineSeparator()
                + ResponseTest.MESSAGE_INDENT + "message" + System.lineSeparator()
                + ResponseTest.LINE, new Response("message").toString());
    }
}
