package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void constructor_invalidFileFormat_fileParseExceptionThrown() throws IOException {
        String invalidFileLocation = "resources/invalidFileFormat.txt";
        try {
            new TaskList(new StorageStub(invalidFileLocation));
            fail();
        } catch (FileParseException e) {
            assertEquals(
                    "The contents of the file in storage are formatted wrongly.",
                    e.getMessage());
        }
    }

    @Test
    public void taskList_validFileFormat_correctTasksAndSize() throws DukeException, FileParseException, IOException {
        String validFileLocation = "resources/validFileFormat.txt";
        TaskList tasks = new TaskList(new StorageStub(validFileLocation));

        Task firstTask = new Deadline("assignment", "01/09/2021");
        Task secondTask = new Event("concert", "03/10/2021");
        secondTask.markAsDone();
        Task thirdTask = new Todo("borrow book");
        Task fourthTask = new Todo("go school");
        fourthTask.markAsDone();

        assertEquals(4, tasks.getSize());
        assertEquals(firstTask, tasks.getTask(0));
        assertEquals(secondTask, tasks.getTask(1));
        assertEquals(thirdTask, tasks.getTask(2));
        assertEquals(fourthTask, tasks.getTask(3));
    }

    @Test
    public void addTask_newTask_taskCanBeRetrieved() throws FileParseException, IOException {
        String validFileLocation = "resources/validFileFormat.txt";
        TaskList tasks = new TaskList(new StorageStub(validFileLocation));

        Task newTask = new Todo("go back home");
        tasks.addTask(newTask);

        assertEquals(5, tasks.getSize());
        assertEquals(newTask, tasks.getTask(4));
    }

    @Test
    public void markTask_firstUndoneTask_taskIsDone() throws DukeException, FileParseException, IOException {
        String validFileLocation = "resources/validFileFormat.txt";
        TaskList tasks = new TaskList(new StorageStub(validFileLocation));

        Task firstTask = new Deadline("assignment", "01/09/2021");
        assertEquals(firstTask, tasks.getTask(0));

        // after marking both tasks as done, both tasks should still be equal
        firstTask.markAsDone();
        tasks.markTask(1);
        assertEquals(firstTask, tasks.getTask(0));
    }

    @Test
    public void deleteTask_thirdTask_taskCannotBeRetrieved() throws DukeException, FileParseException, IOException {
        String validFileLocation = "resources/validFileFormat.txt";
        TaskList tasks = new TaskList(new StorageStub(validFileLocation));

        Task thirdTask = new Todo("borrow book");
        tasks.deleteTask(3);

        assertEquals(3, tasks.getSize());
        assertNotEquals(thirdTask, tasks.getTask(0));
        assertNotEquals(thirdTask, tasks.getTask(1));
        assertNotEquals(thirdTask, tasks.getTask(2));
    }

}

class StorageStub extends Storage {
    public StorageStub(String fileLocation) throws IOException {
        super(fileLocation);
    }

    @Override
    public void addLine(Task task) {
    }

    @Override
    public void removeLine(int lineNumber) {
    }

    @Override
    public void editLine(int lineNumber, Task task) {
    }
}