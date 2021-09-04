package duke;

import duke.main.StorageElement;
import duke.main.TaskList;
import duke.tasks.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class TaskStub extends Task {
    protected TaskStub(String description) {
        super(description);
    }

    protected TaskStub(StorageElement storageElement) {
        super(storageElement);
    }

    @Override
    public StorageElement getStorageElement() {
        return null;
    }
}

class TaskListTest {

    @Test
    void addTask() {
        Task task = new TaskStub("Test");
        TaskList taskList = new TaskList();
        taskList.addTask(task);
        assertEquals(1, taskList.getNumTask());
    }

    @Test
    void markAsDone() {
        Task task = new TaskStub("test");
        TaskList taskList = new TaskList();
        taskList.addTask(task);
        taskList.markAsDone(0);
        assertEquals(true, taskList.getTask(0).isDone());
    }

    @Test
    void getNumTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(new TaskStub("test"));
        taskList.addTask(new TaskStub("test"));
        taskList.addTask(new TaskStub("test"));
        taskList.deleteTask(1);
        assertEquals(2, taskList.getNumTask());
    }
}
