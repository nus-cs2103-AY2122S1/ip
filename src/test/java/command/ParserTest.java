package command;

import duke.command.InputTypes;
import duke.command.Parser;
import duke.task.Deadline;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void judgeTypeTest() {
        String invalidInput = "This is not a valid input";
        String validInput1 = "deadline go to school /by 2021-08-10 00:00";
        String validInput2 = "todo Finish ip";
        assertEquals(InputTypes.UNKNOWN, Parser.judgeType(invalidInput));
        assertEquals(InputTypes.DEADLINE, Parser.judgeType(validInput1));
        assertEquals(InputTypes.TODO, Parser.judgeType(validInput2));
    }

    @Test
    public void isValidDeadlineTaskTest() throws Exception {
        String input1 = "deadline task1 /by 2020-12-08 09:00";
        String input2 = "deadline task2 /by 2010-12-08 20:00";
        Task expectedTask1 = new Deadline("task1", "2020-12-08 09:00");
        Task expectedTask2 = new Deadline("task2", "2010-12-08 20:00");
        assertEquals(expectedTask1.toString(), Parser.testDeadlineValidity(input1).toString());
        assertEquals(expectedTask2.toString(), Parser.testDeadlineValidity(input2).toString());
    }
}
