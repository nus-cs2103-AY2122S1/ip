package ligma.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testToString() {
        String completeDeadline = "[D][X] push code (by: Aug 29 2021)";
        String uncompleteTodo = "[T][ ] call mom";
        assertEquals(completeDeadline,
                Deadline.createDeadline("push code /by 2021-08-29")
                        .markAsDone()
                        .toString());
        assertEquals(uncompleteTodo,
                new Todo("call mom").toString());
    }
}
