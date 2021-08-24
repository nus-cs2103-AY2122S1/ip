package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseTest {
    @Test
    public void toString_success() {
        assertEquals(
                "    ____________________________________________________________" + System.lineSeparator()
                + "     message" + System.lineSeparator()
                + "    ____________________________________________________________"
                , new Response("message").toString());
    }
}
