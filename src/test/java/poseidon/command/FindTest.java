package poseidon.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import poseidon.exception.PoseidonException;
import poseidon.exception.PoseidonIncorrectCommandFormatException;
import poseidon.stub.StorageStub;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;


/**
 * Represents a testing class for {@code Find}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class FindTest {

    private static final String CMD_FIND_1 = "find";
    private static final String CMD_FIND_2 = "find      ";

    private static final String CMD_VALID_FIND_1 = "find work";
    private static final String CMD_VALID_FIND_2 = "find     dinner     ";

    private static final String CMD_NON_FIND_1 = "todo finish work";
    private static final String CMD_NON_FIND_2 = "deadline finish work /by 2021 09 30 2359";
    private static final String CMD_NON_FIND_3 = "event finish work /from 2021 09 30 2000 to 2021 09 30 2200";
    private static final String CMD_NON_FIND_4 = "bye";
    private static final String CMD_NON_FIND_5 = "delete 10";
    private static final String CMD_NON_FIND_6 = "done 10";
    private static final String CMD_NON_FIND_7 = "help";
    private static final String CMD_NON_FIND_8 = "list";
    private static final String CMD_NON_FIND_9 = "list -s";
    private static final String CMD_NON_FIND_10 = "gibberish";

    private static StorageStub storageStub;
    private static Ui ui;
    private static TaskList taskList;

    @BeforeAll
    public static void setUp() {
        try {
            storageStub = new StorageStub();
            storageStub.clear();

            String findTestStorageStub = "T\tfalse\tfinish work\n"
                    + "D\ttrue\tcomplete work\t2021-09-30T23:59\n"
                    + "E\tfalse\tfinish dinner\t2021-09-30T20:00\t2021-09-30T22:00\n"
                    + "T\ttrue\tcomplete dinner";
            storageStub.storeAdd(findTestStorageStub);
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
    public void isThisCmd_findCommands_true() {
        assertTrue(Find.isThisCmd(CMD_FIND_1));
        assertTrue(Find.isThisCmd(CMD_FIND_2));

        assertTrue(Find.isThisCmd(CMD_VALID_FIND_1));
        assertTrue(Find.isThisCmd(CMD_VALID_FIND_2));
    }

    @Test
    public void isThisCmd_nonFindCommands_false() {
        assertFalse(Find.isThisCmd(CMD_NON_FIND_1));
        assertFalse(Find.isThisCmd(CMD_NON_FIND_2));
        assertFalse(Find.isThisCmd(CMD_NON_FIND_3));
        assertFalse(Find.isThisCmd(CMD_NON_FIND_4));
        assertFalse(Find.isThisCmd(CMD_NON_FIND_5));
        assertFalse(Find.isThisCmd(CMD_NON_FIND_6));
        assertFalse(Find.isThisCmd(CMD_NON_FIND_7));
        assertFalse(Find.isThisCmd(CMD_NON_FIND_8));
        assertFalse(Find.isThisCmd(CMD_NON_FIND_9));
        assertFalse(Find.isThisCmd(CMD_NON_FIND_10));
    }

    @Test
    public void constructor_findCommand_correctField() {
        Find find1 = new Find(CMD_FIND_1);
        Find find2 = new Find(CMD_FIND_2);

        Find validFind1 = new Find(CMD_VALID_FIND_1);
        Find validFind2 = new Find(CMD_VALID_FIND_2);

        assertEquals(CMD_FIND_1, find1.cmdContent);
        assertEquals(CMD_FIND_2, find2.cmdContent);

        assertEquals(CMD_VALID_FIND_1, validFind1.cmdContent);
        assertEquals(CMD_VALID_FIND_2, validFind2.cmdContent);
    }

    @Test
    public void execute_validFindCommand_correctResponse() throws PoseidonException {
        String expectedResponse1 = "Here are the matching tasks in your list:\\n"
                + "  1\\. \\[T]\\[ ] finish work\\n"
                + "  2\\. \\[D]\\[X] complete work \\(by: .+\\)\\n";

        String expectedResponse2 = "Here are the matching tasks in your list:\\n"
                + "  1\\. \\[E]\\[ ] finish dinner \\(from: .+ to .+\\)\\n"
                + "  2\\. \\[T]\\[X] complete dinner\\n";

        Find validFind1 = new Find(CMD_VALID_FIND_1);
        Find validFind2 = new Find(CMD_VALID_FIND_2);
        String response1 = "";
        String response2 = "";

        try {
            response1 = validFind1.execute(storageStub, taskList, ui);
        } catch (PoseidonException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
        assertTrue(Pattern.compile(expectedResponse1).matcher(response1).matches());
        try {
            response2 = validFind2.execute(storageStub, taskList, ui);
        } catch (PoseidonException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
        assertTrue(Pattern.compile(expectedResponse2).matcher(response2).matches());
    }

    @Test
    public void execute_invalidCommandFormat_correctExceptionMsg() throws PoseidonException {
        String expectedExceptionMsg = "There appears to be a typo in your FIND command.\n"
                + "The command should be of the form:\n"
                + "  find 'content'\n"
                + "Please try again.";

        Find invalidFind1 = new Find(CMD_FIND_1);
        Find invalidFind2 = new Find(CMD_FIND_2);

        try {
            String response1 = invalidFind1.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonIncorrectCommandFormatException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }

        try {
            String response2 = invalidFind2.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonIncorrectCommandFormatException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }
    }
}

