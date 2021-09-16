package aoi.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import aoi.data.TaskList;
import aoi.exceptions.AoiException;
import aoi.ui.Ui;

import org.junit.jupiter.api.Test;


public class ParserTest {
    @Test
    public void deadlineTest() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui(taskList);
        Parser parser = new Parser(taskList, ui);
        try {
            String cmd = "deadline return book /by 06/12/2019 1800";
            parser.parse(cmd);
            assertEquals("[D][ ] return book (by: Dec 06 2019 18:00)\n  Notes: ", taskList.get(0).toString());
        } catch (AoiException e) {
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
            assertEquals(0, taskList.getLength());
        } catch (AoiException e) {
            System.out.println(e.getMessage());
        }
    }
}
