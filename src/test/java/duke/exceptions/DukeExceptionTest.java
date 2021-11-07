package duke.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * This class test the construction of DukeExceptions.
 */
public class DukeExceptionTest {

    @Test
    public void commandParamException_deadline_sameMessage() {
        CommandParamException e = new CommandParamException("deadline");
        assertEquals("I don't think your deadline is keyed in correctly. Think again!", e.getMessage());
    }

    @Test
    public void commandParamException_event_sameMessage() {
        CommandParamException e = new CommandParamException("event");
        assertEquals("I don't think your event is keyed in correctly. Think again!", e.getMessage());
    }

    @Test
    public void dukeFileException_sameMessage() {
        DukeFileException e = new DukeFileException();
        assertEquals("I guess I got an error loading data. Hmmm...", e.getMessage());
    }

    @Test
    public void emptyDescriptionException_todo_sameMessage() {
        EmptyDescriptionException e = new EmptyDescriptionException("todo");
        assertEquals("OOPS!!! The description of todo cannot be empty.", e.getMessage());
    }

    @Test
    public void emptyDescriptionException_deadline_sameMessage() {
        EmptyDescriptionException e = new EmptyDescriptionException("deadline");
        assertEquals("OOPS!!! The description of deadline cannot be empty.", e.getMessage());
    }

    @Test
    public void emptyDescriptionException_event_sameMessage() {
        EmptyDescriptionException e = new EmptyDescriptionException("event");
        assertEquals("OOPS!!! The description of event cannot be empty.", e.getMessage());
    }

    @Test
    public void emptyDescriptionException_delete_sameMessage() {
        EmptyDescriptionException e = new EmptyDescriptionException("delete");
        assertEquals("OOPS!!! The description of delete cannot be empty.", e.getMessage());
    }

    @Test
    public void emptyDescriptionException_done_sameMessage() {
        EmptyDescriptionException e = new EmptyDescriptionException("done");
        assertEquals("OOPS!!! The description of done cannot be empty.", e.getMessage());
    }

    @Test
    public void emptyListException_sameMessage() {
        EmptyListException e = new EmptyListException();
        assertEquals("The list is empty!", e.getMessage());
    }

    @Test
    public void taskIsCompleteException_sameMessage() {
        CompletedTaskException e = new CompletedTaskException(123);
        assertEquals("OOPS! I think you have already completed task 123 !", e.getMessage());
    }

    @Test
    public void taskNotFoundException_sameMessage() {
        TaskNotFoundException e = new TaskNotFoundException(123);
        String expectedMessage = "OOPS!!! There is no such task!"
                + "\nI can't seem to find the task at number 123 !";
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    public void unknownCommandException_sameMessage() {
        UnknownCommandException e = new UnknownCommandException();
        assertEquals("OOPS!!! You better think again", e.getMessage());
    }
}
