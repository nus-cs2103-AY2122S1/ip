package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {

    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy kkmm");
    private String testTimeInput = "11/11/1111 1111";
    private LocalDateTime testTime = LocalDateTime.parse(testTimeInput.trim(), inputFormatter);
    private DeadlineTask testTask = new DeadlineTask("Test", testTime);

    @Test
    public void testGetTaskState() {
        assertEquals(testTask.getTaskState(), "[D][ ] Test (By: Nov 11 1111 - 11:11 AM)");
    }

    @Test
    public void testConvertToStorageFormat() {
        assertEquals(testTask.convertToStorageFormat(), "D,0,Test ,Nov 11 1111 - 11:11 AM");
    }
}
