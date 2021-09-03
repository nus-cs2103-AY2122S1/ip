package duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void printString(){
        assertEquals("[T][ ] this", new Todo("this", false).toString());
    }
}
