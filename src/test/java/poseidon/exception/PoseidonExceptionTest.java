package poseidon.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents a testing class for {@code PoseidonException}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class PoseidonExceptionTest {

    @Test
    public void constructor_newPoseidonException_correctErrorMessage() {
        String sampleErrorMsg = "Sample Error Message";
        PoseidonException poseidonException = new PoseidonException(sampleErrorMsg);
        assertEquals(sampleErrorMsg, poseidonException.getMessage());
    }
}
