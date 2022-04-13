package poseidon.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents a testing class for {@code PoseidonIncorrectCommandFormatException}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class PoseidonIncorrectCommandFormatExceptionTest {

    @Test
    public void constructor_newPoseidonIncorrectCommandFormatException_correctErrorMessage() {
        String sampleCommandName = "SAMPLE";
        String sampleCorrectFormat = "sample 'sample'";
        PoseidonIncorrectCommandFormatException p =
                new PoseidonIncorrectCommandFormatException(sampleCommandName, sampleCorrectFormat);

        String expectedMsg = "There appears to be a typo in your " + sampleCommandName + " command.\n"
                + "The command should be of the form:\n"
                + "  " + sampleCorrectFormat + "\n"
                + "Please try again.";
        assertEquals(expectedMsg, p.getMessage());
    }
}
