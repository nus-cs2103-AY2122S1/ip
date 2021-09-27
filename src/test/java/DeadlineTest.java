import duke.tasks.Deadline;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void Subtest1() {
        Deadline deadline = new Deadline(
                                    "Finish 2103 Homework", 
                                    "Apr 16 2021 6PM"
                                );
        assertEquals("[D][ ] Finish 2103 Homework (by: Apr 16 2021 6PM)", deadline.toString());
    }

    @Test
    public void Subtest2() {
        Deadline deadline = new Deadline(
                                    "Finish 2103 Homework", 
                                    "Apr 16 2021 6PM"
                                );
        deadline.setDone();
        assertEquals("[D][X] Finish 2103 Homework (by: Apr 16 2021 6PM)", deadline.toString());
    }

    @Test
    public void Subtest3() {
        Deadline deadline = new Deadline(
                                    "Group Project submission", 
                                    "Oct 15 2021 11PM"
                                );
        deadline.setDone();
        assertEquals("[D][X] Group Project submission (by: Oct 15 2021 11PM)", deadline.toString());
    }
}