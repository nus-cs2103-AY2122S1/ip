package meow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addEvent_correctInput_returnEventString() {
        assertEquals("E | 0 | team meeting | Sunday 1600",
                new TaskList().addEvent("team meeting", "Sunday 1600").toString());
    }
}
