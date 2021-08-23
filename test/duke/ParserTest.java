package duke;

import command.*;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;
import task.Task;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parse_noMeaningCommand_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parse("blah"));
    }

    @Test
    void parse_todoCommand_newTodoCommand() {
        try {
            AddCommand ac = (AddCommand) Parser.parse("todo description");
            assertEquals(Task.TaskType.TODO, ac.type);
            assertEquals("description", ac.description);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    void parse_deadlineCommand_newDeadlineCommand() {
        try {
            AddCommand ac = (AddCommand) Parser.parse("deadline ddl /by 01-01-2021 1800");
            assertEquals(Task.TaskType.DEADLINE, ac.type);
            assertEquals("ddl", ac.description);
            assertEquals(LocalDate.of(2021, 1, 1), ac.date);
            assertEquals(LocalTime.of(18, 0), ac.time);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    void parse_eventCommand_newEventCommand() {
        try {
            AddCommand ac = (AddCommand) Parser.parse("event event /by 01-01-2021");
            assertEquals(Task.TaskType.EVENT, ac.type);
            assertEquals("event", ac.description);
            assertEquals(LocalDate.of(2021, 1, 1), ac.date);
            assertNull(ac.time);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    void parse_deleteCommand_newDeleteCommand() {
        try {
            Command c = Parser.parse("delete 2");
            if (!(c instanceof DeleteCommand)) {
                fail();
            }
            DeleteCommand dc = (DeleteCommand) c;
            assertEquals(1, dc.taskIndex);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    void parse_doneCommand_newDoneCommand() {
        try {
            Command c = Parser.parse("done 100");
            if (!(c instanceof DoneCommand)) {
                fail();
            }
            DoneCommand dc = (DoneCommand) c;
            assertEquals(99, dc.taskIndex);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    void parse_exitCommand_newExitCommand() {
        try {
            Command c = Parser.parse("bye");
            assertTrue(c.isExit());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    void parse_listCommand_newlistCommand() {
        try {
            Command c = Parser.parse("list");
            if (!(c instanceof ListCommand)) {
                fail();
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}