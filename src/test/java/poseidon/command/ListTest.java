package poseidon.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import poseidon.exception.PoseidonException;
import poseidon.stub.StorageStub;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;


/**
 * Represents a testing class for {@code List}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class ListTest {

    private static final String CMD_LIST_1 = "list";
    private static final String CMD_LIST_2 = "list      ";

    private static final String CMD_NON_LIST_1 = "todo finish work";
    private static final String CMD_NON_LIST_2 = "deadline finish work /by 2021 09 30 2359";
    private static final String CMD_NON_LIST_3 = "event finish work /from 2021 09 30 2000 to 2021 09 30 2200";
    private static final String CMD_NON_LIST_4 = "bye";
    private static final String CMD_NON_LIST_5 = "delete 10";
    private static final String CMD_NON_LIST_6 = "done 10";
    private static final String CMD_NON_LIST_7 = "help";
    private static final String CMD_NON_LIST_8 = "find work";
    private static final String CMD_NON_LIST_9 = "list -s";
    private static final String CMD_NON_LIST_10 = "gibberish";

    private static StorageStub storageStub;
    private static Ui ui;
    private static TaskList taskList;

    @BeforeAll
    public static void setUp() {
        try {
            storageStub = new StorageStub();
            storageStub.clear();

            String listTestStorageStub = "T\tfalse\tfinish work\n"
                    + "D\ttrue\tcomplete work\t2021-09-30T23:59\n"
                    + "E\tfalse\tfinish dinner\t2021-09-30T20:00\t2021-09-30T22:00\n"
                    + "T\ttrue\tcomplete dinner";
            storageStub.storeAdd(listTestStorageStub);
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
    public void isThisCmd_listCommands_true() {
        assertTrue(List.isThisCmd(CMD_LIST_1));
        assertTrue(List.isThisCmd(CMD_LIST_2));
    }

    @Test
    public void isThisCmd_nonListCommands_false() {
        assertFalse(List.isThisCmd(CMD_NON_LIST_1));
        assertFalse(List.isThisCmd(CMD_NON_LIST_2));
        assertFalse(List.isThisCmd(CMD_NON_LIST_3));
        assertFalse(List.isThisCmd(CMD_NON_LIST_4));
        assertFalse(List.isThisCmd(CMD_NON_LIST_5));
        assertFalse(List.isThisCmd(CMD_NON_LIST_6));
        assertFalse(List.isThisCmd(CMD_NON_LIST_7));
        assertFalse(List.isThisCmd(CMD_NON_LIST_8));
        assertFalse(List.isThisCmd(CMD_NON_LIST_9));
        assertFalse(List.isThisCmd(CMD_NON_LIST_10));
    }

    @Test
    public void constructor_listCommand_correctField() {
        List list1 = new List(CMD_LIST_1);
        List list2 = new List(CMD_LIST_2);

        assertEquals(CMD_LIST_1, list1.cmdContent);
        assertEquals(CMD_LIST_2, list2.cmdContent);
    }

    @Test
    public void execute_validListCommand_correctResponse() throws PoseidonException {
        String expectedResponse = "Here are the tasks in your list:\\n"
                + "  1\\. \\[T]\\[ ] finish work\\n"
                + "  2\\. \\[D]\\[X] complete work \\(by: .+\\)\\n"
                + "  3\\. \\[E]\\[ ] finish dinner \\(from: .+ to .+\\)\\n"
                + "  4\\. \\[T]\\[X] complete dinner\\n";

        List validList1 = new List(CMD_LIST_1);
        List validList2 = new List(CMD_LIST_2);
        String response1 = validList1.execute(storageStub, taskList, ui);
        String response2 = validList2.execute(storageStub, taskList, ui);

        assertTrue(Pattern.compile(expectedResponse).matcher(response1).matches());
        assertTrue(Pattern.compile(expectedResponse).matcher(response2).matches());
    }
}

