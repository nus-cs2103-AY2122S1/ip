package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateStringTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void constructor_invalidDate_silentDefaultToString() {
        DateString dateString = new DateString("blah");
        assertEquals(dateString.toString(), "blah");
    }

    @Test
    public void constructor_validDate_autoReformat(){
        DateString dateString = new DateString("2019-10-15");
        assertEquals(dateString.toString(), "Oct 15 2019");
    }

}
