//package duke.test;
//
//import duke.Event;
//import duke.Task;
//import duke.TaskList;
//import duke.ToDos;
//import duke.exception.DukeException;
//import duke.exception.OutOfBoundException;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//
//import java.util.ArrayList;
//
///**
// * A class that tests the functionalities of a Tasklist.
// *
// */
//public class TaskListTest {
//    /**
//     * Test functionality of printList().
//     */
//    @Test
//    public void printListTest() {
//        ToDos task1 = new ToDos("shower");
//        ToDos task2 = new ToDos("assignment");
//        ArrayList<Task> l = new ArrayList<>();
//        l.add(task1);
//        l.add(task2);
//        TaskList list = new TaskList(l);
//        assertEquals("1. [T][ ]shower\n"
//                        + "2. [T][ ]assignment\n", list.printList());
//    }
//
//    /**
//     * Test functionality of addTask() when users give valid input.
//     *
//     * @throws DukeException when users gives invalid input.
//     */
//    @Test
//    public void addTaskTest() throws DukeException {
//        TaskList list = new TaskList(new ArrayList<>());
//        String t = list.addTask("todo shower");
//        assertEquals("[T][ ]shower", t);
//    }
//
//    /**
//     * Test functionality of addTask() when users give invalid input.
//     *
//     */
//    @Test
//    public void addTaskTest2() {
//        try {
//            TaskList list = new TaskList(new ArrayList<>());
//            String t = list.addTask("deadline hw");
//            fail();
//        } catch (DukeException e) {
//            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-( " +
//                            "Please key in a valid task!", e.getMessage());
//        }
//    }
//
//    /**
//     * Test functionality of deleteTask() method.
//     *
//     * @throws OutOfBoundException when users gives invalid task number.
//     */
//    @Test
//    public void deleteTaskTest() throws OutOfBoundException {
//        ToDos task1 = new ToDos("shower");
//        ArrayList<Task> l = new ArrayList<>();
//        l.add(task1);
//        TaskList list = new TaskList(l);
//        String t = list.deleteTask("1");
//        assertEquals("[T][ ]shower", t);
//    }
//
//    /**
//     * Test functionality of deleteTask().
//     *
//     */
//    @Test
//    public void deleteTaskTest2() {
//        try {
//            ToDos task1 = new ToDos("shower");
//            ArrayList<Task> l = new ArrayList<>();
//            l.add(task1);
//            TaskList list = new TaskList(l);
//            list.deleteTask("2");
//            fail();
//        } catch (DukeException e) {
//            assertEquals("Task does not exist. " +
//                    "Please send a correct task number ><", e.getMessage());
//        }
//    }
//
//    /**
//     * Test funcionality of MarkAsDone().
//     *
//     * @throws OutOfBoundException when users give invalid input.
//     */
//    @Test
//    public void markAsDoneTest() throws OutOfBoundException {
//        ToDos task1 = new ToDos("shower");
//        ArrayList<Task> l = new ArrayList<>();
//        l.add(task1);
//        TaskList list = new TaskList(l);
//        String t = list.markAsDone("1");
//        assertEquals("[T][X]shower", t);
//    }
//
//    /**
//     * Test functionality of markAsDone().
//     *
//     */
//    @Test
//    public void markAsDoneTest2() {
//        try {
//            ToDos task1 = new ToDos("shower");
//            ArrayList<Task> l = new ArrayList<>();
//            l.add(task1);
//            TaskList list = new TaskList(l);
//            String t = list.markAsDone("2");
//            fail();
//        } catch (DukeException e) {
//            assertEquals("Task does not exist. " +
//                    "Please send a correct task number ><", e.getMessage());
//        }
//    }
//}
