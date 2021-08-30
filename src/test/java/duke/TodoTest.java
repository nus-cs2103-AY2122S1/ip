package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class to test the Todo class.
 */
public class TodoTest {
    /**
     * Tests if the todo representation is generated as expected.
     */
    @Test
    public void todoCreationTest(){
        assertEquals(new Todo("description").toString(),
                "[T] description");
    }
}
