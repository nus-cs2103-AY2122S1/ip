import duke.ToDo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testToDo(){
        ToDo test = new ToDo("test","TODO");
        assertEquals("[T][ ] test", test.toString());
    }
}
