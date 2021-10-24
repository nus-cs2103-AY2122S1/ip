package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void test() {
        Todo todo = new Todo("Eat Food");
        assertEquals("T | | Eat Food", todo.toString());
    }
}
