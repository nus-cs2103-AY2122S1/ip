package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileParserTest {
    @Test
    public void ReadLineFromFile_StringFromFile_CorrectEventReturned() {
        FileParser f = new FileParser();
        assertEquals("[E]|[]|buy food|2011-01-01", f.parse("[E]|[]|buy food|2011-01-01").toString());
    }
    @Test
    public void ReadLineFromFile_StringFromFile_CorrectDeadlineReturned() {
        FileParser f = new FileParser();
        assertEquals("[D]|[]|buy food|2011-01-01", f.parse("[D]|[]|buy food|2011-01-01").toString());
    }
    @Test
    public void ReadLineFromFile_StringFromFile_CorrectTodoReturned() {
        FileParser f = new FileParser();
        assertEquals("[T]|[]|buy food", f.parse("[T]|[]|buy food").toString());
    }

    @Test
    public void ReadLineFromFile_IncorrectStringFromFile_ExceptionThrown() {
        FileParser f = new FileParser();
        try {
            assertEquals("[T]|[]|buy food", f.parse("ss|dd"));
        } catch (DukeException e) {
            assertEquals("Sorry, a line was saved incorrectly. An empty list will be used instead.",
                    e.getMessage());
        }
    }
}
