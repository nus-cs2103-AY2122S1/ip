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
        EventParser eventParser = new EventParser("event Q /at 2020-11-21 08:35");
        eventParser.parse();
        assertEquals("Q", eventParser.getTodo());
    }

    @Test
    public void eventParser_eventWithDescriptionAndTime_checkTime() {
        EventParser eventParser = new EventParser("event Q /at 2020-11-21 08:35");
        eventParser.parse();
        assertEquals("2020-11-21 08:35", eventParser.getDate().toString());
    }

    @Test
    public void eventParser_spaces_checkToDo() {
        EventParser eventParser = new EventParser("  event Q /at 2020-11-21 08:35  ");
        eventParser.parse();
        assertEquals("Q", eventParser.getTodo());
    }

    @Test
    public void eventParser_spaces_checkTime() {
        EventParser eventParser = new EventParser("  event Q /at 2020-11-21 08:35  ");
        eventParser.parse();
        assertEquals("2020-11-21 08:35", eventParser.getDate().toString());
    }


    @Test
    public void eventParser_missingArgumentsDescription_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new EventParser("event /at 2020-11-21 08:35").parse();
        });
        assertTrue(thrown.toString().contains("Event description property cannot be empty.\nPlease ensure you key " +
                "in the command in the format specified."));
    }

    @Test
    public void eventParser_missingArgumentsDateTime_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new EventParser("event QQ /at").parse();
        });
        assertTrue(thrown.toString().contains("Event date property cannot be empty.\nPlease ensure you key " +
                "in the command in the format specified."));
    }

    @Test
    public void eventParser_wrongMatchingCommand_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new EventParser("event Q /by 2020-11-21 08:35").parse();
        });
        assertTrue(thrown.toString().contains("Event date property cannot be empty.\nPlease ensure you key " +
                "in the command in the format specified."));
    }
}
