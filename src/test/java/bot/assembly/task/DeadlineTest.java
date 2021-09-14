package bot.assembly.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testDeadlineToString() {
        assertEquals("[D][ ] testDeadline (by: 2021-01-01 23:59:59)",
                new Deadline("testDeadline",
                        LocalDateTime.parse(
                                "2021-01-01T23:59:59",
                                DateTimeFormatter.ISO_LOCAL_DATE_TIME
                        )
                ).toString()
        );
    }

    @Test
    public void testDeadlineGetDateTime() {
        LocalDateTime testDateTime = LocalDateTime.parse(
                "2021-01-01T23:59:59",
                DateTimeFormatter.ISO_LOCAL_DATE_TIME
        );

        assertEquals(testDateTime,
                new Deadline("testDeadline",
                        LocalDateTime.parse(
                                "2021-01-01T23:59:59",
                                DateTimeFormatter.ISO_LOCAL_DATE_TIME
                        )
                ).getDateTime()
        );
    }

    @Test
    public void testDeadlineGetDate() {
        LocalDate localDate = LocalDateTime.parse(
                "2021-01-01T23:59:59",
                DateTimeFormatter.ISO_LOCAL_DATE_TIME
        ).toLocalDate();

        assertEquals(localDate,
                new Deadline("testDeadline",
                        LocalDateTime.parse(
                                "2021-01-01T23:59:59",
                                DateTimeFormatter.ISO_LOCAL_DATE_TIME
                )
        ).getDate());

    }

    @Test
    public void testDeadlineGetTime() {
        LocalTime localTime = LocalDateTime.parse(
                "2021-01-01T23:59:59",
                DateTimeFormatter.ISO_LOCAL_DATE_TIME
        ).toLocalTime();

        assertEquals(localTime,
                new Deadline("testDeadline",
                        LocalDateTime.parse(
                                "2021-01-01T23:59:59",
                                DateTimeFormatter.ISO_LOCAL_DATE_TIME
                        )
                ).getTime());
    }

    @Test
    public void testDeadlineIsDone() {
        Deadline testDeadline = new Deadline("testDeadline",
                LocalDateTime.parse(
                        "2021-01-01T23:59:59",
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME
                )
        );
        assertEquals(false, testDeadline.getIsDone());

        testDeadline.markAsDone();

        assertEquals(true, testDeadline.getIsDone());
    }

    @Test
    public void testDeadlineDoneDeadlineToString() {
        Deadline testDeadline = new Deadline("testDeadline",
                LocalDateTime.parse(
                        "2021-01-01T23:59:59",
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME
                )
        );
        testDeadline.markAsDone();
        assertEquals("[D][X] testDeadline (by: 2021-01-01 23:59:59)", testDeadline.toString());
    }

    @Test
    public void testDeadlineGetTaskTitle() {
        Deadline testDeadline = new Deadline("testDeadline",
                LocalDateTime.parse(
                        "2021-01-01T23:59:59",
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME
                )
        );
        assertEquals("testDeadline", testDeadline.getTaskTitle());
    }

    @Test
    public void testDeadlineGetTaskType() {
        Deadline testDeadline = new Deadline("testDeadline",
                LocalDateTime.parse(
                        "2021-01-01T23:59:59",
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME
                )
        );
        assertEquals("D", testDeadline.getTaskType());
    }
}
