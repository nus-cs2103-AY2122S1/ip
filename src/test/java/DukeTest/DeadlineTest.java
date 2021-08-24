package DukeTest;

import DukePakage.Deadline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toTxtTest() {
        Deadline deadline = new Deadline("Return book", "2021-08-24 1600");
        assertEquals("D | 0 | Return book | Aug 24 2021 4.00 PM", deadline.toTxt());
    }
}
