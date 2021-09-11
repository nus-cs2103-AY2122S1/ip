package duke;

import org.junit.jupiter.api.Test;

import duke.storage.Storage;
import duke.logic.tasks.Deadline;
import duke.logic.tasks.Event;
import duke.logic.tasks.Task;
import duke.logic.tasks.ToDo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    public void saveFileTest() {
        ArrayList<Task> saved = new ArrayList<>();
        try {
            Storage.loadTaskListFromHardDisk();
        } catch (IOException e) {
            fail();
        }
        saved.add(new ToDo("go to school", ""));
        saved.add(new Deadline("read book", "", LocalDate.parse("2020-10-10")));
        saved.add(new Event("read book", "", LocalDate.parse("2020-10-10")));
        Storage.saveTaskListToHardDisk(saved);

        File file = new File("src/data/duke.txt");
        assert (file.exists());
    }
}
