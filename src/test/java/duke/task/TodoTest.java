package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodoTest {
    @Test
    void testStringifyTask() {
        Todo test1 = new Todo("test1");
        Todo test2 = new Todo("test2");
        test2.markAsDone();
        assertEquals("T|0|test1", test1.stringifyTask(), "Stringify an uncompleted task.");
        assertEquals("T|1|test2", test2.stringifyTask(), "Stringify a completed task.");
    }


    @Test
    void testToString() {
        Todo test1 = new Todo("test1");
        Todo test2 = new Todo("test2");
        test2.markAsDone();
        assertEquals("[T][ ] test1", test1.toString(),
                "String of an uncompleted task.");
        assertEquals("[T][X] test2", test2.toString(),
                "String of a completed task.");
    }
}