package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.CommandList;
import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void dummyTest() {
        assertEquals(CommandList.TODO, new Todo("read book").getType());
    }

}   
