package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class StorageTest {
    private static final String TEST_DATA_FOLDER_PATH = "./dataTest";
    private static final String TEST_DATA_FILE_PATH = "./dataTest/dataTest.txt";

    @Test
    public void load() {
        File directory = new File(TEST_DATA_FOLDER_PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File f;
        try {
            f = new File(TEST_DATA_FILE_PATH);
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(TEST_DATA_FILE_PATH);
            fw.write("T| |todo description\n");
            fw.write("D|X|deadline description|2021-12-12\n");
            fw.write("E| |event description|2012-11-11");
            fw.close();
        } catch (IOException e) {
            fail("Error creating test data file");
        }

        Storage storage = new Storage(TEST_DATA_FILE_PATH);
        ArrayList<Task> actualTasks = storage.load();
        assertEquals(3, actualTasks.size());
        assert(actualTasks.get(0) instanceof Todo);
        assert(actualTasks.get(0).getDescription().equals("todo description"));
        assertFalse(actualTasks.get(0).getDoneStatus());

        assert(actualTasks.get(1) instanceof Deadline);
        assert(actualTasks.get(1).getDescription().equals("deadline description"));
        assert(actualTasks.get(1).getDoneStatus());
        assertEquals(LocalDate.parse("2021-12-12"), ((Deadline) actualTasks.get(1)).getDueDate());

        assert(actualTasks.get(2) instanceof Event);
        assert(actualTasks.get(2).getDescription().equals("event description"));
        assertFalse(actualTasks.get(2).getDoneStatus());
        assertEquals(LocalDate.parse("2012-11-11"), ((Event) actualTasks.get(2)).getEventDate());
    }

    @AfterAll
    public static void cleanUpDataFile() {
        File f = new File(TEST_DATA_FILE_PATH);
        if (f.exists()) {
            f.delete();
        }
        File directory = new File(TEST_DATA_FOLDER_PATH);
        if (directory.exists()) {
            directory.delete();
        }
    }
}
