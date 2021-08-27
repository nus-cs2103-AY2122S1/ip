package duke.parser;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void deadlineTest() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui(taskList);
        Parser parser = new Parser(taskList, ui);
        try {
            String cmd = "deadline return book /by 06/12/2019 1800";
            parser.parse(cmd);
            assertEquals("[D][ ] return book (by: Dec 06 2019 18:00)", taskList.get(0).toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deleteTest() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui(taskList);
        Parser parser = new Parser(taskList, ui);
        try {
            String cmd = "deadline return book /by 06/12/2019 1800";
            parser.parse(cmd);
            cmd = "delete 1";
            parser.parse(cmd);
            assertEquals(0, taskList.count());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}