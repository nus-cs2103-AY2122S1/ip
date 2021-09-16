package meap.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private static final String TIME_IN_FUTURE1 = "12-12-2025 2359";
    private static final String TIME_IN_FUTURE2 = "12-12-2026 2359";


    @Test
    public void equalsMtd_sameObj_returnTrue() {
        Deadline deadline1 = Deadline.of("Eat a potato", TIME_IN_FUTURE1);
        Deadline deadline2 = Deadline.of("Eat a potato", TIME_IN_FUTURE1);
        assertEquals(deadline1.equals(deadline2), true);
    }

    @Test
    public void equalsMtd_sameTypeDiffDesc_returnFalse() {
        Deadline deadline1 = Deadline.of("Eat a potato", TIME_IN_FUTURE1);
        Deadline deadline2 = Deadline.of("Peel a banana", TIME_IN_FUTURE1);
        assertEquals(deadline1.equals(deadline2), false);

    }

    @Test
    public void equalsMtd_sameTypeDiffTime_returnFalse() {
        Deadline deadline1 = Deadline.of("Eat a potato", TIME_IN_FUTURE1);
        Deadline deadline2 = Deadline.of("Eat a potato", TIME_IN_FUTURE2);
        assertEquals(deadline1.equals(deadline2), false);

    }

    @Test
    public void equalsMtd_diffTaskObj_returnFalse() {
        Deadline deadline1 = Deadline.of("Eat a banana", TIME_IN_FUTURE1);
        ToDo toDo1 = ToDo.of("Eat a potato");
        assertEquals(deadline1.equals(toDo1), false);
    }
}