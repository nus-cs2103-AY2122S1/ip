package task;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {


    @Test
    void printSize_emptyTaskList_noTaskDisplayed() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ArrayList<Task> list = new ArrayList<>();
        TaskList testList = new TaskList(list);
        testList.printSize();

        String expectedOutput  = "  →   " + "There are 0 tasks in your list\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void toggleDone_nonemptyTaskList_taskIsCompleted() {
        ArrayList<Task> list = new ArrayList<>();
        Task task = new TaskStub("Test", false);
        list.add(task);
        TaskList testList = new TaskList(list);
        testList.toggleDone(1);

        assertTrue(task.isDone);
    }

    @Test
    void delete_nonemptyTaskList_listIsEmpty() {
        ArrayList<Task> list = new ArrayList<>();
        Task task = new TaskStub("Test", false);
        list.add(task);
        TaskList testList = new TaskList(list);
        testList.delete(1);

        assertTrue(list.isEmpty());
    }

    @Test
    void displayList_emptyList_nothingToDisplay() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ArrayList<Task> list = new ArrayList<>();
        TaskList testList = new TaskList(list);
        ArrayList<Predicate<Task>> filter = new ArrayList<>();
        filter.add((x) -> true);
        testList.displayList(filter);

        String expectedOutput  = "  →   There is nothing to display! :angery:\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}