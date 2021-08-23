package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {
    @Test
    public void getFileRepresentation_notDoneDeadlineTask_success() {
        String taskDescription = "Task description";
        LocalDateTime time = LocalDateTime.parse("2012-12-21T12:34:56");
        DeadlineTask deadlineTask = new DeadlineTask(taskDescription, time);
        assertEquals("D 0 Task description /by 201212211234", deadlineTask.getFileRepresentation());
    }

    @Test
    public void getFileRepresentation_doneDeadlineTask_success() {
        String taskDescription = "Task description";
        LocalDateTime time = LocalDateTime.parse("2012-12-21T12:34:56");
        DeadlineTask deadlineTask = new DeadlineTask(taskDescription, time);
        deadlineTask.markDone();
        assertEquals("D 1 Task description /by 201212211234", deadlineTask.getFileRepresentation());
    }
}
