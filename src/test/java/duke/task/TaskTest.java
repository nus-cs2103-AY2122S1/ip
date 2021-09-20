package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void getStatusIconNotDone() {
        Task t = new Task("return book");
        String actual = t.getStatusIcon();

        assertEquals(" ", actual);
    }

    @Test
    void getStatusIconDone() {
        Task t = new Task("return book");
        t.markAsDone();
        String actual = t.getStatusIcon();

        assertEquals("X", actual);
    }

    @Test
    void markAsDone() {
        Task t = new Task("return book");
        t.markAsDone();
        boolean actual = t.isDone();

        assertEquals(true, actual);
    }
}
