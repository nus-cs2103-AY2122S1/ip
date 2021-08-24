package duke.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toString_stringReturned() {
        String des = "start working on portfolio website";
        ToDo task = new ToDo(des);
        assertEquals("[T][ ] start working on portfolio website", task.toString());
    }
}
