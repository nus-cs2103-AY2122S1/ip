package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void test1() {
        Todo todo = new Todo("return book");
        assertEquals("[T][ ] return book", todo.toString());
    }

    @Test
    public void test2() {
        Todo todo = new Todo("join sports club");
        assertEquals("[T][ ] join sports club", todo.toString());
    }
}
