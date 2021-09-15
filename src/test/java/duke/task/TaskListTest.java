package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void addTaskToList_success() {
        TaskList taskList = new TaskList();

        String output = taskList.addTaskToList(new Todo("run"));
        assertEquals("added: [T][ ] run\nNow you have 1 tasks in your list", output);

        output = taskList.addTaskToList(new Todo("do smth"));
        assertEquals("added: [T][ ] do smth\nNow you have 2 tasks in your list", output);

        output = taskList.addTaskToList(new Deadline("testing 1",
                LocalDateTime.parse("2020-10-10T13:30")));
        assertEquals("added: [D][ ] testing 1 (by: 10 Oct 2020 1:30 PM)"
                + "\nNow you have 3 tasks in your list", output);

        output = taskList.addTaskToList(new Event("testing 2",
                LocalDateTime.parse("2020-10-10T13:30")));
        assertEquals("added: [E][ ] testing 2 (at: 10 Oct 2020 1:30 PM)"
                + "\nNow you have 4 tasks in your list", output);

    }

    @Test
    public void deleteTaskFromList_success() {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("paint"));
        list.add(new Event("funfair", LocalDateTime.parse("2020-10-10T23:59")));
        list.add(new Deadline("submit quiz", LocalDateTime.parse("2021-08-10T23:59")));
        TaskList taskList = new TaskList(list);

        assertEquals("Noted. I've removed this task:\n[E][ ] funfair (at: 10 Oct 2020 11:59 PM)"
                + "\nNow you have 2 tasks in your list\n", taskList.deleteFromList(1));
        assertEquals("Noted. I've removed this task:\n[T][ ] paint"
                + "\nNow you have 1 tasks in your list\n", taskList.deleteFromList(0));
    }

    @Test
    public void setTaskAsDone_success() {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("paint"));
        list.add(new Event("funfair", LocalDateTime.parse("2020-10-10T23:59")));
        list.add(new Deadline("submit quiz", LocalDateTime.parse("2021-08-10T23:59")));
        TaskList taskList = new TaskList(list);

        assertEquals("Nice! I've marked this task as done:\n[T][X] paint",
                taskList.setTaskAsDone(0));
        assertEquals("Nice! I've marked this task as done:\n[E][X] funfair (at: 10 Oct 2020 11:59 PM)",
                taskList.setTaskAsDone(1));
        assertEquals("Nice! I've marked this task as done:\n[D][X] submit "
                        + "quiz (by: 10 Aug 2021 11:59 PM)", taskList.setTaskAsDone(2));
    }

}
