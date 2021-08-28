package java.task;

import main.java.task.DeadlineTask;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {

    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy kkmm");
    String testTimeInput = "11/11/1111 1111";
    LocalDateTime testTime = LocalDateTime.parse(testTimeInput.trim(), inputFormatter);
    DeadlineTask testTask = new DeadlineTask("Test", testTime);

    @Test
    public void testGetTaskState() {
        assertEquals(testTask.getTaskState(), "[D][ ] Test (By: Nov 11 1111 - 11:11 AM)");
    }

    @Test
    public void testConvertToStorageFormat() {
        assertEquals(testTask.convertToStorageFormat(), "D,0,Test ,Nov 11 1111 - 11:11 AM");
    }
}
