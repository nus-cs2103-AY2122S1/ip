package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Test")
public class TasksTest {
    /**
     * Checks various behaviors of an instance of ToDoTask.
     */
    @Test
    public void toDoTaskTest1() {
        Task t = new ToDoTask("this");
        assertEquals("[T]", t.getType());
        assertEquals("this", t.getDescription());
        assertEquals("[ ]", t.getStatus());
        assertEquals("T|this|0", t.getSaveFormat());
        t.setIsDone();
        assertEquals("[X]", t.getStatus());
        assertEquals("T|this|1", t.getSaveFormat());
        t.setIsDone();
    }

    /**
     * Checks the String representation of type of task for an instance of ToDoTask.
     */
    @Test
    public void toDoTaskTest2() {
        assertEquals("[T]", new ToDoTask("this").getType());
    }

    /**
     * Checks the String representation of status of whether an instance of ToDoTask is done
     * is correctly changed.
     */
    @Test
    public void toDoTaskTest3() {
        Task t = new ToDoTask("this");
        t.markAsDone();
        assertEquals("[X]", t.getStatus());
    }


    /**
     * Checks various behaviors of an instance of EventTask.
     */
    @Test
    public void eventTaskTest1() {
        Task t = new EventTask("this", "when");
        assertEquals("[E]", t.getType());
        assertEquals("this (at: when)", t.getDescription());
        assertEquals("[ ]", t.getStatus());
        assertEquals("E|this|when|0", t.getSaveFormat());
        t.setIsDone();
        assertEquals("[X]", t.getStatus());
        assertEquals("E|this|when|1", t.getSaveFormat());
        t.setIsDone();
    }

    /**
     * Checks the String representation of type of task for an instance of EventTask.
     */
    @Test
    public void eventTaskTest2() {
        assertEquals("[E]", new EventTask("this", "when").getType());
    }

    /**
     * Checks the String representation of status of whether an instance of ToDoTask is done
     * is correctly changed.
     */
    @Test
    public void eventTaskTest3() {
        Task t = new EventTask("this", "when");
        t.markAsDone();
        assertEquals("[X]", t.getStatus());
    }

    /**
     * Checks if an LocalDate instance is created with certain format of input and is
     * correctly displayed.
     */
    @Test
    public void eventTaskTest4() {
        Task t = new EventTask("this", "2/12/2019 1800");
        assertEquals("this (at: 2 DECEMBER 2019 18:00)", t.getDescription());
        assertEquals("2019-12-02", t.getLocalDate().toString());
    }

    /**
     * Checks various behaviors of an instance of DeadLineTask.
     */
    @Test
    public void deadLineTaskTest1() {
        Task t = new DeadLineTask("this", "when");
        assertEquals("[D]", t.getType());
        assertEquals("this (by: when)", t.getDescription());
        assertEquals("[ ]", t.getStatus());
        assertEquals("D|this|when|0", t.getSaveFormat());
        t.setIsDone();
        assertEquals("[X]", t.getStatus());
        assertEquals("D|this|when|1", t.getSaveFormat());
        t.setIsDone();
    }

    /**
     * Checks the String representation of type of task for an instance of DeadLineTask.
     */
    @Test
    public void deadLineTaskTest2() {
        assertEquals("[D]", new DeadLineTask("this", "when").getType());
    }

    /**
     * Checks the String representation of status of whether an instance of ToDoTask is done
     * is correctly changed.
     */
    @Test
    public void deadLineTaskTest3() {
        Task t = new DeadLineTask("this", "when");
        t.markAsDone();
        assertEquals("[X]", t.getStatus());
    }

    /**
     * Checks if an LocalDate instance is created with certain format of input and is
     * correctly displayed.
     */
    @Test
    public void deadLineTaskTest4() {
        Task t = new DeadLineTask("this", "2/12/2019 1800");
        assertEquals("this (by: 2 DECEMBER 2019 18:00)", t.getDescription());
        assertEquals("2019-12-02", t.getLocalDate().toString());
    }
}
