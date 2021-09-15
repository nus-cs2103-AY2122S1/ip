import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddToDoCommand;
import commands.DeleteCommand;
import exceptions.MorganException;
import parser.CommandParser;
import storage.Storage;
import tasks.TaskList;
import tasks.ToDoTask;

public class CommandParserTest {
    private final CommandParser parser = new CommandParser();

    @Test
    public void deleteTask() {
        Storage storage = new Storage();
        TaskList expectedTasks = new TaskList();
        TaskList actualTasks = new TaskList();
        expectedTasks.addTask(new ToDoTask("todo do CS2030 quiz"));
        actualTasks.addTask(new ToDoTask("todo do CS2030 quiz"));
        try {
            String expected = (new DeleteCommand("delete 1")).execute(expectedTasks, storage);
            String actual = parser.getCommand("delete 1").execute(actualTasks, storage);
            assertEquals(expected, actual);
        } catch (MorganException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void markTaskDone() {
        Storage storage = new Storage();
        TaskList expectedTasks = new TaskList();
        TaskList actualTasks = new TaskList();
        expectedTasks.addTask(new ToDoTask("todo do CS2030 quiz"));
        actualTasks.addTask(new ToDoTask("todo do CS2030 quiz"));
        try {
            String expected = (new DeleteCommand("done 1")).execute(expectedTasks, storage);
            String actual = parser.getCommand("done 1").execute(actualTasks, storage);
            assertEquals(expected, actual);
        } catch (MorganException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addToDo() {
        Storage storage = new Storage();
        TaskList expectedTasks = new TaskList();
        TaskList actualTasks = new TaskList();
        try {
            String expected = (new AddToDoCommand("todo do CS2030 quiz"))
                    .execute(expectedTasks, storage);
            String actual = parser.getCommand("todo do CS2030 quiz")
                    .execute(actualTasks, storage);
            assertEquals(expected, actual);
        } catch (MorganException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addEvent() {
        Storage storage = new Storage();
        TaskList expectedTasks = new TaskList();
        TaskList actualTasks = new TaskList();
        try {
            String expected = (new AddEventCommand("event do CS2030 quiz /at 27-08-2021 18:00"))
                    .execute(expectedTasks, storage);
            String actual = parser.getCommand("event do CS2030 quiz /at 27-08-2021 18:00")
                    .execute(actualTasks, storage);
            assertEquals(expected, actual);
        } catch (MorganException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addDeadline() {
        Storage storage = new Storage();
        TaskList expectedTasks = new TaskList();
        TaskList actualTasks = new TaskList();
        try {
            String expected = (new AddDeadlineCommand("deadline do CS2030 quiz /by 27-08-2021 18:00"))
                    .execute(expectedTasks, storage);
            String actual = parser.getCommand("deadline do CS2030 quiz /by 27-08-2021 18:00")
                    .execute(actualTasks, storage);
            assertEquals(expected, actual);
        } catch (MorganException e) {
            System.out.println(e.getMessage());
        }
    }
}
