package luke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LukeTest {
    @Test
    public void lukeTest() {
        Luke luke = new Duke();
        assertEquals("Hello! I'm Luke, your slightly useful personal assistant!\n"
                + "I can help you remember tasks and other things :D", luke.toString());
    }
}