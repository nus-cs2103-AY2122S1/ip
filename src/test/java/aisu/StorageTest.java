package aisu;

import org.junit.jupiter.api.Test;

import aisu.exception.AisuException;

/**
 * This class encapsulates a unit test for the Storage class.
 *
 * @author Liaw Xin Yan
 */
public class StorageTest {
    /**
     * Tests the load method of storage to check if the stored tasklist is the same as the current tasklist data.
     */
    @Test
    public void loadTest() throws AisuException {
        // a unit test for Storage#load() method
        Aisu testAisu = new Aisu("data", "new_test.txt");
    }
}
