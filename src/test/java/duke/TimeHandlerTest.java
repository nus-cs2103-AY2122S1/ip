package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeHandlerTest {

    @Test
    public void parseTimeTest(){
        assertEquals(TimeHandler.parseTime("1200"), "12:00 PM");
        assertEquals(TimeHandler.parseTime("2359"), "11:59 PM");
        assertEquals(TimeHandler.parseTime("0000"), "12:00 AM");
        assertEquals(TimeHandler.parseTime("1300"), "1:00 PM");
        assertEquals(TimeHandler.parseTime("2030"), "8:30 PM");
        assertEquals(TimeHandler.parseTime("2103"), "9:03 PM");
    }

    @Test
    public void parseDateTest(){
        assertEquals(TimeHandler.parseDate("1/1/2000"), "1 Jan 2000");
        assertEquals(TimeHandler.parseDate("12/12/2020"), "12 Dec 2020");
        assertEquals(TimeHandler.parseDate("31/12/2021"), "31 Dec 2021");
        assertEquals(TimeHandler.parseDate("31/12/1999"), "31 Dec 1999");
        assertEquals(TimeHandler.parseDate("01/01/2022"), "1 Jan 2022");
        assertEquals(TimeHandler.parseDate("16/9/2021"), "16 Sep 2021");
    }

    @Test
    public void parseTest(){
        assertEquals(TimeHandler.parse("16/9/2021 2100"), "16 Sep 2021 9:00 PM");
    }

}
