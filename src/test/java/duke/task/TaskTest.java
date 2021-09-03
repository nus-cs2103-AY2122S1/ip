package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void getDescriptionTest() {
        assertEquals("return a book", new Task("return a book").getDescription());
    }

    @Test
    public void getIsCompletedTest() {
        assertEquals("0", new Task("return a book").getIsCompleted());
    }

    @Test
    public void getStatusIconTest() {
        assertEquals(" ", new Task("return a book").getStatusIcon());
    }

    @Test
    public void writeToFileTest() {
        assertEquals("TASK | 0 | return a book\n", new Task("return a book").writeToFile());
    }

    @Test
    public void toStringTest() {
        assertEquals("[ ] return a book", new Task("return a book").toString());
    }
}
