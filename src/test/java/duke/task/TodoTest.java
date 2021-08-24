package duke.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {
    private Todo todo;

    @BeforeEach
    void setUp() {
        this.todo = new Todo("test description");
    }

    @Test
    void testToString() {
        assertEquals("[T][ ] test description", this.todo.toString(),
                "should return correct toString");
    }

    @Test
    void stringToStore() {
        assertEquals("T |   | test description\n", this.todo.stringToStore(),
                "should return correct string to store");
    }

    @Test
    void statusIconNotMarkedTest() {
        assertEquals(" ", this.todo.getStatusIcon());
    }

    @Test
    void statusIconMarkedTest() {
        this.todo.markAsDone();
        assertEquals("X", this.todo.getStatusIcon());
    }

    @Test
    void convertToTask() {
        Task task = Task.convertToTask("T |   | test description");
        assertEquals(this.todo.toString(), task.toString());
    }
}