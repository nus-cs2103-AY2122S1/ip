package bot.assembly.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testToDoToString() {
        assertEquals("[T][ ] testToDo 1", new ToDo("testToDo 1").toString());
    }

    @Test
    public void testToDoGetDateTime() {
        assertEquals(null, new ToDo("testToDo 2").getDateTime());
    }

    @Test
    public void testToDoMarkAsDoneToString() {
        ToDo testToDo3 = new ToDo("testToDo 3");
        testToDo3.markAsDone();
        assertEquals("[T][X] testToDo 3", testToDo3.toString());
    }

    @Test
    public void testToDoGetTaskTitle() {
        ToDo testToDo4 = new ToDo("testToDo 4");
        String actual = testToDo4.getTaskTitle();
        assertEquals("testToDo 4", actual);
    }

    @Test
    public void testToDoGetTaskType() {
        ToDo testToDo5 = new ToDo("testToDo 5");
        assertEquals("T", testToDo5.getTaskType());
    }

    @Test
    public void testToDoGetIsDone() {
        ToDo testToDo6 = new ToDo("testToDo 6");
        assertEquals(false, testToDo6.getIsDone());
        testToDo6.markAsDone();
        assertEquals(true, testToDo6.getIsDone());
    }
}
