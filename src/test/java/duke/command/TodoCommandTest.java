package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.NoActionException;
import duke.exception.SaveFileException;
import duke.task.Task;
import duke.task.Todo;
import duke.util.TaskList;


public class TodoCommandTest {
    @Test
    public void execute_goodInput_createTodo() throws SaveFileException, NoActionException {
        TodoCommand command = new TodoCommand("Test Action");
        TaskListStub taskListStub = new TaskListStub();
        command.execute(taskListStub, new StorageStub());
        assertEquals(taskListStub.getTask(), new Todo("Test Action"));
    }

    @Test
    public void execute_noAction_throwNoActionException() {
        TodoCommand command = new TodoCommand("");
        TaskListStub taskListStub = new TaskListStub();
        assertThrows(NoActionException.class, () -> {
            command.execute(taskListStub, new StorageStub());
        });
    }

    private class TaskListStub extends duke.util.TaskList {
        private Task task;

        @Override
        public void add(Task task) {
            this.task = task;
        }

        public Task getTask() {
            return this.task;
        }
    }


    private class StorageStub extends duke.util.Storage {
        public StorageStub() {
            super("");
        }

        @Override
        public void save(TaskList tasks) {
            return;
        }
    }
}
