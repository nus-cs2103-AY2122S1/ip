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
 * Represents a testing class for {@code Sort}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class SortTest {

    private static final String CMD_SORT_1 = "list -s";
    private static final String CMD_SORT_2 = "list      -s      ";

    private static final String CMD_NON_SORT_1 = "todo finish work";
    private static final String CMD_NON_SORT_2 = "deadline finish work /by 2021 09 30 2359";
    private static final String CMD_NON_SORT_3 = "event finish work /from 2021 09 30 2000 to 2021 09 30 2200";
    private static final String CMD_NON_SORT_4 = "bye";
    private static final String CMD_NON_SORT_5 = "delete 10";
    private static final String CMD_NON_SORT_6 = "done 10";
    private static final String CMD_NON_SORT_7 = "help";
    private static final String CMD_NON_SORT_8 = "find work";
    private static final String CMD_NON_SORT_9 = "list";
    private static final String CMD_NON_SORT_10 = "gibberish";

    private static StorageStub storageStub;
    private static Ui ui;
    private static TaskList taskList;

    @BeforeAll
    public static void setUp() {
        try {
            storageStub = new StorageStub();
            storageStub.clear();

            String sortTestStorageStub = "T\tfalse\tfinish work\n"
                    + "D\ttrue\tcomplete work\t2021-09-30T23:59\n"
                    + "E\tfalse\tfinish dinner\t2021-09-30T20:00\t2021-09-30T22:00\n"
                    + "T\ttrue\tcomplete dinner";
            storageStub.storeAdd(sortTestStorageStub);
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
    public void isThisCmd_sortCommands_true() {
        assertTrue(Sort.isThisCmd(CMD_SORT_1));
        assertTrue(Sort.isThisCmd(CMD_SORT_2));
    }

    @Test
    public void isThisCmd_nonSortCommands_false() {
        assertFalse(Sort.isThisCmd(CMD_NON_SORT_1));
        assertFalse(Sort.isThisCmd(CMD_NON_SORT_2));
        assertFalse(Sort.isThisCmd(CMD_NON_SORT_3));
        assertFalse(Sort.isThisCmd(CMD_NON_SORT_4));
        assertFalse(Sort.isThisCmd(CMD_NON_SORT_5));
        assertFalse(Sort.isThisCmd(CMD_NON_SORT_6));
        assertFalse(Sort.isThisCmd(CMD_NON_SORT_7));
        assertFalse(Sort.isThisCmd(CMD_NON_SORT_8));
        assertFalse(Sort.isThisCmd(CMD_NON_SORT_9));
        assertFalse(Sort.isThisCmd(CMD_NON_SORT_10));
    }

    @Test
    public void constructor_sortCommand_correctField() {
        Sort sort1 = new Sort(CMD_SORT_1);
        Sort sort2 = new Sort(CMD_SORT_2);

        assertEquals(CMD_SORT_1, sort1.cmdContent);
        assertEquals(CMD_SORT_2, sort2.cmdContent);
    }

    @Test
    public void execute_validSortCommand_correctResponse() throws PoseidonException {
        String expectedResponse = "Here are the tasks in your list:\\n"
                + "  1\\. \\[E]\\[ ] finish dinner \\(from: .+ to .+\\)\\n"
                + "  2\\. \\[D]\\[X] complete work \\(by: .+\\)\\n"
                + "  3\\. \\[T]\\[ ] finish work\\n"
                + "  4\\. \\[T]\\[X] complete dinner\\n";

        Sort validSort1 = new Sort(CMD_SORT_1);
        Sort validSort2 = new Sort(CMD_SORT_2);
        String response1 = validSort1.execute(storageStub, taskList, ui);
        String response2 = validSort2.execute(storageStub, taskList, ui);

        assertTrue(Pattern.compile(expectedResponse).matcher(response1).matches());
        assertTrue(Pattern.compile(expectedResponse).matcher(response2).matches());
    }
}

