package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlinesTest {
    @Test
    public void testSampleDeadlinesPersistedData_toString_success() {
        Deadlines sampleDeadline = new Deadlines("return book", "2021-10-11", false);

        assertEquals("D,0,return book,2021-10-11", sampleDeadline.persistedDataStringFormat(),
                "Testing whether the persisted data string format is correct.");

        assertEquals("[D][ ] return book (by: 11 Oct 2021)", sampleDeadline.toString());
    }
}
