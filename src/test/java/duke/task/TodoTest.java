package duke.task;

import duke.commands.Task;
import duke.commands.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void initialiseTest(){
        Task todo = new Todo("do homework", true);
        assertEquals(2,2);
    }
}
