package duke;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void sizeTest() {
        try {
            File input = new File("java/duke/input.txt");
            TaskList tasks = new TaskList(input);
            assertEquals(tasks.size(), 4);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void descTest() {
        try {
            File input = new File("java/duke/input.txt");
            TaskList tasks = new TaskList(input);
            assertEquals(tasks.get(1).getDescription(), "return book");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
