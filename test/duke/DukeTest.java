package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DukeTest {
    @Test
    public void todoTest() {
        Task test = new Todo("Go Jogging");
        assertEquals("[T][ ] Go Jogging", test.toString());
    }

    @Test
    public void markAsDoneTest() {
        Task test = new Todo("Do Push ups");
        test.markAsDone();
        assertEquals("[T][X] Do Push ups", test.toString());
    }

    @Test
    public void EventTest() {
        Task test = new Event("My Birthday", "2021-06-20");
        test.markAsDone();
        assertEquals("[E][X] My Birthday (by: Jun 20 2021)", test.toString());
    }

    @Test
    public void deadlineTest() {
        Deadline deadline = new Deadline("Submit Assignment", "2021-08-27");
        assertEquals("[D][ ] Submit Assignment (by: Aug 27 2021)", deadline.toString());
    }
}