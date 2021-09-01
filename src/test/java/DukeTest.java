import duke.*;
import duke.ResponseManager;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void addEventCommandTest() {
        TaskManager tm = new TaskManager(new ArrayList<Task>());
        Storage storage = new Storage("docs\\testSave.txt");
        ResponseManager responseManager = new ResponseManager();

        String addEvent = "event test event /2020-02-02 20:20";
        AddEventCommand command = new AddEventCommand(addEvent);
        command.execute(tm, responseManager, storage);

        assertEquals("#Event (not done) test event (2020-02-02 20:20)", tm.getTasks().get(0).toString());
    }

    @Test
    public void addDeadlineCommandTest() {
        TaskManager tm = new TaskManager(new ArrayList<Task>());
        Storage storage = new Storage("docs\\testSave.txt");
        ResponseManager responseManager = new ResponseManager();

        String addDeadline = "deadline test deadline /2020-02-02 20:20";
        AddDeadlineCommand command = new AddDeadlineCommand(addDeadline);
        command.execute(tm, responseManager, storage);

        assertEquals("#Deadline (not done) test deadline (2020-02-02 20:20)", tm.getTasks().get(0).toString());
    }

    @Test
    public void addToDoCommandTest() {
        TaskManager tm = new TaskManager(new ArrayList<Task>());
        Storage storage = new Storage("docs\\testSave.txt");
        ResponseManager responseManager = new ResponseManager();

        String addDeadline = "todo test todo";
        AddToDoCommand command = new AddToDoCommand(addDeadline);
        command.execute(tm, responseManager, storage);

        assertEquals("#ToDo (not done) test todo", tm.getTasks().get(0).toString());
    }
}
