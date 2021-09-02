package duke;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import task.Deadline;
import task.Event;
import task.Tasklist;
import task.Todo;
import ui.LogMessage;


public class StorageTest {

    private Todo task1 = new Todo("test1", false);
    private Deadline task2 = new Deadline("test2", "10pm", true);
    private Event task3 = new Event("test3", "Aug 25 2021", false);
    private LogMessage logMessage;

    @Test
    public void testLoadingExistingFile() {
        Tasklist expectedList = new Tasklist(new ArrayList<>());
        LogMessage msg = new LogMessage();
        expectedList.add(task1, msg);
        expectedList.add(task2, msg);
        expectedList.add(task3, msg);

        String filepath = "../../text-ui-test/testTaskCase1.txt";
        Storage storage = new Storage(filepath);
        Tasklist tasklist = storage.load(logMessage);

        assertTrue(tasklist.equals(expectedList));
    }
}
