package tiger.parser;

import org.junit.jupiter.api.Test;
import tiger.exceptions.TigerException;
import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.exceptions.inputs.TigerInvalidArgumentException;
import tiger.exceptions.inputs.TigerTooManyInputsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteParserTest {
    @Test
    public void deleteParser_deleteWithIndex_success() {
        assertEquals(1,
                new DeleteParser("delete 1").index);
    }

    @Test
    public void deleteParser_deleteWithIndexLarge_success() {
        assertEquals(212,
                new DeleteParser("delete 212").index);
    }

    @Test
    public void deleteParser_zero_failure() {
        TigerException thrown = assertThrows(TigerInvalidArgumentException.class, () -> {
            DeleteParser deleteParser = new DeleteParser("delete 0");
        });
        assertTrue(thrown.toString().contains("0 is not a valid argument " +
                "for the Delete command."));
    }

    @Test
    public void deleteParser_negative_failure() {
        TigerException thrown = assertThrows(TigerInvalidArgumentException.class, () -> {
            DeleteParser deleteParser = new DeleteParser("delete -55");
        });
        assertTrue(thrown.toString().contains("-55 is not a valid argument " +
                "for the Delete command."));
    }

    @Test
    public void deleteParser_decimal_failure() {
        TigerException thrown = assertThrows(TigerInvalidArgumentException.class, () -> {
            DeleteParser deleteParser = new DeleteParser("delete 5.5");
        });
        assertTrue(thrown.toString().contains("5.5 is not a valid argument " +
                "for the Delete command."));
    }

    @Test
    public void deleteParser_spaces_success() {
        assertEquals(5,
                new DeleteParser("  delete 5  ").index);
    }

    @Test
    public void deleteParser_tooManyArguments_failure() {
        TigerException thrown = assertThrows(TigerTooManyInputsException.class, () -> {
            DeleteParser deleteParser = new DeleteParser("delete 5 5555");
        });
        assertTrue(thrown.toString().contains("Too many arguments specified!"));
    }

    @Test
    public void deleteParser_missingArguments_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            DeleteParser deleteParser = new DeleteParser("delete");
        });
        assertTrue(thrown.toString().contains("Delete index property cannot " +
                "be empty" +
                ".\nPlease ensure you key in the command in the format " +
                "specified."));
    }
}
