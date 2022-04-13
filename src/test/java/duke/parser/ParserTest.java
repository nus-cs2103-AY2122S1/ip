package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.command.*;

public class ParserTest {

    @Test
    public void getCommandTest_toDoCommandSuccess() throws DukeException {
        try {
            Command c = Parser.getCommand("todo sleep");
            assertEquals(new ToDoCommand("sleep"), c);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }

    }

    @Test
    public void getCommandTest_eventCommandSuccess() {
        try {
            Command c = Parser.getCommand("event dinner /at 7PM");
            String[] args = {"dinner", "7PM"};
            assertEquals(new EventCommand(args), c);
        } catch(DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void getCommandTest_deadlineCommandSuccess() {
        try {
            Command c = Parser.getCommand("deadline return book /by 2021-10-01");
            String[] args = {"return book", "2021-10-01"};
            assertEquals(new DeadlineCommand(args), c);
        } catch(DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void getCommandTest_doneCommandSuccess() {
        try {
            Command c = Parser.getCommand("done 2");
            assertEquals(new DoneCommand(2), c);
        } catch (DukeException e ) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void getCommandTest_deleteCommandSuccess() {
        try {
            Command c = Parser.getCommand("delete 3");
            assertEquals(new DeleteCommand(3), c);
        } catch (DukeException e ) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void getCommandTest_findCommandSuccess() {
        try {
            Command c = Parser.getCommand("find sleep");
            assertEquals(new FindCommand("sleep"), c);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void getCommandTest_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Parser.getCommand("hi");
        });
    }

    @Test
    public void getCommandTest_emptyDeadlineException() {
        assertThrows(DukeException.class, () -> {
            Parser.getCommand("deadline");
        });
    }

    @Test
    public void getCommandTest_wrongDateFormatException() {
        assertThrows(DukeException.class, () -> {
            Parser.getCommand("deadline return book /by 20 Oct 2021");
        });
    }

    @Test
    public void getCommandTest_invalidEventFormatException() {
        assertThrows(DukeException.class, () -> {
            Parser.getCommand("event dinner at 7PM");
        });
    }

}
