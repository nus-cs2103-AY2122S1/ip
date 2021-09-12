package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.function.Predicate;

import duke.ui.Ui;
import org.junit.jupiter.api.Test;

class TaskListTest {

    @Test
    void printSize_emptyTaskList_noTaskDisplayed() {
        ArrayList<Task> list = new ArrayList<>();
        TaskList testList = new TaskList(list);
        String actualOutput = testList.getSize();

        String expectedOutput = Ui.messageListSize(0);
        assertEquals(expectedOutput, actualOutput);
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
        String actualOutput = testList.displayList(filter);

        String expectedOutput = Ui.MESSAGE_NOTHING_TO_DISPLAY;
        assertEquals(expectedOutput, actualOutput);
    }
}
