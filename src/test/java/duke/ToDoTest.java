package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toDoTest_createInstance_sameStringRep() {
        ToDo event = new ToDo("Sleep");
        assertEquals(event.toString(), "[T][ ] Sleep");
    }
}
