package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Period;

public class PeriodTest {
    @Test
    public void toDataFormat() {
        Period period = new Period("Finish iP", "22 Aug 2021", "3 Sep 2021");
        assertEquals("P | 0 | Finish iP |  | 22 Aug 2021 | 3 Sep 2021", period.toDataFormat());
    }

    @Test
    public void stringRepresentation() {
        Period period = new Period("Finish iP", "22 Aug 2021", "3 Sep 2021");
        assertEquals("[P][ ] Finish iP (starting: 22 Aug 2021, ending: 3 Sep 2021)", period.toString());
    }

    @Test
    public void dateParse() {
        Period period = new Period("Finish iP", "2021-11-12", "2021-12-25");
        assertEquals("P | 0 | Finish iP |  | Nov 12 2021 | Dec 25 2021", period.toDataFormat());
    }
}
