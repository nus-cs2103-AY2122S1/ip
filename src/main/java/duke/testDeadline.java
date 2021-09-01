package duke;

import org.junit.Test;
//import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;


public class testDeadline {
    @Test
    public void testToString() {
        assertEquals("[D][ ] return book->by: Dec 12 2021",
                      new Deadline("return book", LocalDate.parse("2021-12-12")).toString());
        assertEquals("[D][ ] complete assignment->by: Oct 11 2021",
                      new Deadline("complete assignment", LocalDate.parse("2021-10-11")).toString());
    }

    @Test
    public void testSaveTask() {
        assertEquals("[D][ ] return book->by: 2021-12-12",
                new Deadline("return book", LocalDate.parse("2021-12-12")).saveTask());
        assertEquals("[D][ ] complete assignment->by: 2021-10-11",
                new Deadline("complete assignment", LocalDate.parse("2021-10-11")).saveTask());
    }

}
