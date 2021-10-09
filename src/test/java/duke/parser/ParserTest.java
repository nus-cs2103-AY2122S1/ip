package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.exception.NotRecognizeException;

public class ParserTest {
    @Test
    public void checkDigit_negativeWholeDigit_success() {
        String[] negatives = {"-1", "-1534", "-11"};

        for (String negative : negatives) {
            boolean flag = Parser.checkIsDigit(negative);
            assertTrue(flag);
        }
    }

    @Test
    public void checkResponse_invalidResponse_exceptionThrown() {
        //Solution adapted from https://github.com/nus-cs2103-AY2122S1/ip/pull/13/files#
        String[] commands = {"blahfff", "ls", "lsti", "listaa", "donf", "doffna", "donefa",
            "tod", "todi", "todo?", "dead line ", "deed1", "dead line", "dead",
            "even", "evanta", "event31", "del", "deletaa", "delte", "findf", "fi", "findaff"
        };

        for (String command : commands) {
            NotRecognizeException exception = assertThrows(NotRecognizeException.class, () -> Parser.parse(command));

            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(",
                    exception.getMessage());
        }
    }
}
