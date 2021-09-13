package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

public class TodoTest {
    @Test
    public void todoToString_readBookTaskDone_correctStringFormatReturned() {
        Todo task = new Todo("read book");
        task.markAsDone();
        String expectedOutput = "[T] [X] read book";
        String actualOutput = task.toString();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void todoToDukeStoreFormat_doLaundryTask_correctStringFormatRerturned() {
        Todo task = new Todo("do laundry");
        String expectedOutput = "T | 0 | do laundry";
        String actualOutput = task.toDukeStoreFormat();
        assertEquals(expectedOutput, actualOutput);
    }
}
