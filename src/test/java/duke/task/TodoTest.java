package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toString_descriptionOfTodo_todoStringReturned() {
        assertEquals("[T][ ] Return book",
                new Todo("Return book").toString());
        assertEquals("[T][ ] Buy milk",
                new Todo("Buy milk").toString());
    }
}
