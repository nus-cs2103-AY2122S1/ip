package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FileParserTest {
    @Test
    public void readLineFromFile_stringFromFile_correctEventReturned() {
        FileParser f = new FileParser();
        assertEquals("[E]|[]|buy food|2011-01-01", f.parse("[E]|[]|buy food|2011-01-01").toString());
    }
    @Test
    public void readLineFromFile_stringFromFile_correctDeadlineReturned() {
        FileParser f = new FileParser();
        assertEquals("[D]|[]|buy food|2011-01-01", f.parse("[D]|[]|buy food|2011-01-01").toString());
    }
    @Test
    public void readLineFromFile_stringFromFile_correctTodoReturned() {
        FileParser f = new FileParser();
        assertEquals("[T]|[]|buy food", f.parse("[T]|[]|buy food").toString());
    }

    @Test
    public void readLineFromFile_incorrectStringFromFile_exceptionThrown() {
        FileParser f = new FileParser();
        try {
            assertEquals("[T]|[]|buy food", f.parse("ss|dd"));
        } catch (DukeException e) {
            assertEquals("Sorry, a line was saved incorrectly. An empty list will be used instead.",
                    e.getMessage());
        }
    }
}
