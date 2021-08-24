package duke.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    Command testCommand = new Command();

    @Test
    public void test01() {
        assertEquals(testCommand.isArgumentValid(), true);
    }

    @Test
    public void test02() {
        assertEquals(testCommand.isExit(), false);
    }
}
