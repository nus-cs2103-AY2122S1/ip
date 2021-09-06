package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import java.util.ArrayList;

import duke.utils.Parser;
import duke.commands.Command;
import duke.utils.DukeException;
import duke.utils.TaskList;
import duke.tasks.Task;

public class DukeTest {
    @Test
    public void checkParser() {
        try {
            Command addCom = Parser.parse("todo test");
            Command delCom = Parser.parse("delete 1");
            Command listCom = Parser.parse("list");
            Command exitCom = Parser.parse("bye");
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
            TaskList tasklist = new duke.utils.TaskList();
            Task task = new duke.tasks.Todo("test1");
            tasklist.addTask(task);
            ArrayList<duke.tasks.Task> taskArr = tasklist.getTasks();
            assertEquals(taskArr.size(), 1);
            assertEquals(taskArr.get(0), task);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}