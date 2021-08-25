package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void isByeMsgTest(){
        assertEquals(new Todo("description").toString(),
                "[T] description");
    }
}
