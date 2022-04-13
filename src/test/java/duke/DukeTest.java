package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void test1() {
        String userInput = "deadline homework by 2021-02-02 1200";
        Deadline test = new Deadline(userInput);
        String output = test.getDescription();
        assertEquals("homework (by 02 Feb 2021 12:00PM)", output);
    }

    @Test
    public void test2() {
        String userInput = "event party at 2021-09-09 1500";
        Event test = new Event(userInput);
        String output = test.printTask();
        assertEquals("[E][ ] party (at 09 Sep 2021 3:00PM)", output);
    }
}
