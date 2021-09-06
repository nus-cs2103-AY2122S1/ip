package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.exception.NotRecognizeException;

public class ParserTest {
    @Test
    public void checkDigit_negativeWholeDigit_success() {
        String[] negatives = {
                "-1",
                "-1534",
                "-11"
        };

        for (String negative : negatives) {
            boolean flag = Parser.checkIsDigit(negative);
            assertTrue(flag);
        }
    }

    @Test
    public void checkResponse_invalidResponse_exceptionThrown() {
        String[] commands = {
                "blah",
                "ls", "lst", "lista",
                "don", "dona", "donef",
                "tod", "todi", "todo.",
                "dead", "deedline1", "dead line",
                "eve", "evant", "event3",
                "del", "deleta", "deleteh",
                "fin", "fine", "finda",
        };

        for (String command : commands) {
            NotRecognizeException exception = assertThrows(NotRecognizeException.class,
                    () -> Parser.parse(command));

            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(",
                    exception.getMessage());
        }
    }
}
