package virushade.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void toStringTest() {
        assertEquals("[T][ ] To do CS2103T", new ToDo("To do CS2103T", false).toString());
    }
}
