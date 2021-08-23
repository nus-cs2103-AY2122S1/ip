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

public class DeleteParserTest {
    @Test
    public void deleteParser_deleteWithIndex_success() {
        DeleteParser deleteParser = new DeleteParser("delete 1");
        deleteParser.parse();
        assertEquals(1, deleteParser.getIndex());
    }

    @Test
    public void deleteParser_deleteWithIndexLarge_success() {
        DeleteParser deleteParser = new DeleteParser("delete 212");
        deleteParser.parse();
        assertEquals(212, deleteParser.getIndex());
    }

    @Test
    public void deleteParser_zero_failure() {
        TigerException thrown = assertThrows(TigerInvalidArgumentException.class, () -> {
            DeleteParser deleteParser = new DeleteParser("delete 0");
            deleteParser.parse();
        });
        assertTrue(thrown.toString().contains("0 is not a valid argument " +
                "for the Delete command."));
    }

    @Test
    public void deleteParser_negative_failure() {
        TigerException thrown = assertThrows(TigerInvalidArgumentException.class, () -> {
            DeleteParser deleteParser = new DeleteParser("delete -55");
            deleteParser.parse();
        });
        assertTrue(thrown.toString().contains("-55 is not a valid argument " +
                "for the Delete command."));
    }

    @Test
    public void deleteParser_decimal_failure() {
        TigerException thrown = assertThrows(TigerInvalidArgumentException.class, () -> {
            DeleteParser deleteParser = new DeleteParser("delete 5.5");
            deleteParser.parse();
        });
        assertTrue(thrown.toString().contains("5.5 is not a valid argument " +
                "for the Delete command."));
    }

    @Test
    public void deleteParser_spaces_success() {
        DeleteParser deleteParser = new DeleteParser("  delete 5  ");
        deleteParser.parse();
        assertEquals(5, deleteParser.getIndex());
    }

    @Test
    public void deleteParser_tooManyArguments_failure() {
        TigerException thrown = assertThrows(TigerTooManyInputsException.class, () -> {
            DeleteParser deleteParser = new DeleteParser("delete 5 5555");
            deleteParser.parse();
        });
        assertTrue(thrown.toString().contains(Messages.EXCEPTION_INPUT_TOO_MANY_ARGUMENTS.getMessage()));
    }

    @Test
    public void deleteParser_missingArguments_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            DeleteParser deleteParser = new DeleteParser("delete");
            deleteParser.parse();
        });
        assertTrue(thrown.toString().contains("Delete index property cannot be empty.\nPlease ensure you key in the " +
                "command in the format specified."));
    }
}
