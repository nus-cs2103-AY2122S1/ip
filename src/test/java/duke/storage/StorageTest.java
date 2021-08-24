package duke.storage;

import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    private Storage storage;

    @Test
    public void loadTest() {
        this.storage = new Storage("../../src/test/TestData/StorageTest.txt");
        ArrayList<String> testArray = storage.load();
        ArrayList<String> actualArray = new ArrayList<>();
        actualArray.add("T|0|hello|");
        actualArray.add("E|0|spike pep talk|23/08/2021 2200");
        assertEquals(actualArray, testArray);
    }
}
