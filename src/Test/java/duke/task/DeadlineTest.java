package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DateTimeException;

import org.junit.jupiter.api.Test;

import duke.exception.EmptyFieldException;
import duke.exception.InvalidCommandException;

class DeadlineTest {

    @Test
    void testSaveToFile_success() throws EmptyFieldException, InvalidCommandException {
        Deadline deadlineWithDateTime = new Deadline("read book", "2021-09-18 18:00");
        assertEquals("D | 0 | read book | 2021-09-18 18:00", deadlineWithDateTime.toFileStringFormat());

        deadlineWithDateTime.setIsDone(true);
        assertEquals("D | 1 | read book | 2021-09-18 18:00", deadlineWithDateTime.toFileStringFormat());

        Deadline deadlineWithDate = new Deadline("read book", "2021-09-18");
        assertEquals("D | 0 | read book | 2021-09-18 23:59", deadlineWithDate.toFileStringFormat());
    }

    @Test
    void saveToFile_exceptionThrown() throws EmptyFieldException, InvalidCommandException {
        try {
            //empty description and by
            new Deadline("", "").toFileStringFormat();
        } catch (EmptyFieldException exception) {
            assertEquals("Error! Description or date and time is empty!", exception.getMessage());
        }

        try {
            //more than 1 date and time in by
            new Deadline("read book", "2021-12-08 18:00 18:00").toFileStringFormat();
        } catch (InvalidCommandException exception) {
            assertEquals("Error! You can only enter one date and time, Eg: \"2021-09-12 18:00\",\n"
                            + "one date, Eg: \"2021-09-12\" (This will enter time as 23:59 by default),\n"
                            + "or one time, Eg: \"18:00\" (This will enter today's date by default)",
                    exception.getMessage());
        }

        //invalid date
        assertThrows(DateTimeException.class, () ->
                new Deadline("read book", "2021-13-13").toFileStringFormat());

        //invalid time
        assertThrows(DateTimeException.class, () ->
                new Deadline("read book", "25:00").toFileStringFormat());

        //non 24hr time
        assertThrows(DateTimeException.class, () ->
                new Deadline("read book", "2 pm").toFileStringFormat());

        //invalid date and time
        assertThrows(DateTimeException.class, () ->
                new Deadline("read book", "2021-13-13 25:00").toFileStringFormat());

    }

    @Test
    void testToString_success() throws EmptyFieldException, InvalidCommandException {
        Deadline deadlineWithDateTime = new Deadline("read book", "2021-09-18 18:00");
        assertEquals("[D][ ] read book (by: September 18 2021, 6:00 PM)", deadlineWithDateTime.toString());

        deadlineWithDateTime.setIsDone(true);
        assertEquals("[D][X] read book (by: September 18 2021, 6:00 PM)", deadlineWithDateTime.toString());

        Deadline deadlineWithDate = new Deadline("read book", "2021-09-18");
        assertEquals("[D][ ] read book (by: September 18 2021, 11:59 PM)", deadlineWithDate.toString());
    }

    @Test
    void toString_exceptionThrown() throws EmptyFieldException, InvalidCommandException {
        try {
            //empty description and by
            new Deadline("", "").toString();
        } catch (EmptyFieldException exception) {
            assertEquals("Error! Description or date and time is empty!", exception.getMessage());
        }

        try {
            //more than 1 date and time in by
            new Deadline("read book", "2021-12-08 18:00 18:00").toString();
        } catch (InvalidCommandException exception) {
            assertEquals("Error! You can only enter one date and time, Eg: \"2021-09-12 18:00\",\n"
                            + "one date, Eg: \"2021-09-12\" (This will enter time as 23:59 by default),\n"
                            + "or one time, Eg: \"18:00\" (This will enter today's date by default)",
                    exception.getMessage());
        }

        //invalid date
        assertThrows(DateTimeException.class, () ->
                new Deadline("read book", "2021-13-13").toString());

        //invalid time
        assertThrows(DateTimeException.class, () ->
                new Deadline("read book", "25:00").toString());

        //non 24hr time
        assertThrows(DateTimeException.class, () ->
                new Deadline("read book", "2 pm").toString());

        //invalid date and time
        assertThrows(DateTimeException.class, () ->
                new Deadline("read book", "2021-13-13 25:00").toString());

    }

}
