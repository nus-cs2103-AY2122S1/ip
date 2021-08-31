import org.junit.jupiter.api.Test;
import lebron.task.*;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void getStatusIcon() {
        Task task = new Task("Do work");
        assertEquals(" ",task.getStatusIcon());
    }

    @Test
    void getName() {
        Task task = new Task("Do work");
        assertEquals("Do work",task.getName());
    }

    @Test
    void toFile() {
        Task task = new Task("Do work");
        assertEquals(" | 0 | Do work", task.toFile());
    }
}
