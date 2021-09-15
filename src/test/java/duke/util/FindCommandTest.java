package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.exception.DukeException;

import duke.task.Event;
import duke.task.Deadline;
import duke.task.Todo;
import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class FindCommandTest {

    @Test
    public void execute_success() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("run"));
        list.add(new Event("cs quiz", LocalDate.of(2021, 10, 1)));
        list.add(new Deadline("finish quiz 4", LocalDate.of(2021, 10, 1)));
        list.add(new Todo("sleep"));
        list.add(new Todo("prepare for quiz"));

        TaskList taskList = new TaskList(list);
        Ui ui = new Ui();
        Storage storage = new Storage("duke.txt");


        String input = "find quiz";
        FindCommand findCommand = new FindCommand(input.split(" "));

        assertEquals("Here are the matching tasks in your list:\n"
                + "\t1.[E][ ] cs quiz (at: 2021-10-01)\n"
                + "\t2.[D][ ] finish quiz 4 (by: 2021-10-01)\n"
                + "\t3.[T][ ] prepare for quiz\n", findCommand.execute(taskList, ui, storage));

        input = "find sleep";
        findCommand = new FindCommand(input.split(" "));

        assertEquals("Here are the matching tasks in your list:\n"
                + "\t1.[T][ ] sleep\n", findCommand.execute(taskList, ui, storage));

        input = "find random";
        findCommand = new FindCommand(input.split(" "));

        assertEquals("Here are the matching tasks in your list:\n"
                + "no tasks in list yet...", findCommand.execute(taskList, ui, storage));

    }

    @Test
    public void execute_noKeyword_exceptionThrown() {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("run"));
        list.add(new Event("cs quiz", LocalDate.of(2021, 10, 1)));
        list.add(new Deadline("finish quiz 4", LocalDate.of(2021, 10, 1)));
        list.add(new Todo("sleep"));
        list.add(new Todo("prepare for quiz"));

        TaskList taskList = new TaskList(list);
        Ui ui = new Ui();
        Storage storage = new Storage("duke.txt");
        Throwable exception;

        String input = "find";
        FindCommand findCommand = new FindCommand(input.split(" "));
        exception = assertThrows(DukeException.class,
                () -> findCommand.execute(taskList, ui, storage));
        assertEquals("Please enter a keyword to find matching tasks...",
                exception.getMessage());


    }
}