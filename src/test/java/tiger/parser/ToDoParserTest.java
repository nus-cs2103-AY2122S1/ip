package tiger.parser;

import org.junit.jupiter.api.Test;
import tiger.exceptions.TigerException;
import tiger.exceptions.inputs.TigerEmptyStringException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoParserTest {
    @Test
    public void toDoParser_toDoWithString_success() {
        ToDoParser toDoParser = new ToDoParser("todo fish");
        toDoParser.parse();
        assertEquals("fish", toDoParser.getTodo());
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
}
