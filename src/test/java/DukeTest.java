import duke.dukeException.DukeException;
import duke.tools.Ui;
import duke.tools.Parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testWelcomeMessage(){ ;
        Ui userInterface = new Ui();
        String actualWelcomeMessage = userInterface.getWelcomeMessage();
        String expectedWelcomeMessage = "____________________________________________________________\n" +
                " ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|\n" +
                "\n" +
                "Hello! I'm duke.Duke. A friendly chatbot!! :)\n" +
                "What can I do for you?\n" +
                "____________________________________________________________";
        assertEquals(expectedWelcomeMessage, actualWelcomeMessage);
    }

    @Test
    public void testPraserBye() throws DukeException, IOException {
        boolean isTrue = true;
        boolean actualResult = Parser.parse("bye");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetInput() {
        String expectedResult = "Please input your command: ";
        Ui userInterface = new Ui();
        String actualResult = userInterface.testReadCommand();
        assertEquals(expectedResult, actualResult);
    }

}