package catobot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import catobot.command.Command;
import catobot.command.Parser;
import catobot.exception.BotException;
import catobot.exception.EmptyCommandException;
import catobot.exception.EmptyTaskListException;
import catobot.exception.InvalidCommandException;
import catobot.exception.OutOfBoundException;
import catobot.item.Task;
import catobot.item.TaskList;
import catobot.item.Todo;

public class CatobotTest {
    private static final String filePath = "./data/Catobot.txt";

    @Test
    public void taskList_addTodo_todoAddedToTaskList() {
        String description = "todo demo_description";
        String expected = "";
        String actual = "";
        try {
            Task todo = Todo.of(description);
            expected = todo.toString();
            actual = new TaskList().add(todo).split("\n")[1].trim();
        } catch (BotException e) {
            actual = e.getMessage();
        }
        assertEquals(expected, actual);
    }

    @Test
    public void parser_invalidCommand_invalidCommandExceptionThrown() {
        String expected = new InvalidCommandException().getMessage();
        String actual = "";
        try {
            Parser.parse("helloworld 1");
        } catch (BotException e) {
            actual = e.getMessage();
        }
        assertEquals(expected, actual);
    }

    @Test
    public void deadline_emptyCommand_emptyCommandExceptionThrown() {
        String expected = new EmptyCommandException("deadline").getMessage();
        String actual = "";
        try {
            Command c = Parser.parse("deadline");
            c.execute(new TaskList(), new Storage(filePath));
        } catch (BotException e) {
            actual = e.getMessage();
        }
        assertEquals(expected, actual);
    }

    @Test
    public void taskList_deleteEmptyTaskList_emptyTaskListExceptionThrown() {
        String expected = new EmptyTaskListException().getMessage();
        String actual = "";
        try {
            Command c = Parser.parse("delete 1");
            c.execute(new TaskList(), new Storage(filePath));
        } catch (BotException e) {
            actual = e.getMessage();
        }
        assertEquals(expected, actual);
    }

    @Test
    public void doneCommand_invalidIndex_outOfBoundExceptionThrown() {
        String expected = new OutOfBoundException("1 - 1", "2").getMessage();
        String actual = "";
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            tasks.add(Todo.of("demo"));
            Command c = Parser.parse("done 2");
            c.execute(new TaskList(tasks), new Storage(filePath));
        } catch (BotException e) {
            actual = e.getMessage();
        }
        assertEquals(expected, actual);
    }

}
