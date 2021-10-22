package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.classes.exceptions.DukeException;
import duke.classes.tasks.Task;

public class DukeStorageTest {
    @Test
    public void test1() {
        DukeStorage storage = new DukeStorage();
        List<Task> test = new ArrayList<>();
        try {
            test = storage.retrieve("test.txt");
        } catch (DukeException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        List<Task> expectedOutput = new ArrayList<>();

        assertEquals(expectedOutput, test);
    }
}
