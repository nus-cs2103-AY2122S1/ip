package tiger.parser;

import org.junit.jupiter.api.Test;
import tiger.exceptions.TigerException;
import tiger.exceptions.inputs.TigerEmptyStringException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadLineParserTest {
    @Test
    public void DeadLineParser_deadLineWithIndexAndTime_checkToDo() {
        // we assume time input is correct
        assertEquals("Q",
                new DeadLineParser("dateline Q /by 2020-11-21 08:35").todo);
    }

    @Test
    public void DeadLineParser_deadLineWithIndexAndTime_checkTime() {
        assertEquals("2020-11-21 08:35",
                new DeadLineParser("dateline Q /by 2020-11-21 08:35").dateLine);
    }

    @Test
    public void DeadLineParser_spaces_checkToDo() {
    assertEquals("Q",
                new DeadLineParser("  dateline Q /by 2020-11-21 08:35  ").todo);
    }

    @Test
    public void DeadLineParser_spaces_checkTime() {
        assertEquals("2020-11-21 08:35",
                new DeadLineParser("  dateline Q /by 2020-11-21 08:35  ").dateLine);
    }


    @Test
    public void DeadLineParser_missingArgumentsDescription_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            DeadLineParser deadLineParser = new DeadLineParser("dateline /by 2020-11-21 08:35");
        });
        assertTrue(thrown.toString().contains("Deadline description property cannot " +
                "be empty" +
                ".\nPlease ensure you key in the command in the format " +
                "specified."));
    }

    @Test
    public void DeadLineParser_missingArgumentsDateTime_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            DeadLineParser deadLineParser = new DeadLineParser("dateline " +
                    "QQ /by");
        });
        assertTrue(thrown.toString().contains("Deadline date property cannot " +
                "be empty" +
                ".\nPlease ensure you key in the command in the format " +
                "specified."));
    }
}