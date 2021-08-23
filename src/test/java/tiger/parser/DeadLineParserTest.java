package tiger.parser;

import org.junit.jupiter.api.Test;
import tiger.exceptions.TigerException;
import tiger.exceptions.inputs.TigerEmptyStringException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadLineParserTest {
    @Test
    public void deadLineParser_deadLineWithDescriptionAndTime_checkToDo() {
        // we assume time input is correct
        DeadLineParser deadLineParser = new DeadLineParser("dateline Q /by 2020-11-21 08:35");
        deadLineParser.parse();
        assertEquals("Q", deadLineParser.getTodo());
    }

    @Test
    public void deadLineParser_deadLineWithDescriptionAndTime_checkTime() {
        DeadLineParser deadLineParser = new DeadLineParser("dateline Q /by 2020-11-21 08:35");
        deadLineParser.parse();
        assertEquals("2020-11-21 08:35", deadLineParser.getDate().toString());
    }

    @Test
    public void deadLineParser_spaces_checkToDo() {
        DeadLineParser deadLineParser = new DeadLineParser("  dateline Q /by 2020-11-21 08:35  ");
        deadLineParser.parse();
        assertEquals("Q", deadLineParser.getTodo());
    }

    @Test
    public void deadLineParser_spaces_checkTime() {
        DeadLineParser deadLineParser = new DeadLineParser("  dateline Q /by 2020-11-21 08:35  ");
        deadLineParser.parse();
        assertEquals("2020-11-21 08:35", deadLineParser.getDate().toString());
    }


    @Test
    public void deadLineParser_missingArgumentsDescription_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new DeadLineParser("dateline /by 2020-11-21 08:35").parse();
        });
        assertTrue(thrown.toString().contains("Deadline description property cannot be empty.\nPlease ensure you key " +
                "in the command in the format specified."));
    }

    @Test
    public void deadLineParser_missingArgumentsDateTime_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new DeadLineParser("dateline QQ /by").parse();
        });
        assertTrue(thrown.toString().contains("Deadline date property cannot be empty.\nPlease ensure you key " +
                "in the command in the format specified."));
    }
}