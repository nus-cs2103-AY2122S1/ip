package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTimeTest {

    @Test
    public void test_1a() {
        assertEquals("Oct 10 2020", TaskTime.convertDateTimeFormat("2020-10-10"));
    }

    @Test
    public void test_1b() {
        assertEquals("Oct 10 2020 0600PM", TaskTime.convertDateTimeFormat("2020-10-10 18:00"));
    }

    @Test
    public void test_2a() {
        assertEquals("2021-10-10", TaskTime.unconvertDateTime("Oct 10 2021"));
    }

    @Test
    public void test_2b() {
        assertEquals("2020-10-10 17:00", TaskTime.unconvertDateTime("Oct 10 2020 0500PM"));
    }
}
