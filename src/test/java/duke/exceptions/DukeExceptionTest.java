package duke.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeExceptionTest {
    @Test
    public void dukeExceptionTest_dukeException_sameOutput() {
        DukeException dukeException = new DukeException("OOPS!!! I'm sorry, something has gone wrong");
        assertEquals("OOPS!!! I'm sorry, something has gone wrong"
                + "\nTo access the help page, type \"help\"", dukeException.getMessage());
    }

    @Test
    public void corruptedFileExceptionTest_corruptedFile_sameOutput() {
        DukeException dukeException = new CorruptedFileException();
        assertEquals("Sorry! Your duke.txt file is corrupted and cannot be loaded!"
                        + "\nTo access the help page, type \"help\"",
                dukeException.getMessage());
    }

    @Test
    public void emptyListExceptionTest_emptyList_sameOutput() {
        DukeException dukeException = new EmptyListException();
        assertEquals("Sorry! Your list is empty!"
                        + "\nTo access the help page, type \"help\"",
                dukeException.getMessage());
    }

    @Test
    public void taskNotFoundExceptionTest_taskNotFound_sameOutput() {
        DukeException dukeException = new TaskNotFoundException();
        assertEquals("Sorry! There is no such task with this task index!"
                        + "\nTo access the help page, type \"help\"",
                dukeException.getMessage());

    }

    @Test
    public void unknownCommandExceptionTest_unknownCommand_sameOutput() {
        DukeException dukeException = new UnknownCommandException();
        assertEquals("OOPS!!! I'm sorry, but I don't know what that means."
                        + "\nTo access the help page, type \"help\"",
                dukeException.getMessage());
    }

    @Test
    public void illegalFormatExceptionTest_toDo_sameOutput() {
        String format = "todo <description>";
        DukeException dukeException = new IllegalFormatException(format);
        assertEquals("Sorry! Please follow the following input format:\n" + format
                        + "\nTo access the help page, type \"help\"",
                dukeException.getMessage());
    }
}
