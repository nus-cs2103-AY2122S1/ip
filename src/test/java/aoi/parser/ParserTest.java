package aoi.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import aoi.data.TaskList;
import aoi.exceptions.AoiException;
import aoi.storage.Storage;

import org.junit.jupiter.api.Test;


public class ParserTest {
    @Test
    public void deadlineTest() {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/tasks.txt", taskList);
        try {
            String cmd = "deadline return book /by 06/12/2019 1800";
            Parser.parse(cmd).execute(taskList, storage);
            assertEquals("[D][ ] return book (by: Dec 06 2019 18:00)", taskList.get(0).toString());
        } catch (AoiException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deleteTest() {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/tasks.txt", taskList);
        try {
            String cmd = "deadline return book /by 06/12/2019 1800";
            Parser.parse(cmd).execute(taskList, storage);
            cmd = "delete 1";
            Parser.parse(cmd).execute(taskList, storage);
            assertEquals(0, taskList.getLength());
        } catch (AoiException e) {
            System.out.println(e.getMessage());
        }
    }
}
