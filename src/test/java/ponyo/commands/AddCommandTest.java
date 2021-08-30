package ponyo.commands;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import ponyo.data.task.Deadline;

public class AddCommandTest {
    @Test
    public void addCommand_invalidDateForDeadline_throwsException() {
        final String[] invalidDates = {"2020/10/12"};
        for (String date : invalidDates) {
            try {
                new Deadline("example description", date);
            } catch (DateTimeParseException e) {
                return;
            }
        }
        fail("A task has been added with an invalid date.");
    }

    @Test
    public void addCommand_invalidDescription_throwsException() {
        final String[] invalidDescription = {""};
        for (String desc : invalidDescription) {
            try {
                new Deadline(desc, "2020-10-12");
            } catch (ArrayIndexOutOfBoundsException e) {
                return;
            }
        }
        fail("A task has been added with an invalid description.");
    }
}
