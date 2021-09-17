import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import blitz.Blitz;
import blitz.BlitzException;
import blitz.Parser;
import blitz.Ui;
import blitz.tasks.Deadline;
import blitz.tasks.Todo;

class ParserTest {
    @Test
    void parseCommand_invalidCommandEntered_errorMessage() {
        Blitz b = new Blitz("data/blitz.txt");
        boolean isEqual = "Sorry, but I don't know what that means :-("
                .equals(Parser.parseCommand("hello", b.getTasks(), b.getUi()));
        assertEquals(true, isEqual);
    }
    @Test
    void parseCommand_taskWithEmptyDescription_errorMessage() {
        Blitz b = new Blitz("data/blitz.txt");
        String result = Parser.parseCommand("todo", b.getTasks(), b.getUi());
        boolean isEqual = Ui.getTaskDescriptionCannotBeIncompleteMessage()
                .equals(result);
        assertEquals(true, isEqual);
    }
    @Test
    void parseCommand_taskToAdd_invalidDateTimeFormat() {
        Blitz b = new Blitz("data/blitz.txt");
        String result = Parser.parseCommand("event party /on Sunday", b.getTasks(), b.getUi());
        boolean isEqual = Ui.getIncorrectDateTimeFormatMessage().equals(result);
        assertEquals(true, isEqual);
    }
    @Test
    void parseCommand_taskToAdd_success() {
        Blitz b = new Blitz("data/blitz.txt");

        String result = Parser.parseCommand("deadline return book /by 12/08/2021 2359", b.getTasks(), b.getUi());
        String message = "Got it. I've added this task:" + "\n\t" + new Deadline("return book",
                Parser.parseDateTime("12/08/2021 2359")) + "\n\nNow you have "
                + b.getTasks().size() + " blitz.tasks in the list.";
        boolean isEqual = message.equals(result);
        assertEquals(true, isEqual);
    }
    @Test
    void parseCommand_duplicateTaskToAdd_exceptionThrown() throws BlitzException {
        Blitz b = new Blitz("data/blitz.txt");
        b.getTasks().addTask(new Todo("go running"));
        String result = Parser.parseCommand("todo go running", b.getTasks(), b.getUi());
        boolean isEquals = result.equals("OOPS!!! This task already exists in the list! "
                + "Do you want to consider adding some other task?");
        assertEquals(true, isEquals);
    }
}
