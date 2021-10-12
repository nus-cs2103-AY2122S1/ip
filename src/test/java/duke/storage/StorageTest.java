package duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;

public class StorageTest {

    @Test
    public void storageLoad_validFilePath_correctArrayList() {

        ArrayList<Task> correctArraylistOfTasks = new ArrayList<Task>();

        correctArraylistOfTasks.add(new ToDo("1"));
        correctArraylistOfTasks.add((new ToDo("ok3")));
        correctArraylistOfTasks.add((new ToDo("ok4")));
        correctArraylistOfTasks.add(new Deadline("ok", LocalDate.of(2019, 12, 12)));

        Storage testStorage = new Storage("src/test/data/duke.txt");
        try {
            assertEquals(correctArraylistOfTasks, testStorage.load());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
