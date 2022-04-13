package poseidon.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents a testing class for {@code PoseidonDateTimeParseException}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class PoseidonDateTimeParseExceptionTest {

    @Test
    public void constructor_newPoseidonDateTimeParseException_correctErrorMessage() {
        String sampleErrorMsg = "Sample Error Message";
        PoseidonDateTimeParseException p = new PoseidonDateTimeParseException(sampleErrorMsg);

        String expectedMsg = "Date and Time couldn't be parsed.\n"
                + sampleErrorMsg + "\n"
                + "Please try again.";
        assertEquals(expectedMsg, p.getMessage());
    }
}
