package duke.exceptions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFormatExceptionTest {
    @Test
    public void getMessageTest() {
        FileFormatException de = new FileFormatException("File formatting error");
        assertEquals("File formatting error", de.getMessage());
    }
}
