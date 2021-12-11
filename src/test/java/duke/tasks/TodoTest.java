package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testSampleToDosPersistedData_toString_success() {
        ToDos sampleToDo = new ToDos("join sports club", true);

        assertEquals("T,1,join sports club", sampleToDo.persistedDataStringFormat(),
                "Testing whether the persisted data string format is correct.");

        assertEquals("[T][X] join sports club", sampleToDo.toString());
    }
}
