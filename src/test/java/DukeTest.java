import duke.Duke;

import duke.dukeException.DukeException;
import duke.tools.Parser;
import duke.tools.Ui;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testWelcomeMessage(){ ;
        Ui userInterface = new Ui();
        String actualWelcomeMessage = userInterface.getWelcomeMessage();
        String expectedWelcomeMessage = "Hello! I'm Duke. A friendly chatbot!! :)\n"
                + "What can I do for you?\n";
        assertEquals(expectedWelcomeMessage, actualWelcomeMessage);
    }


    @Test
    public void testGetInput() {
        String expectedResult = "Please input your command: ";
        Ui userInterface = new Ui();
        String actualResult = userInterface.testReadCommand();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetList() {
        String expectedResult = "Sure!! You have 5 tasks in your list: \n" + "\n" +
                "1. [T][ ] borrow book\n" +
                "2. [D][ ] return book (by: Aug 24 2021, 11:59)\n" +
                "3. [E][ ] project meeting (at: Aug 24 2021, 11:59)\n" +
                "4. [T][ ] sleep\n" +
                "5. [T][ ] eat now";
        Duke duke = new Duke("src/test/dukeTest.txt");
        String actualResult = duke.getTaskList();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testFindTask1() {
        String expectedResult = "Here are the matching tasks in your list: \n" +
                "1. [T][ ] borrow book\n" +
                "2. [D][ ] return book (by: Aug 24 2021, 11:59)";
        Duke duke = new Duke("src/test/dukeTest.txt");
        String actualResult = duke.findTask("find book");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testFindTask2() {
        String expectedResult = "Here are the matching tasks in your list: ";
        Duke duke = new Duke("src/test/dukeTest.txt");
        String actualResult = duke.findTask("find some");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testParser() throws DukeException, IOException {
        String expectedResult = "Bye. Hope to see you again soon!";
        String actualResult = Parser.parse("bye");
        assertEquals(expectedResult, actualResult);
    }
}