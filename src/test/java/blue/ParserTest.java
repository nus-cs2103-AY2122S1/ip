package blue;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void getArguments_nullInput_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> Parser.getArguments(null));
    }

    @Test
    public void getArguments_emptyInput_emptyArrayReturned() {
        assertArrayEquals(new String[]{}, Parser.getArguments(""));
    }
}
