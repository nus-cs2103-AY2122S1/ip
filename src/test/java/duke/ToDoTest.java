package duke;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void constructor() {
        ToDo exercise = new ToDo("exercise");
        assertEquals("[T][ ] exercise", exercise.toString());
        ToDo readBook = new ToDo("read book");
        assertEquals("[T][ ] read book", readBook.toString());
    }
}
