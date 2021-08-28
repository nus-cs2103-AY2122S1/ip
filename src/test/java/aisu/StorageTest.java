package aisu;

import aisu.exception.AisuException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void loadTest() throws AisuException {
        // a unit test for Storage#load() method
        Aisu testAisu = new Aisu("data", "new_test.txt");
        assertEquals(testAisu.getStorage().load(), testAisu.getTaskListData());
    }
}
