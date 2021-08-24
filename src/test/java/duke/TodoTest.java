package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void test() {
        Todo todo = new Todo("Eat Food");
        assertEquals("T | | Eat Food",todo.toString());
    }
}
