package lifeline.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LifelineExceptionTest {

    @Test
    public void testConstructor() {
        LifelineException lle = new LifelineException("Testing Lifeline Exception");
        assertEquals("Testing Lifeline Exception", lle.getMessage());
    }
}
