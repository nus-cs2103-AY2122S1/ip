package tiger.parser;

import org.junit.jupiter.api.Test;
import tiger.constants.Priority;
import tiger.exceptions.TigerException;
import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.exceptions.inputs.TigerInvalidArgumentException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoParserTest {
    @Test
    public void toDoParser_toDoWithString_success() {
        ToDoParser toDoParser = new ToDoParser("todo fish");
        toDoParser.parse();
        assertEquals("Fish", toDoParser.getTodo());
    }

    @Test
    public void toDoParser_toDoWithStringLarge_success() {
        ToDoParser toDoParser = new ToDoParser("todo 3.1415926535897932384626433832795");
        toDoParser.parse();
        assertEquals("3.1415926535897932384626433832795", toDoParser.getTodo());
    }

    @Test
    public void toDoParser_toDoWithStringWithSpaces_success() {
        ToDoParser toDoParser = new ToDoParser("todo 3.1415926535897932 384626433 832795");
        toDoParser.parse();
        assertEquals("3.1415926535897932 384626433 832795", toDoParser.getTodo());
    }

    @Test
    public void toDoParser_padding_success() {
        ToDoParser toDoParser = new ToDoParser("  todo 3.1415926535897932 384626433 832795  ");
        toDoParser.parse();
        assertEquals("3.1415926535897932 384626433 832795", toDoParser.getTodo());
    }

    @Test
    public void toDoParser_missingArguments_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new ToDoParser("todo").parse();
        });
        assertTrue(thrown.toString().contains("ToDo description property cannot be empty.\nPlease ensure you key in " +
                "the command in the format specified."));
    }

    @Test
    public void toDoParser_withPriority_success() {
        ToDoParser toDoParser = new ToDoParser("todo 4444 /priority H");
        toDoParser.parse();
        assertEquals("4444", toDoParser.getTodo());
        assertEquals(Priority.HIGH, toDoParser.getPriority());
    }

    @Test
    public void toDoParser_withPriorityWithSpaces_success() {
        ToDoParser toDoParser = new ToDoParser("todo     444  4      /priority   H   ");
        toDoParser.parse();
        assertEquals("4444  4", toDoParser.getTodo());
        assertEquals(Priority.HIGH, toDoParser.getPriority());
    }

    @Test
    public void toDoParser_multipleTodoSmall_success() {
        // testing that the regrex actually selects only the first todo
        ToDoParser toDoParser = new ToDoParser("todo ToDo mop floor /priority M");
        toDoParser.parse();
        assertEquals("ToDo mop floor", toDoParser.getTodo());
        assertEquals(Priority.MEDIUM, toDoParser.getPriority());
    }

    @Test
    public void toDoParser_multipleTodoCaptial_success() {
        // testing that the regrex actually selects only the first todo
        ToDoParser toDoParser = new ToDoParser("todo TODO mop floor /priority M");
        toDoParser.parse();
        assertEquals("TODO mop floor", toDoParser.getTodo());
        assertEquals(Priority.MEDIUM, toDoParser.getPriority());
    }

    @Test
    public void toDoParser_noPriorityArgumentWithSpace_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new ToDoParser("todo 4444 /priority ").parse();
        });
        assertTrue(thrown.toString().contains("ToDo priority property cannot be empty.\nPlease ensure you key in " +
                "the command in the format specified."));
    }

    @Test
    public void toDoParser_noPriorityArgumentMissingSpace_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new ToDoParser("todo 4444 /priority").parse();
        });
        assertTrue(thrown.toString().contains("ToDo priority property cannot be empty.\nPlease ensure you key in " +
                "the command in the format specified."));
    }

    @Test
    public void toDoParser_invalidPriorityArgument_failure() {
        TigerException thrown = assertThrows(TigerInvalidArgumentException.class, () -> {
            new ToDoParser("todo 4444 /priority S").parse();
        });
        assertTrue(thrown.toString().contains("S is not a valid argument for the ToDo priority command."));
    }

    @Test
    public void toDoParser_emptyDescriptionWithPriority_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new ToDoParser("todo /priority H").parse();
        });
        assertTrue(thrown.toString().contains("ToDo description property cannot be empty.\nPlease ensure you key in " +
                "the command in the format specified."));
    }

    @Test
    public void toDoParser_missingDescriptionAndPriority_failure() {
        TigerException thrown = assertThrows(TigerEmptyStringException.class, () -> {
            new ToDoParser("todo /priority ").parse();
        });
        assertTrue(thrown.toString().contains("ToDo priority property cannot be empty.\nPlease ensure you key in " +
                "the command in the format specified."));
    }
}
