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
 * Represents a testing class for {@code AddEvent}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class AddEventTest {

    private static final String CMD_EVENT_1 = "event";
    private static final String CMD_EVENT_2 = "event finish work";
    private static final String CMD_EVENT_3 = "event finish work /from today to tomorrow";
    private static final String CMD_EVENT_4 = "event     finish work         /from     2021 09 30 2000     to      "
            + "2021 09 30 2200";
    private static final String CMD_EVENT_5 = "event finish work /from 2021 19 40 4000 to 2021 19 40 4400";
    private static final String CMD_EVENT_6 = "event finish work /from 2021 09 30 2200 to 2021 09 30 2000";

    private static final String CMD_VALID_EVENT_1 = "event finish work /from 2021 09 30 2000 to 2021 09 30 2200";
    private static final String CMD_VALID_EVENT_2 = "event    finish work    /from 2021 09 30 2000 to 2021 09 30 2200";

    private static final String CMD_NON_EVENT_1 = "todo finish work";
    private static final String CMD_NON_EVENT_2 = "deadline finish work /by 2021 09 30 2359";
    private static final String CMD_NON_EVENT_3 = "bye";
    private static final String CMD_NON_EVENT_4 = "delete 10";
    private static final String CMD_NON_EVENT_5 = "done 10";
    private static final String CMD_NON_EVENT_6 = "find work";
    private static final String CMD_NON_EVENT_7 = "help";
    private static final String CMD_NON_EVENT_8 = "list";
    private static final String CMD_NON_EVENT_9 = "list -s";
    private static final String CMD_NON_EVENT_10 = "gibberish";

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
    public void isThisCmd_eventCommands_true() {
        assertTrue(AddEvent.isThisCmd(CMD_EVENT_1));
        assertTrue(AddEvent.isThisCmd(CMD_EVENT_2));
        assertTrue(AddEvent.isThisCmd(CMD_EVENT_3));
        assertTrue(AddEvent.isThisCmd(CMD_EVENT_4));
        assertTrue(AddEvent.isThisCmd(CMD_EVENT_5));
        assertTrue(AddEvent.isThisCmd(CMD_EVENT_6));

        assertTrue(AddEvent.isThisCmd(CMD_VALID_EVENT_1));
        assertTrue(AddEvent.isThisCmd(CMD_VALID_EVENT_2));
    }

    @Test
    public void isThisCmd_nonEventCommands_false() {
        assertFalse(AddEvent.isThisCmd(CMD_NON_EVENT_1));
        assertFalse(AddEvent.isThisCmd(CMD_NON_EVENT_2));
        assertFalse(AddEvent.isThisCmd(CMD_NON_EVENT_3));
        assertFalse(AddEvent.isThisCmd(CMD_NON_EVENT_4));
        assertFalse(AddEvent.isThisCmd(CMD_NON_EVENT_5));
        assertFalse(AddEvent.isThisCmd(CMD_NON_EVENT_6));
        assertFalse(AddEvent.isThisCmd(CMD_NON_EVENT_7));
        assertFalse(AddEvent.isThisCmd(CMD_NON_EVENT_8));
        assertFalse(AddEvent.isThisCmd(CMD_NON_EVENT_9));
        assertFalse(AddEvent.isThisCmd(CMD_NON_EVENT_10));
    }

    @Test
    public void constructor_eventCommand_correctField() {
        AddEvent addEvent1 = new AddEvent(CMD_EVENT_1);
        AddEvent addEvent2 = new AddEvent(CMD_EVENT_2);
        AddEvent addEvent3 = new AddEvent(CMD_EVENT_3);
        AddEvent addEvent4 = new AddEvent(CMD_EVENT_4);
        AddEvent addEvent5 = new AddEvent(CMD_EVENT_5);
        AddEvent addEvent6 = new AddEvent(CMD_EVENT_6);

        AddEvent validAddEvent1 = new AddEvent(CMD_VALID_EVENT_1);
        AddEvent validAddEvent2 = new AddEvent(CMD_VALID_EVENT_2);

        assertEquals(CMD_EVENT_1, addEvent1.cmdContent);
        assertEquals(CMD_EVENT_2, addEvent2.cmdContent);
        assertEquals(CMD_EVENT_3, addEvent3.cmdContent);
        assertEquals(CMD_EVENT_4, addEvent4.cmdContent);
        assertEquals(CMD_EVENT_5, addEvent5.cmdContent);
        assertEquals(CMD_EVENT_6, addEvent6.cmdContent);

        assertEquals(CMD_VALID_EVENT_1, validAddEvent1.cmdContent);
        assertEquals(CMD_VALID_EVENT_2, validAddEvent2.cmdContent);
    }

    @Test
    public void execute_validAddEventCommand_correctResponse() throws PoseidonException {
        String expectedResponse = "Got it\\. I've added this task:\\n"
                + "  \\[E]\\[ ] finish work \\(from: .+ to .+\\)\\n"
                + "Now you have \\d+ tasks in the list\\.";

        AddEvent validAddEvent1 = new AddEvent(CMD_VALID_EVENT_1);
        AddEvent validAddEvent2 = new AddEvent(CMD_VALID_EVENT_2);
        String response1 = "";
        String response2 = "";

        try {
            response1 = validAddEvent1.execute(storageStub, taskList, ui);
        } catch (PoseidonException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
        assertTrue(Pattern.compile(expectedResponse).matcher(response1).matches());

        try {
            response2 = validAddEvent2.execute(storageStub, taskList, ui);
        } catch (PoseidonException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
        assertTrue(Pattern.compile(expectedResponse).matcher(response2).matches());
    }

    @Test
    public void execute_invalidAddEventCommand_correctExceptionMsg() throws PoseidonException {
        String expectedExceptionMsg = "There appears to be a typo in your EVENT command.\n"
                + "The command should be of the form:\n"
                + "  event 'description' /from 'yyyy mm dd hhmm' to 'yyyy mm dd hhmm'\n"
                + "Please try again.";

        AddEvent invalidAddEvent1 = new AddEvent(CMD_EVENT_1);
        AddEvent invalidAddEvent2 = new AddEvent(CMD_EVENT_2);
        AddEvent invalidAddEvent3 = new AddEvent(CMD_EVENT_3);
        AddEvent invalidAddEvent4 = new AddEvent(CMD_EVENT_4);

        try {
            String response1 = invalidAddEvent1.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonIncorrectCommandFormatException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }

        try {
            String response2 = invalidAddEvent2.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonIncorrectCommandFormatException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }

        try {
            String response3 = invalidAddEvent3.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonIncorrectCommandFormatException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }

        try {
            String response4 = invalidAddEvent4.execute(storageStub, taskList, ui);
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

        AddEvent invalidDateTime = new AddEvent(CMD_EVENT_5);

        try {
            String response = invalidDateTime.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonDateTimeParseException ex) {
            assertTrue(Pattern.compile(expectedExceptionMsg).matcher(ex.getMessage()).matches());
        }
    }

    @Test
    public void execute_invalidFromToPair_correctExceptionMsg() throws PoseidonException {
        String expectedExceptionMsg = "There appears to be a typo in your EVENT command.\n"
                + "The command should be of the form:\n"
                + "  event 'description' /from 'yyyy mm dd hhmm' to 'yyyy mm dd hhmm'\n"
                + "The event's from/start time is after its to/end time. "
                + "Based on our current knowledge of the Arrow of Time, this is impossible.\n"
                + "Please try again.";

        AddEvent invalidFromToPair = new AddEvent(CMD_EVENT_6);

        try {
            String response = invalidFromToPair.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonIncorrectCommandFormatException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }
    }
}

