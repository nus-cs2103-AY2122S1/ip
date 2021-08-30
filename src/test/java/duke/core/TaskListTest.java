package duke.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class TaskListTest {
    @Test
    public void printTasks_noTask_emptyListText() {
        TaskList t = new TaskList();
        assertEquals("--- List is Empty ---", t.printTasks());
    }

    @Test
    public void recordTodo_validTodo_listWithOneItem() {
        try {
            TaskList t = new TaskList();
            t.recordTodo("Hello");
            assertEquals("--- Start of List ---\n"
                    + "1. [T][ ] Hello\n"
                    + "--- End of List ---", t.printTasks());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void recordTodo_overHundredItemsRecorded_exceptionThrown() {
        try {
            int limit = 100;
            TaskList t = new TaskList();
            for (int i = 0; i <= limit; i++) {
                t.recordTodo("Hello");
            }
            fail();
        } catch (DukeException e) {
            assertEquals("I cannot remember so many things!", e.getMessage());
        }
    }

    @Test
    public void deleteTask_validInput_emptyListText() {
        try {
            TaskList t = new TaskList();
            t.recordTodo("Hello");
            assertEquals("--- Start of List ---\n"
                    + "1. [T][ ] Hello\n"
                    + "--- End of List ---", t.printTasks());
            t.deleteTask(1);
            assertEquals("--- List is Empty ---", t.printTasks());
        } catch (DukeException e) {
            fail();
        }
    }
}
