import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import bobbybot.tasks.Task;
import bobbybot.util.Storage;

public class StorageTest {

    @Test
    public void load_loadTestDB_success() throws FileNotFoundException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Storage storage = new Storage("src/test/testDatabase.txt");

        List<Task> tasks = storage.load();
        System.out.println(tasks);
        String expectedString = "[[T][X] to this as well, [T][X] and this,"
                + " [D][X] this assignment  (by: Jan 01 2021 10:22), "
                + "[T][ ] show this in db, [T][ ] this, [E][ ] that (at: sunday)]";
        assertEquals(expectedString, tasks.toString());
    }

    @Test
    public void load_loadInvalidFileDb_emptyLoad() {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        String invalidFilePath = "data/invalidfile.txt";
        Storage storage = new Storage(invalidFilePath);
        assertEquals(Collections.emptyList(), storage.load());
        // check file created and directory created properly
        File dir = new File("data");
        File f = new File(invalidFilePath);
        assertTrue(dir.isDirectory());
        assertTrue(f.isFile());
        // Clear test files
        f.delete();
    }
}
