package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tasks.Deadline;
import tasks.ToDo;

/**
 * JUnit test class for TaskList.
 */
public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        this.taskList = new TaskList();
        this.taskList.add(new ToDo("get bread"));
        this.taskList.add(new Deadline("submit assignment", "2021-08-25"));
    }

    /**
     * Tests if the TaskList toString() method works as expected.
     */
    @Test
    public void toStringTest() {
        String expected = "      1. [T][ ] get bread\n      2. [D][ ] submit assignment(by: Aug 25 2021)\n";
        assertEquals(expected, this.taskList.toString());
    }

    /**
     * Tests if the TaskList toStorage() method works as expected.
     */
    @Test
    public void toStorageTest() {
        String expected = "T|0|get bread\nD|0|submit assignment/by 2021-08-25\n";
        assertEquals(expected, this.taskList.toStorage());
    }
}
