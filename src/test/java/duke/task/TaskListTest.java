package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void addTodoToList_success() {
        TaskList taskList = new TaskList();
        String[] inputs = {"do quiz", "eat", "run"};
        for (int i = 0; i < inputs.length; i++) {
            String output = taskList.addTodoToList(inputs[i]);
            assertEquals(String.format("added: [T][ ] %s\n"
                    + "Now you have %s tasks in your list", inputs[i], i+1), output);
        }
    }

    @Test
    public void deleteTaskFromList_success() {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("paint"));
        list.add(new Event("funfair", LocalDate.parse("2020-10-10")));
        list.add(new Deadline("submit quiz", LocalDate.parse("2021-08-10")));
        TaskList taskList = new TaskList(list);

        assertEquals("Noted. I've removed this task:\n[E][ ] funfair (at: 2020-10-10)"
                + "\nNow you have 2 tasks in your list\n", taskList.deleteFromList(1));
        assertEquals("Noted. I've removed this task:\n[T][ ] paint"
                + "\nNow you have 1 tasks in your list\n", taskList.deleteFromList(0));
    }

    @Test
    public void setTaskAsDone_success() {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Todo("paint"));
        list.add(new Event("funfair", LocalDate.parse("2020-10-10")));
        list.add(new Deadline("submit quiz", LocalDate.parse("2021-08-10")));
        TaskList taskList = new TaskList(list);

        assertEquals("Nice! I've marked this task as done:\n[T][X] paint",
                taskList.setTaskAsDone(0));
        assertEquals("Nice! I've marked this task as done:\n[E][X] funfair (at: 2020-10-10)",
                taskList.setTaskAsDone(1));
        assertEquals("Nice! I've marked this task as done:\n[D][X] submit quiz (by: 2021-08-10)",
                taskList.setTaskAsDone(2));
    }

}
