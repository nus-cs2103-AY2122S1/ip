package eightbit.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eightbit.EightBitException;
import eightbit.command.Command;
import eightbit.command.DeadlineCommand;
import eightbit.command.DoneCommand;
import eightbit.command.ListCommand;

class ParserTest {

    @Test
    void parse_listCommand() {
        try {
            Command c = Parser.parse("list");
            assertTrue(c instanceof ListCommand);
        } catch (EightBitException e) {
            fail();
        }
    }

    @Test
    void parse_doneCommand() {
        try {
            Command c = Parser.parse("done 1");
            assertTrue(c instanceof DoneCommand);
        } catch (EightBitException e) {
            fail();
        }
    }

    @Test
    void parse_invalidDoneCommand_exceptionThrown() {
        assertThrows(EightBitException.class, () -> Parser.parse("done -5"));
    }

    @Test
    void parse_deadlineCommand() {
        try {
            Command c = Parser.parse("deadline abc /by 2021-12-31 23:59");
            assertTrue(c instanceof DeadlineCommand);
        } catch (EightBitException e) {
            fail();
        }
    }

    @Test
    void parse_invalidDeadlineCommand_exceptionThrown() {
        assertThrows(EightBitException.class, () -> Parser.parse("deadline abc /by 2021-20-20"));
        assertThrows(EightBitException.class, () -> Parser.parse("deadline qwerty"));
    }
}
