package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void getTime_emptyInput_correctLocalDate() {
        assertEquals(
                LocalDate.of(2020, 10, 15),
                new Event("event 1", LocalDate.of(2020, 10, 15)).getTime()
        );
        assertEquals(
                LocalDate.of(2020, 10, 15),
                new Event(
                        "event 1",
                        LocalDate.of(2020, 10, 15),
                        true,
                        new String[Task.MAX_TAGS]
                ).getTime()
        );
    }

    @Test
    public void getSaveFormat_emptyInput_correctSaveFormat() {
        assertEquals(
                "E|i|event 1||15/10/2020",
                new Event("event 1", LocalDate.of(2020, 10, 15)).getSaveFormat()
        );
        assertEquals(
                "E|c|event 1||15/10/2020",
                new Event(
                        "event 1",
                        LocalDate.of(2020, 10, 15),
                        true,
                        new String[Task.MAX_TAGS]
                ).getSaveFormat()
        );
    }

    @Test
    public void getName_emptyInput_correctName() {
        assertEquals(
                "event 1",
                new Event("event 1", LocalDate.of(2020, 10, 15)).getName()
        );
        assertEquals(
                "event 1",
                new Event(
                        "event 1",
                        LocalDate.of(2020, 10, 15),
                        true,
                        new String[Task.MAX_TAGS]
                ).getName()
        );
    }

    @Test
    public void completeTask_emptyInput_true() {
        assertTrue(
                new Event("event 1", LocalDate.of(2020, 10, 15)).completeTask()
        );
        assertTrue(
                new Event(
                        "event 1",
                        LocalDate.of(2020, 10, 15),
                        true,
                        new String[Task.MAX_TAGS]
                ).completeTask()
        );
    }

    @Test
    public void isCompleted_emptyInput_correctCompletion() {
        assertFalse(
                new Event("event 1", LocalDate.of(2020, 10, 15)).hasCompleted()
        );
        assertTrue(
                new Event(
                        "event 1",
                        LocalDate.of(2020, 10, 15),
                        true,
                        new String[Task.MAX_TAGS]
                ).hasCompleted()
        );
    }

    @Test
    public void getTaskType_emptyInput_stringE() {
        assertEquals(
                "E",
                new Event("event 1", LocalDate.of(2020, 10, 15)).getTaskType()
        );
        assertEquals(
                "E",
                new Event("event 1",
                        LocalDate.of(2020, 10, 15),
                        true,
                        new String[Task.MAX_TAGS]
                ).getTaskType()
        );
    }
}
