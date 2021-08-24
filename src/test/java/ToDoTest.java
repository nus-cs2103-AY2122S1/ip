import duke.tasks.ToDo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void Subtest1() {
        Todo event = new ToDo("Finish 2103 Homework");
        assertEquals("[T][ ] Finish 2103 Homework", event.toString());
    }

    @Test
    public void Subtest2() {
        Todo todo = new ToDo("A very long long long long long task");
        todo.setDone();
        assertEquals("[T][X] A very long long long long long task", event.toString());
    }

    @Test
    public void Subtest3() {
        Deadline event = new Deadline("Do pre-class readings");
        event.setDone();
        assertEquals("[T][X] Do pre-class readings)", event.toString());
    }
}