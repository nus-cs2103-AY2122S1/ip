package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ParserTest {
    private static Parser parser;

    @BeforeAll
    public static void setUpTest() {
        Storage storage = new Storage("data/duke.txt");
        parser = new Parser();
        TaskList tasks = new TaskList();
        parser.addTaskList(tasks);
        parser.addStorage(storage);

    }

    @Test
    public void parseTest() {
        String output = parser.parse("list");
        assertEquals(output, "Here are the tasks in your list:\n");
    }
}
