package task;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        Task deadlineOne = new Deadline(TaskType.DEADLINE,"CS2100 Lab 4", "2021-09-14");
        assertEquals("[D] [ ] CS2100 Lab 4 (by: Sep 14 2021)", deadlineOne.toString());

        Task deadlineTwo = new Deadline(TaskType.DEADLINE,"CS2100 Assignment 1", "2021-09-15 1300");
        assertEquals("[D] [ ] CS2100 Assignment 1 (by: Sep 15 2021, 01:00 PM)", deadlineTwo.toString());
    }
}
