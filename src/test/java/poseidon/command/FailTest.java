package poseidon.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import poseidon.exception.PoseidonException;
import poseidon.stub.StorageStub;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;


/**
 * Represents a testing class for {@code Fail}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class FailTest {

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
    public void isThisCmd_anyCommand_true() {
        assertTrue(Fail.isThisCmd(CMD_1));
        assertTrue(Fail.isThisCmd(CMD_2));
        assertTrue(Fail.isThisCmd(CMD_3));
        assertTrue(Fail.isThisCmd(CMD_4));
        assertTrue(Fail.isThisCmd(CMD_5));
        assertTrue(Fail.isThisCmd(CMD_6));
        assertTrue(Fail.isThisCmd(CMD_7));
        assertTrue(Fail.isThisCmd(CMD_8));
        assertTrue(Fail.isThisCmd(CMD_9));
        assertTrue(Fail.isThisCmd(CMD_10));
        assertTrue(Fail.isThisCmd(CMD_11));
    }

    @Test
    public void constructor_anyFailCommand_correctField() {
        Fail fail1 = new Fail(CMD_1);
        Fail fail2 = new Fail(CMD_2);
        // ...
        Fail fail10 = new Fail(CMD_10);
        Fail fail11 = new Fail(CMD_11);

        assertEquals(CMD_1, fail1.cmdContent);
        assertEquals(CMD_2, fail2.cmdContent);
        // ...
        assertEquals(CMD_10, fail10.cmdContent);
        assertEquals(CMD_11, fail11.cmdContent);
    }

    @Test
    public void execute_anyFailCommand_correctResponse() {
        String expectedResponse = "I didn't get that. Please try again.";

        Fail fail1 = new Fail(CMD_1);
        String response1 = fail1.execute(storageStub, taskList, ui);

        Fail fail2 = new Fail(CMD_2);
        String response2 = fail2.execute(storageStub, taskList, ui);
        // ...
        Fail fail10 = new Fail(CMD_10);
        String response10 = fail10.execute(storageStub, taskList, ui);

        Fail fail11 = new Fail(CMD_11);
        String response11 = fail11.execute(storageStub, taskList, ui);

        assertEquals(expectedResponse, response1);
        assertEquals(expectedResponse, response2);
        // ...
        assertEquals(expectedResponse, response10);
        assertEquals(expectedResponse, response11);
    }
}

