package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class TaskTest {
    private class TaskStub extends Task {
        public TaskStub(String description) {
            super(description);
        }
    }

    @Test
    public void toString_descriptionOfTask_taskStringReturned() {
        assertEquals("[ ] Return book",
                new TaskStub("Return book").toString());
    }

    @Test
    public void getIsDone_undoneTask_falseReturned() {
        assertFalse(new TaskStub("Return Book").getIsDone());
    }

    @Test
    public void getDescription_dummyTaskStub_descriptionStringReturned() {
        assertEquals("Return book",
                new TaskStub("Return book").getDescription());
    }

    @Test
    public void markAsDone_taskMarkedDone_returnTaskStringMarkedDone() {
        Task task = new TaskStub("Return book");
        task.markAsDone();
        assertEquals("[X] Return book", task.toString());
    }
}
