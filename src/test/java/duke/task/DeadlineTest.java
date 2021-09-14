package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void encodeTaskForStorage_notDoneDeadlineTask_encodedString() {
        assertEquals("D | 0 | testingDeadlineTaskOne | 2021-08-26",
                new Deadline("testingDeadlineTaskOne",
                        LocalDate.of(2021, 8, 26),
                false)
                        .encodeTaskForStorage());
    }

    @Test
    public void encodeTaskForStorage_doneDeadlineTask_encodedString() {
        assertEquals("D | 1 | testingDeadlineTaskTwo | 2021-08-26",
                new Deadline("testingDeadlineTaskTwo",
                        LocalDate.of(2021, 8, 26),
                        true)
                        .encodeTaskForStorage());
    }

    @Test
    public void toString_notDoneDeadlineTask_string() {
        assertEquals("[D][ ] testingDeadlineTaskThree (by: Aug 26 2021)",
                new Deadline("testingDeadlineTaskThree",
                        LocalDate.of(2021, 8, 26),
                        false).toString());
    }

    @Test
    public void toString_doneDeadlineTask_string() {
        assertEquals("[D][X] testingDeadlineTaskFour (by: Aug 26 2021)",
                new Deadline("testingDeadlineTaskFour",
                        LocalDate.of(2021, 8, 26),
                        true).toString());
    }
}
