package tiger.parser;

import org.junit.jupiter.api.Test;
import tiger.exceptions.TigerException;
import tiger.exceptions.inputs.TigerEmptyStringException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventParserTest {
    @Test
    public void eventParser_eventWithDescriptionAndTime_checkToDo() {
        // we assume time input is correct
        assertEquals("Q",
                new EventParser("event Q /at 2020-11-21 08:35").todo);
    }

    @Test
    public void eventParser_eventWithDescriptionAndTime_checkTime() {
        assertEquals("2020-11-21 08:35",
                new EventParser("event Q /at 2020-11-21 08:35").eventAt);
    }

    @Test
    public void eventParser_spaces_checkToDo() {
        assertEquals("Q",
                new EventParser("  event Q /at 2020-11-21 08:35  ").todo);
    }

    @Test
    public void eventParser_spaces_checkTime() {
        assertEquals("2020-11-21 08:35",
                new EventParser("  event Q /at 2020-11-21 08:35  ").eventAt);
    }


    @Test
    public void eventParser_missingArgumentsDescription_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            EventParser eventParser = new EventParser("event /at 2020-11-21 " +
                    "08:35");
        });
        assertTrue(thrown.toString().contains("Event description property cannot " +
                "be empty" +
                ".\nPlease ensure you key in the command in the format " +
                "specified."));
    }

    @Test
    public void eventParser_missingArgumentsDateTime_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            EventParser eventParser = new EventParser("event " +
                    "QQ /at");
        });
        assertTrue(thrown.toString().contains("Event date property cannot " +
                "be empty" +
                ".\nPlease ensure you key in the command in the format " +
                "specified."));
    }

    @Test
    public void eventParser_wrongMatchingCommand_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            EventParser eventParser = new EventParser("event Q /by 2020-11-21" +
                    " 08:35");
        });
        assertTrue(thrown.toString().contains("Event date property cannot " +
                "be empty" +
                ".\nPlease ensure you key in the command in the format " +
                "specified."));
    }
}
