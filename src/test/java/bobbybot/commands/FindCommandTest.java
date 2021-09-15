package bobbybot.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import bobbybot.tasks.ToDo;
import bobbybot.util.PersonList;
import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;


public class FindCommandTest {
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
    public void findCommand_testInput_success() {
        String keyword = "test";
        tasks.addTask(new ToDo("test"));
        tasks.addTask(new ToDo("testing"));
        tasks.addTask(new ToDo("tested"));
        tasks.addTask(new ToDo("not related"));

        FindCommand c = new FindCommand(keyword);
        c.execute(tasks, ui, storage, contacts);
        String response = c.getResponse();
        String expected = "Here are the tasks you're looking for sir!\n"
                + "1.[T][ ] test\n"
                + "2.[T][ ] testing\n"
                + "3.[T][ ] tested";
        assertEquals(expected, response);
    }
}
