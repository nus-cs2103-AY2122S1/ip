package janet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void parseByDateTest_validDateString() {
        assertEquals(new Deadline("", "2020-12-31").parseByDate(), "Dec 31 2020");
    }

    @Test
    public void toStringTest_validDateString() {
        assertEquals(new Deadline("Task", "2019-11-30").toString(), "[D][ ] Task (by: Nov 30 2019)");
    }

    @Test
    public void toStringTest_otherString() {
        assertEquals(new Deadline("Task", "20191130").toString(), "[D][ ] Task (by: 20191130)");
    }
}
