package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void test01() {
        assertEquals(Parser.getTaskID("T"), 0);
    }

    @Test
    public void test02() {
        assertEquals(Parser.getTaskID("1"), -1);
    }

}
