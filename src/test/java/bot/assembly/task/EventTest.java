package bot.assembly.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testEventToString() {
        assertEquals("[E][ ] testEvent (at: 2021-01-01 23:59:59)",
                new Event("testEvent",
                        LocalDateTime.parse(
                                "2021-01-01T23:59:59",
                                DateTimeFormatter.ISO_LOCAL_DATE_TIME
                        )
                ).toString()
        );
    }

    @Test
    public void testEventGetDateTime() {
        LocalDateTime testDateTime = LocalDateTime.parse(
                "2021-01-01T23:59:59",
                DateTimeFormatter.ISO_LOCAL_DATE_TIME
        );

        assertEquals(testDateTime,
                new Event("testEvent",
                        LocalDateTime.parse(
                                "2021-01-01T23:59:59",
                                DateTimeFormatter.ISO_LOCAL_DATE_TIME
                        )
                ).getDateTime()
        );
    }

    @Test
    public void testEventGetDate() {
        LocalDate localDate = LocalDateTime.parse(
                "2021-01-01T23:59:59",
                DateTimeFormatter.ISO_LOCAL_DATE_TIME
        ).toLocalDate();

        assertEquals(localDate,
                new Event("testEvent",
                        LocalDateTime.parse(
                                "2021-01-01T23:59:59",
                                DateTimeFormatter.ISO_LOCAL_DATE_TIME
                        )
                ).getDate()
        );

    }

    @Test
    public void testEventGetTime() {
        LocalTime localTime = LocalDateTime.parse(
                "2021-01-01T23:59:59",
                DateTimeFormatter.ISO_LOCAL_DATE_TIME
        ).toLocalTime();

        assertEquals(localTime,
                new Event("testEvent",
                        LocalDateTime.parse(
                                "2021-01-01T23:59:59",
                                DateTimeFormatter.ISO_LOCAL_DATE_TIME
                        )
                ).getTime()
        );
    }

    @Test
    public void testEventIsDone() {
        Event testEvent = new Event("testEvent",
                LocalDateTime.parse(
                        "2021-01-01T23:59:59",
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME
                )
        );
        assertEquals(false, testEvent.getIsDone());

        testEvent.markAsDone();

        assertEquals(true, testEvent.getIsDone());
    }

    @Test
    public void testEventDoneEventToString() {
        Event testEvent = new Event("testEvent",
                LocalDateTime.parse(
                        "2021-01-01T23:59:59",
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME
                )
        );
        testEvent.markAsDone();
        assertEquals("[E][X] testEvent (at: 2021-01-01 23:59:59)", testEvent.toString());
    }

    @Test
    public void testEventGetTaskTitle() {
        Event testEvent = new Event("testEvent",
                LocalDateTime.parse(
                        "2021-01-01T23:59:59",
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME
                )
        );
        assertEquals("testEvent", testEvent.getTaskTitle());
    }

    @Test
    public void testEventGetTaskType() {
        Event testEvent = new Event("testEvent",
                LocalDateTime.parse(
                        "2021-01-01T23:59:59",
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME
                )
        );
        assertEquals("E", testEvent.getTaskType());
    }
}
