package ashy.exceptions;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class AshyExceptionTest {
    @Test
    public void constructor_success() {
        assertEquals("message", new AshyException("message").getMessage());
    }
}
