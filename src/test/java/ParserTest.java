import duke.DukeException;
import duke.Parser;
import duke.command.*;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void exitTest() throws DukeException {
        TaskList testList = new TaskList();
        Parser test = new Parser("bye", testList);
        assertEquals(true, test.checkOperation() instanceof ExitCommand);
    }

    @Test
    public void listTest() throws DukeException {
        TaskList testList = new TaskList();
        Parser test = new Parser("list", testList);
        assertEquals(true, test.checkOperation() instanceof ListCommand);

    }

    @Test
    public void todoTest() throws DukeException {
        TaskList testList = new TaskList();
        Parser test = new Parser("todo cs2103t project", testList);
        assertEquals(true, test.checkOperation() instanceof AddCommand);
    }

    @Test
    public void deadlineTest() throws DukeException {
        TaskList testList = new TaskList();
        Parser test = new Parser("deadline cs2103t project /by 2021-08-27 23:59", testList);
        assertEquals(true, test.checkOperation() instanceof AddCommand);
    }

    @Test
    public void eventTest() throws DukeException {
        TaskList testList = new TaskList();
        Parser test = new Parser("event cs2103t project /at 2021-08-27 23:59", testList);
        assertEquals(true, test.checkOperation() instanceof AddCommand);
    }

    @Test
    public void doneTest() throws DukeException {
        TaskList testList = new TaskList();
        testList.add(new Todo("work"));
        Parser test = new Parser("done 1", testList);
        assertEquals(true, test.checkOperation() instanceof DoneCommand);
    }

    @Test
    public void deleteTest() throws DukeException {
        TaskList testList = new TaskList();
        testList.add(new Todo("work"));
        Parser test = new Parser("delete 1", testList);
        assertEquals(true, test.checkOperation() instanceof DeleteCommand);
    }


    @Test
    public void invalidCommandTest1() {
        TaskList testList = new TaskList();
        Parser test = new Parser("bye bye", testList);
        Exception exception = assertThrows(DukeException.class, () -> test.checkOperation());

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void invalidCommandTest2() {
        TaskList testList = new TaskList();
        Parser test = new Parser("deadline work /by ", testList);
        Exception exception = assertThrows(DukeException.class, () -> test.checkOperation());

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void invalidCommandTest3() {
        TaskList testList = new TaskList();
        Parser test = new Parser("deadline work /at 2021-08-27 23:59", testList);
        Exception exception = assertThrows(DukeException.class, () -> test.checkOperation());

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void invalidCommandTest4() {
        TaskList testList = new TaskList();
        Parser test = new Parser("event work /at ", testList);
        Exception exception = assertThrows(DukeException.class, () -> test.checkOperation());

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void invalidCommandTest5() {
        TaskList testList = new TaskList();
        Parser test = new Parser("event work /by 2021-08-27 23:59", testList);
        Exception exception = assertThrows(DukeException.class, () -> test.checkOperation());

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void invalidCommandTest6() {
        TaskList testList = new TaskList();
        Parser test = new Parser("event /at 2021-08-27 23:59", testList);
        Exception exception = assertThrows(DukeException.class, () -> test.checkOperation());

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void invalidCommandTest7() {
        TaskList testList = new TaskList();
        Parser test = new Parser("deadline /by 2021-08-27 23:59", testList);
        Exception exception = assertThrows(DukeException.class, () -> test.checkOperation());

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void invalidCommandTest8() {
        TaskList testList = new TaskList();
        testList.add(new Todo("work"));
        Parser test = new Parser("done -1", testList);
        Exception exception = assertThrows(DukeException.class, () -> test.checkOperation());

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void invalidCommandTest9() {
        TaskList testList = new TaskList();
        Parser test = new Parser("done 2", testList);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> test.checkOperation().execute());

        String expectedMessage = "☹ OOPS!!! Index entered is not valid. " +
                "Please use 'list' and check for the appropriate index for task(s).";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void invalidCommandTest10() {
        TaskList testList = new TaskList();
        testList.add(new Todo("work"));
        Parser test = new Parser("delete -1", testList);
        Exception exception = assertThrows(DukeException.class, () -> test.checkOperation());

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));
    }

    @Test
    public void invalidCommandTest11() {
        TaskList testList = new TaskList();
        Parser test = new Parser("delete 2", testList);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> test.checkOperation().execute());

        String expectedMessage = "☹ OOPS!!! Index entered is not valid. " +
                "Please use 'list' and check for the appropriate index for task(s).";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.equals(expectedMessage));
    }
}
