package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testInstantiateToDo1() {
        Task todo = new ToDo("testInstantiateToDo1", 1);
        assertEquals("[T][ ][!] testInstantiateToDo1", todo.toString());
    }

    @Test
    public void testInstantiateToDo2() {
        Task todo = new ToDo("testInstantiateToDo2", true, 2);
        assertEquals("[T][X][*] testInstantiateToDo2", todo.toString());
    }

    @Test
    public void testInstantiateToDo3() {
        Task todo = new ToDo("testInstantiateToDo3", false, 3);
        assertEquals("[T][ ][ ] testInstantiateToDo3", todo.toString());
    }

    @Test
    public void testParseForStorage1() {
        Task todo = new ToDo("testParseForStorage1", 3);
        assertEquals("T | 0 | 3 | testParseForStorage1", todo.parseForStorage());
    }

    @Test
    public void testParseForStorage2() {
        Task todo = new ToDo("testParseForStorage2", false, 2);
        assertEquals("T | 0 | 2 | testParseForStorage2", todo.parseForStorage());
    }

    @Test
    public void testParseForStorage3() {
        Task todo = new ToDo("testParseForStorage3", true, 1);
        assertEquals("T | 1 | 1 | testParseForStorage3", todo.parseForStorage());
    }
}
