package poseidon.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import poseidon.exception.PoseidonException;
import poseidon.stub.StorageStub;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;


/**
 * Represents a testing class for {@code Bye}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class ByeTest {

    private static final String CMD_BYE_1 = "bye";
    private static final String CMD_BYE_2 = "bye      ";

    private static final String CMD_NON_BYE_1 = "todo finish work";
    private static final String CMD_NON_BYE_2 = "deadline finish work /by 2021 09 30 2359";
    private static final String CMD_NON_BYE_3 = "event finish work /from 2021 09 30 2000 to 2021 09 30 2200";
    private static final String CMD_NON_BYE_4 = "delete 10";
    private static final String CMD_NON_BYE_5 = "done 10";
    private static final String CMD_NON_BYE_6 = "find work";
    private static final String CMD_NON_BYE_7 = "help";
    private static final String CMD_NON_BYE_8 = "list";
    private static final String CMD_NON_BYE_9 = "list -s";
    private static final String CMD_NON_BYE_10 = "gibberish";

    private static StorageStub storageStub;
    private static Ui ui;
    private static TaskList taskList;

    @BeforeAll
    public static void setUp() {
        try {
            storageStub = new StorageStub();
            storageStub.clear();
            taskList = new TaskList(storageStub.load());
            ui = new Ui();
        } catch (PoseidonException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @AfterAll
    public static void closeUp() {
        try {
            storageStub.clear();
            storageStub = null;
            taskList = null;
            ui = null;
        } catch (PoseidonException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void isThisCmd_byeCommands_true() {
        assertTrue(Bye.isThisCmd(CMD_BYE_1));
        assertTrue(Bye.isThisCmd(CMD_BYE_2));
    }

    @Test
    public void isThisCmd_nonByeCommands_false() {
        assertFalse(Bye.isThisCmd(CMD_NON_BYE_1));
        assertFalse(Bye.isThisCmd(CMD_NON_BYE_2));
        assertFalse(Bye.isThisCmd(CMD_NON_BYE_3));
        assertFalse(Bye.isThisCmd(CMD_NON_BYE_4));
        assertFalse(Bye.isThisCmd(CMD_NON_BYE_5));
        assertFalse(Bye.isThisCmd(CMD_NON_BYE_6));
        assertFalse(Bye.isThisCmd(CMD_NON_BYE_7));
        assertFalse(Bye.isThisCmd(CMD_NON_BYE_8));
        assertFalse(Bye.isThisCmd(CMD_NON_BYE_9));
        assertFalse(Bye.isThisCmd(CMD_NON_BYE_10));
    }

    @Test
    public void constructor_byeCommand_correctField() {
        Bye bye1 = new Bye(CMD_BYE_1);
        Bye bye2 = new Bye(CMD_BYE_2);

        assertEquals(CMD_BYE_1, bye1.cmdContent);
        assertEquals(CMD_BYE_2, bye2.cmdContent);
    }

    @Test
    public void execute_byeCommand_correctResponse() {
        String expectedResponse = "Bye. Hope to see you again soon!";

        Bye bye1 = new Bye(CMD_BYE_1);
        String response1 = bye1.execute(storageStub, taskList, ui);

        Bye bye2 = new Bye(CMD_BYE_2);
        String response2 = bye2.execute(storageStub, taskList, ui);

        assertEquals(expectedResponse, response1);
        assertEquals(expectedResponse, response2);
    }
}

