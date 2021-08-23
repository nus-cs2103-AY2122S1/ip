package meow;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void toAddEvent(){
        assertEquals("E | 0 | team meeting | Sunday 1600",
                new TaskList().addEvent("team meeting", "Sunday 1600").toString());
    }
}
