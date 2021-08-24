package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void getTime_emptyInput_correctLocalDate() {
        assertEquals(
                LocalDate.of(2020, 10, 15),
                new Event("event 1", LocalDate.of(2020, 10, 15)).getTime()
        );
        assertEquals(
                LocalDate.of(2020, 10, 15),
                new Event("event 1", LocalDate.of(2020, 10, 15), true)
                        .getTime()
        );
    }

    @Test
    public void getSaveFormat_emptyInput_correctSaveFormat() {
        assertEquals(
                "E|i|event 1|15/10/2020",
                new Event("event 1", LocalDate.of(2020, 10, 15)).getSaveFormat()
        );
        assertEquals(
                "E|c|event 1|15/10/2020",
                new Event("event 1", LocalDate.of(2020, 10, 15), true)
                        .getSaveFormat()
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
                new Event("event 1", LocalDate.of(2020, 10, 15), true)
                        .getName()
        );
    }

    @Test
    public void completeTask_emptyInput_true() {
        assertEquals(
                true,
                new Event("event 1", LocalDate.of(2020, 10, 15)).completeTask()
        );
        assertEquals(
                true,
                new Event("event 1", LocalDate.of(2020, 10, 15), true)
                        .completeTask()
        );
    }

    @Test
    public void isCompleted_emptyInput_correctCompletion() {
        assertEquals(
                false,
                new Event("event 1", LocalDate.of(2020, 10, 15)).isCompleted()
        );
        assertEquals(
                true,
                new Event("event 1", LocalDate.of(2020, 10, 15), true)
                        .isCompleted()
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
                new Event("event 1", LocalDate.of(2020, 10, 15), true)
                        .getTaskType()
        );
    }
}
