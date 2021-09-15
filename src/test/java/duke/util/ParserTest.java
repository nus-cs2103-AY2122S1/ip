package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import duke.exception.DukeException;
import duke.exception.InvalidTaskDeletionException;
import duke.exception.InvalidTaskDoneException;
import duke.exception.NoDescriptionAndTimeException;
import duke.exception.NoDescriptionException;
import duke.exception.NoTimeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class ParserTest {
//
//    @Test
//    public void parse_addTask_success() throws DukeException {
//        Ui ui = new Ui();
//        TaskList taskList = new TaskList();
//        Storage storage = new Storage("duke.txt");
//
//        Parser.parse("todo clean", taskList, ui, storage);
//        assertEquals("Here are the tasks in your list:\n" + "\t1.[T][ ] clean\n",
//                taskList.printTasksInList());
//
//        Parser.parse("deadline test /by 10 Oct 2021", taskList, ui, storage);
//        assertEquals("Here are the tasks in your list:\n" + "\t1.[T][ ] clean\n"
//                + "\t2.[D][ ] test (by: 2021-10-10)\n", taskList.printTasksInList());
//
//        Parser.parse("deadline     test 2    /by 09 Jun 2021", taskList, ui, storage);
//        assertEquals("Here are the tasks in your list:\n" + "\t1.[T][ ] clean\n"
//                + "\t2.[D][ ] test (by: 2021-10-10)\n" + "\t3.[D][ ] test 2 (by: 2021-06-09)\n",
//                taskList.printTasksInList());
//
//        Parser.parse("event test 3 /at 10 Jun 2021", taskList, ui, storage);
//        assertEquals("Here are the tasks in your list:\n" + "\t1.[T][ ] clean\n"
//                + "\t2.[D][ ] test (by: 2021-10-10)\n" + "\t3.[D][ ] test 2 (by: 2021-06-09)\n"
//                + "\t4.[E][ ] test 3 (at: 2021-06-10)\n", taskList.printTasksInList());
//    }
//
//
//    @Test
//    public void parse_addTask_exceptionThrown() {
//        Ui ui = new Ui();
//        TaskList taskList = new TaskList();
//        Storage storage = new Storage("duke.txt");
//        Throwable exception;
//
//        exception = assertThrows(NoDescriptionException.class,
//                () -> Parser.parse("todo", taskList, ui, storage));
//        assertEquals("OOPS! The description of todo cannot be empty.", exception.getMessage());
//
//        exception = assertThrows(NoDescriptionAndTimeException.class,
//                () -> Parser.parse("event", taskList, ui, storage));
//        assertEquals("OOPS! The description & time of event cannot\nbe empty.",
//                exception.getMessage());
//
//        exception = assertThrows(NoDescriptionException.class,
//                () -> Parser.parse("event /at 20 Nov 2021", taskList, ui, storage));
//        assertEquals("OOPS! The description of event cannot be empty.", exception.getMessage());
//
//        exception = assertThrows(NoTimeException.class,
//                () -> Parser.parse("event funfair", taskList, ui, storage));
//        assertEquals("OOPS! The time of event cannot be empty.", exception.getMessage());
//
//        exception = assertThrows(NoDescriptionAndTimeException.class,
//                () -> Parser.parse("deadline", taskList, ui, storage));
//        assertEquals("OOPS! The description & time of deadline cannot\nbe empty.",
//                exception.getMessage());
//
//        exception = assertThrows(NoDescriptionException.class,
//                () -> Parser.parse("deadline /by 20 Nov 2021", taskList, ui, storage));
//        assertEquals("OOPS! The description of deadline cannot be empty.", exception.getMessage());
//
//        exception = assertThrows(NoTimeException.class,
//                () -> Parser.parse("deadline funfair", taskList, ui, storage));
//        assertEquals("OOPS! The time of deadline cannot be empty.", exception.getMessage());
//
//    }
//
//    @Test
//    public void parseDone_success() throws DukeException {
//        ArrayList<Task> list = new ArrayList<>();
//        list.add(new Todo("run"));
//        list.add(new Deadline("paint", LocalDate.parse("2020-10-10")));
//        TaskList taskList = new TaskList(list);
//
//        String[] input = "done 1".split(" ");
//        Parser.parseDone(taskList, input);
//        assertEquals("Here are the tasks in your list:\n" + "\t1.[T][X] run\n"
//                + "\t2.[D][ ] paint (by: 2020-10-10)\n", taskList.printTasksInList());
//
//        String[] nextInput = "done 2".split(" ");
//        Parser.parseDone(taskList, nextInput);
//        assertEquals("Here are the tasks in your list:\n"
//                + "\t1.[T][X] run\n" + "\t2.[D][X] paint (by: 2020-10-10)\n", taskList.printTasksInList());
//    }
//
//    @Test
//    public void parseDone_noOrInvalidNumber_exceptionThrown() {
//        ArrayList<Task> list = new ArrayList<>();
//        list.add(new Todo("run"));
//        list.add(new Deadline("paint", LocalDate.parse("2020-10-10")));
//        TaskList taskList = new TaskList(list);
//
//        String[] input1 = "done".split(" ");
//        Throwable exception = assertThrows(DukeException.class,
//                () -> Parser.parseDone(taskList, input1));
//        assertEquals("No task number entered!", exception.getMessage());
//
//        String[] input2 = "done 3".split(" ");
//        exception = assertThrows(InvalidTaskDoneException.class,
//                () -> Parser.parseDone(taskList, input2));
//        assertEquals("OOPS! You are setting a non-existent task as done!",
//                exception.getMessage());
//
//        String[] input3 = "done 0".split(" ");
//        exception = assertThrows(InvalidTaskDoneException.class,
//                () -> Parser.parseDone(taskList, input3));
//        assertEquals("OOPS! You are setting a non-existent task as done!",
//                exception.getMessage());
//    }
//
//    @Test
//    public void parseDelete_success() throws DukeException {
//        ArrayList<Task> list = new ArrayList<>();
//        list.add(new Todo("run"));
//        list.add(new Deadline("paint", LocalDate.parse("2020-10-10")));
//        list.add(new Event("funfair", LocalDate.parse("2021-09-08")));
//        TaskList taskList = new TaskList(list);
//
//        String[] input = "delete 1".split(" ");
//        Parser.parseDelete(taskList, input);
//        assertEquals("Here are the tasks in your list:\n" + "\t1.[D][ ] paint (by: 2020-10-10)\n"
//                + "\t2.[E][ ] funfair (at: 2021-09-08)\n", taskList.printTasksInList());
//
//        String[] nextInput = "delete 2".split(" ");
//        Parser.parseDelete(taskList, nextInput);
//        assertEquals("Here are the tasks in your list:\n"
//                + "\t1.[D][ ] paint (by: 2020-10-10)\n", taskList.printTasksInList());
//    }
//
//    @Test
//    public void parseDelete_noOrInvalidNumber_exceptionThrown(){
//        ArrayList<Task> list = new ArrayList<>();
//        list.add(new Todo("run"));
//        list.add(new Deadline("paint", LocalDate.parse("2020-10-10")));
//        TaskList taskList = new TaskList(list);
//
//        String[] input1 = "delete".split(" ");
//        Throwable exception = assertThrows(DukeException.class,
//                () -> Parser.parseDelete(taskList, input1));
//        assertEquals("No task number entered!", exception.getMessage());
//
//        String[] input2 = "delete 3".split(" ");
//        exception = assertThrows(InvalidTaskDeletionException.class,
//                () -> Parser.parseDelete(taskList, input2));
//        assertEquals("OOPS! You are trying to delete a non-existent task!",
//                exception.getMessage());
//
//        String[] input3 = "delete 0".split(" ");
//        exception = assertThrows(InvalidTaskDeletionException.class,
//                () -> Parser.parseDelete(taskList, input3));
//        assertEquals("OOPS! You are trying to delete a non-existent task!",
//                exception.getMessage());
//    }

}
