package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;



public class TaskTest {
    @Test
    public void taskTest_commandLineInput_sameOutput() {
        Task task = new Task("Task");
        assertEquals("[ ] Task", task.toString());
    }

    @Test
    public void markDoneTest_commandLineInput_sameOutput() {
        Task task = new Task("Task");
        task.markDone();
        assertEquals("[X] Task", task.toString());
    }

    @Test
    public void toDoTest_commandLineInput_sameOutput() {
        Task task = new ToDo("Todo");
        assertEquals("[T][ ] Todo", task.toString());
    }

    @Test
    public void eventTest_commandLineInput_sameOutput() {
        LocalDate date = LocalDate.now();
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = LocalTime.now().plusHours(1);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");

        Task task = new Event("event", date, startTime, endTime);
        assertEquals("[E][ ] event (at: "
                        + date.format(dateFormatter) + " "
                        + startTime.format(timeFormatter) + "-"
                        + endTime.format(timeFormatter) + ")",
                        task.toString());
    }

    @Test
    public void deadlineTest_commandLineInput_sameOutput() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");

        Task task = new Deadline("deadline", dateTime);
        assertEquals("[D][ ] deadline (by: "
                        + dateTime.format(dateTimeFormatter) + ")",
                task.toString());
    }
}
