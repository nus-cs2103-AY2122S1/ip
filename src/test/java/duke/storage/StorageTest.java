package duke.storage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void transformToDataTest() {
        String curr = Storage.transformToData("[T] [ ] fa");
        assertEquals("T | 0 | fa", curr);
    }
}
