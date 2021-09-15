package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void getLocalStorageLocation_correctValue() {
        assertEquals(Duke.getLocalStorageLocation(), "src/Data/LocalStorage.txt");
    }
}
