package meap.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    private static final String TIME_IN_FUTURE = "12-12-2025 2359";

    @Test
    public void equalsMtd_sameObj_returnTrue() {
        ToDo toDo1 = ToDo.of("Eat a potato");
        ToDo toDo2 = ToDo.of("Eat a potato");
        assertEquals(toDo1.equals(toDo2), true);
    }

    @Test
    public void equalsMtd_sameTypeDiffDesc_returnFalse() {
        ToDo toDo1 = ToDo.of("Eat a potato");
        ToDo toDo2 = ToDo.of("Eat a banana");
        assertEquals(toDo1.equals(toDo2), false);
    }

    @Test
    public void equalsMtd_diffTaskObj_returnFalse() {
        ToDo toDo1 = ToDo.of("Eat a potato");
        Event event1 = Event.of("Eat a banana", TIME_IN_FUTURE);
        assertEquals(toDo1.equals(event1), false);
    }
}
