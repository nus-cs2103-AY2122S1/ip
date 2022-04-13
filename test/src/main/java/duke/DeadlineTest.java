package src.main.java.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void markAsDone() {
        Deadline deadline = new Deadline("return book", false, "2019-12-01");
        deadline.markAsDone();
        assertEquals("D | 1 | return book | Dec 1 2019", deadline.toString());
    }
}
