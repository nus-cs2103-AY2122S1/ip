import duke.core.Duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void dummyTest(){
        Duke duke = new Duke("todoList.txt");
        assertEquals(2, 2);
    }
}
