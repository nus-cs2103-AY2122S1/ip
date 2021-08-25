package duke.Command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExitCommandTest {
    @Test
    public void isExitTest(){
        assertTrue(new ExitCommand().isExit());
    }
}
