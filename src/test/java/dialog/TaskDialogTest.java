package dialog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import dialog.exceptions.DialogException;
import dialog.exceptions.TaskDialogException;
import model.task.Deadline;
import model.task.Event;
import model.task.Task;
import model.task.TaskList;
import model.task.Todo;

public class TaskDialogTest {
    @Test
    public void addTaskTest() throws DialogException, TaskDialogException {
        List<Task> tasks = List.of(new Todo("todo1"), new Todo("Todo2"));
        TaskList taskList = new TaskList(new ArrayList<>(tasks));
        TaskDialog taskDialog = (TaskDialog) TaskDialog.generate("addTaskTest", taskList);

        taskDialog.addTask(new Deadline("deadlineInAddTest", "2020-08-21"));

        String expected = "Here are the tasks in your list:\n"
            + "    1.[T][ ✘ ] todo1\n"
            + "    2.[T][ ✘ ] Todo2\n"
            + "    3.[D][ ✘ ] deadlineInAddTest(by: Aug 21 2020)\n";

        assertEquals(expected, taskDialog.toString());
    }

    @Test
    public void getFromDeadlineTest() throws DialogException, TaskDialogException {
        List<Task> tasks = List.of(new Todo("todo1"), new Todo("Todo2"));
        TaskList taskList = new TaskList(new ArrayList<>(tasks));
        TaskDialog taskDialog = (TaskDialog) TaskDialog.generate("getFromDeadlineTest", taskList);
        taskDialog.addTask(new Deadline("deadline", "2020-08-21"));
        taskDialog.addTask(new Deadline("deadline2", "2019-08-21"));
        TaskDialog fromDeadline = taskDialog.getFromDeadline("2019-08-21");


        String expected = "Deadline: Aug 21 2019\n"
            + "Here are the tasks in your list:\n"
            + "    1.[T][ ✘ ] todo1\n"
            + "    2.[T][ ✘ ] Todo2\n"
            + "    3.[D][ ✘ ] deadline2(by: Aug 21 2019)\n";

        assertEquals(expected, fromDeadline.toString());
    }

    @Test
    public void getFromKeywordTest() throws DialogException, TaskDialogException {
        List<Task> tasks = List.of(new Todo("todo1"), new Todo("Todo2"));
        TaskList taskList = new TaskList(new ArrayList<>(tasks));
        TaskDialog taskDialog = (TaskDialog) TaskDialog.generate("getFromKeywordTest", taskList);
        taskDialog.addTask(new Event("Dinner with John", "2020-08-21"));
        taskDialog.addTask(new Event("Golfing with John", "2019-08-21"));
        TaskDialog fromKeyword = taskDialog.getFromKeyword("John");


        String expected = "Find: John\n"
            + "Here are the tasks in your list:\n"
            + "    1.[E][ ✘ ] Dinner with John(at: Aug 21 2020)\n"
            + "    2.[E][ ✘ ] Golfing with John(at: Aug 21 2019)\n";

        assertEquals(expected, fromKeyword.toString());
    }

    @Test
    public void markTaskAsDoneTest() throws DialogException, TaskDialogException {
        List<Task> tasks = List.of(new Todo("todo1"), new Todo("Todo2"));
        TaskList taskList = new TaskList(new ArrayList<>(tasks));
        TaskDialog taskDialog = (TaskDialog) TaskDialog.generate("markTaskAsDoneTest", taskList);
        taskDialog.addTask(new Event("Dinner with Babe", "2020-08-21"));
        taskDialog.addTask(new Event("Golfing with Babe", "2019-08-21"));

        taskDialog.markTaskAsDone(1);
        taskDialog.markTaskAsDone(2);

        String expected = "Here are the tasks in your list:\n"
            + "    1.[T][ ✘ ] todo1\n"
            + "    2.[T][✔] Todo2\n"
            + "    3.[E][✔] Dinner with Babe(at: Aug 21 2020)\n"
            + "    4.[E][ ✘ ] Golfing with Babe(at: Aug 21 2019)\n";

        assertEquals(expected, taskDialog.toString());

    }

    @Test
    public void deleteTaskByIndexTest() throws DialogException, TaskDialogException {
        List<Task> tasks = List.of(new Todo("todo1"), new Todo("Todo2"));
        TaskList taskList = new TaskList(new ArrayList<>(tasks));
        TaskDialog taskDialog = (TaskDialog) TaskDialog.generate("deleteTaskByIndexTest", taskList);
        taskDialog.addTask(new Event("Dinner with Jennifer", "2020-08-21"));
        taskDialog.addTask(new Event("Golfing with Jennifer", "2019-08-21"));

        taskDialog.deleteTaskByIndex(2);

        String expected = "Here are the tasks in your list:\n"
            + "    1.[T][ ✘ ] todo1\n"
            + "    2.[T][ ✘ ] Todo2\n"
            + "    3.[E][ ✘ ] Golfing with Jennifer(at: Aug 21 2019)\n";

        assertEquals(expected, taskDialog.toString());
    }


}
