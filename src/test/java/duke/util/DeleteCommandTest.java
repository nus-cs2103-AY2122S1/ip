package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.exception.DukeException;
import duke.exception.InvalidTaskDeletionException;

import duke.task.Event;
import duke.task.Deadline;
import duke.task.Todo;
import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;


class DeleteCommandTest {

    @Test
    public void execute_validDeleteNumber_success() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("run"));
        list.add(new Event("funfair", LocalDateTime.parse("2021-10-01T23:59")));
        list.add(new Deadline("quiz", LocalDateTime.parse("2021-10-01T23:59")));

        TaskList taskList = new TaskList(list);
        Ui ui = new Ui();
        Storage storage = new Storage("duke.txt");

        String input = "delete 1";
        DeleteCommand deleteCommand = new DeleteCommand(input.split(" "));
        assertEquals("Noted. I've removed this task:\n" + "[T][ ] run\n"
                + "Now you have 2 tasks in your list\n", deleteCommand.execute(taskList, ui, storage));

        input = "delete 2";
        deleteCommand = new DeleteCommand(input.split(" "));
        assertEquals("Noted. I've removed this task:\n" + "[D][ ] quiz (by: 01 Oct 2021 11:59 PM)\n"
                + "Now you have 1 tasks in your list\n", deleteCommand.execute(taskList, ui, storage));
    }

    @Test
    public void execute_invalidOrNoDeleteNumber_exceptionThrown() {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("run"));
        list.add(new Event("funfair", LocalDateTime.parse("2021-10-01T23:59")));
        list.add(new Deadline("quiz", LocalDateTime.parse("2021-10-01T23:59")));

        TaskList taskList = new TaskList(list);
        Ui ui = new Ui();
        Storage storage = new Storage("duke.txt");
        Throwable exception;

        String input = "delete 0";
        DeleteCommand deleteCommand = new DeleteCommand(input.split(" "));
        exception = assertThrows(InvalidTaskDeletionException.class,
                () -> deleteCommand.execute(taskList, ui, storage));
        assertEquals("OOPS! You are trying to delete a non-existent task!",
                exception.getMessage());

        input = "delete";
        DeleteCommand deleteCommand1 = new DeleteCommand(input.split(" "));
        exception = assertThrows(DukeException.class,
                () -> deleteCommand1.execute(taskList, ui, storage));
        assertEquals("No task number entered!",
                exception.getMessage());

        input = "delete 10";
        DeleteCommand deleteCommand2 = new DeleteCommand(input.split(" "));
        exception = assertThrows(InvalidTaskDeletionException.class,
                () -> deleteCommand2.execute(taskList, ui, storage));
        assertEquals("OOPS! You are trying to delete a non-existent task!",
                exception.getMessage());

    }
}