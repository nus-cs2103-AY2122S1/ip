package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest(){
        String s = "event Greet the users /at 2021-11-25 2000";
        Event d = new Event(s.substring(6, s.indexOf("/")),
                s.substring(s.indexOf("/at") + 3));
        String ans = d.toString();
        assertEquals("[E][ ] Greet the users  (at:2021-11-25 2000)", ans);
    }

    @Test public void toStringConvertTest(){
        String s = "event sleep /at 2020-11-11 1800";
        Event d = new Event(s.substring(6, s.indexOf("/")),
                s.substring(s.indexOf("/at") + 3));
        String ans = d.toStringConvert();
        assertEquals("E | 0 | sleep | 2020-11-11 1800", ans);
    }
}