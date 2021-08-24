package duke.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Task;

public class TaskListTest {
    private class TaskStub extends Task {
        private String description;

        public TaskStub(String description) {
            super(description);
            this.description = description;
        }

        @Override
        public String toString() {
            return this.description;
        }
    }

    @Test
    public void addTaskAndRemoveTask_dummyTaskStub_taskStubDescriptionReturned() {
        TaskList taskList = new TaskList();
        taskList.addTask(new TaskStub("Return book"));
        assertEquals("Return book", taskList.removeTask(0).toString());
    }
}
