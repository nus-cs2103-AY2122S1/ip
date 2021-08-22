package botto.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringConversion() {
        Todo task1 = new Todo("read book");
        assertEquals("[T][ ] read book", task1.toString());
        Todo task2 = new Todo("return book");
        assertEquals("[T][ ] return book", task2.toString());
    }
}
