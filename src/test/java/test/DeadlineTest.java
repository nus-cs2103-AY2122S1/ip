package test;

import lebron.task.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void toFile() {
        Deadline deadline = new Deadline("work", "2021-08-30");
        assertEquals("D | 0 | work | 2021-08-30", deadline.getStringForFile());
    }
}
