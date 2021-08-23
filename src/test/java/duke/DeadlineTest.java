package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest(){
        String input = "deadline Greet the users /by 2021-11-25 2000";
        Deadline d = new Deadline(input.substring(9, input.indexOf("/")), input.substring(input.indexOf("/by") + 3));
        String ans = d.toString();
        assertEquals("[D][ ] Greet the users  (by:2021-11-25 2000)", ans);
    }

    @Test public void toStringConvertTest(){
        String input = "deadline sleep /by 2020-11-11 1800";
        Deadline d = new Deadline(input.substring(9, input.indexOf("/")), input.substring(input.indexOf("/by") + 3));
        String ans = d.toStringConvert();
        assertEquals("D | 0 | sleep | 2020-11-11 1800", ans);
    }
}
