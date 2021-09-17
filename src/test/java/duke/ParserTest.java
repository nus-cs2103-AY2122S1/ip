package duke;

import duke.command.Command;
import duke.command.CommandsTypes;
import duke.task.Task;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;

public class ParserTest {
    @Test
    public void testAddTodo() {
        try {
            Parser parser = new Parser();
            Task newTask = Task.makeTask("todo", "get a life");
            Command addCommand = Command.makeCommand(CommandsTypes.ADD, newTask);
            String command = "todo get a life";
            assertTrue(addCommand.toString().replaceAll("\\s+","")
                    .equals(parser.parse(command).toString().replaceAll("\\s+","")));
        } catch (DukeException e) {
            assertTrue(false);
            System.out.println("adding failed. Exception thrown");
        }
    }

    @Test
    public void testAddDeadline() {
        try {
            Parser parser = new Parser();
            Task newTask = Task.makeTask("deadline", "get a life /by 1/1/2021 900");
            Command addCommand = Command.makeCommand(CommandsTypes.ADD, newTask);
            String command = "deadline get a life /by 1/1/2021 900";
            assertTrue(addCommand.toString().replaceAll("\\s+","")
                    .equals(parser.parse(command).toString().replaceAll("\\s+","")));
        } catch (DukeException e) {
            assertTrue(false);
            System.out.println("adding failed. Exception thrown");
        }
    }

    @Test
    public void testAddEvent() {
        try {
            Parser parser = new Parser();
            Task newTask = Task.makeTask("event", "get a life /at 1/1/2021 900");
            Command addCommand = Command.makeCommand(CommandsTypes.ADD, newTask);
            String command = "event get a life /at 1/1/2021 900";
            assertTrue(addCommand.toString().replaceAll("\\s+","")
                    .equals(parser.parse(command).toString().replaceAll("\\s+","")));
        } catch (DukeException e) {
            assertTrue(false);
            System.out.println("adding failed. Exception thrown");
        }
    }

    @Test
    public void testList() {
        try {
            Parser parser = new Parser();
            Command addCommand = Command.makeCommand(CommandsTypes.LIST);
            assertTrue(addCommand.toString().replaceAll("\\s+", "")
                    .equals(parser.parse("list").toString().replaceAll("\\s+", "")));
        } catch (DukeException e) {
            assertTrue(false);
            System.out.println("adding failed. Exception thrown");
        }
    }
}
