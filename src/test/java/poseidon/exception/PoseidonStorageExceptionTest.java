package poseidon.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents a testing class for {@code PoseidonStorageException}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class PoseidonStorageExceptionTest {

    @Test
    public void constructor_newPoseidonStorageException_correctErrorMessage() {
        String sampleErrorMsg = "Sample Error Message";
        PoseidonStorageException p = new PoseidonStorageException(sampleErrorMsg);

        String expectedMsg = sampleErrorMsg + "\n"
                + "Please exit the bot and fix the issue to prevent any data loss.";
        assertEquals(expectedMsg, p.getMessage());
    }
}
