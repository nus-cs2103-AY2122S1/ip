package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.storage.Storage;

public class TaskListTest {
    @Test
    public void get_normal_success() throws IOException {
        TaskList taskList = new TaskList(new TaskStorageStub());
        try {
            assertEquals(TaskStorageStub.taskList.get(0), taskList.get(1));
        } catch (InvalidTaskException e) {
            fail("Should not have thrown InvalidTaskException");
        }
    }

    @Test
    public void get_invalidIndex_throwsInvalidTaskException() throws IOException {
        TaskList taskList = new TaskList(new TaskStorageStub());
        assertThrows(InvalidTaskException.class, () -> taskList.get(100));
        assertThrows(InvalidTaskException.class, () -> taskList.get(0));
        assertThrows(InvalidTaskException.class, () -> taskList.get(-1));
    }

    private static class TaskStorageStub implements Storage<Task> {
        private static List<Task> taskList;

        static {
            try {
                taskList = List.of(
                        new Todo("homework")
                );
            } catch (InvalidTaskException ignored) {
                // we are not expecting any exceptions here since we create the tasks ourselves
            }
        }

        public List<Task> load() {
            return taskList;
        }

        public void save(List<Task> list) {
        }
    }
}
