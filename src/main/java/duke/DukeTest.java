package duke;

import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest() {
        Assertions.assertEquals(2, 2);
    }

    @Test
    public void toDoTest() {
        Task test = new Todo("return book");
        assertEquals("[T][ ] return book", test.toString());
    }

    @Test
    public void toDoTest2() {
        Task test = new Todo("return book");
        test.markAsDone();
        assertEquals("[T][X] return book", test.toString());
    }

    @Test
    public void EventTest() {
        Task test = new Event("bomb world trade center", "11/09/2001");
        test.markAsDone();
        assertEquals("[E][X] bomb world trade center (at:Sep 11 2001)", test.toString());
    }
}
