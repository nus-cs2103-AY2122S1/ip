package duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.TaskList;

public class StorageTest {

    private static String testFilePath = "./data/Duke.txt";
    private TaskList tasks = new TaskList();

    @Test
    public void load_invalidFilePath_throwsException() {
        try {
            Storage test = new Storage(testFilePath);
            test.load();
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Sorry, I can't find your list of tasks. I'll create a new one for you.");
        }
    }

    @Test
    public void write_invalidFilePath_throwsException() {
        try {
            Storage test = new Storage("...///./");
            test.write(tasks);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Sorry, I was unable to store your list of tasks");
        }
    }

}
