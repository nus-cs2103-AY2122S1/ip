import duke.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toDoCreation_Task1Completed_toString() {
        ToDo test = new ToDo("Task1", true) ;
        assertEquals("[T][x] Task1", test.toString());
    }

    public void toDoCreation_Task1Completed_save() {
        ToDo test = new ToDo("Task1", true) ;
        assertEquals("T|1|Task1", test.save());
    }

}
