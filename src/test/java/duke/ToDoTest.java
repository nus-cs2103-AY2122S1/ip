package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testInstantiateToDo1() {
        Task todo = new ToDo("testInstantiateToDo1");
        assertEquals("[T][ ] testInstantiateToDo1", todo.toString());
    }

    @Test
    public void testInstantiateToDo2() {
        Task todo = new ToDo("testInstantiateToDo2", true);
        assertEquals("[T][X] testInstantiateToDo2", todo.toString());
    }

    @Test
    public void testInstantiateToDo3() {
        Task todo = new ToDo("testInstantiateToDo3", false);
        assertEquals("[T][ ] testInstantiateToDo3", todo.toString());
    }

    @Test
    public void testParseForStorage1() {
        Task todo = new ToDo("testParseForStorage1");
        assertEquals("T | 0 | testParseForStorage1", todo.parseForStorage());
    }

    @Test
    public void testParseForStorage2() {
        Task todo = new ToDo("testParseForStorage2", false);
        assertEquals("T | 0 | testParseForStorage2", todo.parseForStorage());
    }

    @Test
    public void testParseForStorage3() {
        Task todo = new ToDo("testParseForStorage3", true);
        assertEquals("T | 1 | testParseForStorage3", todo.parseForStorage());
    }
}
