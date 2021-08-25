package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void test1() {
        assertEquals("[E][X] Celebrate End of uni (at: Oct 10 2020 0500PM)" ,
                new Event("Celebrate End of uni", "2020-10-10 17:00").done());
    }

    @Test
    public void test2() {
        assertEquals("E | 0 | Adopt kat | Oct 10 2020" ,new Event("Adopt kat", "2020-10-10").write());
    }

    @Test
    public void test3() {
        assertEquals("[E][ ] Slap brother (at: Jan 10 2021 0900PM)" ,new Event("Slap brother", "2021-01-10 21:00").toString());
    }
}
