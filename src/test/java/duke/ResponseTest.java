package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseTest {
    private static String LINE = "    ____________________________________________________________";
    private static String MESSAGE_INDENT = "     ";

    @Test
    public void toString_success() {
        assertEquals(
                ResponseTest.LINE + System.lineSeparator()
                + ResponseTest.MESSAGE_INDENT + "message" + System.lineSeparator()
                + ResponseTest.LINE
                , new Response("message").toString());
    }
}
