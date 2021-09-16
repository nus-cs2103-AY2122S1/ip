import duke.*;
import duke.io.ResponseManager;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.task.Task;
import duke.task.TaskManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DukeTest {
    TaskManager tm = new TaskManager(new ArrayList<>());
    Storage storage = new Storage("docs\\testSave.txt");
    ResponseManager responseManager = new ResponseManager();

    @Test
    public void addEventCommandTest() {
        String addEvent = "event test event /2020-02-02 20:20";
        AddEventCommand command = new AddEventCommand(addEvent);
        command.execute(tm, responseManager, storage);

        assertEquals("#Event (not done) test event (2020-02-02 20:20)", tm.getTasks().get(0).toString());
    }

    @Test
    public void addDeadlineCommandTest() {
        String addDeadline = "deadline test deadline /2020-02-02 20:20";
        AddDeadlineCommand command = new AddDeadlineCommand(addDeadline);
        command.execute(tm, responseManager, storage);

        assertEquals("#Deadline (not done) test deadline (2020-02-02 20:20)", tm.getTasks().get(0).toString());
    }

    @Test
    public void addToDoCommandTest() {
        String addDeadline = "todo test todo";
        AddToDoCommand command = new AddToDoCommand(addDeadline);
        command.execute(tm, responseManager, storage);

        assertEquals("#ToDo (not done) test todo", tm.getTasks().get(0).toString());
    }

    @Test
    public void responseManagerTest() {
        TaskManager tm = new TaskManager(new ArrayList<>());
        ResponseManager rm = new ResponseManager();


        // Dummy tasks to be used for testing
        Task.ToDo dummyTodo;
        Task.Deadline dummyDeadline;
        Task.Event dummyEvent;

        try {
            dummyTodo = (Task.ToDo) tm.addToDo("todo todo");
            dummyDeadline = (Task.Deadline) tm
                    .addDeadline("deadline deadline /2020-12-15 18:20");
            dummyEvent = (Task.Event) tm
                    .addEvent("event event /2020-12-15 18:20");
        } catch (DukeException.NoNameException | DukeException.NoTimeSpecifiedException e) {
            assertEquals("Tasks successfully created", "Tasks encountered error");
        }

        ArrayList<Task> tasks = tm.getTasks();
        int numTasks = tm.getTasks().size();

        assertEquals("Bye. Hope to see you again soon!", rm.getByeMessage());

        assertEquals("Here is your list of tasks\n" +
                "1.#ToDo (not done) todo\n" +
                "2.#Deadline (not done) deadline (2020-12-15 18:20)\n" +
                "3.#Event (not done) event (2020-12-15 18:20)\n", rm.getListTasksMessage(tm.getTasks()));

        assertEquals("I've added the task: \n"
                + "     #ToDo (not done) todo\n"
                + "You now have 3 tasks, jiayouz!",
                rm.getTaskAdditionMessage(tasks.get(0), numTasks));

        assertEquals("I've added the task: \n" +
                        "     #Deadline (not done) deadline (2020-12-15 18:20)\n" +
                        "You now have 3 tasks, jiayouz!",
                rm.getTaskAdditionMessage(tasks.get(1), numTasks));

        assertEquals("I've added the task: \n" +
                        "     #Event (not done) event (2020-12-15 18:20)\n" +
                        "You now have 3 tasks, jiayouz!",
                rm.getTaskAdditionMessage(tasks.get(2), numTasks));

        assertEquals("I've deleted the task: \n" +
                        "     #ToDo (not done) todo\n" +
                        "You now have 2 tasks, jiayouz!",
                rm.getTaskDeletionMessage(tasks.get(0), numTasks - 1));

        assertEquals("You've completed the task: \n" +
                        "     #Deadline (not done) deadline (2020-12-15 18:20) Well done!\n" +
                        "You now have 3 tasks, jiayouz!",
                rm.getTaskCompletionMessage(tasks.get(1), numTasks));

        assertEquals("The task: \n" +
                        "     #Event (not done) event (2020-12-15 18:20)\n" +
                        "has been snoozed\n",
                rm.getSnoozeTaskMessage(tasks.get(2)));

        assertEquals("\u26A0 \u26A0 \u26A0 \u26A0 \u26A0 \u26A0 \u26A0 \u26A0 \n" +
                "Sorry I don't understand what that means", rm.getUnknownCommandMessage());

        assertEquals("\u26A0 \u26A0 \u26A0 \u26A0 \u26A0 \u26A0 \u26A0 \u26A0 \n" +
                "You don't have that many tasks!", rm.getInvalidIndexMessage());

    }

}
