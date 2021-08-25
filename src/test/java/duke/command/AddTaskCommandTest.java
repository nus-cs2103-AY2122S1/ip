package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTaskCommandTest {
    private TaskList taskList1 = new TaskList();
    private TaskList taskList2 = new TaskList();

    private ToDo task1 = new ToDo("task1");
    private Deadline task2 = new Deadline("task2", "2021-09-10 18:00");
    private Event task3 = new Event("task3", "2021-09-10 18:00 to 2021-09-10 18:50");

    private Path path = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");

    @Test
    public void execute_emptyTaskList_success() {
        Storage storage = new Storage(path, taskList1);
        taskList2.addTask(task1);
        AddTaskCommand command = new AddTaskCommand(task1);
        command.execute(taskList1, storage);
        assertEquals(taskList2.get(0).toString(), taskList1.get(0).toString());
        path.toFile().delete();
    }

    @Test
    public void execute_TaskList_success() {
        Task[] tasks = new Task[] {task1, task2, task3};
        Storage storage = new Storage(path, taskList1);
        for (int i = 0; i < 3; i++) {
            taskList2.addTask(tasks[i]);
            AddTaskCommand command = new AddTaskCommand(tasks[i]);
            command.execute(taskList1, storage);
        }
        for (int i = 0; i < 3; i++) {
            assertEquals(taskList2.get(i).toString(), taskList1.get(i).toString());
        }
        path.toFile().delete();
    }

    @Test
    public void execute_File_success() {
        Storage storage = new Storage(path, taskList1);
        AddTaskCommand command = new AddTaskCommand(task1);
        command.execute(taskList1, storage);
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(path.toFile());
            assertEquals("T / 0 / task1", fileScanner.nextLine());
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        path.toFile().delete();
    }
}
