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
 * Represents a testing class for {@code Done}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class DoneTest {

    private static final String CMD_DONE_1 = "done";
    private static final String CMD_DONE_2 = "done      ";
    private static final String CMD_DONE_3 = "done two";
    private static final String CMD_DONE_4 = "done     two     ";

    private static final String CMD_VALID_DONE_1 = "done 2";
    private static final String CMD_VALID_DONE_2 = "done     2     ";

    private static final String CMD_NON_EXISTENT_DONE_1 = "done 4";
    private static final String CMD_NON_EXISTENT_DONE_2 = "done     5     ";

    private static final String CMD_NON_DONE_1 = "todo finish work";
    private static final String CMD_NON_DONE_2 = "deadline finish work /by 2021 09 30 2359";
    private static final String CMD_NON_DONE_3 = "event finish work /from 2021 09 30 2000 to 2021 09 30 2200";
    private static final String CMD_NON_DONE_4 = "bye";
    private static final String CMD_NON_DONE_5 = "delete 10";
    private static final String CMD_NON_DONE_6 = "find work";
    private static final String CMD_NON_DONE_7 = "help";
    private static final String CMD_NON_DONE_8 = "list";
    private static final String CMD_NON_DONE_9 = "list -s";
    private static final String CMD_NON_DONE_10 = "gibberish";

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
    public void isThisCmd_doneCommands_true() {
        assertTrue(Done.isThisCmd(CMD_DONE_1));
        assertTrue(Done.isThisCmd(CMD_DONE_2));
        assertTrue(Done.isThisCmd(CMD_DONE_3));
        assertTrue(Done.isThisCmd(CMD_DONE_4));

        assertTrue(Done.isThisCmd(CMD_VALID_DONE_1));
        assertTrue(Done.isThisCmd(CMD_VALID_DONE_2));
    }

    @Test
    public void isThisCmd_nonDoneCommands_false() {
        assertFalse(Done.isThisCmd(CMD_NON_DONE_1));
        assertFalse(Done.isThisCmd(CMD_NON_DONE_2));
        assertFalse(Done.isThisCmd(CMD_NON_DONE_3));
        assertFalse(Done.isThisCmd(CMD_NON_DONE_4));
        assertFalse(Done.isThisCmd(CMD_NON_DONE_5));
        assertFalse(Done.isThisCmd(CMD_NON_DONE_6));
        assertFalse(Done.isThisCmd(CMD_NON_DONE_7));
        assertFalse(Done.isThisCmd(CMD_NON_DONE_8));
        assertFalse(Done.isThisCmd(CMD_NON_DONE_9));
        assertFalse(Done.isThisCmd(CMD_NON_DONE_10));
    }

    @Test
    public void constructor_doneCommand_correctField() {
        Done done1 = new Done(CMD_DONE_1);
        Done done2 = new Done(CMD_DONE_2);
        Done done3 = new Done(CMD_DONE_3);
        Done done4 = new Done(CMD_DONE_4);

        Done validDone1 = new Done(CMD_VALID_DONE_1);
        Done validDone2 = new Done(CMD_VALID_DONE_2);

        assertEquals(CMD_DONE_1, done1.cmdContent);
        assertEquals(CMD_DONE_2, done2.cmdContent);
        assertEquals(CMD_DONE_3, done3.cmdContent);
        assertEquals(CMD_DONE_4, done4.cmdContent);

        assertEquals(CMD_VALID_DONE_1, validDone1.cmdContent);
        assertEquals(CMD_VALID_DONE_2, validDone2.cmdContent);
    }

    @Test
    public void execute_validDoneCommand_correctResponse() {
        String expectedResponse = "Nice! I've marked this task as done:\\n"
                + "  \\d+\\. \\[(T|D|E)]\\[X] .+";

        Done done1 = new Done(CMD_VALID_DONE_1);
        Done done2 = new Done(CMD_VALID_DONE_2);
        String response1 = "";
        String response2 = "";

        try {
            response1 = done1.execute(storageStub, taskList, ui);
        } catch (PoseidonException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
        assertTrue(Pattern.compile(expectedResponse).matcher(response1).matches());

        try {
            response2 = done2.execute(storageStub, taskList, ui);
        } catch (PoseidonException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
        assertTrue(Pattern.compile(expectedResponse).matcher(response2).matches());
    }

    @Test
    public void execute_nonExistentTaskDoneCommand_correctExceptionMsg() {
        String expectedExceptionMsg = "That task doesn't exist.\n"
                + "Please Try again.";

        Done done1 = new Done(CMD_NON_EXISTENT_DONE_1);
        Done done2 = new Done(CMD_NON_EXISTENT_DONE_2);

        try {
            String response1 = done1.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }

        try {
            String response2 = done2.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }
    }

    @Test
    public void execute_invalidCommandFormat_correctExceptionMsg() throws PoseidonException {
        String expectedExceptionMsg = "There appears to be a typo in your DONE command.\n"
                + "The command should be of the form:\n"
                + "  done 'index'\n"
                + "Please try again.";

        Done invalidDone1 = new Done(CMD_DONE_1);
        Done invalidDone2 = new Done(CMD_DONE_2);
        Done invalidDone3 = new Done(CMD_DONE_3);
        Done invalidDone4 = new Done(CMD_DONE_4);

        try {
            String response1 = invalidDone1.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonIncorrectCommandFormatException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }

        try {
            String response2 = invalidDone2.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonIncorrectCommandFormatException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }

        try {
            String response3 = invalidDone3.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonIncorrectCommandFormatException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }

        try {
            String response4 = invalidDone4.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonIncorrectCommandFormatException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }
    }
}

