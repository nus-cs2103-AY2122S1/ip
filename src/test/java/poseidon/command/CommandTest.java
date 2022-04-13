package poseidon.command;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;


/**
 * Represents a testing class for {@code Command}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class CommandTest {

    private static final String CMD_1 = "todo finish work";
    private static final String CMD_2 = "deadline finish work /by 2021 09 30 2359";
    private static final String CMD_3 = "event finish work /from 2021 09 30 2000 to 2021 09 30 2200";
    private static final String CMD_4 = "bye";
    private static final String CMD_5 = "delete 10";
    private static final String CMD_6 = "done 10";
    private static final String CMD_7 = "find work";
    private static final String CMD_8 = "help";
    private static final String CMD_9 = "list";
    private static final String CMD_10 = "list -s";
    private static final String CMD_11 = "gibberish";

    @Test
    public void isThisCmd_anyCommand_false() {
        assertFalse(Command.isThisCmd(CMD_1));
        assertFalse(Command.isThisCmd(CMD_2));
        assertFalse(Command.isThisCmd(CMD_3));
        assertFalse(Command.isThisCmd(CMD_4));
        assertFalse(Command.isThisCmd(CMD_5));
        assertFalse(Command.isThisCmd(CMD_6));
        assertFalse(Command.isThisCmd(CMD_7));
        assertFalse(Command.isThisCmd(CMD_8));
        assertFalse(Command.isThisCmd(CMD_9));
        assertFalse(Command.isThisCmd(CMD_10));
        assertFalse(Command.isThisCmd(CMD_11));
    }
}

