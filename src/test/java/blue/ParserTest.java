package blue;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void getArguments_nullInput_exceptionThrown() {
        assertThrows(AssertionError.class, () -> Parser.getArguments(null));
    }

    @Test
    public void getArguments_emptyInput_emptyArrayReturned() {
        assertArrayEquals(new String[]{}, Parser.getArguments(""));
    }
}
