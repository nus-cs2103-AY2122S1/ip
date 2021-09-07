package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.stubs.DeadlineStub;
import duke.stubs.EventStub;
import duke.stubs.TodoStub;
import duke.task.Task;


class UiTest {

    @Test
    void testShowWelcome() {
        Ui ui = new Ui();

        assertEquals("Hello! I'm Duke\nWhat can I do for you?", ui.showWelcome());
    }

    @Test
    void testList() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new TodoStub());
        tasks.add(new DeadlineStub());
        tasks.add(new EventStub());
        TaskList taskList = new TaskList(tasks);

        Ui ui = new Ui();

        assertEquals("Here are the tasks in your list:\n"
                + "1.[T][ ] Create a todo task\n2.[D][ ] Create a deadline task (by: Dec 4 2021)\n"
                + "3.[E][ ] Create an event task (at: Dec 4 2021)", ui.list(taskList));
    }

    @Test
    void testDone() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new TodoStub());
        tasks.add(new DeadlineStub());
        tasks.add(new EventStub());
        TaskList taskList = new TaskList(tasks);

        Ui ui = new Ui();
        assertEquals("Nice! I've marked this task as done:\n  [T][ ] Create a todo task",
                ui.done(taskList, 1));
    }

    @Test
    void testDelete() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new TodoStub());
        tasks.add(new DeadlineStub());
        tasks.add(new EventStub());
        TaskList taskList = new TaskList(tasks);

        Ui ui = new Ui();
        assertEquals("Noted. I've removed this task:\n  [T][ ] Create a todo task\n"
                        + "Now you have 2 tasks in the list.", ui.delete(taskList, 1));
    }

    @Test
    void testExit() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new TodoStub());
        tasks.add(new DeadlineStub());
        tasks.add(new EventStub());
        TaskList taskList = new TaskList(tasks);

        Ui ui = new Ui();
        assertEquals("Bye. Hope to see you again soon!",
                ui.exit());
    }
}
