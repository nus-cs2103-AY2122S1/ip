package duke;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class ParserTest {


    @Test
    void parseTestList() throws DukeException {
        Parser testParser = new Parser();
        String input = "list";
        assertEquals(0, testParser.parse(input));
    }

    @Test
    void parseTestBye() throws DukeException {
        Parser testParser = new Parser();
        String input = "byebye";
        assertEquals(1, testParser.parse(input));
    }

    @Test
    void parseTestError() throws DukeException {
        Parser testParser = new Parser();
        String input = "lalla";
        Exception exception = assertThrows(DukeException.class, () -> {
            testParser.parse(input);
        });
        String expectedMessage = "Dude! I don't understand what you're saying!";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
