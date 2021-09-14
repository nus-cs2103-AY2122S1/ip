import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import memocat.MemoCatException;
import memocat.Parser;
import memocat.task.Deadline;

public class ParserTest {

    private static final Parser p = new Parser();

    @Test
    public void getCommandAction_allCommand_commandStringReturned() {

        String command1 = "todo junit test";
        String command2 = "event junit test 2 /at 2021-08-25";
        String command3 = "deadline junit test 3 /at 2021-08-26";
        String command4 = "notRecognized command";

        Assertions.assertEquals("todo", p.getCommandAction(command1));
        Assertions.assertEquals("event", p.getCommandAction(command2));
        Assertions.assertEquals("deadline", p.getCommandAction(command3));
        Assertions.assertEquals("notRecognized", p.getCommandAction(command4));
    }

    @Test
    public void getCommandActionIndex_validCommand_indexReturned() throws MemoCatException {
        String command1 = "done 7";
        String command2 = "delete 3";

        Assertions.assertEquals(7, p.getCommandActionIndex(command1));
        Assertions.assertEquals(3, p.getCommandActionIndex(command2));
    }

    @Test
    public void getCommandActionIndex_invalidAction_throwmemoCatException() {
        String command = "invalid command";

        Assertions.assertThrows(MemoCatException.class, () -> p.getCommandActionIndex(command));

    }

    @Test
    public void commandToTask_testCommand_convertToTask() {
        String command = "deadline cs2103t ip /by 2021-08-24";

        try {
            Assertions.assertEquals(new Deadline("cs2103t ip", p.stringToLocalDate("2021-08-24")),
                    p.commandToTask(command));
        } catch (MemoCatException e) {
            Assertions.fail();
        }

    }

    @Test
    public void formatLocalDate_testDate_formatted() {
        LocalDate ld = LocalDate.parse("2021-08-24");

        Assertions.assertEquals("Aug 24 2021", p.formatLocalDate(ld));
    }
}
