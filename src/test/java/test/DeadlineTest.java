package test;

import lebron.exception.LebronException;
import lebron.task.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void toFile() throws LebronException {
        Deadline deadline = new Deadline("work", "2021-08-30", "0830");
        assertEquals("D | 0 | work | 2021-08-30 0830", deadline.getStringForFile());
    }
}
