package duke.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeExceptionTest {
    @Test
    public void DukeExceptionTest_dukeException_sameOutput() {
        DukeException dukeException = new DukeException("☹ OOPS!!! I'm sorry, something has gone wrong");
        assertEquals("☹ OOPS!!! I'm sorry, something has gone wrong", dukeException.getMessage());
    }

    @Test
    public void CorruptedFileExceptionTest_corruptedFile_sameOutput() {
        DukeException dukeException = new CorruptedFileException();
        assertEquals("☹ Sorry! Your duke.txt file is corrupted and cannot be loaded!",
                dukeException.getMessage());
    }

    @Test
    public void EmptyListExceptionTest_emptyList_sameOutput() {
        DukeException dukeException = new EmptyListException();
        assertEquals("☹ Sorry! Your list is empty!",
                dukeException.getMessage());
    }

    @Test
    public void TaskNotFoundExceptionTest_taskNotFound_sameOutput() {
        DukeException dukeException = new TaskNotFoundException();
        assertEquals("☹ Sorry! There is no such task with this task index!",
                dukeException.getMessage());

    }

    @Test
    public void UnknownCommandExceptionTest_unknownCommand_sameOutput() {
        DukeException dukeException = new UnknownCommandException();
        assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means.",
                dukeException.getMessage());
    }

    @Test
    public void IllegalFormatExceptionTest_Todo_sameOutput() {
        String format = "todo <description>";
        DukeException dukeException = new IllegalFormatException(format);
        assertEquals("☹ Sorry! Please follow the following input format:\n" + format,
                dukeException.getMessage());
    }
}