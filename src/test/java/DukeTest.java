import main.java.Duke;
import main.java.parser.Parser;
import main.java.storage.Storage;
import main.java.task.Deadline;
import main.java.task.Event;
import main.java.task.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void parserTestSanitization(){
        String[] sanitisedInput = Parser.sanitizeInput("Hello World !#!@$% $TY%$^$%^$BR");

        String[] correctSanitised = new String[]{"Hello", "World", "!#!@$%", "$TY%$^$%^$BR"};
        assertEquals(sanitisedInput[0], correctSanitised[0]);
        assertEquals(sanitisedInput[1], correctSanitised[1]);
        assertEquals(sanitisedInput[2], correctSanitised[2]);
        assertEquals(sanitisedInput[3], correctSanitised[3]);
    }

    @Test
    public void parserTestEventEncode() {
        Event eventTask = new Event("Party ALL DAy!", "Sentosa");
        String encodedDescription = Base64.getEncoder().encodeToString(eventTask.getDescription().getBytes());
        String encodedAt = Base64.getEncoder().encodeToString(eventTask.getAt().getBytes());
        String eventText = "event " + eventTask.getIsDone() + " " + encodedAt + " " + encodedDescription;

        String parserEncoded = Parser.encodeEvent(eventTask);

        assertEquals(parserEncoded, eventText);
    }

    @Test
    public void parserTestDeadlineEncode() {
        Deadline deadlineTask = new Deadline("Get back to work", LocalDate.parse("2021-08-21"));
        String encodedString = Base64.getEncoder().encodeToString(deadlineTask.getDescription().getBytes());
        String dbEntry = "deadline " + deadlineTask.getIsDone() + " " + deadlineTask.getBy() + " " + encodedString;

        String parserEncoded = Parser.encodeDeadline(deadlineTask);
        assertEquals(parserEncoded, dbEntry);
    }

    @Test
    public void parserTestTodoEncode() {
        Todo todoTask = new Todo("Hangover from Sentosa...");
        String encodedString = Base64.getEncoder().encodeToString(todoTask.getDescription().getBytes());
        String dbEntry = "todo " + todoTask.getIsDone() + " " + encodedString;

        String parserEncoded = Parser.encodeTodo(todoTask);

        assertEquals(parserEncoded, dbEntry);
    }
}
