package duchess.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * This class implements a JUnit Test for the Deadline class' methods.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class ToDoTest {
    /**
     * Tests the toFileFormat() method.
     */
    @Test
    public void testToFileFormat() {
        ToDo t = new ToDo("test");
        assertEquals(t.toFileFormat(), "Ttest,false");
        t.setDoneStatus(true);
        assertEquals(t.toFileFormat(), "Ttest,true");
    }
}
