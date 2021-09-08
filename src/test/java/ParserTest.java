import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tasks.Deadline;


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
        boolean isEqual = b.getUi().getTaskDescriptionCannotBeEmptyMessage()
                .equals(result);
        assertEquals(true, isEqual);
    }
    @Test
    void parseCommand_taskToAdd_invalidDateTimeFormat() {
        Blitz b = new Blitz("data/blitz.txt");
        String result = Parser.parseCommand("event party /on Sunday", b.getTasks(), b.getUi());
        boolean isEqual = b.getUi().getIncorrectDateTimeFormatMessage().equals(result);
        assertEquals(true, isEqual);
    }

    @Test
    void parseCommand_taskToAdd_success() {
        Blitz b = new Blitz("data/blitz.txt");

        String result = Parser.parseCommand("deadline return book /by 12/08/2021 2359", b.getTasks(), b.getUi());
        String message = "Got it. I've added this task:" + "\n\t" + new Deadline("return book",
                Parser.parseDateTime("12/08/2021 2359")) + "\n\nNow you have "
                + b.getTasks().size() + " tasks in the list.";
        boolean isEqual = message.equals(result);
        assertEquals(true, isEqual);
    }
}

