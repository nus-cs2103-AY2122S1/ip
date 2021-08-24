package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void getSaveFormat_emptyInput_correctSaveFormat() {
        assertEquals(
                "G|i|task 1",
                new Task("task 1").getSaveFormat()
        );
        assertEquals(
                "A|i|task 1",
                new Task("task 1", "A").getSaveFormat()
        );
        assertEquals(
                "K|c|task 1",
                new Task("task 1", true, "K").getSaveFormat()
        );
    }

    @Test
    public void getName_emptyInput_correctName() {
        assertEquals(
                "task 1",
                new Task("task 1").getName()
        );
        assertEquals(
                "task 1",
                new Task("task 1", "A").getName()
        );
        assertEquals(
                "task 1",
                new Task("task 1", true, "K").getName()
        );
    }

    @Test
    public void completeTask_emptyInput_true() {
        assertEquals(
                true,
                new Task("task 1").completeTask()
        );
        assertEquals(
                true,
                new Task("task 1", "A").completeTask()
        );
        assertEquals(
                true,
                new Task("task 1", true, "K").completeTask()
        );
    }

    @Test
    public void isCompleted_emptyInput_correctCompletion() {
        assertEquals(
                false,
                new Task("task 1").isCompleted()
        );
        assertEquals(
                false,
                new Task("task 1", "A").isCompleted()
        );
        assertEquals(
                true,
                new Task("task 1", true, "K").isCompleted()
        );
    }

    @Test
    public void getTaskType_emptyInput_stringD() {
        assertEquals(
                "G",
                new Task("task 1").getTaskType()
        );
        assertEquals(
                "A",
                new Task("task 1", "A").getTaskType()
        );
        assertEquals(
                "K",
                new Task("task 1", true, "K").getTaskType()
        );
    }
}
