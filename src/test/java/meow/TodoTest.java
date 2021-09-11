package meow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toString_correctInput_returnToDoString() {
        assertEquals("T | 0 | do homework",
                new Todo("do homework").toString());
    }
}
