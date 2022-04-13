package petal.components;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import petal.exception.EmptyDescException;
import petal.exception.InvalidInputException;
import petal.task.Task;
import petal.task.ToDo;

public class TaskListTest {

    private final TaskList taskList = new TaskList();

    @Test
    public void addTasks_tasks_noOutput() {
        ArrayList<Task> arrayList1 = new ArrayList<>();
        ArrayList<Task> arrayList2 = new ArrayList<>();
        ToDo todo1 = new ToDo("go for a run", false);
        ToDo todo2 = new ToDo("go for a run", false);
        arrayList1.add(todo1);
        arrayList2.add(todo2);
        taskList.addTasks(arrayList1, arrayList2);
    }

    @Test
    public void addTask_todo_taskAddOutput() {
        String output = "";
        ToDo todo = new ToDo("go for a run", false);
        try {
            output = taskList.handleTask("todo", "Go for a run");
        } catch (InvalidInputException | EmptyDescException e) {
            //Don't do anything here
        }
        assertEquals("Okay. I've added this task:\n[T][ ] Go for a run\nYou now have 1 task!", output);
    }

    @Test
    public void deleteTheTask_todo_taskAddOutput() {
        ToDo todo = new ToDo("go for a run", false);
        String output = "";
        try {
            taskList.handleTask("todo", "Go for a run");
            output = taskList.deleteTask("1");
        } catch (InvalidInputException | EmptyDescException e) {
            //Do not do anything here
        } finally {
            assertEquals("Okay. I've deleted this task:\n[T][ ] Go for a run\nYou now have 0 tasks!", output);
        }
    }

    @Test
    public void archiveTask_index_taskArchived() {
        ToDo todo = new ToDo("go for a run", false);
        String output = "";
        try {
            taskList.handleTask("todo", "Go for a run");
            output = taskList.archiveTask("1");
        } catch (InvalidInputException | EmptyDescException e) {
            //Don't do anything here
        } finally {
            assertEquals("Okay. I've added this task:\n[T][ ] Go for a run\nYou now have"
                    + " 1 task in your archives!", output);
        }
    }

    @Test
    public void markTaskAsDone_todo_taskMarkedAsDone() {
        ToDo todo = new ToDo("go for a run", false);
        String output = "";
        try {
            taskList.handleTask("todo", "Go for a run");
            output = taskList.markTaskAsDone("1");
        } catch (InvalidInputException | EmptyDescException e) {
            //Don't do anything here
        } finally {
            assertEquals("You have completed the task: '" + "Go for a run" + "'!"
                    + "\nI am so happy for you!\n", output);
        }
    }

    @Test
    public void printCurrTasks_todo_printCurrTasks() {
        ToDo todo = new ToDo("go for a run", false);
        try {
            taskList.handleTask("todo", "Go for a run");
        } catch (InvalidInputException | EmptyDescException e) {
            //Don't do anything here
        }
        String result = taskList.printCurrTasks();
        assertEquals("1. [T][ ] Go for a run", result);
    }

    @Test
    public void printArchive_todo_printArchive() {
        ToDo todo = new ToDo("go for a run", false);
        try {
            taskList.handleTask("todo", "Go for a run");
        } catch (InvalidInputException | EmptyDescException e) {
            //Don't do anything here
        }
        String output = taskList.printArchive();
        assertEquals("No items in list yet!", output);
    }

    @Test
    public void showTaskOnDate_deadline_showTask() {
        String output = " ";
        try {
            taskList.handleTask("deadline", "Go for a run /by 2/12/2021 1800");
            output = taskList.showTaskOnDate("02/12/2021");
        } catch (InvalidInputException | EmptyDescException e) {
            //Don't do anything here
        } finally {
            assertEquals("Here are the tasks on this date: \n1. [D][ ] Go"
                    + " for a run (by: December 2, 2021 18:00)", output);
        }
    }

    @Test
    public void findTaskWithKeyword_todo_findTask() {
        String output = " ";
        try {
            taskList.handleTask("todo", "Go for a run");
            output = taskList.findTaskWithKeyword("Go");
        } catch (InvalidInputException | EmptyDescException e) {
            //Don't do anything here
        } finally {
            assertEquals("Here are the tasks:\n1. [T][ ] Go for a run", output);
        }
    }

    @Test
    public void formatForCurrSaving_todo_formatted() {
        String output = " ";
        try {
            taskList.handleTask("todo", "Go for a run");
            output = taskList.formatForCurrSaving();
        } catch (InvalidInputException | EmptyDescException e) {
            //Don't do anything here
        } finally {
            assertEquals("T| |Go for a run", output);
        }
    }

    @Test
    public void formatForArchivesSaving_todo_formatted() {
        String output = " ";
        try {
            taskList.handleTask("todo", "Go for a run");
            output = taskList.formatForArchivesSaving();
        } catch (InvalidInputException | EmptyDescException e) {
            //Don't do anything here
        } finally {
            assertEquals("", output);
        }
    }


}
