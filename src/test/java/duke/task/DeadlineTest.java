package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineInstanceCreationDateAndTime_createGeneralInstance_success() {
        String expected = "[D][ ] test sentence(23 AUGUST 2021 7.30 PM)";
        Deadline deadline = new Deadline("test sentence" , "2021-08-23 1930", false);
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void deadlineInstanceCreationDate_createGeneralInstance_success() {
        String expected = "[D][ ] test sentence(23 AUGUST 2021)";
        Deadline deadline = new Deadline("test sentence", "2021-08-23", false);
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void deadlineInstanceCreationOthers_createGeneralInstance_success() {
        String expected = "[D][ ] test sentence(by sunday or something)";
        Deadline deadline = new Deadline("test sentence", "by sunday or something", false);
        assertEquals(expected, deadline.toString());
    }
}
