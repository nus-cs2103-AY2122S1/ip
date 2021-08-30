package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void sizeTest() {
        try {
            File input = new File("java/duke/input.txt");
            TaskList tasks = new TaskList();
            assertEquals(tasks.size(), 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void descTest() {
        try {
            File input = new File("java/duke/input.txt");
            TaskList tasks = new TaskList();
            assertEquals(tasks.get(1).getDescription(), "return book");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
