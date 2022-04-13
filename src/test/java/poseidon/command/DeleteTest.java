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
 * Represents a testing class for {@code Delete}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class DeleteTest {

    private static final String CMD_DELETE_1 = "delete";
    private static final String CMD_DELETE_2 = "delete      ";
    private static final String CMD_DELETE_3 = "delete two";
    private static final String CMD_DELETE_4 = "delete     two     ";

    private static final String CMD_VALID_DELETE_1 = "delete 2";
    private static final String CMD_VALID_DELETE_2 = "delete     2     ";

    private static final String CMD_NON_EXISTENT_DELETE_1 = "delete 4";
    private static final String CMD_NON_EXISTENT_DELETE_2 = "delete     5     ";

    private static final String CMD_NON_DELETE_1 = "todo finish work";
    private static final String CMD_NON_DELETE_2 = "deadline finish work /by 2021 09 30 2359";
    private static final String CMD_NON_DELETE_3 = "event finish work /from 2021 09 30 2000 to 2021 09 30 2200";
    private static final String CMD_NON_DELETE_4 = "bye";
    private static final String CMD_NON_DELETE_5 = "done 10";
    private static final String CMD_NON_DELETE_6 = "find work";
    private static final String CMD_NON_DELETE_7 = "help";
    private static final String CMD_NON_DELETE_8 = "list";
    private static final String CMD_NON_DELETE_9 = "list -s";
    private static final String CMD_NON_DELETE_10 = "gibberish";

    private static StorageStub storageStub;
    private static Ui ui;
    private static TaskList taskList;

    @BeforeAll
    public static void setUp() {
        try {
            storageStub = new StorageStub();
            storageStub.clear();

            String sampleTaskStorageStub = "T\tfalse\tfinish work\n";
            for (int i = 0; i < 3; i++) {
                storageStub.storeAdd(sampleTaskStorageStub);
            }

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
    public void isThisCmd_deleteCommands_true() {
        assertTrue(Delete.isThisCmd(CMD_DELETE_1));
        assertTrue(Delete.isThisCmd(CMD_DELETE_2));
        assertTrue(Delete.isThisCmd(CMD_DELETE_3));
        assertTrue(Delete.isThisCmd(CMD_DELETE_4));

        assertTrue(Delete.isThisCmd(CMD_VALID_DELETE_1));
        assertTrue(Delete.isThisCmd(CMD_VALID_DELETE_2));
    }

    @Test
    public void isThisCmd_nonDeleteCommands_false() {
        assertFalse(Delete.isThisCmd(CMD_NON_DELETE_1));
        assertFalse(Delete.isThisCmd(CMD_NON_DELETE_2));
        assertFalse(Delete.isThisCmd(CMD_NON_DELETE_3));
        assertFalse(Delete.isThisCmd(CMD_NON_DELETE_4));
        assertFalse(Delete.isThisCmd(CMD_NON_DELETE_5));
        assertFalse(Delete.isThisCmd(CMD_NON_DELETE_6));
        assertFalse(Delete.isThisCmd(CMD_NON_DELETE_7));
        assertFalse(Delete.isThisCmd(CMD_NON_DELETE_8));
        assertFalse(Delete.isThisCmd(CMD_NON_DELETE_9));
        assertFalse(Delete.isThisCmd(CMD_NON_DELETE_10));
    }

    @Test
    public void constructor_deleteCommand_correctField() {
        Delete delete1 = new Delete(CMD_DELETE_1);
        Delete delete2 = new Delete(CMD_DELETE_2);
        Delete delete3 = new Delete(CMD_DELETE_3);
        Delete delete4 = new Delete(CMD_DELETE_4);

        Delete validDelete1 = new Delete(CMD_VALID_DELETE_1);
        Delete validDelete2 = new Delete(CMD_VALID_DELETE_2);

        assertEquals(CMD_DELETE_1, delete1.cmdContent);
        assertEquals(CMD_DELETE_2, delete2.cmdContent);
        assertEquals(CMD_DELETE_3, delete3.cmdContent);
        assertEquals(CMD_DELETE_4, delete4.cmdContent);

        assertEquals(CMD_VALID_DELETE_1, validDelete1.cmdContent);
        assertEquals(CMD_VALID_DELETE_2, validDelete2.cmdContent);
    }

    @Test
    public void execute_validDeleteCommand_correctResponse() {
        String expectedResponse = "Noted\\. I've removed this task:\\n"
                + "  \\d+\\. \\[(T|D|E)]\\[(\\s|X)] .+\\n"
                + "(Now you have \\d+ tasks in the list\\.|There are no tasks in your list\\.)";

        Delete delete1 = new Delete(CMD_VALID_DELETE_1);
        Delete delete2 = new Delete(CMD_VALID_DELETE_2);
        String response1 = "";
        String response2 = "";

        try {
            response1 = delete1.execute(storageStub, taskList, ui);
        } catch (PoseidonException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
        assertTrue(Pattern.compile(expectedResponse).matcher(response1).matches());

        try {
            response2 = delete2.execute(storageStub, taskList, ui);
        } catch (PoseidonException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
        assertTrue(Pattern.compile(expectedResponse).matcher(response2).matches());
    }

    @Test
    public void execute_nonExistentTaskDeleteCommand_correctExceptionMsg() {
        String expectedExceptionMsg = "That task doesn't exist.\n"
                + "Please Try again.";

        Delete delete1 = new Delete(CMD_NON_EXISTENT_DELETE_1);
        Delete delete2 = new Delete(CMD_NON_EXISTENT_DELETE_2);

        try {
            String response1 = delete1.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }

        try {
            String response2 = delete2.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }
    }

    @Test
    public void execute_invalidCommandFormat_correctExceptionMsg() throws PoseidonException {
        String expectedExceptionMsg = "There appears to be a typo in your DELETE command.\n"
                + "The command should be of the form:\n"
                + "  delete 'index'\n"
                + "Please try again.";

        Delete invalidDelete1 = new Delete(CMD_DELETE_1);
        Delete invalidDelete2 = new Delete(CMD_DELETE_2);
        Delete invalidDelete3 = new Delete(CMD_DELETE_3);
        Delete invalidDelete4 = new Delete(CMD_DELETE_4);

        try {
            String response1 = invalidDelete1.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonIncorrectCommandFormatException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }

        try {
            String response2 = invalidDelete2.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonIncorrectCommandFormatException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }

        try {
            String response3 = invalidDelete3.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonIncorrectCommandFormatException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }

        try {
            String response4 = invalidDelete4.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonIncorrectCommandFormatException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }
    }
}

