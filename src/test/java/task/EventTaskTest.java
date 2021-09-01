package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTaskTest {

    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy kkmm");
    private String testTimeInput = "11/11/1111 1111";
    private LocalDateTime testTime = LocalDateTime.parse(testTimeInput.trim(), inputFormatter);
    private EventTask testTask = new EventTask("Test", testTime);

    @Test
    public void testGetTaskState() {
        assertEquals(testTask.getTaskState(), "[E][ ] Test (At: Nov 11 1111 - 11:11 AM)");
    }

    @Test
    public void testConvertToStorageFormat() {
        assertEquals(testTask.convertToStorageFormat(), "E,0,Test ,Nov 11 1111 - 11:11 AM");
    }
}
