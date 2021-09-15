package duke;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ParserTest {

    @Test
    void isValidDate_validDate_true() {
        assertTrue(Parser.isValidDate("1999-03-29", DateTimeFormatter.ISO_LOCAL_DATE));
    }

    @Test
    void isValidDate_invalidDate_false() {
        assertFalse(Parser.isValidDate("1999/03/29", DateTimeFormatter.ISO_LOCAL_DATE));
    }

    @Test
    void isValidTime_validTime_true() {
        assertTrue(Parser.isValidTime("23:59", DateTimeFormatter.ISO_LOCAL_TIME));
    }

    @Test
    void isValidTime_invalidTime_false() {
        assertFalse(Parser.isValidTime("2359", DateTimeFormatter.ISO_LOCAL_TIME));
    }

    @Test
    void isRemove_correctForm_true() {
        assertTrue(Parser.isDelete("remove 2"));
    }

    @Test
    void isRemove_incorrectForm_false() {
        assertFalse(Parser.isDelete("remove2"));
    }

    @Test
    void isDone_correctFormAndLength_true() {
        assertTrue(Parser.isDone("done "));
    }

    @Test
    void isDone_incorrectForm_false() {
        assertFalse(Parser.isDone("done2"));
    }
}
