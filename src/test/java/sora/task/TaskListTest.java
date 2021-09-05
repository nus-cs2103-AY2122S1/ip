package sora.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import sora.exception.EmptyListException;
import sora.exception.EmptyResultException;
import sora.exception.IllegalFormatException;
import sora.exception.SoraException;
import sora.exception.TaskNotFoundException;

class TaskListTest {
    @Test
    public void constructor_emptyInput_success() {
        assertEquals(new ArrayList<>(), new TaskList().getTasks());
    }

    @Test
    public void constructor_listInput_success() {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("todo 1"));
        list.add(new Deadline("deadline 2", null));
        list.add(new Event("event 3", null, null, null));

        assertEquals(list, new TaskList(list).getTasks());
    }

    @Test
    void taskDelete_wrongFormat_exceptionThrown() {
        String[] invalidCommands = {
                "",
                "del",
                "delete",
                "delete ",
                "delete1",
                "deletea1",
                "delete -1",
                "delete abc",
        };

        TaskList taskList = new TaskList();

        for (String command : invalidCommands) {
            IllegalFormatException exception = assertThrows(IllegalFormatException.class,
                    () -> taskList.taskDelete(command));

            assertEquals("Please follow this format:\n"
                            + "  delete [task number]",
                    exception.getMessage());
        }
    }

    @Test
    void taskDelete_invalidTaskNumber_exceptionThrown() {
        String[] invalidCommands = {
                "delete 0",
                "delete 3",
                "delete 1000",
        };

        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("todo 1"));
        list.add(new Todo("todo 2"));

        TaskList taskList = new TaskList(list);

        for (String command : invalidCommands) {
            TaskNotFoundException exception = assertThrows(TaskNotFoundException.class,
                    () -> taskList.taskDelete(command));

            assertEquals("There is no such tasks with this task number!", exception.getMessage());
        }
    }

    @Test
    void taskDelete_success() throws SoraException {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("task 1"));
        list.add(new Todo("todo 2"));
        list.add(new Todo("test 3"));

        TaskList taskList = new TaskList(list);

        assertEquals("Sure, I've deleted this task:\n"
                        + "  [T][ ] task 1\n"
                        + "You still have 2 tasks in the list.",
                taskList.taskDelete("delete 1"));

        assertEquals("Sure, I've deleted this task:\n"
                        + "  [T][ ] todo 2\n"
                        + "You still have 1 task in the list.",
                taskList.taskDelete("delete 1"));

        assertEquals("Sure, I've deleted this task:\n"
                        + "  [T][ ] test 3\n"
                        + "You have no more tasks in the list!",
                taskList.taskDelete("delete 1"));
    }

    @Test
    void taskDone_wrongFormat_exceptionThrown() {
        String[] invalidCommands = {
                "",
                "don",
                "done",
                "done ",
                "done1",
                "donea1",
                "done -1",
                "done abc",
        };

        TaskList taskList = new TaskList();

        for (String command : invalidCommands) {
            IllegalFormatException exception = assertThrows(IllegalFormatException.class,
                    () -> taskList.taskDone(command));

            assertEquals("Please follow this format:\n"
                            + "  done [task number]",
                    exception.getMessage());
        }
    }

    @Test
    void taskDone_invalidTaskNumber_exceptionThrown() {
        String[] invalidCommands = {
                "done 0",
                "done 3",
                "done 1000",
        };

        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("task 1"));
        list.add(new Todo("todo 2"));

        TaskList taskList = new TaskList(list);

        for (String command : invalidCommands) {
            TaskNotFoundException exception = assertThrows(TaskNotFoundException.class,
                    () -> taskList.taskDone(command));

            assertEquals("There is no such tasks with this task number!", exception.getMessage());
        }
    }

    @Test
    void taskDone_success() throws SoraException {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("task 1"));
        list.add(new Todo("todo 2"));

        TaskList taskList = new TaskList(list);

        assertEquals("Congrats! You have accomplished the following task:\n"
                        + "  [T][X] task 1",
                taskList.taskDone("done 1"));

        assertEquals("Congrats! You have accomplished the following task:\n"
                        + "  [T][X] todo 2",
                taskList.taskDone("done 2"));
    }

    @Test
    void addEvent_wrongFormat_exceptionThrown() {
        String[] invalidCommands = {
                "",
                "eve",
                "event",
                "event ",
                "event1",
                "eventa1",
                "event fail",
                "event fail 06/08/21 /from 1400 /to 1800",
                "event fail /att 06/08/21 /from 1400 /to 1800",
                "event fail /at 06-08-21 /from 1400 /to 1800",
                "event fail /at 06/08/21 1400 /to 1800",
                "event fail /at 06/08/21 /froma 1400 /to 1800",
                "event fail /at 06/08/21 /from 200 PM /to 1800",
                "event fail /at 06/08/21 /from 1400 1800",
                "event fail /at 06/08/21 /from 1400 /t 1800",
                "event fail /at 06/08/21 /from 1400 /to",
                "event fail /at 06/08/21 /from 1400 /to 600",
                "event fail /at 06/08/21 /from 1400 /to 600 PM",
        };

        TaskList taskList = new TaskList();

        for (String command : invalidCommands) {
            IllegalFormatException exception = assertThrows(IllegalFormatException.class,
                    () -> taskList.addEvent(command));

            assertEquals("Please follow this format:\n"
                            + "  event [description] /at [dd/MM/yy] /from [HHmm] /to [HHmm]",
                    exception.getMessage());
        }
    }

    @Test
    void addEvent_success() throws IllegalFormatException {
        TaskList taskList = new TaskList();

        assertEquals("Alright, new task added to the list:\n"
                        + "  [E][ ] pass 1 (at: Aug 6 2021, 2:00 PM - 6:00 PM)\n"
                        + "Now you have 1 task in the list.",
                taskList.addEvent("event pass 1 /at 06/08/21 /from 1400 /to 1800"));

        assertEquals("Alright, new task added to the list:\n"
                        + "  [E][ ] pass 2 (at: Oct 15 2019, 12:42 AM - 10:59 PM)\n"
                        + "Now you have 2 tasks in the list.",
                taskList.addEvent("event pass 2 /at 15/10/19 /from 042 /to 2259"));

        assertEquals("Alright, new task added to the list:\n"
                        + "  [E][ ] pass 3 (at: Jan 4 2020, 4:01 AM - 8:20 AM)\n"
                        + "Now you have 3 tasks in the list.",
                taskList.addEvent("event pass 3 /at 4/1/20 /from 401 /to 820"));
    }

    @Test
    void addDeadline_wrongFormat_exceptionThrown() {
        String[] invalidCommands = {
                "",
                "dead",
                "deadline",
                "deadline ",
                "deadline1",
                "deadlinea1",
                "deadline fail",
                "deadline fail /byy 06/06/21 1712",
                "deadline fail /by now",
                "deadline fail /by 1712",
                "deadline fail /by 6-6-21 1712",
                "deadline fail /by 06/06/21",
                "deadline fail /by 06/06/21 512 PM",
        };

        TaskList taskList = new TaskList();

        for (String command : invalidCommands) {
            IllegalFormatException exception = assertThrows(IllegalFormatException.class,
                    () -> taskList.addDeadline(command));

            assertEquals("Please follow this format:\n"
                            + "  deadline [description] /by [dd/MM/yy] [HHmm]",
                    exception.getMessage());
        }
    }

    @Test
    void addDeadline_success() throws IllegalFormatException {
        TaskList taskList = new TaskList();

        assertEquals("Alright, new task added to the list:\n"
                        + "  [D][ ] pass 1 (by: Aug 6 2021, 12:00 AM)\n"
                        + "Now you have 1 task in the list.",
                taskList.addDeadline("deadline pass 1 /by 06/08/21 000"));

        assertEquals("Alright, new task added to the list:\n"
                        + "  [D][ ] pass 2 (by: Nov 15 2019, 11:59 PM)\n"
                        + "Now you have 2 tasks in the list.",
                taskList.addDeadline("deadline pass 2 /by 15/11/19 2359"));

        assertEquals("Alright, new task added to the list:\n"
                        + "  [D][ ] pass 3 (by: Jan 4 2020, 2:01 PM)\n"
                        + "Now you have 3 tasks in the list.",
                taskList.addDeadline("deadline pass 3 /by 4/1/20 1401"));
    }

    @Test
    void addTodo_wrongFormat_exceptionThrown() {
        String[] invalidCommands = {
                "",
                "tod",
                "todo",
                "todo1",
                "todoa1",
        };

        TaskList taskList = new TaskList();

        for (String command : invalidCommands) {
            IllegalFormatException exception = assertThrows(IllegalFormatException.class,
                    () -> taskList.addTodo(command));

            assertEquals("Please follow this format:\n"
                            + "  todo [description]",
                    exception.getMessage());
        }
    }

    @Test
    void addTodo_success() throws IllegalFormatException {
        TaskList taskList = new TaskList();

        assertEquals("Alright, new task added to the list:\n"
                        + "  [T][ ] pass 1\n"
                        + "Now you have 1 task in the list.",
                taskList.addTodo("todo pass 1"));

        assertEquals("Alright, new task added to the list:\n"
                        + "  [T][ ] \n"
                        + "Now you have 2 tasks in the list.",
                taskList.addTodo("todo "));
    }

    @Test
    void printFullList_emptyList_exceptionThrown() {
        TaskList taskList = new TaskList();

        EmptyListException exception = assertThrows(EmptyListException.class, taskList::printFullList);
        assertEquals("Your list is empty! Maybe add some tasks into it?", exception.getMessage());
    }

    @Test
    void printFullList_success() throws EmptyListException {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("task 1"));
        list.add(new Todo("todo 2"));

        TaskList taskList = new TaskList(list);

        assertEquals("Here are the tasks in your list:\n"
                        + "1. [T][ ] task 1\n"
                        + "2. [T][ ] todo 2",
                taskList.printFullList());
    }

    @Test
    void findInList_wrongFormat_exceptionThrown() {
        String[] invalidCommands = {
                "",
                "fid",
                "find",
                "find1",
                "finda1",
        };

        TaskList taskList = new TaskList();

        for (String command : invalidCommands) {
            IllegalFormatException exception = assertThrows(IllegalFormatException.class,
                    () -> taskList.findInList(command));

            assertEquals("Please follow this format:\n"
                            + "  find [keyword]",
                    exception.getMessage());
        }
    }

    @Test
    void findInList_nothingFound_exceptionThrown() {
        String expected = "I found nothing... Maybe try another keyword?";
        TaskList taskList1 = new TaskList();

        EmptyResultException exception = assertThrows(EmptyResultException.class,
                () -> taskList1.findInList("find test"));

        assertEquals(expected, exception.getMessage());

        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("task 1"));
        list.add(new Todo("todo 2"));

        TaskList taskList2 = new TaskList(list);

        exception = assertThrows(EmptyResultException.class,
                () -> taskList2.findInList("find test"));

        assertEquals(expected, exception.getMessage());
    }

    @Test
    void findInList_success() throws SoraException {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("task 1"));
        list.add(new Todo("todo 2"));

        TaskList taskList = new TaskList(list);

        assertEquals("Here are the tasks in your list:\n"
                        + "1. [T][ ] task 1\n"
                        + "2. [T][ ] todo 2",
                taskList.findInList("find todo"));
    }

    @Test
    void sort_wrongFormat_exceptionThrown() {
        String[] invalidCommands = {
                "",
                "sot",
                "sort1",
                "sort r",
                "sort -rev",
        };

        TaskList taskList = new TaskList();

        for (String command : invalidCommands) {
            IllegalFormatException exception = assertThrows(IllegalFormatException.class,
                    () -> taskList.sort(command));

            assertEquals("Please follow this format:\n"
                            + "  sort [-r]",
                    exception.getMessage());
        }
    }

    @Test
    void sort_emptyList_exceptionThrown() {
        TaskList taskList = new TaskList();

        EmptyListException exception = assertThrows(EmptyListException.class, () -> taskList.sort("sort"));
        assertEquals("Your list is empty! Maybe add some tasks into it?", exception.getMessage());
    }

    @Test
    void sort_success() throws SoraException {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("todo 1"));
        list.add(new Todo("todo 2"));

        LocalDateTime dateTime = LocalDateTime.of(2012, 5, 21, 3, 14);
        list.add(new Deadline("deadline 3", dateTime));

        LocalDate date = LocalDate.of(2021, 1, 1);
        LocalTime startTime = LocalTime.of(4, 1);
        LocalTime endTime = LocalTime.of(4, 3);
        list.add(new Event("event 4", date, startTime, endTime));

        TaskList taskList = new TaskList(list);

        assertEquals("Here are the tasks in your list:\n"
                        + "1. [D][ ] deadline 3 (by: May 21 2012, 3:14 AM)\n"
                        + "2. [E][ ] event 4 (at: Jan 1 2021, 4:01 AM - 4:03 AM)\n"
                        + "3. [T][ ] todo 1\n"
                        + "4. [T][ ] todo 2",
                taskList.sort("sort"));

        assertEquals("Here are the tasks in your list:\n"
                        + "1. [T][ ] todo 2\n"
                        + "2. [T][ ] todo 1\n"
                        + "3. [E][ ] event 4 (at: Jan 1 2021, 4:01 AM - 4:03 AM)\n"
                        + "4. [D][ ] deadline 3 (by: May 21 2012, 3:14 AM)",
                taskList.sort("sort -r"));
    }
}
