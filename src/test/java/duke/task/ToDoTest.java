package duke.task;

import duke.Duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testToDoInput(){
        Task test = new ToDo("hubbub");
        assertEquals(test.toString(), "[T][ ] hubbub");
    }
}
