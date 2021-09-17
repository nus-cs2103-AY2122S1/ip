package duke.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.task.Task;

public class TaskListTest {
    private class TaskStub extends Task {
        private String description;
        private boolean isDone;

        public TaskStub(String description) {
            super(description);
            this.description = description;
            this.isDone = false;
        }

        @Override
        public void markAsDone() {
            this.isDone = true;
        }

        @Override
        public boolean getIsDone() {
            return this.isDone;
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

    @Test
    public void markDoneInTaskList_secondItem_getDoneTrueReturned() {
        TaskList taskList = new TaskList();
        taskList.addTask(new TaskStub("Borrow book"));
        taskList.addTask(new TaskStub("Return book"));
        assertTrue(taskList.markDoneInTaskList(1).getIsDone());
    }

    @Test
    public void updateTask_secondItem_taskInTaskListChanged() {
        TaskList taskList = new TaskList();
        taskList.addTask(new TaskStub("Borrow book"));
        taskList.addTask(new TaskStub("Return book"));
        Task newTask = new TaskStub("Burn book");
        Task oldTask = taskList.updateTask(1, newTask);
        assertNotEquals(oldTask, newTask);
    }
}
