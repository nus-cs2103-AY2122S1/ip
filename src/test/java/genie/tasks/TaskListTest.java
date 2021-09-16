package genie.tasks;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void invalidDateForDeadlineTask() {
        final String invalidDate = "12/12/2000";
        try {
            new Deadline("test description", invalidDate);
        } catch (DateTimeParseException e) {
            return;
        }
        fail("The task you added has an invalid date");
    }

    @Test
    public void invalidDescriptionForDeadlineTask() {
        final String invalidDescription = "";
        try {
            new Deadline(invalidDescription, "2000-20-20");
        } catch (DateTimeParseException e) {
            return;
        }
        fail("The task you added has an invalid date");
    }
}
