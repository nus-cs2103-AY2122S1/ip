import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import java.util.ArrayList;

import duke.*;
import duke.commands.AddCommand;
import duke.utils.DukeException;

public class DukeTest {
    @Test
    public void checkParser() {
        try {
            duke.commands.Command addCom = duke.utils.Parser.parse("todo test");
            duke.commands.Command delCom = duke.utils.Parser.parse("delete 1");
            duke.commands.Command listCom = duke.utils.Parser.parse("list");
            duke.commands.Command exitCom = duke.utils.Parser.parse("bye");
            assertEquals(addCom.getClass().getName(), duke.commands.AddCommand.class.getName());
            assertEquals(delCom.getClass().getName(), duke.commands.DeleteCommand.class.getName());
            assertEquals(listCom.getClass().getName(), duke.commands.ListCommand.class.getName());
            assertEquals(exitCom.getClass().getName(), duke.commands.ExitCommand.class.getName());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
    @Test
    public void checkTaskList() {
        try {
            duke.utils.TaskList tasklist = new duke.utils.TaskList();
            duke.tasks.Task task = new duke.tasks.Todo("test1");
            tasklist.addTask(task);
            ArrayList<duke.tasks.Task> taskArr = tasklist.getTasks();
            assertEquals(taskArr.size(), 1);
            assertEquals(taskArr.get(0), task);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}