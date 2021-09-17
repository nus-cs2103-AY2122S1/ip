import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import hyddd.exceptions.HydddException;
import hyddd.saveloadmanager.Storage;
/**
 * @author Hang Zelin
 *
 * A JUnit class that tests some methods in Storage.
 */
public class StorageTest {

    @Test
    public void load_correctFilePath_success() {
        try {
            assertEquals("[T][X] borrow book",
                    new Storage("tasks.txt").load().get(0).getTaskStatus());
            assertEquals("[D][X] return book (by: Dec 02 2019 18:00)",
                    new Storage("tasks.txt").load().get(1).getTaskStatus());
            assertEquals("[E][X] project meeting (at: I don't know the time.)",
                    new Storage("tasks.txt").load().get(2).getTaskStatus());
        } catch (HydddException e) {
            //Should not reach this.
            fail();
        }
    }

    @Test
    public void load_wrongFilePath_exceptionThrown() {
        try {
            assertEquals(new ArrayList<>(), new Storage("I don't know").load());
            fail(); //Should not reach this.
        } catch (HydddException e) {
            assertEquals("OOPS!!! Cannot Read From Data!!!", e.getErrorMessage());
        }
    }
}
