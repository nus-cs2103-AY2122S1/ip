package virushade.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        assertEquals("[D][ ] DeadlineTask /by: 20 Aug 2021 12:01PM",
                new Deadline("DeadlineTask", "2021-08-20 1201", false).toString());
    }
}
