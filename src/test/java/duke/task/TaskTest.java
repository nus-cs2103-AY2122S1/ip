package duke.task;

import duke.exceptions.CommandParamException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void todoTest_fromCommandLineInput_sameResult() {
        assertEquals("todo go jogging", new ToDo("go jogging").fullCommand());
    }

    @Test
    public void deadlineTest_fromCommandLineInput_sameResult() throws CommandParamException {
        assertEquals("deadline go jogging /by 2021-08-21 2359",
                new Deadline("go jogging","2021-08-21 2359").fullCommand());
    }

    @Test
    public void eventTest_fromCommandLineInput_sameResult() throws CommandParamException {
        assertEquals("event go jogging /at 2021-08-21 2359",
                new Event("go jogging","2021-08-21 2359").fullCommand());
    }

}
