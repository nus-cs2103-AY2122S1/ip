import duke.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dumbTest() {
        assertEquals(2,2);
    }

    @Test
    public void secondTest() {
        Todo todo = new Todo("SMth");
        assertEquals(todo, todo);
    }
}
