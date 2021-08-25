package duke.parser;

import duke.data.DukeException;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void processTest(){
        Parser parser = new Parser(new TaskList(), new Storage(), new Ui());
        String cmd = "deadline finish 2103T iP /by 2021-8-25 23:59";
        try {
            assertEquals(true, parser.process(cmd));
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }
}
