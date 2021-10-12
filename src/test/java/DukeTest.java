import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Event;
import duke.Task;
import duke.Todo;

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
        Task test = new Event("find house", "11/09/2001");
        test.markAsDone();
        assertEquals("[E][X] find house (at: Sep 11 2001)", test.toString());
    }
}
