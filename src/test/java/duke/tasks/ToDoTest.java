package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toString_stringReturned() {
        String des = "start working on portfolio website";
        ToDo task = new ToDo(des);
        assertEquals("[T][ ] start working on portfolio website", task.toString());
    }
}
