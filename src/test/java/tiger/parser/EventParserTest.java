package tiger.parser;

import org.junit.jupiter.api.Test;
import tiger.constants.Priority;
import tiger.exceptions.TigerException;
import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.exceptions.inputs.TigerInvalidArgumentException;

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

    @Test
    public void eventParser_withPriority_success() {
        EventParser eventParser = new EventParser("event 4444 /at 15:24 /priority H");
        eventParser.parse();
        assertEquals("4444", eventParser.getTodo());
        assertEquals(Priority.HIGH, eventParser.getPriority());
    }

    @Test
    public void eventParser_withPriorityWithSpaces_success() {
        EventParser eventParser = new EventParser("event   44  44   /at 15:24 /priority H");
        eventParser.parse();
        assertEquals("44  44", eventParser.getTodo());
        assertEquals(Priority.HIGH, eventParser.getPriority());
    }

    @Test
    public void eventParser_multipleEventSmall_success() {
        // testing that the regrex actually selects only the first todo
        EventParser eventParser = new EventParser("event Event mop floor /at 14:25 /priority M");
        eventParser.parse();
        assertEquals("Event mop floor", eventParser.getTodo());
        assertEquals(Priority.MEDIUM, eventParser.getPriority());
    }

    @Test
    public void toDoParser_multipleTodoCaptial_success() {
        // testing that the regrex actually selects only the first todo
        EventParser eventParser = new EventParser("event EVENT mop floor /at 14:25 /priority M");
        eventParser.parse();
        assertEquals("EVENT mop floor", eventParser.getTodo());
        assertEquals(Priority.MEDIUM, eventParser.getPriority());
    }

    @Test
    public void eventParser_noDescription_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new EventParser("event ").parse();
        });
        assertTrue(thrown.toString().contains("Event description property cannot be empty.\nPlease ensure you key in " +
                "the command in the format specified."));
    }

    @Test
    public void eventParser_descriptionNoDate_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new EventParser("event fish").parse();
        });
        assertTrue(thrown.toString().contains("Event date property cannot be empty.\nPlease ensure you key in " +
                "the command in the format specified."));
    }

    @Test
    public void eventParser_descriptionNoDateWithPriority_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new EventParser("event fish /at /priority H").parse();
        });
        assertTrue(thrown.toString().contains("Event date property cannot be empty.\nPlease ensure you key in " +
                "the command in the format specified."));
    }

    @Test
    public void eventParser_descriptionEmptyWithDateAndPriority_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new EventParser("event /at 15:24 /priority H").parse();
        });
        assertTrue(thrown.toString().contains("Event description property cannot be empty.\nPlease ensure you key in " +
                "the command in the format specified."));
    }

    @Test
    public void eventParser_priorityEmptyWithDateAndDescription_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new EventParser("event fa fa /at 15:24 /priority").parse();
        });
        assertTrue(thrown.toString().contains("Event priority property cannot be empty.\nPlease ensure you key in " +
                "the command in the format specified."));
    }

    @Test
    public void eventParser_dateEmptyWithDescriptionAndDate_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new EventParser("event fa fa /at  /priority H").parse();
        });
        assertTrue(thrown.toString().contains("Event date property cannot be empty.\nPlease ensure you key in " +
                "the command in the format specified."));
    }

    @Test
    public void toDoParser_invalidPriorityArgument_failure() {
        TigerException thrown = assertThrows(TigerInvalidArgumentException.class, () -> {
            new EventParser("event fa fa /at  /priority S").parse();
        });
        assertTrue(thrown.toString().contains("S is not a valid argument for the Event priority command."));
    }
}
