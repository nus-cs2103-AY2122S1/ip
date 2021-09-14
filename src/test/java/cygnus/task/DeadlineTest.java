package cygnus.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class DeadlineTest {

    private static final String dateInputFormat = "ddMMyy";

    @Test
    public void getBy_deadline_success() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateInputFormat);
        Deadline deadline = new Deadline("deadlineDescription", LocalDate.parse("160921", dateFormatter));
        assertTrue(deadline.getBy().isEqual(LocalDate.of(2021, 9, 16)));
    }

    @Test
    public void stringConversion_doneDeadline_success() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateInputFormat);
        Deadline deadline = new Deadline("deadlineDescription", LocalDate.parse("160921", dateFormatter));
        deadline.setDone();
        assertEquals("[D][X] deadlineDescription | by: Thu 16 Sep 2021", deadline.toString());
    }

}