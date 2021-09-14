package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toString_descriptionOfToDo_toDoStringReturned() {
        assertEquals("[T][ ] study \n", new ToDo("study").toString());
    }
}
