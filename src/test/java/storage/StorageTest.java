package storage;

import org.junit.jupiter.api.Test;
import task.Operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StorageTest {

    @Test
    void getOperation() {
        Storage storage = new Storage(null);
        assertEquals(Operation.TODO, storage.getOperation("todo read book"));
    }
}
