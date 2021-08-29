package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void getSaveFormat_emptyInput_correctSaveFormat() {
        assertEquals(
                "T|i|todo 1",
                new ToDo("todo 1").getSaveFormat()
        );
        assertEquals(
                "T|c|todo 1",
                new ToDo("todo 1", true).getSaveFormat()
        );
    }

    @Test
    public void getName_emptyInput_correctName() {
        assertEquals(
                "todo 1",
                new ToDo("todo 1").getName()
        );
        assertEquals(
                "todo 1",
                new ToDo("todo 1", true).getName()
        );
    }

    @Test
    public void completeTask_emptyInput_true() {
        assertEquals(
                true,
                new ToDo("todo 1").completeTask()
        );
        assertEquals(
                true,
                new ToDo("todo 1", true).completeTask()
        );
    }

    @Test
    public void isCompleted_emptyInput_correctCompletion() {
        assertEquals(
                false,
                new ToDo("todo 1").hasCompleted()
        );
        assertEquals(
                true,
                new ToDo("todo 1", true).hasCompleted()
        );
    }

    @Test
    public void getTaskType_emptyInput_stringT() {
        assertEquals(
                "T",
                new ToDo("todo 1").getTaskType()
        );
        assertEquals(
                "T",
                new ToDo("todo 1", true).getTaskType()
        );
    }
}
