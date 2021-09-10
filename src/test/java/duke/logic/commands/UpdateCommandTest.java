package duke.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import duke.logic.commands.UpdateCommand.UpdateTaskDescriptor;
import duke.logic.tasks.Task;
import duke.logic.tasks.TaskList;
import duke.logic.tasks.TaskListTest.TaskStub;

class UpdateCommandTest {
    private UpdateCommand updateCommand;

    @Test
    public void testExecute_emptyTaskList_failure() {
        updateCommand = new UpdateCommand(Integer.MAX_VALUE, null, null, null, null);
        updateCommand.setTaskList(new TaskListStubWithFixedSize(0));
        CommandResult cr = updateCommand.execute();
        assertEquals(UpdateCommand.EMPTY_TASK_LIST_MSG, cr.getMessage());
    }

    @Test
    public void testExecute_taskNumberOutOfBounds_failure() {
        CommandResult cr;

        // Task number too big
        updateCommand = new UpdateCommand(Integer.MAX_VALUE, null, null, null, null);
        updateCommand.setTaskList(new TaskListStubWithFixedSize(3));
        cr = updateCommand.execute();
        assertEquals(String.format(UpdateCommand.TASK_NUMBER_OUT_OF_BOUNDS_MSG, 3), cr.getMessage());

        // Task number too small
        updateCommand = new UpdateCommand(Integer.MIN_VALUE, null, null, null, null);
        updateCommand.setTaskList(new TaskListStubWithFixedSize(3));
        cr = updateCommand.execute();
        assertEquals(String.format(UpdateCommand.TASK_NUMBER_OUT_OF_BOUNDS_MSG, 3), cr.getMessage());
    }

    @Test
    public void testExecute_noUpdateFieldProvided_failure() {
        updateCommand = new UpdateCommand(1, null, null, null, null);
        updateCommand.setTaskList(new TaskListStubWithFixedSize(3));
        CommandResult cr = updateCommand.execute();
        assertEquals(UpdateCommand.NO_FIELD_PROVIDED_MSG, cr.getMessage());
    }

    @Test
    public void testExecute_validUpdate_success() {
        Task taskStub = new TaskStub(1);
        TaskListStubWithFixedSize taskListStub = new TaskListStubWithFixedSize(3);
        taskListStub.setFakeTaskForUpdate(taskStub);

        updateCommand = new UpdateCommand(1, "Dummy description", null, null, null);
        updateCommand.setTaskList(taskListStub);

        CommandResult cr = updateCommand.execute();
        assertEquals(String.format(UpdateCommand.TASK_UPDATED_MSG, taskStub), cr.getMessage());
    }

    /**
     * A task list stub that has a fixed size and a single (fake) task to be returned when calling update().
     * All irrelevant methods throw an exception to prevent possible misuse.
     */
    private static class TaskListStubWithFixedSize extends TaskList {
        private final Integer size;
        private Task fakeTaskForUpdate;

        public TaskListStubWithFixedSize(Integer size) {
            this.size = size;
        }

        public void setFakeTaskForUpdate(Task fakeTaskForUpdate) {
            this.fakeTaskForUpdate = fakeTaskForUpdate;
        }

        @Override
        public void add(Task t) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Task delete(int taskNo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public Task get(int taskNo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean markAsDone(int taskNo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Task update(int taskNo, UpdateTaskDescriptor updateDescriptor) {
            return fakeTaskForUpdate;
        }

        @Override
        public List<Task> find(String[] keywords) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<String> getSaveFormat() {
            throw new AssertionError("This method should not be called.");
        }
    }
}
