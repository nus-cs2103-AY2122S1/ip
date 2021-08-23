package meow;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest(){
        assertEquals("T | 0 | do homework",
                new Todo("do homework").toString());
    }
}