package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void parseCommand() throws Exception{
        assertEquals("[D][ ] Birthday (by: Apr 15 2000, 23:59)", Deadline.parseCommand("Birthday /by 15-04-2000 23:59").toString());
    }
}
