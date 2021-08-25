import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toFileTest() {
        assertEquals("T | 0 | sample name", new Todo("sample name").toFile());
    }

    @Test
    public void toStringTest() {
        assertEquals("[T][X] sample name", new Todo("sample name", true).toString());
    }
}
