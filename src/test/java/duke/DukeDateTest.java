package duke;

import exceptions.DukeInvalidDateException;
import exceptions.DukeInvalidStorageTaskException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeDateTest {

    private static final String testDateInput = "24/12/2021";
    private static final String testDateTimeInput = "24/06/1999 0400";

    @Test
    public void recoverDateFromStorage() throws DukeInvalidDateException, DukeInvalidStorageTaskException {
        DukeDate date = DukeDate.getDukeDateFromStorage("24 Dec 2021", DukeDate.HAS_DATE);
        DukeDate testDate = DukeDate.of(testDateInput, true);
        assertEquals(date, testDate);
    }

    @Test
    public void recoverDateTimeFromStorage() throws DukeInvalidDateException, DukeInvalidStorageTaskException {
        DukeDate date = DukeDate.getDukeDateFromStorage("24 Jun 1999 0400", DukeDate.HAS_DATE_TIME);
        DukeDate testDate = DukeDate.of(testDateTimeInput, true);
        assertEquals(date, testDate);
    }
}
