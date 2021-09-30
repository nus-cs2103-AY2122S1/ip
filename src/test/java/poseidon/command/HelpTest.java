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
 * Represents a testing class for {@code Help}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class HelpTest {

    private static final String CMD_HELP_1 = "help";
    private static final String CMD_HELP_2 = "help      ";

    private static final String CMD_NON_HELP_1 = "todo finish work";
    private static final String CMD_NON_HELP_2 = "deadline finish work /by 2021 09 30 2359";
    private static final String CMD_NON_HELP_3 = "event finish work /from 2021 09 30 2000 to 2021 09 30 2200";
    private static final String CMD_NON_HELP_4 = "delete 10";
    private static final String CMD_NON_HELP_5 = "done 10";
    private static final String CMD_NON_HELP_6 = "find work";
    private static final String CMD_NON_HELP_7 = "bye";
    private static final String CMD_NON_HELP_8 = "list";
    private static final String CMD_NON_HELP_9 = "list -s";
    private static final String CMD_NON_HELP_10 = "gibberish";

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
    public void isThisCmd_helpCommands_true() {
        assertTrue(Help.isThisCmd(CMD_HELP_1));
        assertTrue(Help.isThisCmd(CMD_HELP_2));
    }

    @Test
    public void isThisCmd_nonHelpCommands_false() {
        assertFalse(Help.isThisCmd(CMD_NON_HELP_1));
        assertFalse(Help.isThisCmd(CMD_NON_HELP_2));
        assertFalse(Help.isThisCmd(CMD_NON_HELP_3));
        assertFalse(Help.isThisCmd(CMD_NON_HELP_4));
        assertFalse(Help.isThisCmd(CMD_NON_HELP_5));
        assertFalse(Help.isThisCmd(CMD_NON_HELP_6));
        assertFalse(Help.isThisCmd(CMD_NON_HELP_7));
        assertFalse(Help.isThisCmd(CMD_NON_HELP_8));
        assertFalse(Help.isThisCmd(CMD_NON_HELP_9));
        assertFalse(Help.isThisCmd(CMD_NON_HELP_10));
    }

    @Test
    public void constructor_helpCommand_correctField() {
        Help help1 = new Help(CMD_HELP_1);
        Help help2 = new Help(CMD_HELP_2);

        assertEquals(CMD_HELP_1, help1.cmdContent);
        assertEquals(CMD_HELP_2, help2.cmdContent);
    }

    @Test
    public void execute_helpCommand_correctResponse() {
        String expectedResponse = "Here's a list of all the commands I can understand:\n\n"
                + "FOR HELP - " + Help.CMD_USER_FORMAT + "\n\n"
                + "ADD TODO - " + AddTodo.CMD_USER_FORMAT + "\n\n"
                + "ADD DEADLINE - " + AddDeadline.CMD_USER_FORMAT + "\n\n"
                + "ADD EVENT - " + AddEvent.CMD_USER_FORMAT + "\n\n"
                + "MARK TASK DONE - " + Done.CMD_USER_FORMAT + "\n\n"
                + "DELETE TASK - " + Delete.CMD_USER_FORMAT + "\n\n"
                + "LIST TASKS - " + List.CMD_USER_FORMAT + "\n\n"
                + "SORT TASKS - " + Sort.CMD_USER_FORMAT + "\n\n"
                + "FIND CONTENT - " + Find.CMD_USER_FORMAT + "\n\n"
                + "EXIT - " + Bye.CMD_USER_FORMAT;

        Help help1 = new Help(CMD_HELP_1);
        String response1 = help1.execute(storageStub, taskList, ui);

        Help help2 = new Help(CMD_HELP_2);
        String response2 = help2.execute(storageStub, taskList, ui);

        assertEquals(expectedResponse, response1);
        assertEquals(expectedResponse, response2);
    }
}

