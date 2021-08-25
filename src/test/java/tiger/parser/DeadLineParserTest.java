package tiger.parser;

import org.junit.jupiter.api.Test;
import tiger.constants.Priority;
import tiger.exceptions.TigerException;
import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.exceptions.inputs.TigerInvalidArgumentException;

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
        System.out.println(thrown.toString());
        assertTrue(thrown.toString().contains("Deadline date property cannot be empty.\nPlease ensure you key " +
                "in the command in the format specified."));
    }

    @Test
    public void dateParser_withPriority_success() {
        DeadLineParser dateParser = new DeadLineParser("deadline 4444 /by 15:24 /priority H");
        dateParser.parse();
        assertEquals("4444", dateParser.getTodo());
        assertEquals(Priority.HIGH, dateParser.getPriority());
    }

    @Test
    public void dateParser_withPriorityWithSpaces_success() {
        DeadLineParser dateParser = new DeadLineParser("deadline   44  44   /by 15:24 /priority H");
        dateParser.parse();
        assertEquals("44  44", dateParser.getTodo());
        assertEquals(Priority.HIGH, dateParser.getPriority());
    }

    @Test
    public void dateParser_multipleDeadlineSmall_success() {
        // testing that the regrex actually selects only the first todo
        DeadLineParser dateParser = new DeadLineParser("deadline Deadline mop floor /by 14:25 /priority M");
        dateParser.parse();
        assertEquals("Deadline mop floor", dateParser.getTodo());
        assertEquals(Priority.MEDIUM, dateParser.getPriority());
    }

    @Test
    public void toDoParser_multipleTodoCaptial_success() {
        // testing that the regrex actually selects only the first todo
        DeadLineParser dateParser = new DeadLineParser("deadline EVENT mop floor /by 14:25 /priority M");
        dateParser.parse();
        assertEquals("EVENT mop floor", dateParser.getTodo());
        assertEquals(Priority.MEDIUM, dateParser.getPriority());
    }

    @Test
    public void dateParser_noDescription_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new DeadLineParser("deadline ").parse();
        });
        assertTrue(thrown.toString().contains("Deadline description property cannot be empty.\nPlease ensure you key in " +
                "the command in the format specified."));
    }

    @Test
    public void dateParser_descriptionNoDate_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new DeadLineParser("deadline fish").parse();
        });
        assertTrue(thrown.toString().contains("Deadline date property cannot be empty.\nPlease ensure you key in " +
                "the command in the format specified."));
    }

    @Test
    public void dateParser_descriptionNoDateWithPriority_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new DeadLineParser("deadline fish /by /priority H").parse();
        });
        assertTrue(thrown.toString().contains("Deadline date property cannot be empty.\nPlease ensure you key in " +
                "the command in the format specified."));
    }

    @Test
    public void dateParser_descriptionEmptyWithDateAndPriority_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new DeadLineParser("deadline /by 15:24 /priority H").parse();
        });
        assertTrue(thrown.toString().contains("Deadline description property cannot be empty.\nPlease ensure you key in " +
                "the command in the format specified."));
    }

    @Test
    public void dateParser_priorityEmptyWithDateAndDescription_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new DeadLineParser("deadline fa fa /by 15:24 /priority").parse();
        });
        assertTrue(thrown.toString().contains("Deadline priority property cannot be empty.\nPlease ensure you key in " +
                "the command in the format specified."));
    }

    @Test
    public void dateParser_dateEmptyWithDescriptionAndDate_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new DeadLineParser("deadline fa fa /by  /priority H").parse();
        });
        assertTrue(thrown.toString().contains("Deadline date property cannot be empty.\nPlease ensure you key in " +
                "the command in the format specified."));
    }

    @Test
    public void toDoParser_invalidPriorityArgument_failure() {
        TigerException thrown = assertThrows(TigerInvalidArgumentException.class, () -> {
            new DeadLineParser("deadline fa fa /by  /priority S").parse();
        });
        assertTrue(thrown.toString().contains("S is not a valid argument for the Deadline priority command."));
    }
}