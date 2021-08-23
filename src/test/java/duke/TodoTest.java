package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest(){
        String s = "Todo eat breakfast";
        Todo d = new Todo(s.substring(5));
        String ans = d.toString();
        assertEquals("[T][ ] eat breakfast", ans);
    }

    @Test public void toStringConvertTest(){
        String s = "todo sleep";
        Todo d = new Todo(s.substring(5));
        String ans = d.toStringConvert();
        assertEquals("T | 0 | sleep", ans);
    }
}