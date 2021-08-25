package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void formatTimingTest(){
        String formattedDate = Parser.parseTiming("2020-09-30 1800");
        assertEquals("Sep 30 2020 6pm", formattedDate);
    }

    @Test
    public void deadlineTaskTest() {
        Deadline deadline = new Deadline("do homework /by 2020-09-30 1900");
        assertEquals("[D][ ] do homework (by: Sep 30 2020 7pm)", deadline.toString());
    }
}