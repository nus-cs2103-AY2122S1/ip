package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;

class UiTest {

    @Test
    void listAllTasks() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        Ui ui = new Ui();
        Task taskA = new ToDo("TaskA");
        Task taskB = new Deadline("TaskB", "2020/10/10 10:10");
        tasks.add(taskA);
        tasks.add(taskB);

        assertEquals(
                "1.[T][ ] TaskA\n2.[D][ ] TaskB (by: 10/10/2020 10:10)\n",
                ui.listAllTasks(tasks));
    }
}
