import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    private final ToDo todo = new ToDo("test");

    @Test
    public void testGetTask() {
        assertEquals("T", todo.getTask());
        assertEquals("test", todo.getDescription());
    }
}
