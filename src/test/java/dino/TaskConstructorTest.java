package dino;

import dino.task.ToDo;
import dino.task.Deadline;
import dino.task.Event;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskConstructorTest {
    @Test
    public void toDoConstructor_success() {
        assertEquals("T | 0 | watch lecture",
                new ToDo("watch lecture").toString());
    }
    @Test
    public void deadlineConstructor_success() {
        assertEquals("D | 0 | finish project | Aug 24 2021",
                new Deadline("finish project", LocalDate.parse("2021-08-24")).toString());
    }
    @Test
    public void eventConstructor_success() {
        assertEquals("E | 0 | play tennis | Aug 25 2021",
                new Event("play tennis", LocalDate.parse("2021-08-25")).toString());
    }
}
