import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Collections;
import java.util.List;

import bobbybot.person.Person;
import org.junit.jupiter.api.Test;

import bobbybot.tasks.Task;
import bobbybot.util.Storage;

public class StorageTest {

    @Test
    public void load_loadTaskTestDB_success() {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Storage storage = new Storage("src/test/testDatabase.txt");

        List<Task> tasks = storage.loadTasks();
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
        assertEquals(Collections.emptyList(), storage.loadTasks());
        // check file created and directory created properly
        File dir = new File("data");
        File f = new File(invalidFilePath);
        assertTrue(dir.isDirectory());
        assertTrue(f.isFile());
        // Clear test files
        f.delete();
    }

    @Test
    public void load_loadContactTestDB_success() {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Storage storage = new Storage("src/test/testDatabase.txt",
                                    "src/test/testContactsDatabase.txt");

        List<Person> contacts = storage.loadContacts();
        System.out.println(contacts);
        String expectedString = "[Darren; Phone: 83821019; Email: mokkwd@gmail.com; Address: address,"
                + " Luoling; Phone: 83821019; Email: mokkwd@gmail.com; Address: address]";
        assertEquals(expectedString, contacts.toString());
    }

    @Test
    public void load_loadInvalidContactFileDb_emptyLoad() {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        String invalidFilePath = "data/invalidfile.txt";
        Storage storage = new Storage("test.txt", invalidFilePath);
        assertEquals(Collections.emptyList(), storage.loadContacts());
        // check file created and directory created properly
        File dir = new File("data");
        File f = new File(invalidFilePath);
        assertTrue(dir.isDirectory());
        assertTrue(f.isFile());
        // Clear test files
        f.delete();
    }
}
