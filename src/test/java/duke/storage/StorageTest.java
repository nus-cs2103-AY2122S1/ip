package duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void transformToDataTest() {
        String curr = Storage.transformToData("[T] [ ] fa");
        assertEquals("T | 0 | fa", curr);
    }
}
