package poseidon.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import poseidon.exception.PoseidonDateTimeParseException;
import poseidon.exception.PoseidonException;
import poseidon.exception.PoseidonIncorrectCommandFormatException;
import poseidon.stub.StorageStub;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;


/**
 * Represents a testing class for {@code AddDeadline}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class AddDeadlineTest {

    private static final String CMD_DEADLINE_1 = "deadline";
    private static final String CMD_DEADLINE_2 = "deadline finish work";
    private static final String CMD_DEADLINE_3 = "deadline finish work /by tomorrow";
    private static final String CMD_DEADLINE_4 = "deadline     finish work     /by     2021 09 30 2359";
    private static final String CMD_DEADLINE_5 = "deadline     finish work     /by 2021 13 30 2559";

    private static final String CMD_VALID_DEADLINE_1 = "deadline finish work /by 2021 09 30 2359";
    private static final String CMD_VALID_DEADLINE_2 = "deadline     finish work     /by 2021 09 30 2359";

    private static final String CMD_NON_DEADLINE_1 = "todo finish work";
    private static final String CMD_NON_DEADLINE_2 = "event finish work /from 2021 09 30 2000 to 2021 09 30 2200";
    private static final String CMD_NON_DEADLINE_3 = "bye";
    private static final String CMD_NON_DEADLINE_4 = "delete 10";
    private static final String CMD_NON_DEADLINE_5 = "done 10";
    private static final String CMD_NON_DEADLINE_6 = "find work";
    private static final String CMD_NON_DEADLINE_7 = "help";
    private static final String CMD_NON_DEADLINE_8 = "list";
    private static final String CMD_NON_DEADLINE_9 = "list -s";
    private static final String CMD_NON_DEADLINE_10 = "gibberish";

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
    public void isThisCmd_deadlineCommands_true() {
        assertTrue(AddDeadline.isThisCmd(CMD_DEADLINE_1));
        assertTrue(AddDeadline.isThisCmd(CMD_DEADLINE_2));
        assertTrue(AddDeadline.isThisCmd(CMD_DEADLINE_3));
        assertTrue(AddDeadline.isThisCmd(CMD_DEADLINE_4));
        assertTrue(AddDeadline.isThisCmd(CMD_DEADLINE_5));

        assertTrue(AddDeadline.isThisCmd(CMD_VALID_DEADLINE_1));
        assertTrue(AddDeadline.isThisCmd(CMD_VALID_DEADLINE_2));
    }

    @Test
    public void isThisCmd_nonDeadlineCommands_false() {
        assertFalse(AddDeadline.isThisCmd(CMD_NON_DEADLINE_1));
        assertFalse(AddDeadline.isThisCmd(CMD_NON_DEADLINE_2));
        assertFalse(AddDeadline.isThisCmd(CMD_NON_DEADLINE_3));
        assertFalse(AddDeadline.isThisCmd(CMD_NON_DEADLINE_4));
        assertFalse(AddDeadline.isThisCmd(CMD_NON_DEADLINE_5));
        assertFalse(AddDeadline.isThisCmd(CMD_NON_DEADLINE_6));
        assertFalse(AddDeadline.isThisCmd(CMD_NON_DEADLINE_7));
        assertFalse(AddDeadline.isThisCmd(CMD_NON_DEADLINE_8));
        assertFalse(AddDeadline.isThisCmd(CMD_NON_DEADLINE_9));
        assertFalse(AddDeadline.isThisCmd(CMD_NON_DEADLINE_10));
    }

    @Test
    public void constructor_deadlineCommand_correctField() {
        AddDeadline addDeadline1 = new AddDeadline(CMD_DEADLINE_1);
        AddDeadline addDeadline2 = new AddDeadline(CMD_DEADLINE_2);
        AddDeadline addDeadline3 = new AddDeadline(CMD_DEADLINE_3);
        AddDeadline addDeadline4 = new AddDeadline(CMD_DEADLINE_4);
        AddDeadline addDeadline5 = new AddDeadline(CMD_DEADLINE_5);

        AddDeadline validAddDeadline1 = new AddDeadline(CMD_VALID_DEADLINE_1);
        AddDeadline validAddDeadline2 = new AddDeadline(CMD_VALID_DEADLINE_2);

        assertEquals(CMD_DEADLINE_1, addDeadline1.cmdContent);
        assertEquals(CMD_DEADLINE_2, addDeadline2.cmdContent);
        assertEquals(CMD_DEADLINE_3, addDeadline3.cmdContent);
        assertEquals(CMD_DEADLINE_4, addDeadline4.cmdContent);
        assertEquals(CMD_DEADLINE_5, addDeadline5.cmdContent);

        assertEquals(CMD_VALID_DEADLINE_1, validAddDeadline1.cmdContent);
        assertEquals(CMD_VALID_DEADLINE_2, validAddDeadline2.cmdContent);
    }

    @Test
    public void execute_validAddDeadlineCommand_correctResponse() {
        String expectedResponse = "Got it\\. I've added this task:\\n"
                + "  \\[D]\\[ ] finish work \\(by: .+\\)\\n"
                + "Now you have \\d+ tasks in the list\\.";

        AddDeadline validAddDeadline1 = new AddDeadline(CMD_VALID_DEADLINE_1);
        AddDeadline validAddDeadline2 = new AddDeadline(CMD_VALID_DEADLINE_2);
        String response1 = "";
        String response2 = "";

        try {
            response1 = validAddDeadline1.execute(storageStub, taskList, ui);
        } catch (PoseidonException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
        assertTrue(Pattern.compile(expectedResponse).matcher(response1).matches());

        try {
            response2 = validAddDeadline2.execute(storageStub, taskList, ui);
        } catch (PoseidonException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
        assertTrue(Pattern.compile(expectedResponse).matcher(response2).matches());
    }

    @Test
    public void execute_invalidCommandFormat_correctExceptionMsg() throws PoseidonException {
        String expectedExceptionMsg = "There appears to be a typo in your DEADLINE command.\n"
                + "The command should be of the form:\n"
                + "  deadline 'description' /by 'yyyy mm dd hhmm'\n"
                + "Please try again.";

        AddDeadline invalidAddDeadline1 = new AddDeadline(CMD_DEADLINE_1);
        AddDeadline invalidAddDeadline2 = new AddDeadline(CMD_DEADLINE_2);
        AddDeadline invalidAddDeadline3 = new AddDeadline(CMD_DEADLINE_3);
        AddDeadline invalidAddDeadline4 = new AddDeadline(CMD_DEADLINE_4);

        try {
            String response1 = invalidAddDeadline1.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonIncorrectCommandFormatException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }

        try {
            String response2 = invalidAddDeadline2.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonIncorrectCommandFormatException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }

        try {
            String response3 = invalidAddDeadline3.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonIncorrectCommandFormatException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }

        try {
            String response4 = invalidAddDeadline4.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonIncorrectCommandFormatException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }
    }

    @Test
    public void execute_invalidDateTime_correctExceptionMsg() throws PoseidonException {
        String expectedExceptionMsg = "Date and Time couldn't be parsed.\\n"
                + ".+\\n"
                + "Please try again\\.";

        try {
            AddDeadline invalidDateTime = new AddDeadline(CMD_DEADLINE_5);
            String response = invalidDateTime.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonDateTimeParseException ex) {
            assertTrue(Pattern.compile(expectedExceptionMsg).matcher(ex.getMessage()).matches());
        }
    }
}

