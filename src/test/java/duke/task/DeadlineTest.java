package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        String input = "mumu /by 2021-09-10 2359";
        String expected = "[D][ ] mumu (10 Sep 2021 11.59PM)";
        try {
            Deadline deadline = new Deadline(input);
            assertEquals(expected, deadline.toString());
        } catch (DukeException e) {
            //This shouldn't happen
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getSaveTest() {
        String input = "mumu /by 2021-09-10 2359";
        String expected = "D1|mumu|2021-09-10 2359";
        try {
            Deadline deadline = new Deadline(input);
            deadline.setDone();
            assertEquals(expected, deadline.getSave());
        } catch (DukeException e) {
            //This shouldn't happen
            System.out.println(e.getMessage());
        }
    }
}
