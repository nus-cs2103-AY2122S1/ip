package poseidon.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents a testing class for {@code PoseidonStorageReadWriteException}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class PoseidonStorageReadWriteExceptionTest {

    @Test
    public void constructor_newPoseidonStorageReadWriteException_correctErrorMessage() {
        String sampleErrorMsg = "Sample Error Message";
        PoseidonStorageReadWriteException p = new PoseidonStorageReadWriteException(sampleErrorMsg);

        String expectedMsg = "Read and/or Write operations with the file on the local hard disk failed.\n"
                + sampleErrorMsg + "\n"
                + "Please exit the bot and fix the issue to prevent any data loss.";
        assertEquals(expectedMsg, p.getMessage());
    }
}
