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
    }

    @Test
    void testToString() {
    }
}