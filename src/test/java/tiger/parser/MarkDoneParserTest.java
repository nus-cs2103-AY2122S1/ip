package tiger.parser;

import org.junit.jupiter.api.Test;
import tiger.exceptions.TigerException;
import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.exceptions.inputs.TigerInvalidArgumentException;
import tiger.exceptions.inputs.TigerTooManyInputsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MarkDoneParserTest {
    @Test
    public void markDoneParser_doneWithIndex_success() {
        assertEquals(1,
                new MarkDoneParser("done 1").index);
    }

    @Test
    public void markDoneParser_doneWithIndexLarge_success() {
        assertEquals(212,
                new MarkDoneParser("done 212").index);
    }

    @Test
    public void markDoneParser_zero_failure() {
        TigerException thrown = assertThrows(TigerInvalidArgumentException.class, () -> {
            MarkDoneParser markDoneParser = new MarkDoneParser("done 0");
        });
        assertTrue(thrown.toString().contains("0 is not a valid argument " +
                "for the Done command."));
    }

    @Test
    public void markDoneParser_negative_failure() {
        TigerException thrown = assertThrows(TigerInvalidArgumentException.class, () -> {
            MarkDoneParser markDoneParser = new MarkDoneParser("done -55");
        });
        assertTrue(thrown.toString().contains("-55 is not a valid argument " +
                "for the Done command."));
    }

    @Test
    public void markDoneParser_decimal_failure() {
        TigerException thrown = assertThrows(TigerInvalidArgumentException.class, () -> {
            MarkDoneParser markDoneParser = new MarkDoneParser("done 5.5");
        });
        assertTrue(thrown.toString().contains("5.5 is not a valid argument " +
                "for the Done command."));
    }

    @Test
    public void markDoneParser_spaces_success() {
        assertEquals(5,
                new MarkDoneParser("  done 5  ").index);
    }

    @Test
    public void markDoneParser_tooManyArguments_failure() {
        TigerException thrown = assertThrows(TigerTooManyInputsException.class, () -> {
            MarkDoneParser markDoneParser = new MarkDoneParser("done 5 5555");
        });
        assertTrue(thrown.toString().contains("Too many arguments specified!"));
    }

    @Test
    public void markDoneParser_missingArguments_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            MarkDoneParser markDoneParser = new MarkDoneParser("done");
        });
        assertTrue(thrown.toString().contains("Done index property cannot " +
                "be empty" +
                ".\nPlease ensure you key in the command in the format " +
                "specified."));
    }
}
