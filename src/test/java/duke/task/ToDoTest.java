package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void getSaveFormat_emptyInput_correctSaveFormat() {
        assertEquals(
                "T|i|todo 1|",
                new ToDo("todo 1").getSaveFormat()
        );
        assertEquals(
                "T|c|todo 1|",
                new ToDo("todo 1", true, new String[Task.MAX_TAGS]).getSaveFormat()
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
                new ToDo("todo 1", true, new String[Task.MAX_TAGS]).getName()
        );
    }

    @Test
    public void completeTask_emptyInput_true() {
        assertTrue(
                new ToDo("todo 1").completeTask()
        );
        assertTrue(
                new ToDo("todo 1", true, new String[Task.MAX_TAGS]).completeTask()
        );
    }

    @Test
    public void isCompleted_emptyInput_correctCompletion() {
        assertFalse(
                new ToDo("todo 1").hasCompleted()
        );
        assertTrue(
                new ToDo("todo 1", true, new String[Task.MAX_TAGS]).hasCompleted()
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
                new ToDo("todo 1", true, new String[Task.MAX_TAGS]).getTaskType()
        );
    }
}
