package dino;
import dino.task.Task;
import dino.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkDoneTest {
    @Test
    public void markAsDone_success() {
        Task task = new ToDo("test todo");
        task.setDone();
        assertEquals("T | 1 | test todo", task.toString());
    }
}
