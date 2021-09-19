import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {
    private Duke dukeTest = new Duke();

    @Test
    public void getResponse_validUserInput_showSuccess() {
        String response = dukeTest.getResponse("todo abc");
        assertEquals((response.split("\nNow you have"))[0],
                "Got it. I've added this task:\n  [T][ ] abc");

    }
}
