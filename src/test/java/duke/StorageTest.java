package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Tests the methods in Storage class.
 */
public class StorageTest {

    @Test
    public void writeToFileAndTaskSaveToFile() throws FileNotFoundException, DukeException {
        Storage storage = new Storage("test.txt");
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new ToDo("say hello"));
        taskList.add(new Event("dance performance", true, "at three"));
        taskList.add(new Deadline("cs2106 lab", false, "by next week"));
        storage.writeToFile(taskList);
        File file = new File("test.txt");
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        assertEquals("T|| ||say hello",line);
        line = scanner.nextLine();
        assertEquals("E||X||dance performance||at three", line);
        line = scanner.nextLine();
        assertEquals("D|| ||cs2106 lab||by next week", line);
    }

    @Test
    public void readAndWriteFile() throws DukeException, FileNotFoundException {
        Storage storage = new Storage("test.txt");
        ArrayList<Task> taskList = new ArrayList<>();
        storage.writeToFile(taskList);
        assertEquals(0, storage.readFile().size());
        taskList.add(new ToDo("finish CS2101 Week 3"));
        assertEquals(0, storage.readFile().size());
        storage.writeToFile(taskList);
        assertEquals("[T][ ] finish CS2101 Week 3",storage.readFile().get(0).toString());
    }
}
