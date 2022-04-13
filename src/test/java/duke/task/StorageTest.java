package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Storage;

public class StorageTest {

    @Test
    public void getLocalStorageLocation_correctValue() {
        assertEquals(Storage.getLocalStorageLocation(), "/LocalStorage.txt");
    }
}
