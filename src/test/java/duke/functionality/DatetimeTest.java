package duke.functionality;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DatetimeTest {
    @Test
    public void getDatetimeStringTest() {
        Datetime datetime = new Datetime("2021-09-19");
        assertEquals("2021-09-19", datetime.getDatetimeString());
    }
}
