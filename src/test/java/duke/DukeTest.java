package duke;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void addTask_event_success(){
        TaskList tasks = new TaskList(new ArrayList<Task>());
        File file = new File("dukeTest.txt");
        file.delete();
        Storage storage = new Storage("dukeTest.txt");
        Parser parser = new Parser(tasks, storage);

        parser.parseInput("event meeting /at 22/2/2022 2222");

        assertEquals("[E][ ] meeting (22 Feb 2022 22:22)", tasks.get(0).toString());
    }

    @Test
    public void removeTask_event_success() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        File file = new File("dukeTest.txt");
        file.delete();
        Storage storage = new Storage("dukeTest.txt");
        Parser parser = new Parser(tasks, storage);

        parser.parseInput("event meeting /at 22/2/2022 2222");
        parser.parseInput("delete 1");
        assertEquals(0, tasks.size());

    }

}
