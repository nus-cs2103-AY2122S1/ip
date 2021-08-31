/**
 * @author Hang Zelin
 *
 * A JUnit class that tests some methods in Storage.
 */
package duke.junittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.testng.annotations.Test;

import duke.excpetions.DukeException;
import duke.saveloadmanager.Storage;

public class StorageTest {

    @Test
    public void handleTaskTest() {
        assertEquals('0', new Storage("data/tasks.txt")
                .handleTaskText("T | 0 | borrow book"));
        assertEquals('1', new Storage("data/tasks.txt")
                .handleTaskText("D | 1 | return book | 2/12/2019 1800"));
        assertEquals('1', new Storage("data/tasks.txt")
                .handleTaskText("E | 1 | project meeting | I don't know the time"));
        assertEquals('0', new Storage("data/tasks.txt")
                .handleTaskText("D | 0 | return book | 23/9/2020 1525"));
        assertEquals('0', new Storage("data/tasks.txt")
                .handleTaskText("E | 0 | project splashdown | 15/10/2019 0000"));
        assertEquals('1', new Storage("data/tasks.txt")
                .handleTaskText("T | 1 | join sports club"));
    }

    @Test
    public void load_correctFilePath_success() {
        try {
            assertEquals("[T][ ] borrow book",
                    new Storage("data/tasks.txt").load().get(0).getTaskInfo());
            assertEquals("[D][ ] return book (by: Dec 02 2019 18:00)",
                    new Storage("data/tasks.txt").load().get(1).getTaskInfo());
            assertEquals("[E][X] project meeting (at: I don't know the time.)",
                    new Storage("data/tasks.txt").load().get(2).getTaskInfo());
            assertEquals("[D][ ] return book (by: Sep 23 2020 15:25)",
                    new Storage("data/tasks.txt").load().get(3).getTaskInfo());
            assertEquals("[E][X] project splashdown (at: Oct 15 2019 00:00)",
                    new Storage("data/tasks.txt").load().get(4).getTaskInfo());
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
