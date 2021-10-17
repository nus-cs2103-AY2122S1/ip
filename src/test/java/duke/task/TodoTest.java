package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.CommandList;

public class TodoTest {
    @Test
    public void dummyTest() {
        assertEquals(CommandList.TODO, new Todo("read book").getType());
    }
}
