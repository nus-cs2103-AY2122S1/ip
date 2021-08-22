package duke;

import duke.commands.AddEventCommand;
import duke.commands.Command;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void dummyTest() {
        Parser parser = new Parser();
        try {
            File input = new File("java/duke/input.txt");
            TaskList tasks = new TaskList(input);
            AddEventCommand c = (AddEventCommand) parser.parse("event project meeting /at Aug 6th 2-4pm", tasks);
            assertEquals(c.getDesc(), "project meeting");
            assertEquals(c.getAt(), "Aug 6th 2-4pm");
        } catch (FileNotFoundException | DukeException e) {
            e.printStackTrace();
        }
    }
}
