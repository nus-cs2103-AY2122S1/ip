package uhtredragnarson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import uhtredragnarson.exception.UhtredRagnarsonException;
import uhtredragnarson.util.Parser;
import uhtredragnarson.util.Storage;
import uhtredragnarson.util.TaskList;
import uhtredragnarson.util.Ui;

public class ParserTest {
    private final String filePath = "data/UhtredRagnarsonTest.txt";
    private final Storage storage = new Storage(filePath);

    @Test
    public void wrongCommandTest() {
        String userInput = "add borrow books";
        String expectedOutput = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualOutput;
        try {
            actualOutput = Parser.parse(userInput, new TaskList(), new Ui(), storage);
        } catch (UhtredRagnarsonException | IOException e) {
            actualOutput = e.getMessage();
        }
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void wrongDateTimeFormatTest() {
        String userInput = "deadline assignment /by 2021-09-3106:30";
        String expectedOutput = "☹ OOPS!!! You have to enter a valid date in the form yyyy-mm-dd";
        String actualOutput;
        try {
            actualOutput = Parser.parse(userInput, new TaskList(), new Ui(), storage);
        } catch (DateTimeParseException | UhtredRagnarsonException | IOException e) {
            actualOutput = e.getMessage();
        }
        assertEquals(expectedOutput, actualOutput);
    }
}
