package virushade.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void toStringTest() {
        assertEquals("[T][ ] To do CS2103T", new ToDo("To do CS2103T", false).toString());
    }
}
