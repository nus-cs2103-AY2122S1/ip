package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskStorageTest {
    @Test
    public void loadTasks_invalidFileFormat_fileParseExceptionThrown() throws IOException {
        String invalidFileLocation = "resources/invalidFileFormat.txt";
        try {
            new TaskStorage(invalidFileLocation).loadTasks();
            fail();
        } catch (FileParseException e) {
            assertEquals(
                    "The contents of the file in storage are formatted wrongly.",
                    e.getMessage());
        }
    }

    @Test
    public void loadTasks_correctTasksAndSize() throws DukeException, IOException, FileParseException {
        Task firstTask = new Deadline("assignment", "01/09/2021");
        Task secondTask = new Event("concert", "03/10/2021");
        secondTask.markAsDone();
        Task thirdTask = new Todo("borrow book");
        Task fourthTask = new Todo("go school");
        fourthTask.markAsDone();

        String validFileLocation = "resources/validFileFormat.txt";
        ArrayList<Task> tasks = new TaskStorage(validFileLocation).loadTasks();

        assertEquals(4, tasks.size());
        assertEquals(firstTask, tasks.get(0));
        assertEquals(secondTask, tasks.get(1));
        assertEquals(thirdTask, tasks.get(2));
        assertEquals(fourthTask, tasks.get(3));
    }

}
