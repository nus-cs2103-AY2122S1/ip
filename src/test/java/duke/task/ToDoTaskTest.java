package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ToDoTaskTest {
    @Test
    public void writeToFileTest() {
        assertEquals("TODO | 0 | return a book\n", new ToDoTask("return a book").writeToFile());
    }


    @Test
    public void toStringTest() {
        assertEquals("[T][ ] return a book", new ToDoTask("return a book").toString());
    }
}
