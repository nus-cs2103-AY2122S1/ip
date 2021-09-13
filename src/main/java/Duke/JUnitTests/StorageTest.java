package duke.junittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.testng.annotations.Test;

import duke.exceptions.DukeException;
import duke.saveloadmanager.Storage;
/**
 * @author Hang Zelin
 *
 * A JUnit class that tests some methods in Storage.
 */
public class StorageTest {

    @Test
    public void load_correctFilePath_success() {
        try {
            assertEquals("[T][ ] borrow book",
                    new Storage("data/tasks.txt").load().get(0).getTaskStatus());
            assertEquals("[D][ ] return book (by: Dec 02 2019 18:00)",
                    new Storage("data/tasks.txt").load().get(1).getTaskStatus());
            assertEquals("[E][X] project meeting (at: I don't know the time.)",
                    new Storage("data/tasks.txt").load().get(2).getTaskStatus());
            assertEquals("[D][ ] return book (by: Sep 23 2020 15:25)",
                    new Storage("data/tasks.txt").load().get(3).getTaskStatus());
            assertEquals("[E][X] project splashdown (at: Oct 15 2019 00:00)",
                    new Storage("data/tasks.txt").load().get(4).getTaskStatus());
        } catch (DukeException e) {
            //Should not reach this.
            fail();
        }
    }

    @Test
    public void load_wrongFilePath_exceptionThrown() {
        try {
            assertEquals(new ArrayList<>(), new Storage("I don't know").load());
            fail(); //Should not reach this.
        } catch (DukeException e) {
            assertEquals("Cannot Read From Data.", e.getMessage());
        }
    }
}
