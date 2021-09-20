package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.main.Storage;
import duke.main.StorageElement;

/**
 * Class to test Storage class in Duke
 */
class StorageTest {
    @Test
    void storageElementToFileLine() {
        StorageElement storageElement = new StorageElement("E", true, "task description");

        Storage storage = new Storage("./test_folder");

        String fileLine = "E" + Storage.DELIMITER + "1" + Storage.DELIMITER + "task description";
        assertEquals(fileLine, storage.storageElementToFileLine(storageElement));
    }

    @Test
    void fileLineToStorageElement() {
        String fileLine = String.join(Storage.DELIMITER,
                new String[]{"D", "0", "finish tP task", "2021-12-08"});

        Storage storage = new Storage("./test_folder");

        LocalDate time = LocalDate.parse("2021-12-08");

        assertEquals("D", storage.fileLineToStorageElement(fileLine).getTaskIcon());
        assertEquals(false, storage.fileLineToStorageElement(fileLine).getDone());
        assertEquals(time, storage.fileLineToStorageElement(fileLine).getTime());
        assertEquals("finish tP task", storage.fileLineToStorageElement(fileLine).getDescription());
    }
}
