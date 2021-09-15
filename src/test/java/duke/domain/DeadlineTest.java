package duke.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {
    static final String DEADLINE_NAME = "Some Deadline";
    static final String DATE_STRING = "3/5/2052 1700";
    static final String DAY = "3/5/2052";
    Deadline deadline;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        deadline = null;
    }

    @Test
    void isDueOn() {
        deadline = new Deadline(DEADLINE_NAME, DATE_STRING);
        assertTrue(deadline.isDueOn(DAY));
    }
}
