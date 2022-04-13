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
 * Represents a testing class for {@code AddTodo}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class AddTodoTest {

    private static final String CMD_TODO_1 = "todo";
    private static final String CMD_TODO_2 = "todo      ";

    private static final String CMD_VALID_TODO_1 = "todo finish work";
    private static final String CMD_VALID_TODO_2 = "todo     finish work     ";

    private static final String CMD_NON_TODO_1 = "deadline finish work /by 2021 09 30 2359";
    private static final String CMD_NON_TODO_2 = "event finish work /from 2021 09 30 2000 to 2021 09 30 2200";
    private static final String CMD_NON_TODO_3 = "bye";
    private static final String CMD_NON_TODO_4 = "delete 10";
    private static final String CMD_NON_TODO_5 = "done 10";
    private static final String CMD_NON_TODO_6 = "find work";
    private static final String CMD_NON_TODO_7 = "help";
    private static final String CMD_NON_TODO_8 = "list";
    private static final String CMD_NON_TODO_9 = "list -s";
    private static final String CMD_NON_TODO_10 = "gibberish";

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
    public void isThisCmd_todoCommands_true() {
        assertTrue(AddTodo.isThisCmd(CMD_TODO_1));
        assertTrue(AddTodo.isThisCmd(CMD_TODO_2));

        assertTrue(AddTodo.isThisCmd(CMD_VALID_TODO_1));
        assertTrue(AddTodo.isThisCmd(CMD_VALID_TODO_2));
    }

    @Test
    public void isThisCmd_nonTodoCommands_false() {
        assertFalse(AddTodo.isThisCmd(CMD_NON_TODO_1));
        assertFalse(AddTodo.isThisCmd(CMD_NON_TODO_2));
        assertFalse(AddTodo.isThisCmd(CMD_NON_TODO_3));
        assertFalse(AddTodo.isThisCmd(CMD_NON_TODO_4));
        assertFalse(AddTodo.isThisCmd(CMD_NON_TODO_5));
        assertFalse(AddTodo.isThisCmd(CMD_NON_TODO_6));
        assertFalse(AddTodo.isThisCmd(CMD_NON_TODO_7));
        assertFalse(AddTodo.isThisCmd(CMD_NON_TODO_8));
        assertFalse(AddTodo.isThisCmd(CMD_NON_TODO_9));
        assertFalse(AddTodo.isThisCmd(CMD_NON_TODO_10));
    }

    @Test
    public void constructor_todoCommand_correctField() {
        AddTodo addTodo1 = new AddTodo(CMD_TODO_1);
        AddTodo addTodo2 = new AddTodo(CMD_TODO_2);

        AddTodo addValidTodo1 = new AddTodo(CMD_VALID_TODO_1);
        AddTodo addValidTodo2 = new AddTodo(CMD_VALID_TODO_2);

        assertEquals(CMD_TODO_1, addTodo1.cmdContent);
        assertEquals(CMD_TODO_2, addTodo2.cmdContent);

        assertEquals(CMD_VALID_TODO_1, addValidTodo1.cmdContent);
        assertEquals(CMD_VALID_TODO_2, addValidTodo2.cmdContent);
    }

    @Test
    public void execute_validAddTodoCommand_correctResponse() throws PoseidonException {
        String expectedResponse = "Got it\\. I've added this task:\\n"
                + "  \\[T]\\[ ] finish work\\n"
                + "Now you have \\d+ tasks in the list\\.";

        AddTodo validAddTodo1 = new AddTodo(CMD_VALID_TODO_1);
        AddTodo validAddTodo2 = new AddTodo(CMD_VALID_TODO_2);
        String response1 = "";
        String response2 = "";

        try {
            response1 = validAddTodo1.execute(storageStub, taskList, ui);
        } catch (PoseidonException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
        assertTrue(Pattern.compile(expectedResponse).matcher(response1).matches());

        try {
            response2 = validAddTodo2.execute(storageStub, taskList, ui);
        } catch (PoseidonException ex) {
            System.out.println(ex.getMessage());
            fail();
        }
        assertTrue(Pattern.compile(expectedResponse).matcher(response2).matches());
    }

    @Test
    public void execute_invalidCommandFormat_correctExceptionMsg() throws PoseidonException {
        String expectedExceptionMsg = "There appears to be a typo in your TODO command.\n"
                + "The command should be of the form:\n"
                + "  todo 'description'\n"
                + "Please try again.";

        AddTodo invalidAddTodo1 = new AddTodo(CMD_TODO_1);
        AddTodo invalidAddTodo2 = new AddTodo(CMD_TODO_2);

        try {
            String response1 = invalidAddTodo1.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonIncorrectCommandFormatException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }

        try {
            String response2 = invalidAddTodo2.execute(storageStub, taskList, ui);
            fail();
        } catch (PoseidonIncorrectCommandFormatException ex) {
            assertEquals(expectedExceptionMsg, ex.getMessage());
        }
    }
}

