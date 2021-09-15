package bobbybot.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import bobbybot.enums.BotCommand;
import bobbybot.exceptions.InvalidArgumentException;
import bobbybot.tasks.Deadline;
import bobbybot.tasks.Event;
import bobbybot.tasks.Task;
import bobbybot.tasks.ToDo;
import bobbybot.util.PersonList;
import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;

public class AddCommandTest {
    private static final String STORAGE_PATH = "test.txt";
    private final TaskList tasks = new TaskList(new ArrayList<>());
    private final Ui ui = new Ui();
    private final Storage storage = new Storage(STORAGE_PATH);
    private final PersonList contacts = new PersonList(Collections.emptyList());
    @AfterAll
    public static void cleanUp() {
        File file = new File(STORAGE_PATH);
        file.delete();
    }

    @Test
    public void addCommand_todo_success() {
        AddCommand c = new AddCommand("test description");
        c.execute(tasks, ui, storage, contacts);
        assertEquals(tasks.getTasks().size(), 1);
        Task addedTask = tasks.getTask(0);
        assertTrue(addedTask instanceof ToDo);
    }

    @Test
    public void addCommand_deadline_success() throws InvalidArgumentException {
        AddCommand c = new AddCommand("test description", "01-01-2020 12:00", BotCommand.DEADLINE);
        c.execute(tasks, ui, storage, contacts);
        assertEquals(tasks.getTasks().size(), 1);
        Task addedTask = tasks.getTask(0);
        assertTrue(addedTask instanceof Deadline);
    }

    @Test
    public void addCommand_deadlineWrongDatetimeFormat_exceptionThrown() {
        Assertions.assertThrows(InvalidArgumentException.class, () -> {
            AddCommand c = new AddCommand("test description", "2020-01-01 12:00", BotCommand.DEADLINE);
            c.execute(tasks, ui, storage, contacts);
        });
    }

    @Test
    public void addCommand_event_success() throws InvalidArgumentException {
        AddCommand c = new AddCommand("test description", "Sunday", BotCommand.EVENT);
        c.execute(tasks, ui, storage, contacts);
        assertEquals(tasks.getTasks().size(), 1);
        Task addedTask = tasks.getTask(0);
        assertTrue(addedTask instanceof Event);
    }
}
