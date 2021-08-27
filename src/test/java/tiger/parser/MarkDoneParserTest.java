package tiger.parser;

import org.junit.jupiter.api.Test;
import tiger.exceptions.TigerException;
import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.exceptions.inputs.TigerInvalidArgumentException;
import tiger.exceptions.inputs.TigerTooManyInputsException;
import tiger.constants.Messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MarkDoneParserTest {
    @Test
    public void markDoneParser_doneWithIndex_success() {
        MarkDoneParser markDoneParser = new MarkDoneParser("done 1");
        markDoneParser.parse();
        assertEquals(1, markDoneParser.getIndex());
    }

    @Test
    public void markDoneParser_doneWithIndexLarge_success() {
        MarkDoneParser markDoneParser = new MarkDoneParser("done 212");
        markDoneParser.parse();
        assertEquals(212, markDoneParser.getIndex());
    }

    @Test
    public void markDoneParser_zero_failure() {
        TigerException thrown = assertThrows(TigerInvalidArgumentException.class, () -> {
            new MarkDoneParser("done 0").parse();
        });
        assertTrue(thrown.toString().contains("0 is not a valid argument for the Done command."));
    }

    @Test
    public void markDoneParser_negative_failure() {
        TigerException thrown = assertThrows(TigerInvalidArgumentException.class, () -> {
            new MarkDoneParser("done -55").parse();
        });
        assertTrue(thrown.toString().contains("-55 is not a valid argument for the Done command."));
    }

    @Test
    public void markDoneParser_decimal_failure() {
        TigerException thrown = assertThrows(TigerInvalidArgumentException.class, () -> {
            new MarkDoneParser("done 5.5").parse();
        });
        assertTrue(thrown.toString().contains("5.5 is not a valid argument for the Done command."));
    }

    @Test
    public void markDoneParser_spaces_success() {
        MarkDoneParser markDoneParser = new MarkDoneParser("  done 5  ");
        markDoneParser.parse();
        assertEquals(5, markDoneParser.getIndex());
    }

    @Test
    public void markDoneParser_tooManyArguments_failure() {
        TigerException thrown = assertThrows(TigerTooManyInputsException.class, () -> {
            new MarkDoneParser("done 5 5555").parse();
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_TOO_MANY_ARGUMENTS.getMessage()));
    }

    @Test
    public void markDoneParser_missingArguments_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new MarkDoneParser("done").parse();
        });
        assertTrue(thrown.toString().contains("Done index property cannot be empty.\nPlease ensure you key " +
                "in the command in the format specified."));
    }
}
