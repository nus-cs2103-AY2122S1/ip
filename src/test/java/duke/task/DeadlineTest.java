package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void getTime_emptyInput_correctLocalDate() {
        assertEquals(
                LocalDate.of(2020, 10, 15),
                new Deadline("deadline 1", LocalDate.of(2020, 10, 15)).getTime()
        );
        assertEquals(
                LocalDate.of(2020, 10, 15),
                new Deadline("deadline 1", LocalDate.of(2020, 10, 15), true)
                        .getTime()
        );
    }

    @Test
    public void getSaveFormat_emptyInput_correctSaveFormat() {
        assertEquals(
                "D|i|deadline 1|15/10/2020",
                new Deadline("deadline 1", LocalDate.of(2020, 10, 15)).getSaveFormat()
        );
        assertEquals(
                "D|c|deadline 1|15/10/2020",
                new Deadline("deadline 1", LocalDate.of(2020, 10, 15), true)
                        .getSaveFormat()
        );
    }

    @Test
    public void getName_emptyInput_correctName() {
        assertEquals(
                "deadline 1",
                new Deadline("deadline 1", LocalDate.of(2020, 10, 15)).getName()
        );
        assertEquals(
                "deadline 1",
                new Deadline("deadline 1", LocalDate.of(2020, 10, 15), true)
                        .getName()
        );
    }

    @Test
    public void completeTask_emptyInput_true() {
        assertEquals(
                true,
                new Deadline("deadline 1", LocalDate.of(2020, 10, 15)).completeTask()
        );
        assertEquals(
                true,
                new Deadline("deadline 1", LocalDate.of(2020, 10, 15), true)
                        .completeTask()
        );
    }

    @Test
    public void isCompleted_emptyInput_correctCompletion() {
        assertEquals(
                false,
                new Deadline("deadline 1", LocalDate.of(2020, 10, 15)).hasCompleted()
        );
        assertEquals(
                true,
                new Deadline("deadline 1", LocalDate.of(2020, 10, 15), true)
                        .hasCompleted()
        );
    }

    @Test
    public void getTaskType_emptyInput_stringD() {
        assertEquals(
                "D",
                new Deadline("deadline 1", LocalDate.of(2020, 10, 15)).getTaskType()
        );
        assertEquals(
                "D",
                new Deadline("deadline 1", LocalDate.of(2020, 10, 15), true)
                        .getTaskType()
        );
    }
}
