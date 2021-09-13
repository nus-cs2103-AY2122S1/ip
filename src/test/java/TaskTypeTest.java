import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import iris.task.Deadline;
import iris.task.Event;
import iris.task.TaskType;
import iris.task.ToDo;

public class TaskTypeTest {

    @Test
    public void taskTypeTodo() {
        ToDo toDo = new ToDo("read book", false);
        assertEquals(TaskType.TODO, toDo.taskType);
    }

    @Test
    public void taskTypeDeadline() {
        String commandDetails = "return book /by 2021-08-31 15:00";
        int byIndex = commandDetails.indexOf("/by") - 1;
        String deadlineDetails = commandDetails.substring(0, byIndex);
        LocalDateTime by = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("by") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Deadline deadline = new Deadline(deadlineDetails, false, by);
        assertEquals(TaskType.DEADLINE, deadline.taskType);
    }

    @Test
    public void taskTypeEvent() {
        String commandDetails = "project meeting /at 2021-08-31 15:00";
        int atIndex = commandDetails.indexOf("/at") - 1;
        String eventDetails = commandDetails.substring(0, atIndex);
        LocalDateTime at = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("at") + 3),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Event event = new Event(eventDetails, false, at);
        assertEquals(TaskType.EVENT, event.taskType);
    }
}
