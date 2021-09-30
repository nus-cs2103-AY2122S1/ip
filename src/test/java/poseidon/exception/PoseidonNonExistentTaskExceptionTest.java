package poseidon.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents a testing class for {@code PoseidonNonExistentTaskException}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class PoseidonNonExistentTaskExceptionTest {

    @Test
    public void constructor_newPoseidonNonExistentTaskException_correctErrorMessage() {
        PoseidonNonExistentTaskException p = new PoseidonNonExistentTaskException();

        String expectedMsg = "That task doesn't exist.\n"
                + "Please Try again.";
        assertEquals(expectedMsg, p.getMessage());
    }
}
