import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.ToDo;


public class TodoTest {
    @Test
    public void testToDo() {
        ToDo test = new ToDo("test", "TODO");
        assertEquals("[T][ ] test", test.toString());
    }
}
