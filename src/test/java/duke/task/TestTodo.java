package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exceptions.DucIncompleteException;
import duke.exceptions.DucWrongCommandException;

public class TestTodo {

    /**
     * Test a Todo Task object
     */
    @Test
    public void test1() {
        Todo testObject1 = new Todo("Go to gym");
        String expected1 = "[T][ ] Go to gym";
        assertEquals(expected1, testObject1.toString());
        testObject1.markAsCompleted();
        String expected2 = "[T][X] Go to gym";
        assertEquals(expected2, testObject1.toString());
        assertEquals(expected2, testObject1.save());
        String expected3 = "There are no date specified with task ";
        assertEquals(expected3, testObject1.getDate());
    }

    /**
     * Test a Todo object that would throw exception
     */
    @Test
    public void test2() {
        assertThrows(DucIncompleteException.class, () -> {
            Todo testObject2 = new Todo("");
        });
        assertThrows(DucWrongCommandException.class, () -> {
            Todo testObject2 = new Todo("Go to gym /by 2021-09-02");
        });
        assertThrows(DucWrongCommandException.class, () -> {
            Todo testObject2 = new Todo("Go to gym /at 2021-09-02");
        });
    }
}
